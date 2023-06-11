package cn.lognn.controller;

import cn.lognn.domain.User;
import cn.lognn.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 用户登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result loginP(@RequestBody User user, HttpServletRequest request){
        return userService.login(user, request);
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
        HttpSession session = request.getSession();
        session.invalidate();
        return new Result(Code.SAVE_OK,"","");
    }

}
