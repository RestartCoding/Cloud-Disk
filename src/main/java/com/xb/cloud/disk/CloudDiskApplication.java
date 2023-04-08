package com.xb.cloud.disk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.xb.cloud.disk.dao")
@ServletComponentScan(basePackages = "com.xb.cloud.disk.filter")
public class CloudDiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDiskApplication.class, args);
    }

}
