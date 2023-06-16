package cn.lognn.controller;

import cn.lognn.service.TrayMenuService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("trayMenu")
public class TrayMenuController {

    @Autowired
    private TrayMenuService trayMenuService;

    @GetMapping
    public Result getTrayMenu(){
        List<String> trayNameMenu = trayMenuService.getTrayNameMenu();
        return new Result(Code.GET_OK, trayNameMenu, "");
    }

    @GetMapping("/getType/{trayName}")
    public Result trayTypeMenu(@PathVariable String trayName){
        List<String> trayTypeMenu = trayMenuService.getTrayTypeMenu(trayName);
        return new Result(Code.GET_OK, trayTypeMenu, "");
    }


}
