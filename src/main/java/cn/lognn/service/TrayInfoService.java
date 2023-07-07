package cn.lognn.service;

import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayInfoDownload;
import cn.lognn.domain.TrayNGDownload;

import java.util.List;

public interface TrayInfoService {
    /**
     * 保存托盘信息
     *
     * @param trayInfo 托盘信息
     * @return 保存成功返回true，否则返回false
     */
    boolean save(TrayInfo trayInfo);

    /**
     * 修改托盘信息
     *
     * @param trayInfo 托盘信息
     * @return 修改成功返回true，否则返回false
     */
    boolean update(TrayInfo trayInfo);

    /**
     * 根据ID删除托盘信息
     *
     * @param id 数据ID
     * @return 删除成功返回true，否则返回false
     */
    boolean delete(Integer id);

    /**
     * 根据名称查询托盘信息
     *
     * @param name 托盘名称
     * @return 匹配的托盘信息列表
     */
    List<TrayInfo> getByName(String name);

    /**
     * 查询所有托盘信息
     *
     * @return 包含所有托盘信息的列表
     */
    List<TrayInfo> getAll();

    /**
     * 获取所有托盘下载信息
     *
     * @return 包含所有托盘下载信息的列表
     */
    List<TrayInfoDownload> getAllDown();

    /**
     * 检查Tray盘信息
     *
     * @param tray 托盘信息
     * @return 检查通过返回true，否则返回false
     */
    boolean trayCheck(TrayInfo tray);

    /**
     * 获取Tray盘编号
     *
     * @param trayNGDownload Tray盘非正常下载信息
     * @return Tray盘编号
     */
    String getTrayNumber(TrayNGDownload trayNGDownload);

    /**
     * 获取托盘下载信息
     *
     * @return 包含托盘下载信息的列表
     */
    List<TrayInfoDownload> getDownload();

    /**
     * 删除并更新托盘信息
     *
     * @param type      类型
     * @param sum       数量
     * @param trayName  托盘名称
     * @param trayType  托盘类型
     * @param state     状态
     * @return 删除并更新成功返回true，否则返回false
     */
    boolean deleteUpdate(String type, Integer sum, String trayName, String trayType, Integer state);

}
