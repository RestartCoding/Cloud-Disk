package com.xb.cloud.disk.service.impl;

import com.xb.cloud.disk.entity.File;
import com.xb.cloud.disk.service.FileService;
import com.xb.cloud.disk.vo.file.FileTreeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FileServiceImplTest {

    @Resource
    private FileService fileService;

    @Test
    public void test() {
        File file = fileService.getFileTree(167, true);
        FileTreeVO vo = FileTreeVO.from(file);
        System.out.println("vo = " + vo);
    }
}