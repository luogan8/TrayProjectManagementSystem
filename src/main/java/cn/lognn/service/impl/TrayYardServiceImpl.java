package cn.lognn.service.impl;

import cn.lognn.dao.TrayYardDao;
import cn.lognn.domain.TrayYard;
import cn.lognn.service.TrayYardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrayYardServiceImpl implements TrayYardService {
    @Autowired
    private TrayYardDao trayYardDao;


    @Override
    public boolean add(TrayYard tray) {
        tray.setDate(tray.getDate().replace("T", " ") + ":00");
        return trayYardDao.insert(tray) > 0;
    }

    /**
     * 更新库存
     * @param tray
     * @return
     */
    @Override
    public boolean upYard(TrayYard tray) {
        return false;
    }
}
