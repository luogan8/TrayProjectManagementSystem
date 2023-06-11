package cn.lognn.controller;

import cn.lognn.service.TrayMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<String> trayTypeMenu = trayMenuService.getTrayTypeMenu();
        Map<String, List<String>> map = new HashMap<>();
        map.put("nameMenu", trayNameMenu);
        map.put("typeMenu", trayTypeMenu);
        return new Result(Code.GET_OK, map, "");
    }

}
