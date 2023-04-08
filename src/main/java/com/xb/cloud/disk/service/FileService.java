package com.xb.cloud.disk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xb.cloud.disk.entity.File;
import com.xb.cloud.disk.exception.BusinessException;
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
    InputStream downlaod(int fileId) throws BusinessException;

    /**
     * @param page page
     * @return file page list
     */
    IPage<File> pageList(IPage<File> page);
}
