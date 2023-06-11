package cn.lognn.service.impl;

import cn.lognn.dao.TrayInfoDao;
import cn.lognn.domain.TrayInfo;
import cn.lognn.service.TrayMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrayMenuServiceImpl implements TrayMenuService {
    @Autowired
    private TrayInfoDao trayInfoDao;

    @Override
    public List<String> getTrayNameMenu() {
        List<String> list = new ArrayList<>();
        List<TrayInfo> names = trayInfoDao.getNames();
        for (TrayInfo name : names) {
            list.add(name.getTrayName());
        }
        return list;
    }

    @Override
    public List<String> getTrayTypeMenu() {
        List<String> list = new ArrayList<>();
        List<TrayInfo> types = trayInfoDao.getTypes();
        for (TrayInfo type : types) {
            list.add(type.getTrayType());
        }
        return list;
    }
}
