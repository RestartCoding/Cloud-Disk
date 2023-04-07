package com.xb.cloud.disk.dao;

import com.xb.cloud.disk.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        User user = userMapper.selectById(0);
        Assert.isNull(user, "user is not null.");
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("jack");
        user.setPassword("123456");
        int n = userMapper.insert(user);
        Assert.isTrue(n == 1, "insert 1 row");
    }
}