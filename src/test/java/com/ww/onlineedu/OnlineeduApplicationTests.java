package com.ww.onlineedu;

import com.ww.onlineedu.entity.User;
import com.ww.onlineedu.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineeduApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    public void getUserList() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
