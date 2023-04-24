package com.aiit.friendlink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Shuoliu
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.aiit.friendlink.mapper")
public class FriendLinkBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendLinkBackendApplication.class, args);
    }
}
