package cn.lognn.service;

import cn.lognn.domain.NGLogDate;
import cn.lognn.domain.TrayNG;
import cn.lognn.domain.TrayNGDownload;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TrayNGService {
    /**
     * 添加NG记录
     *
     * @param trayNG NG记录
     * @return 添加成功返回true，否则返回false
     */
    boolean add(TrayNG trayNG);

    /**
     * 更新库存
     *
     * @param tray NG记录
     * @return 更新成功返回true，否则返回false
     */
    boolean update(TrayNG tray);

    /**
     * 根据日期获取NG下载记录
     *
     * @param date 日期字符串
     * @return 包含指定日期的NG下载记录列表
     */
    List<TrayNGDownload> getByDate(String date);

    /**
     * 根据名称获取NG下载记录
     *
     * @param name 托盘名称
     * @return 包含指定名称的NG下载记录列表
     */
    List<TrayNGDownload> getByName(String name);

    /**
     * 根据ID删除NG记录
     *
     * @param id 数据ID
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteById(Integer id);

    /**
     * 获取所有NG记录
     *
     * @return 包含所有NG记录的列表
     */
    List<TrayNG> getAll();

    /**
     * 根据ID查询NG记录
     *
     * @param id 数据ID
     * @return 匹配的NG记录，若不存在则返回null
     */
    TrayNG getById(Integer id);

    /**
     * 根据名称查询所有NG记录
     *
     * @param name 托盘名称
     * @return 包含所有指定名称的NG记录的列表
     */
    List<TrayNG> getByNameAll(String name);

    /**
     * 获取NG日志日期
     *
     * @return 包含NG日志日期的列表
     */
    List<NGLogDate> getNGLogDate();
}
