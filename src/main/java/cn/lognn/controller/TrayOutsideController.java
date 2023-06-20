package cn.lognn.controller;



import cn.lognn.domain.TrayOutside;
import cn.lognn.service.TrayOutsideService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
//定义请求路径
@RequestMapping("/traysOutside")
public class TrayOutsideController {
    @Autowired
    private TrayOutsideService trayOutsideService;


    //添加
   @PostMapping
    public Result save(@RequestBody TrayOutside tray, HttpServletRequest request){
       if (!MyUtils.checkLogin(request)){
           return new Result(Code.SAVE_ERR, "", "非法操作！");
       }
       tray.setUser(MyUtils.getUser(request));
       boolean addSuccess = trayOutsideService.add(tray);
       boolean updateSuccess = false;
       if (addSuccess){
           updateSuccess = trayOutsideService.upOutside(tray);
       }
       Integer code = updateSuccess ? Code.SAVE_OK : Code.SAVE_ERR;
       String message = updateSuccess ? "添加成功" : "添加失败";

       return new Result(code, updateSuccess, message);
    }
}
