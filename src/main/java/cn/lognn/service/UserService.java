package cn.lognn.service;

import cn.lognn.controller.Result;
import cn.lognn.domain.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    public Result login(User user, HttpServletRequest request);
}
