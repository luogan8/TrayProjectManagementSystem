package cn.lognn.service;

import cn.lognn.domain.TrayEnter;
import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayLRDownload;

import java.util.List;

public interface TrayEnterService {
    /**
     * 添加
     * @param trayEnter
     * @return
     */
    public boolean add(TrayEnter trayEnter);

    /**
     * 查询所有
     * @return
     */
    public List<TrayEnter> getAll();

    /**
     * 更新库存
     * @param trayEnter
     * @return
     */
    public boolean upInLine(TrayEnter trayEnter);

    public List<TrayLRDownload> getDownload(String name);

    public boolean deleteById(Integer id);
}
