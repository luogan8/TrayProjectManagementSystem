package cn.lognn.service;

import java.util.List;

public interface TrayMenuService {
    /**
     * 获取托盘名称菜单
     *
     * @return 包含托盘名称的菜单列表
     */
    List<String> getTrayNameMenu();

    /**
     * 根据托盘名称获取托盘类型菜单
     *
     * @param trayName 托盘名称
     * @return 包含托盘类型的菜单列表
     */
    List<String> getTrayTypeMenu(String trayName);
}
