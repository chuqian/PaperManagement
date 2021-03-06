package com.cchl.service.student.Ipml;

import com.cchl.dao.StudentMapper;
import com.cchl.dao.UserMapper;
import com.cchl.dto.Result;
import com.cchl.entity.Student;
import com.cchl.entity.Teacher;
import com.cchl.entity.User;
import com.cchl.entity.vo.AdminMsg;
import com.cchl.eumn.Dictionary;
import com.cchl.execption.DataInsertException;
import com.cchl.execption.SystemException;
import com.cchl.service.LoginService;
import com.cchl.service.MsgHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学生类处理登录与注册的请求
 */
@Service
@Qualifier(value = "student")
public class StudentLoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(StudentLoginServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private MsgHandle msgHandle;

    /**
     * 登录校验
     *
     * @param id       账号
     * @param password 密码
     * @return 返回结果，0：失败 1：成功
     */
    @Override
    public Integer loginCheck(String id, String password) {
        try {
            Long _id = Long.parseLong(id);
            Integer userId = studentMapper.loginCheck(_id, password);
            return userId == null ? 0 : userId;
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }

    }

    @Override
    @Transactional
    public Result studentRegister(Student student) {
        //注册学生前先添加一个user信息，类型为学生（0），状态为待审核（0）
        User user = new User();
        user.setStatus((byte) 0);
        user.setType((byte) 0);
        if (userMapper.insert(user) > 0) {
            //如果插入成功
            //获取数据库返回的主键值
            student.setUserId(user.getId());
            if (studentMapper.insert(student) > 0) {
                logger.info("学生注册成功，学生实体为：{}", student.toString());
                //添加消息
                msgHandle.addAdminMsg(AdminMsg.TYPE.USER, student.getDepartmentId());
                return new Result(Dictionary.SUCCESS);
            } else {
                logger.error("学生信息插入失败，学生实体为：{}", student.toString());
                throw new DataInsertException(Dictionary.DATA_INSERT_FAIL);
            }
        } else {
            //插入失败，返回失败信息
            logger.error("用户信息插入失败，用户实体为：{}", user.toString());
            throw new DataInsertException(Dictionary.DATA_INSERT_FAIL);
        }
    }

    @Override
    public Result teacherRegister(Teacher teacher) {
        return null;
    }

}
