package cn.lognn.service.impl;

import cn.lognn.controller.Code;
import cn.lognn.controller.Result;
import cn.lognn.dao.UserDao;
import cn.lognn.domain.User;
import cn.lognn.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result login(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //是否登录状态
        if (session.getAttribute("user") != null){
            return new Result(Code.SAVE_ERR, "", "你已经登陆过了");
        }
        //校验
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserId, user.getUserId()).eq(User::getPassword, user.getPassword());
        User user1 = userDao.selectOne(lqw);
        //判断登录结果
        if (user1 != null){
            session.setAttribute("user", user1);
            return new Result(Code.SAVE_OK,"","登录成功");
        }
        return new Result(Code.SAVE_ERR,"","登录失败，请检查账户或密码是否有误！");
    }
}
