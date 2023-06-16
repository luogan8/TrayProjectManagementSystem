package cn.lognn.service;

import java.util.List;

public interface TrayMenuService {
    public List<String> getTrayNameMenu();
    public List<String> getTrayTypeMenu(String trayName);
}
