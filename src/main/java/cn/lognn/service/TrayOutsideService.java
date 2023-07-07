package cn.lognn.service;

import cn.lognn.domain.TrayOutside;
import java.util.List;

public interface TrayOutsideService {
    /**
     * 添加外部托盘记录
     *
     * @param tray 外部托盘记录
     * @return 添加成功返回true，否则返回false
     */
    boolean add(TrayOutside tray);

    /**
     * 更新外部托盘记录
     *
     * @param tray 外部托盘记录
     * @return 更新成功返回true，否则返回false
     */
    boolean upOutside(TrayOutside tray);

    /**
     * 获取所有外部托盘记录
     *
     * @return 包含所有外部托盘记录的列表
     */
    List<TrayOutside> getAll();

    /**
     * 根据ID删除外部托盘记录
     *
     * @param id 数据ID
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteById(Integer id);

    /**
     * 根据ID查询外部托盘记录
     *
     * @param id 数据ID
     * @return 匹配的外部托盘记录，若不存在则返回null
     */
    TrayOutside getById(Integer id);
}
