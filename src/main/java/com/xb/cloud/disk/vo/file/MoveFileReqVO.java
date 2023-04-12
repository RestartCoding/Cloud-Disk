package com.xb.cloud.disk.vo.file;

import lombok.Data;

/**
 * @author jack
 */
@Data
public class MoveFileReqVO {

    private Integer fileId;

    private Integer targetFileId;
}
