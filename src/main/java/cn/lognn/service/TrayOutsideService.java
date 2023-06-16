package cn.lognn.service;




import cn.lognn.domain.TrayOutside;

import java.util.List;


public interface TrayOutsideService {
    public boolean add(TrayOutside tray);
    public boolean upOutside(TrayOutside tray);

    public List<TrayOutside> getAll();

    public boolean deleteById(Integer id);
}
