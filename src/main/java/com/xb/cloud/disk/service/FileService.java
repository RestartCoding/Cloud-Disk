package com.xb.cloud.disk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xb.cloud.disk.entity.File;
import com.xb.cloud.disk.exception.BusinessException;
import com.xb.cloud.disk.vo.file.CreateFolderVO;
import com.xb.cloud.disk.vo.file.UploadFileVO;

import java.io.InputStream;

/**
 * @author jack
 */
public interface FileService extends IService<File> {
    /**
     * 上传文件
     *
     * @param vo vo
     */
    void uploadFile(UploadFileVO vo) throws BusinessException;

    /**
     * 下载文件
     *
     * @param fileId fileId
     * @return in
     * @throws BusinessException BusinessException
     */
    InputStream read(int fileId) throws BusinessException;

    /**
     * @param fileId fileId
     * @return preview input stream
     * @throws BusinessException BusinessException
     */
    InputStream preview(int fileId) throws BusinessException;

    /**
     * @param page page
     * @return file page list
     */
    IPage<File> pageList(IPage<File> page, int pid);

    /**
     * 创建文件夹
     *
     * @param createFolderVO vo
     */
    void createFolder(CreateFolderVO createFolderVO);

    /**
     * 树形结构
     *
     * @param userId     userId
     * @param onlyFolder 是否只要文件夹
     * @return 用户文件树结构
     */
    File getFileTree(int userId, boolean onlyFolder);

    /**
     * @param fileId       fileId
     * @param targetFileId targetFileId
     */
    void moveFile(int fileId, int targetFileId) throws BusinessException;

    /**
     * 删除文件
     *
     * @param fileId fileId
     * @throws BusinessException BusinessException
     */
    void deleteFile(int fileId) throws BusinessException;
}
