package cn.lognn.controller;


import cn.lognn.domain.TrayNG;
import cn.lognn.service.TrayNGService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
//定义请求路径
@RequestMapping("/traysNG")
public class TrayNGController {
    @Autowired
    private TrayNGService trayNGService;

    //添加
   @PostMapping
    public Result save(@RequestBody TrayNG tray, HttpServletRequest request){
       if (!MyUtils.checkLogin(request)){
           return new Result(Code.SAVE_ERR, "", "非法操作！");
       }

       boolean addSuccess = trayNGService.add(tray);
       boolean updateSuccess = false;
       if (addSuccess){
           updateSuccess = trayNGService.update(tray);
       }
       System.out.println(updateSuccess);
       Integer code = updateSuccess ? Code.SAVE_OK : Code.SAVE_ERR;
       String message = updateSuccess ? "添加成功" : "添加失败,检查数据重复添加or系统无对应项目。";

       return new Result(code, updateSuccess, message);
    }

}
