package cn.lognn.service.impl;

import cn.lognn.dao.TrayInfoDao;
import cn.lognn.dao.TrayOutsideDao;
import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayNG;
import cn.lognn.domain.TrayOutside;
import cn.lognn.service.TrayNGService;
import cn.lognn.service.TrayOutsideService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrayOutsideServiceImpl implements TrayOutsideService {
    private final TrayOutsideDao trayOutsideDao;
    private final TrayInfoDao trayInfoDao;

    @Autowired
    public TrayOutsideServiceImpl(TrayOutsideDao trayOutsideDao, TrayInfoDao trayInfoDao) {
        this.trayOutsideDao = trayOutsideDao;
        this.trayInfoDao = trayInfoDao;
    }

    /**
     * 添加记录
     * @param tray
     * @return
     */
    @Override
    public boolean add(TrayOutside tray) {
        tray.setDate(tray.getDate().replace("T", " ") + ":00");
        LambdaQueryWrapper<TrayOutside> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(TrayOutside::getName, tray.getName())
                .eq(TrayOutside::getType, tray.getType())
                .eq(TrayOutside::getNumber, tray.getNumber())
                .eq(TrayOutside::getDate, tray.getDate())
                .eq(TrayOutside::getState, tray.getState());
        List<TrayOutside> trayNGS = trayOutsideDao.selectList(lqw);
        if (trayNGS.size() > 0){
            return false;
        }
        return trayOutsideDao.insert(tray) > 0;
    }

    /**
     * 更新库存
     * @param tray
     * @return
     */
    @Override
    public boolean upOutside(TrayOutside tray) {
        LambdaQueryWrapper<TrayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrayInfo::getTrayName, tray.getName()).eq(TrayInfo::getTrayType, tray.getType());
        TrayInfo trayInfo = trayInfoDao.selectOne(queryWrapper);
        Integer state = tray.getState();
        if (state == 0) {
            //out
            trayInfo.setTrayInside(Math.max(trayInfo.getTrayInside() - tray.getNumber(), 0));
            trayInfo.setTrayOutside(trayInfo.getTrayOutside() + tray.getNumber());
        } else if (state == 1) {
            //in
            trayInfo.setTrayInside(trayInfo.getTrayInside() + tray.getNumber());
            trayInfo.setTrayOutside(Math.max(trayInfo.getTrayOutside() - tray.getNumber(), 0));
        }
        return trayInfoDao.update(trayInfo, queryWrapper) > 0;
    }
}
