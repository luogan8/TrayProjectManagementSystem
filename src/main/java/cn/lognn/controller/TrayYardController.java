package cn.lognn.controller;



import cn.lognn.domain.TrayYard;
import cn.lognn.service.TrayYardService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//定义请求路径
@RequestMapping("/traysYard")
public class TrayYardController {
    @Autowired
    private TrayYardService trayYardService;

    //添加
   @PostMapping
    public Result save(@RequestBody TrayYard tray, HttpServletRequest request){
       if (!MyUtils.checkLogin(request)){
           return new Result(Code.SAVE_ERR, "", "非法操作！");
       }
       boolean flag = trayYardService.add(tray);
       Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
       return new Result(code, flag, flag ? "添加成功" : "添加失败");
    }

}
