package com.xb.cloud.disk.config;

import com.xb.cloud.disk.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @author jack
 */
@Data
@Component
@ConfigurationProperties(prefix = "storage")
public class StorageConfig implements InitializingBean {

    /**
     * 存储目录
     */
    private String dir;

    /**
     * 检查配置的正确性
     *
     * @throws Exception Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isEmpty(dir)) {
            throw new BusinessException("please config storage dir.");
        }
        File file = new File(dir);
        if (!file.exists()) {
            throw new BusinessException("storage dir not exists. please manual create it.");
        }
    }
}
