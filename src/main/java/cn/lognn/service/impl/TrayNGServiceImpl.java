package cn.lognn.service.impl;

import cn.lognn.dao.TrayInfoDao;
import cn.lognn.dao.TrayNGDao;
import cn.lognn.dao.TrayNGDownloadDao;
import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayNG;
import cn.lognn.domain.TrayNGDownload;
import cn.lognn.service.TrayInfoService;
import cn.lognn.service.TrayNGService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class TrayNGServiceImpl implements TrayNGService {
    @Autowired
    private TrayNGDownloadDao trayNGDownloadDao;
    @Autowired
    private TrayInfoDao trayInfoDao;

    @Autowired
    private TrayNGDao trayNGDao;

    @Autowired
    private TrayInfoService trayInfoService;





    @Override
    public boolean add(TrayNG trayNG) {
        trayNG.setDate(trayNG.getDate().replace("T", " ") + ":00");
        LambdaQueryWrapper<TrayNG> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(TrayNG::getName, trayNG.getName())
                .eq(TrayNG::getType, trayNG.getType())
                .eq(TrayNG::getNumber, trayNG.getNumber())
                .eq(TrayNG::getDate, trayNG.getDate());
        List<TrayNG> trayNGS = trayNGDao.selectList(lqw);
        if (trayNGS.size() > 0){
            return false;
        }
        return trayNGDao.insert(trayNG) > 0;
    }


    /**
     * 更新库存
     * @param tray
     * @return
     */
    @Override
    public boolean update(TrayNG tray) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TrayInfo::getTrayName, tray.getName()).eq(TrayInfo::getTrayType, tray.getType());
        TrayInfo trayInfo = trayInfoDao.selectOne(lqw);
        trayInfo.setTrayInside(Math.max(trayInfo.getTrayInside() - tray.getNumber(), 0));
        lqw.eq(TrayInfo::getTrayName, trayInfo.getTrayName()).eq(TrayInfo::getTrayType, tray.getType());
        return trayInfoDao.update(trayInfo, lqw) > 0;
    }

    @Override
    public List<TrayNGDownload> getByDate(String date) {
        List<TrayNGDownload> data = trayNGDownloadDao.getByDate(date + "%");
        List<TrayNGDownload> result = new ArrayList<>();
        for (TrayNGDownload datum : data) {
            String trayNumber = trayInfoService.getTrayNumber(datum);
            datum.setTrayNumber(trayNumber);
            result.add(datum);
        }
        return result;
    }


}
