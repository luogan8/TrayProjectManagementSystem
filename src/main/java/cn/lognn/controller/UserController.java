package cn.lognn.controller;

import cn.lognn.domain.User;
import cn.lognn.domain.UserChangePassword;
import cn.lognn.service.LogService;
import cn.lognn.service.UserService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    /**
     * 用户登录
     *
     * @param user    用户对象
     * @param request HTTP请求对象
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request) {
        Result loginResult = userService.login(user, request);

        // 登录成功后添加日志
        if (Objects.equals(loginResult.getCode(), Code.SAVE_OK)) {
            logService.add(MyUtils.setLog(request, null, null, "登录", null));
        }

        return loginResult;
    }

    /**
     * 用户退出登录
     *
     * @param request HTTP请求对象
     * @return 注销结果
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        MyUtils.logOut(request);
        return new Result(Code.SAVE_OK, "", "");
    }

    /**
     * 修改用户密码
     *
     * @param request             HTTP请求对象
     * @param userChangePassword  用户密码修改对象
     * @return 修改密码结果
     */
    @PostMapping("/changePassword")
    public Result changePassword(HttpServletRequest request, @RequestBody UserChangePassword userChangePassword) {
        boolean passwordChanged = userService.changePassword(userChangePassword);
        Integer code = passwordChanged ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String message = passwordChanged ? "密码修改成功" : "密码修改失败，请检查原密码是否填写正确。";

        logService.add(MyUtils.setLog(request, null, null, "修改密码", null));

        if (passwordChanged) {
            MyUtils.logOut(request);
        }

        return new Result(code, null, message);
    }

    /**
     * 添加用户
     *
     * @param request HTTP请求对象
     * @param user    用户对象
     * @return 添加用户结果
     */
    @PostMapping("/add")
    public Result addUser(HttpServletRequest request, @RequestBody User user) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }

        if (userService.checkUser(user)) {
            return new Result(Code.SAVE_ERR, "", "要添加的用户已经存在");
        }

        boolean userAdded = userService.add(user);
        logService.add(MyUtils.setLog(request, null, null, "添加用户", null));
        Integer code = userAdded ? Code.SAVE_OK : Code.SAVE_ERR;
        String message = userAdded ? "用户添加成功" : "用户添加失败";

        return new Result(code, null, message);
    }
}
