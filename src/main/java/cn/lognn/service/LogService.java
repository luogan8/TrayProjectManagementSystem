package cn.lognn.service;

import cn.lognn.domain.Log;

import java.util.List;

public interface LogService {
    public boolean add(Log log);

    public List<Log> getAll();
}
