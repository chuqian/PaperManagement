package com.cchl.service.teacher;

import com.cchl.entity.TeacherMessage;
import com.cchl.entity.UserMsgRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherHandleTest {

    @Autowired
    private TeacherHandle teacherHandle;

    @Test
    public void hasNewMsg() throws Exception {
    }

    @Test
    public void getMsg() throws Exception {
    }

    @Test
    public void updateMsg() throws Exception {
        int userId = 12;
        if (teacherHandle.hasNewMsg(userId)) {
            List<TeacherMessage> list = teacherHandle.getMsg(userId, 1);
            for (TeacherMessage message:list)
                System.out.println(message);
        }
    }


}