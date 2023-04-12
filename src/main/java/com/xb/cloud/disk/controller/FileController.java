package com.xb.cloud.disk.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xb.cloud.disk.entity.File;
import com.xb.cloud.disk.service.FileService;
import com.xb.cloud.disk.support.UserContext;
import com.xb.cloud.disk.vo.ApiResponse;
import com.xb.cloud.disk.vo.file.CreateFolderVO;
import com.xb.cloud.disk.vo.file.FileTreeVO;
import com.xb.cloud.disk.vo.file.MoveFileReqVO;
import com.xb.cloud.disk.vo.file.UploadFileVO;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jack
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public ApiResponse<Void> upload(UploadFileVO uploadFileVO) throws IOException {
        fileService.uploadFile(uploadFileVO);
        return ApiResponse.ok(null);
    }

    @GetMapping("/download")
    public void download(int fileId, HttpServletResponse response) throws IOException {
        // 先写头
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment");

        try (InputStream in = fileService.read(fileId)) {
            IOUtils.copy(in, response.getOutputStream());
        }
    }

    @GetMapping("/preview")
    public void preview(int fileId, HttpServletResponse response) throws IOException {
        try (InputStream in = fileService.preview(fileId)) {
            IOUtils.copy(in, response.getOutputStream());
        }
    }

    @GetMapping("/pageList")
    public ApiResponse<IPage<File>> pageList(int pageNum, int pageSize, int pid) {
        IPage<File> pageList = fileService.pageList(new Page<>(pageNum, pageSize), pid);
        return ApiResponse.ok(pageList);
    }

    @PostMapping("/folder")
    public ApiResponse<Void> createFolder(@RequestBody CreateFolderVO vo) {
        fileService.createFolder(vo);
        return ApiResponse.ok(null);
    }

    @GetMapping("/tree")
    public ApiResponse<FileTreeVO> getFileTree(@RequestParam boolean onlyFolder) {
        Integer userId = UserContext.getUser().getId();
        File file = fileService.getFileTree(userId, onlyFolder);
        return ApiResponse.ok(FileTreeVO.from(file));
    }

    @PutMapping("/move")
    public ApiResponse<Void> move(@RequestBody MoveFileReqVO vo){
        fileService.moveFile(vo.getFileId(), vo.getTargetFileId());
        return ApiResponse.ok(null);
    }
}
