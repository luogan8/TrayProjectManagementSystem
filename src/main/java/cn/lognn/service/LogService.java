package cn.lognn.service;

import cn.lognn.domain.Log;
import java.util.List;

public interface LogService {

    /**
     * 添加用户操作记录
     *
     * @param log 用户操作记录
     * @return 添加成功返回true，否则返回false
     */
    boolean add(Log log);

    /**
     * 查询所有操作记录
     *
     * @return 包含所有操作记录的列表
     */
    List<Log> getAll();
}
