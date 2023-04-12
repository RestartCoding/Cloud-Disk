package com.xb.cloud.disk.vo.file;

import com.xb.cloud.disk.entity.File;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jack
 */
@Data
public class FileTreeVO {

    private Integer id;

    private String title;

    private List<FileTreeVO> children;

    /**
     * vo转换
     *
     * @param file file
     * @return fileTreeVO
     */
    public static FileTreeVO from(File file) {
        if (file == null) {
            return null;
        }

        FileTreeVO vo = new FileTreeVO();
        vo.setId(file.getId());
        vo.setTitle(file.getFilename());
        List<FileTreeVO> children = new ArrayList<>();
        vo.setChildren(children);
        if (file.getChildren() != null) {
            for (File e : file.getChildren()) {
                children.add(from(e));
            }
        }
        return vo;
    }
}
