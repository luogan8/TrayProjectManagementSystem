package cn.lognn.service.impl;

import cn.lognn.dao.LogDao;
import cn.lognn.domain.Log;
import cn.lognn.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;
    @Override
    public boolean add(Log log) {
        return logDao.insert(log) > 0;
    }
}
