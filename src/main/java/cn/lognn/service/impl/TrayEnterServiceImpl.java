package cn.lognn.service.impl;

import cn.lognn.dao.TrayEnterDao;
import cn.lognn.dao.TrayInfoDao;
import cn.lognn.dao.TrayLRDownloadDao;
import cn.lognn.domain.TrayEnter;
import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayLRDownload;
import cn.lognn.service.TrayEnterService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrayEnterServiceImpl implements TrayEnterService {

    private final TrayEnterDao trayEnterDao;
    private final TrayInfoDao trayInfoDao;
    private final TrayLRDownloadDao trayLRDownloadDao;

    @Autowired
    public TrayEnterServiceImpl(TrayEnterDao trayEnterDao, TrayInfoDao trayInfoDao, TrayLRDownloadDao trayLRDownloadDao) {
        this.trayEnterDao = trayEnterDao;
        this.trayInfoDao = trayInfoDao;
        this.trayLRDownloadDao = trayLRDownloadDao;
    }

    @Override
    public boolean add(TrayEnter trayEnter) {
        trayEnter.setDate(trayEnter.getDate().replace("T", " ") + ":00");
        LambdaQueryWrapper<TrayEnter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(TrayEnter::getName, trayEnter.getName())
                .eq(TrayEnter::getType, trayEnter.getType())
                .eq(TrayEnter::getNumber, trayEnter.getNumber())
                .eq(TrayEnter::getDate, trayEnter.getDate());
        if (trayEnterDao.selectList(queryWrapper).size() > 0) {
            return false;
        }
        return trayEnterDao.insert(trayEnter) > 0;
    }

    @Override
    public List<TrayEnter> getAll() {
        LambdaQueryWrapper<TrayEnter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(TrayEnter::getId);
        return trayEnterDao.selectList(queryWrapper);
    }

    @Override
    public boolean upInLine(TrayEnter trayEnter) {
        LambdaQueryWrapper<TrayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrayInfo::getTrayName, trayEnter.getName()).eq(TrayInfo::getTrayType, trayEnter.getType());
        TrayInfo tray = trayInfoDao.selectOne(queryWrapper);
        Integer trayInside = tray.getTrayInside() + trayEnter.getNumber();
        tray.setTrayInside(trayInside);
        queryWrapper.eq(TrayInfo::getId, tray.getId());
        return trayInfoDao.update(tray, queryWrapper) > 0;
    }

    @Override
    public List<TrayLRDownload> getDownload(String name) {
        return trayLRDownloadDao.getByName(name);
    }

    @Override
    public boolean deleteById(Integer id) {
        return trayEnterDao.deleteById(id) == 1;
    }

    @Override
    public TrayEnter getById(Integer id) {
        return trayEnterDao.selectById(id);
    }

    @Override
    public List<TrayEnter> getByName(String name) {
        LambdaQueryWrapper<TrayEnter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrayEnter::getName, name);
        queryWrapper.orderByDesc(TrayEnter::getId);
        return trayEnterDao.selectList(queryWrapper);
    }
}
