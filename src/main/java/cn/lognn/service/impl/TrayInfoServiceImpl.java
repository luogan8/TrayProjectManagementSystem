package cn.lognn.service.impl;

import cn.hutool.core.io.file.FileReader;
import cn.lognn.dao.TrayInfoDao;
import cn.lognn.dao.TrayInfoDownloadDao;
import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayInfoDownload;
import cn.lognn.domain.TrayNGDownload;
import cn.lognn.service.TrayInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrayInfoServiceImpl implements TrayInfoService {
    @Autowired
    private TrayInfoDao trayDao;

    @Autowired
    private TrayInfoDownloadDao trayInfoDownloadDao;


    @Override
    public boolean save(TrayInfo trayInfo) {
        return trayDao.insert(trayInfo) > 0;
    }

    @Override
    public boolean update(TrayInfo trayInfo) {
        return trayDao.updateById(trayInfo) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return trayDao.deleteById(id) > 0;
    }

    @Override
    public List<TrayInfo> getByName(String tray_name) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        //查询条件
        lqw.eq(TrayInfo::getTrayName,tray_name);
        return trayDao.selectList(lqw);
    }

    @Override
    public List<TrayInfo> getAll() {
        return trayDao.selectList(null);
    }

    @Override
    public boolean addTrays() {
        FileReader fileReader = new FileReader("trays.txt");
        String s = fileReader.readString();
        String[] splits = s.split("\n");
        List<TrayInfo> list = new ArrayList<>();
        for (String split : splits) {
            String[] s1 = split.replace(" ", "").split("\t");
            TrayInfo tray = new TrayInfo();
            tray.setTrayName(s1[0]);
            tray.setTrayType(s1[1]);
            tray.setTrayNumber(s1[2]);
            tray.setTrayInside(Integer.parseInt(s1[3]));
            tray.setTrayOutside(Integer.parseInt(s1[4]));
            list.add(tray);
        }
        trayDao.deleteAll();
        for (TrayInfo tray : list) {
            trayDao.insert(tray);
            System.out.println(tray);
        }


        System.out.println("操作成功...");
        return true;
    }

    @Override
    public boolean trayCheck(TrayInfo trayInfo) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TrayInfo::getTrayNumber, trayInfo.getTrayNumber())
                .or()
                .eq(TrayInfo::getTrayName, trayInfo.getTrayName())
                .eq(TrayInfo::getTrayType, trayInfo.getTrayType());
        return trayDao.selectOne(lqw) != null;
    }

    @Override
    public String getTrayNumber(TrayNGDownload trayNGDownload) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(TrayInfo::getTrayName, trayNGDownload.getName())
                .eq(TrayInfo::getTrayType, trayNGDownload.getType());
        TrayInfo trayInfo = trayDao.selectOne(lqw);
        return trayInfo != null ? trayInfo.getTrayNumber() : "未查询到信息，你可以添加项目，再导出数据！";
    }

    @Override
    public List<TrayInfoDownload> getDownload() {
        LambdaQueryWrapper<TrayInfoDownload> lqw = new LambdaQueryWrapper<>();
        lqw.ne(TrayInfoDownload::getTrayInside, 0).or()
                .ne(TrayInfoDownload::getTrayOutside, 0);
        return trayInfoDownloadDao.selectList(lqw);
    }


    public TrayInfoServiceImpl() {
    }
}
