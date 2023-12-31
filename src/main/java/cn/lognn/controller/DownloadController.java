package cn.lognn.controller;

import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayInfoDownload;
import cn.lognn.domain.TrayLRDownload;
import cn.lognn.domain.TrayNGDownload;
import cn.lognn.service.TrayEnterService;
import cn.lognn.service.TrayInfoService;
import cn.lognn.service.TrayNGService;
import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("download")
public class DownloadController {
    @Autowired
    private TrayNGService trayNGService;

    @Autowired
    private TrayInfoService trayInfoService;

    @Autowired
    private TrayEnterService trayEnterService;

    private static final String FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String FILE_ENCODING = "utf-8";

    private void setResponseHeaders(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType(FILE_TYPE);
        response.setCharacterEncoding(FILE_ENCODING);
        String encodedFileName = URLEncoder.encode(fileName, FILE_ENCODING).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");
    }

    /**
     * 导出报废记录
     *
     * @param response HTTP响应对象
     * @param date     日期
     * @throws IOException IO异常
     */
    @GetMapping("/ng/{date}")
    public void ngDownload(HttpServletResponse response, @PathVariable String date) throws IOException {
        List<TrayNGDownload> data = trayNGService.getByDate(date);
        String fileName = "TrayNG报废@" + date;
        setResponseHeaders(response, fileName);
        EasyExcel.write(response.getOutputStream(), TrayNGDownload.class).sheet(date + "TrayNG报废数据").doWrite(data);
    }

    /**
     * 导出库存信息
     *
     * @param response HTTP响应对象
     * @throws IOException IO异常
     */
    @GetMapping("/info")
    public void infoDownload(HttpServletResponse response) throws IOException {
        List<TrayInfoDownload> data = trayInfoService.getDownload();
        String fileName = "Tray库存信息";
        setResponseHeaders(response, fileName);
        EasyExcel.write(response.getOutputStream(), TrayInfoDownload.class).sheet("Tray库存信息").doWrite(data);
    }

    /**
     * 导出全部库存信息
     *
     * @param response HTTP响应对象
     * @throws IOException IO异常
     */
    @GetMapping("/infoAll")
    public void infoAllDownload(HttpServletResponse response) throws IOException {
        List<TrayInfoDownload> data = trayInfoService.getAllDown();
        String fileName = "Tray库存信息@all";
        setResponseHeaders(response, fileName);
        EasyExcel.write(response.getOutputStream(), TrayInfoDownload.class).sheet("Tray库存信息@all").doWrite(data);
    }

    /**
     * 导出领入记录
     *
     * @param response HTTP响应对象
     * @param name     领入名称
     * @throws IOException IO异常
     */
    @GetMapping("/lr/{name}")
    public void lrDownload(HttpServletResponse response, @PathVariable String name) throws IOException {
        List<TrayLRDownload> data = trayEnterService.getDownload(name);
        String fileName = name + "领入明细";
        setResponseHeaders(response, fileName);
        EasyExcel.write(response.getOutputStream(), TrayLRDownload.class).sheet(name + "领入明细").doWrite(data);
    }

    /**
     * 根据项目名称导出NG数据
     *
     * @param response HTTP响应对象
     * @param name     项目名称
     * @throws IOException IO异常
     */
    @GetMapping("/ngProject/{name}")
    public void ngGetByNameDownload(HttpServletResponse response, @PathVariable String name) throws IOException {
        List<TrayNGDownload> data = trayNGService.getByName(name);
        String fileName = "TrayNG报废@" + name;
        setResponseHeaders(response, fileName);
        EasyExcel.write(response.getOutputStream(), TrayNGDownload.class).sheet(name + "TrayNG报废数据").doWrite(data);
    }
}
