package cn.lognn.service.impl;

import cn.lognn.dao.LogDao;
import cn.lognn.domain.Log;
import cn.lognn.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogDao logDao;

    @Autowired
    public LogServiceImpl(LogDao logDao) {
        this.logDao = logDao;
    }

    @Override
    public boolean add(Log log) {
        return logDao.insert(log) > 0;
    }

    @Override
    public List<Log> getAll() {
        LambdaQueryWrapper<Log> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Log::getId);
        return logDao.selectList(queryWrapper);
    }
}
