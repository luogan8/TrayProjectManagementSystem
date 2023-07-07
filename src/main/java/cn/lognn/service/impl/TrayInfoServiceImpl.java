package cn.lognn.service.impl;

import cn.lognn.dao.TrayInfoDao;
import cn.lognn.dao.TrayInfoDownloadDao;
import cn.lognn.domain.TrayInfo;
import cn.lognn.domain.TrayInfoDownload;
import cn.lognn.domain.TrayNGDownload;
import cn.lognn.service.TrayInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrayInfoServiceImpl implements TrayInfoService {

    private final TrayInfoDao trayDao;
    private final TrayInfoDownloadDao trayInfoDownloadDao;

    @Autowired
    public TrayInfoServiceImpl(TrayInfoDao trayDao, TrayInfoDownloadDao trayInfoDownloadDao) {
        this.trayDao = trayDao;
        this.trayInfoDownloadDao = trayInfoDownloadDao;
    }

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
    public List<TrayInfo> getByName(String trayName) {
        LambdaQueryWrapper<TrayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrayInfo::getTrayName, trayName);
        return trayDao.selectList(queryWrapper);
    }

    @Override
    public List<TrayInfo> getAll() {
        return trayDao.selectList(null);
    }

    @Override
    public List<TrayInfoDownload> getAllDown() {
        return trayInfoDownloadDao.selectList(null);
    }

    @Override
    public boolean trayCheck(TrayInfo trayInfo) {
        LambdaQueryWrapper<TrayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrayInfo::getTrayNumber, trayInfo.getTrayNumber())
                .or()
                .eq(TrayInfo::getTrayName, trayInfo.getTrayName())
                .eq(TrayInfo::getTrayType, trayInfo.getTrayType());
        return trayDao.selectOne(queryWrapper) != null;
    }

    @Override
    public String getTrayNumber(TrayNGDownload trayNGDownload) {
        LambdaQueryWrapper<TrayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(TrayInfo::getTrayName, trayNGDownload.getName())
                .eq(TrayInfo::getTrayType, trayNGDownload.getType());
        TrayInfo trayInfo = trayDao.selectOne(queryWrapper);
        return trayInfo != null ? trayInfo.getTrayNumber() : "未查询到信息，你可以添加项目，再导出数据！";
    }

    @Override
    public List<TrayInfoDownload> getDownload() {
        LambdaQueryWrapper<TrayInfoDownload> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(TrayInfoDownload::getTrayInside, 0).or()
                .ne(TrayInfoDownload::getTrayOutside, 0);
        return trayInfoDownloadDao.selectList(queryWrapper);
    }

    @Override
    public boolean deleteUpdate(String type, Integer sum, String trayName, String trayType, Integer state) {
        LambdaQueryWrapper<TrayInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TrayInfo::getTrayName, trayName).eq(TrayInfo::getTrayType, trayType);
        TrayInfo trayInfo = trayDao.selectOne(queryWrapper);
        switch (type) {
            case "lr":
                System.out.println("lr删除更新数据");
                trayInfo.setTrayInside(trayInfo.getTrayInside() - sum);
                break;
            case "ng":
                System.out.println("ng删除更新数据");
                trayInfo.setTrayInside(trayInfo.getTrayInside() + sum);
                break;
            case "outside":
                switch (state) {
                    case 0:
                        System.out.println(0);
                        trayInfo.setTrayOutside(trayInfo.getTrayOutside() - sum);
                        trayInfo.setTrayInside(trayInfo.getTrayInside() + sum);
                        break;
                    case 1:
                        System.out.println(1);
                        trayInfo.setTrayOutside(trayInfo.getTrayOutside() + sum);
                        trayInfo.setTrayInside(trayInfo.getTrayInside() - sum);
                        break;
                }
                break;
            default:
                break;
        }
        trayDao.updateById(trayInfo);
        return false;
    }

}
