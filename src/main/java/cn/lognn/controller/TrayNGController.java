package cn.lognn.controller;

import cn.lognn.domain.Log;
import cn.lognn.domain.NGLogDate;
import cn.lognn.domain.TrayNG;
import cn.lognn.service.LogService;
import cn.lognn.service.TrayNGService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traysNG")
public class TrayNGController {
    @Autowired
    private TrayNGService trayNGService;

    @Autowired
    private LogService logService;

    /**
     * 添加NG数据
     *
     * @param tray    NG数据对象
     * @param request HTTP请求对象
     * @return 响应对象
     */
    @PostMapping
    public Result save(@RequestBody TrayNG tray, HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        tray.setUser(MyUtils.getUser(request));
        boolean addSuccess = trayNGService.add(tray);
        boolean updateSuccess = false;
        if (addSuccess) {
            Log log = MyUtils.setLog(request, tray.getName(), tray.getType(), "添加NG数据", tray.getNumber());
            logService.add(log);
            updateSuccess = trayNGService.update(tray);
        }
        Integer code = updateSuccess ? Code.SAVE_OK : Code.SAVE_ERR;
        String message = updateSuccess ? "添加成功" : "添加失败，请检查数据是否重复添加或系统无对应项目。";

        return new Result(code, updateSuccess, message);
    }

    /**
     * 根据名称获取所有NG数据
     *
     * @param name 名称
     * @return 响应对象
     */
    @GetMapping("/{name}")
    public Result getByName(@PathVariable String name) {
        List<TrayNG> trayNGS = trayNGService.getByNameAll(name);
        Integer code = trayNGS.size() > 0 ? Code.GET_OK : Code.GET_ERR;
        return new Result(code, trayNGS, "");
    }

    /**
     * 获取最近3次的报废录入时间
     *
     * @return 响应对象
     */
    @GetMapping("/getNGDate/")
    public Result getNGDate() {
        List<NGLogDate> ngLogDate = trayNGService.getNGLogDate();
        for (NGLogDate logDate : ngLogDate) {
            //格式化日期  yyyy-MM-dd
            logDate.setDate(logDate.getDate().substring(0, 10));
        }
        Integer code = ngLogDate.size() > 0 ? Code.GET_OK : Code.GET_ERR;
        return new Result(code, ngLogDate, "");
    }
}
