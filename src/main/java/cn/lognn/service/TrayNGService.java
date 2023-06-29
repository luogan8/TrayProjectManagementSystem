package cn.lognn.service;


import cn.lognn.domain.TrayNG;
import cn.lognn.domain.TrayNGDownload;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TrayNGService {
    public boolean add(TrayNG trayNG);

    /**
     * 更新库存
     * @param tray
     * @return
     */
    public boolean update(TrayNG tray);

    public List<TrayNGDownload> getByDate(String date);
    public List<TrayNGDownload> getByName(String name);

    public boolean deleteById (Integer id);

    public List<TrayNG> getAll();


    public TrayNG getById(Integer id);

    public List<TrayNG> getByNameAll(String name);
}
