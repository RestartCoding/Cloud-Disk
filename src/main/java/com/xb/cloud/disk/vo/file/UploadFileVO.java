package com.xb.cloud.disk.vo.file;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jack
 */
@Data
public class UploadFileVO {
    /**
     * 父目录id
     */
    private Integer pid;

    /**
     * files
     */
    private MultipartFile file;
}
