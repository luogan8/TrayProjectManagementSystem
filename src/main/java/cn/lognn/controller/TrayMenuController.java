package cn.lognn.controller;

import cn.lognn.service.TrayMenuService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("trayMenu")
public class TrayMenuController {

    @Autowired
    private TrayMenuService trayMenuService;

    /**
     * 获取托盘名称菜单
     *
     * @return 响应对象
     */
    @GetMapping
    public Result getTrayMenu() {
        List<String> trayNameMenu = trayMenuService.getTrayNameMenu();
        return new Result(Code.GET_OK, trayNameMenu, "");
    }

    /**
     * 根据托盘名称获取托盘类型菜单
     *
     * @param trayName 托盘名称
     * @return 响应对象
     */
    @GetMapping("/getType/{trayName}")
    public Result trayTypeMenu(@PathVariable String trayName) {
        List<String> trayTypeMenu = trayMenuService.getTrayTypeMenu(trayName);
        return new Result(Code.GET_OK, trayTypeMenu, "");
    }
}
