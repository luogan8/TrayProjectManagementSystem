package cn.lognn.controller;


import cn.lognn.domain.TrayEnter;
import cn.lognn.service.TrayEnterService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
//定义请求路径
@RequestMapping("/traysLR")
public class TrayEnterController {
    @Autowired
    private TrayEnterService trayEnterService;

    //添加
    @PostMapping()
    public Result save(@RequestBody TrayEnter trayEnter, HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        boolean addSuccess = trayEnterService.add(trayEnter);
        boolean upInLineSuccess = false;
        if (addSuccess){
            upInLineSuccess = trayEnterService.upInLine(trayEnter);
        }

        Integer code = upInLineSuccess ? Code.SAVE_OK : Code.SAVE_ERR;
        String message = upInLineSuccess ? "添加成功" : "添加失败";

        return new Result(code, upInLineSuccess, message);
    }


}
