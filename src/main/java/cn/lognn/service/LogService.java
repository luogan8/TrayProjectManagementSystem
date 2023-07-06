package cn.lognn.service;

import cn.lognn.domain.Log;

import java.util.List;

public interface LogService {

    /**
     * 用户操作记录
     * @param log log
     * @return boolean
     */
    public boolean add(Log log);


    /**
     * 查询所有操作记录
     * @return list<Log>
     */
    public List<Log> getAll();
}
