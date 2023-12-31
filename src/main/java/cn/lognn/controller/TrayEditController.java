package cn.lognn.controller;

import cn.lognn.domain.Log;
import cn.lognn.domain.TrayEnter;
import cn.lognn.domain.TrayNG;
import cn.lognn.domain.TrayOutside;
import cn.lognn.service.*;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edit")
public class TrayEditController {

    @Autowired
    private TrayEnterService trayEnterService;

    @Autowired
    private TrayNGService trayNGService;

    @Autowired
    private TrayOutsideService trayOutsideService;

    @Autowired
    private LogService logService;

    /**
     * 获取编辑领入数据
     *
     * @param request HTTP请求对象
     * @return 响应对象
     */
    @GetMapping("/lr")
    public Result getEditLRData(HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        List<TrayEnter> data = trayEnterService.getAll();
        return new Result(Code.GET_OK, data, "");
    }

    /**
     * 获取编辑NG数据
     *
     * @param request HTTP请求对象
     * @return 响应对象
     */
    @GetMapping("/ng")
    public Result getEditNGData(HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        List<TrayNG> data = trayNGService.getAll();
        return new Result(Code.GET_OK, data, "");
    }

    /**
     * 获取编辑外围数据
     *
     * @param request HTTP请求对象
     * @return 响应对象
     */
    @GetMapping("/outside")
    public Result getEditOutsideData(HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        List<TrayOutside> data = trayOutsideService.getAll();
        return new Result(Code.GET_OK, data, "");
    }

    /**
     * 获取用户操作日志数据
     *
     * @param request HTTP请求对象
     * @return 响应对象
     */
    @GetMapping("/log")
    public Result getUserActionData(HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        List<Log> data = logService.getAll();
        return new Result(Code.GET_OK, data, "");
    }

    @Autowired
    private TrayInfoService trayInfoService;

    /**
     * 根据ID删除领入数据
     *
     * @param request HTTP请求对象
     * @param id      领入数据ID
     * @return 响应对象
     */
    @GetMapping("/delete/lr/{id}")
    public Result deleteLRById(HttpServletRequest request, @PathVariable Integer id) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        TrayEnter trayEnter = trayEnterService.getById(id);
        boolean flag = trayEnterService.deleteById(id);
        if (flag) {
            trayInfoService.deleteUpdate("lr", trayEnter.getNumber(), trayEnter.getName(), trayEnter.getType(), null);
            logService.add(MyUtils.setLog(request, trayEnter.getName(), trayEnter.getType(), "领入数据删除", trayEnter.getNumber()));
        }
        return new Result(Code.DELETE_OK, flag, flag ? "删除成功" : "删除失败");
    }

    /**
     * 根据ID删除NG数据
     *
     * @param request HTTP请求对象
     * @param id      NG数据ID
     * @return 响应对象
     */
    @GetMapping("/delete/ng/{id}")
    public Result deleteNGById(HttpServletRequest request, @PathVariable Integer id) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        TrayNG trayNG = trayNGService.getById(id);
        boolean flag = trayNGService.deleteById(id);
        if (flag) {
            trayInfoService.deleteUpdate("ng", trayNG.getNumber(), trayNG.getName(), trayNG.getType(), null);
            logService.add(MyUtils.setLog(request, trayNG.getName(), trayNG.getType(), "NG数据删除", trayNG.getNumber()));
        }
        return new Result(Code.DELETE_OK, flag, flag ? "删除成功" : "删除失败");
    }

    /**
     * 根据ID删除外围数据
     *
     * @param request HTTP请求对象
     * @param id      外围数据ID
     * @return 响应对象
     */
    @GetMapping("/delete/outside/{id}")
    public Result deleteOutsideById(HttpServletRequest request, @PathVariable Integer id) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        TrayOutside trayOutside = trayOutsideService.getById(id);
        boolean flag = trayOutsideService.deleteById(id);
        if (flag) {
            trayInfoService.deleteUpdate("outside", trayOutside.getNumber(), trayOutside.getName(), trayOutside.getType(), trayOutside.getState());
            logService.add(MyUtils.setLog(request, trayOutside.getName(), trayOutside.getType(), "外围数据删除", trayOutside.getNumber()));
        }
        return new Result(Code.DELETE_OK, flag, flag ? "删除成功" : "删除失败");
    }
}
