package cn.lognn.service;

import cn.lognn.domain.TrayEnter;
import cn.lognn.domain.TrayLRDownload;

import java.util.List;

public interface TrayEnterService {
    /**
     * 添加托盘进入记录
     *
     * @param trayEnter 托盘进入记录
     * @return 添加成功返回true，否则返回false
     */
    boolean add(TrayEnter trayEnter);

    /**
     * 查询所有托盘进入记录
     *
     * @return 包含所有托盘进入记录的列表
     */
    List<TrayEnter> getAll();

    /**
     * 更新库存状态
     *
     * @param trayEnter 托盘进入记录
     * @return 更新成功返回true，否则返回false
     */
    boolean upInLine(TrayEnter trayEnter);

    /**
     * 导出Excel并封装返回
     *
     * @param name 托盘名称
     * @return 包含导出的托盘下载记录的列表
     */
    List<TrayLRDownload> getDownload(String name);

    /**
     * 根据ID删除托盘进入记录
     *
     * @param id 数据ID
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteById(Integer id);

    /**
     * 根据ID查询托盘进入记录
     *
     * @param id 数据ID
     * @return 匹配的托盘进入记录，若不存在则返回null
     */
    TrayEnter getById(Integer id);

    /**
     * 根据名称查询托盘进入记录
     *
     * @param name 托盘名称
     * @return 匹配的托盘进入记录列表
     */
    List<TrayEnter> getByName(String name);
}
