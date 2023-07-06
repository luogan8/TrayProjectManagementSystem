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
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result loginP(@RequestBody User user, HttpServletRequest request){
        Result login = userService.login(user, request);
        //登陆成功再添加日志
        if (Objects.equals(login.getCode(), Code.SAVE_OK)){
            logService.add(MyUtils.setLog(request,null,null,"登陆", null));
        }
        return login;
    }

/*    @GetMapping ("/login")
    public Result loginG(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer state = session.getAttribute("user") != null ? Code.LOGIN_STATE_Y : Code.LOGIN_STATE_N;
        return new Result(state,"","别逗");
    }*/

    /**
     * 用户退出登录
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        MyUtils.logOut(request);
        return new Result(Code.SAVE_OK,"","");
    }

    @PostMapping("/changePassword")
    public Result changePassword(HttpServletRequest request, @RequestBody UserChangePassword userChangePassword){
        boolean flag = userService.changePassword(userChangePassword);
        Integer code = flag ? Code.UPDATE_OK : Code.UPDATE_ERR;
        String msg = flag ? "修改成功" : "修改失败，请检查原密码是否填写正确。";
        logService.add(MyUtils.setLog(request,null,null,"修改密码", null));
        if (flag){
            MyUtils.logOut(request);
        }
        return new Result(code, null, msg);
    }

    /**
     * 添加用户
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Result addUser (HttpServletRequest request, @RequestBody User user){
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        if (userService.checkUser(user)){
            return new Result(Code.SAVE_ERR, "", "添加的用户已经存在");
        }
        boolean flag = userService.add(user);
        logService.add(MyUtils.setLog(request,null,null,"添加用户", null));
        Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
        String msg = flag ? "添加成功" : "添加失败";
        return new Result(code, null, msg);
    }

}
