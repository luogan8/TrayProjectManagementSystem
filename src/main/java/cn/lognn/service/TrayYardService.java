package cn.lognn.service;



import cn.lognn.domain.TrayYard;

public interface TrayYardService {
    public boolean add(TrayYard tray);

    /**
     * 更新库存
     * @param tray
     * @return
     */
    public boolean upYard(TrayYard tray);
}
