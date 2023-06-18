package cn.lognn.service;

import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayInfoDownload;
import cn.lognn.domain.TrayNGDownload;

import java.util.List;

public interface TrayInfoService {
    /**
     * 保存
     * @param trayInfo
     * @return
     */
    public boolean save(TrayInfo trayInfo);

    /**
     * 修改
     * @param trayInfo
     * @return
     */
    public boolean update(TrayInfo trayInfo);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    public List<TrayInfo> getByName(String name);

    /**
     * 查询所有
     * @return
     */
    public List<TrayInfo> getAll();

    /**
     * add Tray盘信息
     * @return
     */
    public boolean addTrays();

    public boolean trayCheck(TrayInfo tray);


    public String getTrayNumber(TrayNGDownload trayNGDownload);

    public List<TrayInfoDownload> getDownload();

    public boolean deleteUpdate(String type, Integer sum, String trayName, String trayType, Integer state);
}
