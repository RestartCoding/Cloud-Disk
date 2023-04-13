package com.xb.cloud.disk.support;

import com.xb.cloud.disk.config.StorageConfig;
import com.xb.cloud.disk.exception.BusinessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.util.UUID;

/**
 * @author jack
 */
@Component
public class LocalFileSystemStorageService implements StorageService {

    @Resource
    private StorageConfig storageConfig;

    @Override
    public String store(InputStream in) {
        String path = storageConfig.getDir() + File.separator + UUID.randomUUID().toString().replaceAll("-", "");
        File file = new File(path);
        try {
            boolean b = file.createNewFile();
            if (b) {
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getAbsolutePath();
    }

    @Override
    public InputStream read(String url) {
        try {
            return new FileInputStream(url);
        } catch (FileNotFoundException e) {
            throw new BusinessException("file not found.");
        }
    }

    @Override
    public void delete(String url) throws BusinessException{
        File file = new File(url);
        if (file.exists()) {
            boolean b = file.delete();
            if (!b) {
                throw new BusinessException("failed to delete file.");
            }
        }
    }
}