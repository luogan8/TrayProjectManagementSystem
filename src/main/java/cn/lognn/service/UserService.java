package cn.lognn.service;

import cn.lognn.controller.Result;
import cn.lognn.domain.User;
import cn.lognn.domain.UserChangePassword;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    public Result login(User user, HttpServletRequest request);

    public boolean changePassword(UserChangePassword userChangePassword);

    public boolean add(User user);

    public boolean checkUser(User user);
}
