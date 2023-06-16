package cn.lognn.service.impl;


import cn.lognn.dao.TrayEnterDao;
import cn.lognn.dao.TrayInfoDao;
import cn.lognn.dao.TrayLRDownloadDao;
import cn.lognn.domain.TrayEnter;
import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayLRDownload;
import cn.lognn.domain.TrayNGDownload;
import cn.lognn.service.TrayEnterService;
import cn.lognn.service.TrayInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TrayEnterServiceImpl implements TrayEnterService {

    @Autowired
    private TrayEnterDao trayEnterDao;
    @Autowired
    private TrayInfoDao trayInfoDao;
    @Autowired
    private TrayLRDownloadDao trayLRDownloadDao;


    @Override
    public boolean add(TrayEnter trayEnter) {
        trayEnter.setDate(trayEnter.getDate().replace("T", " ") + ":00");
        LambdaQueryWrapper<TrayEnter> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(TrayEnter::getName, trayEnter.getName())
                .eq(TrayEnter::getType, trayEnter.getType())
                .eq(TrayEnter::getNumber, trayEnter.getNumber())
                .eq(TrayEnter::getDate, trayEnter.getDate());
        if (trayEnterDao.selectList(lqw).size() > 0){
            return false;
        }
        return trayEnterDao.insert(trayEnter) > 0;
    }

    @Override
    public List<TrayEnter> getAll() {
        LambdaQueryWrapper<TrayEnter> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(TrayEnter::getId);
        return trayEnterDao.selectList(lqw);
    }

    @Override
    public boolean upInLine(TrayEnter trayEnter) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TrayInfo::getTrayName, trayEnter.getName()).eq(TrayInfo::getTrayType, trayEnter.getType());
        TrayInfo tray = trayInfoDao.selectOne(lqw);
        Integer trayInside = tray.getTrayInside() + trayEnter.getNumber();
        tray.setTrayInside(trayInside);
        lqw.eq(TrayInfo::getId, tray.getId());
        return trayInfoDao.update(tray, lqw) > 0;
    }

    @Override
    public List<TrayLRDownload> getDownload(String name) {
        return trayLRDownloadDao.getByName(name);
    }

    @Override
    public boolean deleteById(Integer id) {
        return trayEnterDao.deleteById(id) == 1;
    }


}
