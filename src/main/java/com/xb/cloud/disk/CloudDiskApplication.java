package com.xb.cloud.disk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xb.cloud.disk.dao")
public class CloudDiskApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudDiskApplication.class, args);
	}

}
