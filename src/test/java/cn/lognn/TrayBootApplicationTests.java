package cn.lognn;


import cn.lognn.dao.TrayEnterDao;
import cn.lognn.dao.TrayInfoDao;
import cn.lognn.dao.UserDao;
import cn.lognn.domain.TrayInfoDownload;
import cn.lognn.domain.TrayLRDownload;
import cn.lognn.domain.TrayNGDownload;
import cn.lognn.service.*;
;
import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootTest
@MapperScan("cn.lognn.dao")
class TrayBootApplicationTests {
    @Autowired
    private TrayInfoService trayService;
    @Autowired
    private TrayInfoDao trayDao;

    @Autowired
    private TrayInfoDao trayInfoDao;

    @Autowired
    private TrayEnterService trayEnterService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TrayEnterDao trayEnterDao;

    @Autowired
    private TrayNGService trayNGService;

    @Autowired
    private TrayInfoService trayInfoService;

    @Autowired
    private TrayMenuService trayMenuService;



    @Test
    void getTrayType(){
        List<String> max = trayMenuService.getTrayTypeMenu("max");
        System.out.println(max);
    }

}
