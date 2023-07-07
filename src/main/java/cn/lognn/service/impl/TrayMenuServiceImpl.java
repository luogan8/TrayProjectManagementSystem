package cn.lognn.service.impl;

import cn.lognn.dao.TrayInfoDao;
import cn.lognn.domain.TrayInfo;
import cn.lognn.service.TrayMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrayMenuServiceImpl implements TrayMenuService {

    private final TrayInfoDao trayInfoDao;

    @Autowired
    public TrayMenuServiceImpl(TrayInfoDao trayInfoDao) {
        this.trayInfoDao = trayInfoDao;
    }

    @Override
    public List<String> getTrayNameMenu() {
        List<TrayInfo> names = trayInfoDao.getNames();
        return names.stream()
                .map(TrayInfo::getTrayName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTrayTypeMenu(String trayName) {
        List<TrayInfo> types = trayInfoDao.getTypes(trayName);
        return types.stream()
                .map(TrayInfo::getTrayType)
                .collect(Collectors.toList());
    }
}
