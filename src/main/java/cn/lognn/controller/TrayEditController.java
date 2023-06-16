package cn.lognn.controller;


import cn.lognn.domain.TrayEnter;
import cn.lognn.domain.TrayNG;
import cn.lognn.domain.TrayOutside;
import cn.lognn.service.TrayEnterService;
import cn.lognn.service.TrayNGService;
import cn.lognn.service.TrayOutsideService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//定义请求路径
@RequestMapping("/edit")
public class TrayEditController {

    @Autowired
    private TrayEnterService trayEnterService;

    @Autowired
    private TrayNGService trayNGService;

    @Autowired
    private TrayOutsideService trayOutsideService;


    @GetMapping("/lr")
    public Result getEditLRData(HttpServletRequest request){
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        List<TrayEnter> data = trayEnterService.getAll();
        return new Result(Code.GET_OK, data, "");
    }

    @GetMapping("/ng")
    public Result getEditNGData(HttpServletRequest request){
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        List<TrayNG> data = trayNGService.getAll();
        return new Result(Code.GET_OK, data, "");
    }


    @GetMapping("/outside")
    public Result getEditOutsideData(HttpServletRequest request){
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        List<TrayOutside> data = trayOutsideService.getAll();
        return new Result(Code.GET_OK, data, "");
    }


    @GetMapping("/lr/{id}")
    public Result deleteLRById(HttpServletRequest request, @PathVariable Integer id){
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        boolean flag = trayEnterService.deleteById(id);
        return new Result(Code.DELETE_OK, flag, flag? "删除成功" : "删除失败");
    }

    @GetMapping("/ng/{id}")
    public Result deleteNGById(HttpServletRequest request, @PathVariable Integer id){
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        boolean flag = trayNGService.deleteById(id);
        return new Result(Code.DELETE_OK, flag, flag? "删除成功" : "删除失败");
    }


    @GetMapping("/outside/{id}")
    public Result deleteOutsideById(HttpServletRequest request, @PathVariable Integer id){
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        boolean flag = trayOutsideService.deleteById(id);
        return new Result(Code.DELETE_OK, flag, flag? "删除成功" : "删除失败");
    }

}
