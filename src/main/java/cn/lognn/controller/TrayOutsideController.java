package cn.lognn.controller;

import cn.lognn.domain.Log;
import cn.lognn.domain.TrayOutside;
import cn.lognn.service.LogService;
import cn.lognn.service.TrayOutsideService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traysOutside")
public class TrayOutsideController {
    @Autowired
    private TrayOutsideService trayOutsideService;

    @Autowired
    private LogService logService;

    /**
     * 添加外围数据
     *
     * @param tray    外围数据对象
     * @param request HTTP请求对象
     * @return 响应对象
     */
    @PostMapping
    public Result save(@RequestBody TrayOutside tray, HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        tray.setUser(MyUtils.getUser(request));
        boolean addSuccess = trayOutsideService.add(tray);
        boolean updateSuccess = false;
        String changeType = "";
        switch (tray.getState()) {
            case 0:
                changeType = "添加外围搬出数据";
                break;
            case 1:
                changeType = "添加外围搬入数据";
                break;
        }
        if (addSuccess) {
            Log log = MyUtils.setLog(request, tray.getName(), tray.getType(), changeType, tray.getNumber());
            logService.add(log);
            updateSuccess = trayOutsideService.upOutside(tray);
        }
        Integer code = updateSuccess ? Code.SAVE_OK : Code.SAVE_ERR;
        String message = updateSuccess ? "添加成功" : "添加失败";

        return new Result(code, updateSuccess, message);
    }
}
