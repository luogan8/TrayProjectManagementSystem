package cn.lognn.service.impl;

import cn.lognn.dao.TrayYardDao;
import cn.lognn.domain.TrayYard;
import cn.lognn.service.TrayYardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrayYardServiceImpl implements TrayYardService {

    private final TrayYardDao trayYardDao;

    @Autowired
    public TrayYardServiceImpl(TrayYardDao trayYardDao) {
        this.trayYardDao = trayYardDao;
    }

    @Override
    public boolean add(TrayYard tray) {
        tray.setDate(tray.getDate().replace("T", " ") + ":00");
        return trayYardDao.insert(tray) > 0;
    }

}
