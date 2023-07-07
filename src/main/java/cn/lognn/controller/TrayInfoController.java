package cn.lognn.controller;

import cn.lognn.domain.Log;
import cn.lognn.domain.TrayInfo;
import cn.lognn.service.LogService;
import cn.lognn.service.TrayInfoService;
import cn.lognn.utils.MyUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trays")
public class TrayInfoController {
    @Autowired
    private TrayInfoService trayInfoService;

    @Autowired
    private LogService logService;

    /**
     * 添加新项目
     *
     * @param trayInfo 项目信息
     * @param request  HTTP请求对象
     * @return 响应对象
     */
    @PostMapping("/add")
    public Result save(@RequestBody TrayInfo trayInfo, HttpServletRequest request) {
        if (!MyUtils.checkLogin(request)) {
            return new Result(Code.SAVE_ERR, "", "非法操作！");
        }
        if (trayInfoService.trayCheck(trayInfo)) {
            return new Result(Code.SAVE_ERR, "", "你添加的项目已存在，请勿重复添加。");
        }
        trayInfo.setUser(MyUtils.getUser(request));
        boolean flag = trayInfoService.save(trayInfo);
        if (flag) {
            Log log = MyUtils.setLog(request, trayInfo.getTrayName(), trayInfo.getTrayType(), "添加新项目", null);
            logService.add(log);
        }
        Integer code = flag ? Code.SAVE_OK : Code.SAVE_ERR;
        String msg = flag ? "添加成功" : "添加失败";
        return new Result(code, flag, msg);
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return null;
    }

    //更新
    @PutMapping("/update")
    public String update(@RequestBody TrayInfo tray) {
        return null;
    }

    /**
     * 根据项目名称查询项目信息
     *
     * @param trayName 项目名称
     * @return 响应对象
     */
    @GetMapping("/{trayName}")
    public Result getById(@PathVariable String trayName) {
        List<TrayInfo> trayInfoList = trayInfoService.getByName(trayName);
        Integer code = trayInfoList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = trayInfoList != null ? "" : "数据查询失败！";
        return new Result(code, trayInfoList, msg);
    }

    /**
     * 查询所有项目信息
     *
     * @return 响应对象
     */
    @GetMapping
    public Result getAll() {
        List<TrayInfo> trayInfos = trayInfoService.getAll();
        Integer code = trayInfos != null ? Code.GET_OK : Code.GET_ERR;
        String msg = trayInfos != null ? "" : "数据查询失败！";
        return new Result(code, trayInfos, msg);
    }

    //批量添加
//    @GetMapping("/addTrays")
//    public Result addTrays(){
//        return new Result(Code.GET_OK,trayInfoService.addTrays(),"添加成功");
//    }
}
