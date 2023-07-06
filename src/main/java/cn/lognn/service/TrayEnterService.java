package cn.lognn.service;
import cn.lognn.domain.TrayEnter;
import cn.lognn.domain.TrayLRDownload;

import java.util.List;

public interface TrayEnterService {
    /**
     * 添加
     * @param trayEnter /
     * @return boolean
     */
    public boolean add(TrayEnter trayEnter);

    /**
     * 查询所有
     * @return list<trayEnter></>
     */
    public List<TrayEnter> getAll();

    /**
     * 更新库存
     * @param trayEnter
     * @return
     */
    public boolean upInLine(TrayEnter trayEnter);


    /**
     * 导出Excel封装返回
     * @param name trayName
     * @return List<TrayLRDownload>
     */
    public List<TrayLRDownload> getDownload(String name);


    /**
     * 根据I的删除
     * @param id dataID
     * @return boolean
     */
    public boolean deleteById(Integer id);

    /**
     * 根据ID查询
     * @param id dataID
     * @return TrayEnter
     */
    public TrayEnter getById(Integer id);

    /**
     * 根据Name查询
     * @param name trayName
     * @return List<TrayEnter>
     */
    public List<TrayEnter> getByName(String name);
}
