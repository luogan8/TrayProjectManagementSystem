package cn.lognn.controller;


import cn.lognn.domain.Log;
import cn.lognn.domain.TrayNG;
import cn.lognn.service.LogService;
import cn.lognn.service.TrayNGService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//定义请求路径
@RequestMapping("/traysNG")
public class TrayNGController {
    @Autowired
    private TrayNGService trayNGService;

    @Autowired
    private LogService logService;

    //添加
   @PostMapping
    public Result save(@RequestBody TrayNG tray, HttpServletRequest request){
       if (!MyUtils.checkLogin(request)){
           return new Result(Code.SAVE_ERR, "", "非法操作！");
       }
       tray.setUser(MyUtils.getUser(request));
       boolean addSuccess = trayNGService.add(tray);
       boolean updateSuccess = false;
       if (addSuccess){
           Log log = MyUtils.setLog(request, tray.getName(), tray.getType(), "添加NG数据", tray.getNumber());
           logService.add(log);
           updateSuccess = trayNGService.update(tray);
       }
       System.out.println(updateSuccess);
       Integer code = updateSuccess ? Code.SAVE_OK : Code.SAVE_ERR;
       String message = updateSuccess ? "添加成功" : "添加失败,检查数据重复添加or系统无对应项目。";

       return new Result(code, updateSuccess, message);
    }

    @GetMapping("/{name}")
    public Result getByName(@PathVariable String name){
        List<TrayNG> trayNGS = trayNGService.getByNameAll(name);
        Integer code = trayNGS.size() > 0 ? Code.GET_OK : Code.GET_ERR;
        return new Result(code, trayNGS, "");
    }

}
