package com.xb.cloud.disk.support;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LocalFileSystemStorageServiceTest {

    @Resource
    private LocalFileSystemStorageService localFileSystemStorageService;

    @Test
    public void testStorage() throws IOException {
        byte[] bytes = "123456".getBytes();
        try (InputStream in = new ByteInputStream(bytes, bytes.length)) {
            String url = localFileSystemStorageService.store(in);
            Assert.hasLength(url, "url is empty.");
            System.out.println("url = " + url);
        }
    }
}