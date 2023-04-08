package com.xb.cloud.disk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xb.cloud.disk.entity.File;
import com.xb.cloud.disk.service.FileService;
import com.xb.cloud.disk.vo.ApiResponse;
import com.xb.cloud.disk.vo.file.UploadFileVO;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jack
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @ResponseBody
    @PostMapping("/upload")
    public ApiResponse<Void> upload(UploadFileVO uploadFileVO) throws IOException {
        fileService.uploadFile(uploadFileVO);
        return ApiResponse.ok(null);
    }

    @GetMapping("/download")
    public void download(int fileId, HttpServletResponse response) throws IOException {
        // 先写头
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment");

        try (InputStream in = fileService.downlaod(fileId)) {
            IOUtils.copy(in, response.getOutputStream());
        }
    }

    @ResponseBody
    @GetMapping("/pageList")
    public ApiResponse<IPage<File>> pageList(int pageNum, int pageSize) {
        IPage<File> pageList = fileService.pageList(new Page<>(pageNum, pageSize));
        return ApiResponse.ok(pageList);
    }
}
