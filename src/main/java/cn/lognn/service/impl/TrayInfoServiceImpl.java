package cn.lognn.service.impl;

import cn.hutool.core.io.file.FileReader;
import cn.lognn.dao.*;
import cn.lognn.domain.*;
import cn.lognn.service.TrayInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrayInfoServiceImpl implements TrayInfoService {
    @Autowired
    private TrayInfoDao trayDao;

    @Autowired
    private TrayNGDao trayNGDao;

    @Autowired
    private TrayEnterDao trayEnterDao;

    @Autowired
    private TrayOutsideDao trayOutsideDao;

    @Autowired
    private TrayInfoDownloadDao trayInfoDownloadDao;


    @Override
    public boolean save(TrayInfo trayInfo) {
        return trayDao.insert(trayInfo) > 0;
    }

    @Override
    public boolean update(TrayInfo trayInfo) {
        return trayDao.updateById(trayInfo) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return trayDao.deleteById(id) > 0;
    }

    @Override
    public List<TrayInfo> getByName(String tray_name) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        //查询条件
        lqw.eq(TrayInfo::getTrayName,tray_name);
        return trayDao.selectList(lqw);
    }

    @Override
    public List<TrayInfo> getAll() {
        return trayDao.selectList(null);
    }

    @Override
    public boolean addTrays() {
        FileReader fileReader = new FileReader("trays.txt");
        String s = fileReader.readString();
        String[] splits = s.split("\n");
        List<TrayInfo> list = new ArrayList<>();
        for (String split : splits) {
            String[] s1 = split.replace(" ", "").split("\t");
            TrayInfo tray = new TrayInfo();
            tray.setTrayName(s1[0]);
            tray.setTrayType(s1[1]);
            tray.setTrayNumber(s1[2]);
            tray.setTrayInside(Integer.parseInt(s1[3]));
            tray.setTrayOutside(Integer.parseInt(s1[4]));
            list.add(tray);
        }
        trayDao.deleteAll();
        for (TrayInfo tray : list) {
            trayDao.insert(tray);
            System.out.println(tray);
        }


        System.out.println("操作成功...");
        return true;
    }

    @Override
    public boolean trayCheck(TrayInfo trayInfo) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TrayInfo::getTrayNumber, trayInfo.getTrayNumber())
                .or()
                .eq(TrayInfo::getTrayName, trayInfo.getTrayName())
                .eq(TrayInfo::getTrayType, trayInfo.getTrayType());
        return trayDao.selectOne(lqw) != null;
    }

    @Override
    public String getTrayNumber(TrayNGDownload trayNGDownload) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(TrayInfo::getTrayName, trayNGDownload.getName())
                .eq(TrayInfo::getTrayType, trayNGDownload.getType());
        TrayInfo trayInfo = trayDao.selectOne(lqw);
        return trayInfo != null ? trayInfo.getTrayNumber() : "未查询到信息，你可以添加项目，再导出数据！";
    }

    @Override
    public List<TrayInfoDownload> getDownload() {
        LambdaQueryWrapper<TrayInfoDownload> lqw = new LambdaQueryWrapper<>();
        lqw.ne(TrayInfoDownload::getTrayInside, 0).or()
                .ne(TrayInfoDownload::getTrayOutside, 0);
        return trayInfoDownloadDao.selectList(lqw);
    }

    /**
     * 根据ID更新库存
     * @param type
     * @return
     */
    @Override
    public boolean deleteUpdate(String type, Integer sum, String trayName, String trayType, Integer state) {
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TrayInfo::getTrayName, trayName).eq(TrayInfo::getTrayType, trayType);
        TrayInfo trayInfo = trayInfoDao.selectOne(lqw);
        switch (type) {
            case "lr" -> {
                System.out.println("lr删除更新数据");
                trayInfo.setTrayInside(trayInfo.getTrayInside() - sum);}
            case "ng" -> {
                System.out.println("ng删除更新数据");
                trayInfo.setTrayInside(trayInfo.getTrayInside() + sum);
            }
            case "outside" -> {
                switch (state) {
                    case 0 -> {
                        System.out.println(0);
                        trayInfo.setTrayOutside(trayInfo.getTrayOutside() - sum);
                        trayInfo.setTrayInside(trayInfo.getTrayInside() + sum);
                    }
                    case 1 -> {
                        System.out.println(1);
                        trayInfo.setTrayOutside(trayInfo.getTrayOutside() + sum);
                        trayInfo.setTrayInside(trayInfo.getTrayInside() - sum);
                    }
                }
            }
            default -> {
            }
        }
        trayInfoDao.updateById(trayInfo);
        return false;
    }


    public TrayInfoServiceImpl() {
    }

    @Autowired
    private TrayInfoDao trayInfoDao;
    public TrayInfo getTrayInfo(String trayName, String trayType){
        LambdaQueryWrapper<TrayInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TrayInfo::getTrayName, trayName).eq(TrayInfo::getTrayType, trayType);
        return trayInfoDao.selectOne(lqw);
    }
}
