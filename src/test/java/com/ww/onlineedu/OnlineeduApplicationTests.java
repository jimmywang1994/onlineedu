package com.ww.onlineedu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ww.onlineedu.entity.User;
import com.ww.onlineedu.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void insertUser() {
        User user = new User();
        user.setAge(20);
        user.setName("陈华");
        user.setEmail("3635345@qq.com");
        userMapper.insert(user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1199550051445547010L);
        user.setName("刘明明");
        userMapper.updateById(user);
    }

    /**
     * 测试乐观锁效果
     */
    @Test
    public void testOptimisticLocker() {
        //根据id查询用户信息
        User user = userMapper.selectById(1199568286219997185L);
        user.setName("范晓东");
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    //测试批量查询
    @Test
    public void testBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(2, 3));
        System.out.println(users);
    }

    //测试条件查询
    @Test
    public void testCondition() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 20);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }

    //测试分页
    @Test
    public void testPaging() {
        Page<User> userPage = new Page<>(1, 5);
        IPage<User> userIPage = userMapper.selectPage(userPage, null);
        List<User> records = userPage.getRecords();
        long current = userPage.getCurrent();
        long pages = userPage.getPages();
        long total = userPage.getTotal();
        long size = userPage.getSize();
        boolean prev = userPage.hasPrevious();
        boolean next = userPage.hasNext();
        System.out.println(current);
        System.out.println(pages);
        System.out.println(total);
        System.out.println(size);
        System.out.println(prev);
        System.out.println(next);
    }

    //测试删除
    @Test
    public void delete(){
        int i = userMapper.deleteById(3L);
        System.out.println(i);
    }


/**
 * 测试 逻辑删除
 */
    @Test
    public void testLogicDelete() {
        int result = userMapper.deleteById(1199613306222325762L);
        System.out.println(result);
    }


    /**
     * 测试条件查询
     */
    @Test
    public void testSelectDemo1(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("age",80);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void testSelectDemo2(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("age",20).eq("name","陈华");
        User user=userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }
}
