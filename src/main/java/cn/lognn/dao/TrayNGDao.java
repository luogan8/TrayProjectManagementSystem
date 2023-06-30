package cn.lognn.dao;


import cn.lognn.domain.NGLogDate;
import cn.lognn.domain.TrayNG;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrayNGDao extends BaseMapper<TrayNG> {


    @Select("SELECT DISTINCT date FROM tray_nglog ORDER BY date DESC LIMIT 3")
    public List<NGLogDate> getNGDate();

}
