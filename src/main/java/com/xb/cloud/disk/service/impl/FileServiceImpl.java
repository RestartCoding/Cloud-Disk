package com.xb.cloud.disk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xb.cloud.disk.dao.FileMapper;
import com.xb.cloud.disk.entity.File;
import com.xb.cloud.disk.enums.FileType;
import com.xb.cloud.disk.exception.BusinessException;
import com.xb.cloud.disk.service.FileService;
import com.xb.cloud.disk.support.StorageService;
import com.xb.cloud.disk.support.UserContext;
import com.xb.cloud.disk.vo.file.CreateFolderVO;
import com.xb.cloud.disk.vo.file.UploadFileVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author jack
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Resource
    private StorageService storageService;

    @Override
    public void uploadFile(UploadFileVO vo) {
        Integer pid = vo.getPid();
        Integer userId = UserContext.getUser().getId();
        String filename = vo.getFile().getOriginalFilename();

        if (pid != -1 && baseMapper.selectById(pid) == null) {
            throw new BusinessException("Parent directory not exists.");
        }

        File fileByUserIdAndPidAndFilename = baseMapper.selectOne(new LambdaQueryWrapper<File>()
                .eq(File::getUserId, userId)
                .eq(File::getPid, pid)
                .eq(File::getFilename, filename));
        if (fileByUserIdAndPidAndFilename != null) {
            throw new BusinessException("Filename already exists.");
        }

        String url;
        try {
            url = storageService.store(vo.getFile().getInputStream());
        } catch (IOException e) {
            throw new BusinessException("System error.");
        }

        MultipartFile file = vo.getFile();

        File entity = new File();

        entity.setFilename(filename);
        entity.setType(FileType.NORMAL);
        entity.setUploadTime(new Date());
        entity.setSize(file.getSize());
        entity.setUserId(userId);
        entity.setPid(pid);
        entity.setUrl(url);

        try {
            baseMapper.insert(entity);
        } catch (Exception e) {
            storageService.delete(url);
            throw new BusinessException("System.error");
        }
    }

    @Override
    public InputStream download(int fileId) throws BusinessException {
        File file = baseMapper.selectById(fileId);
        if (file.getType() == FileType.FOLDER) {
            throw new BusinessException("Can't download folder.");
        }
        if (!file.getUserId().equals(UserContext.getUser().getId())) {
            throw new BusinessException("Can't download other people's file.");
        }
        return storageService.read(file.getUrl());
    }

    @Override
    public IPage<File> pageList(IPage<File> page, int pid) {
        LambdaQueryWrapper<File> queryWrapper = new LambdaQueryWrapper<File>().eq(File::getPid, pid);
        queryWrapper.orderByDesc(File::getUploadTime);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void createFolder(CreateFolderVO vo) {
        File file = baseMapper.selectOne(new LambdaQueryWrapper<File>()
                .eq(File::getUserId, UserContext.getUser().getId())
                .eq(File::getPid, vo.getPid())
                .eq(File::getFilename, vo.getFilename()));
        if (file != null) {
            throw new BusinessException("Filename already exists.");
        }

        File entity = new File();
        entity.setType(FileType.FOLDER);
        entity.setFilename(vo.getFilename());
        entity.setPid(vo.getPid());
        entity.setUploadTime(new Date());
        entity.setUserId(UserContext.getUser().getId());
        baseMapper.insert(entity);
    }
}
