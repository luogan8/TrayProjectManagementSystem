package cn.lognn.controller;


import cn.lognn.domain.Log;
import cn.lognn.domain.TrayEnter;
import cn.lognn.service.LogService;
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

    @Autowired
    private LogService logService;

    //添加
    @PostMapping()
    public Result save(@RequestBody TrayEnter trayEnter, HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        String user = MyUtils.getUser(request);
        trayEnter.setUser(user);
        boolean addSuccess = trayEnterService.add(trayEnter);
        boolean upInLineSuccess = false;
        if (addSuccess){
            Log log = MyUtils.setLog(request, trayEnter.getName(), trayEnter.getType(), "添加领入数据" , trayEnter.getNumber());
            logService.add(log);
            upInLineSuccess = trayEnterService.upInLine(trayEnter);
        }

        Integer code = upInLineSuccess ? Code.SAVE_OK : Code.SAVE_ERR;
        String message = upInLineSuccess ? "添加成功" : "添加失败";

        return new Result(code, upInLineSuccess, message);
    }


}
