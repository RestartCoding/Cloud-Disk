package com.xb.cloud.disk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xb.cloud.disk.enums.FileType;
import lombok.Data;

import java.util.Date;

/**
 * @author jack
 */
@Data
public class File {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String filename;

    private Long size;

    private Date uploadTime;

    private String url;

    private FileType type;

    private Integer pid;

    private Date createTime;

    private Date updateTime;
}
