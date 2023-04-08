package com.xb.cloud.disk.support;

import java.io.InputStream;

/**
 * @author jack
 */
public interface StorageService {

    /**
     * @param in in
     * @return url
     */
    String store(InputStream in);

    /**
     * @param url url
     * @return in
     */
    InputStream read(String url);

    /**
     * 删除存储对象
     *
     * @param url url
     */
    void delete(String url);
}
