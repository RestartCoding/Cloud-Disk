package com.xb.cloud.disk.support;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jack
 */
public class FileExtensions {

    private static final Set<String> previewAble = new HashSet<>();

    static {
        previewAble.add("jpg");
        previewAble.add("webp");
    }


    /**
     * 判断文件是否可预览
     *
     * @param filename filename
     * @return 可以预览返回是 否则返回false
     */
    public static boolean isPreviewAble(String filename) {
        return previewAble.contains(getExtension(filename).toLowerCase());
    }

    public static String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
