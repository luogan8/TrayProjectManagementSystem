package cn.lognn.dao;


import cn.lognn.domain.TrayInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrayInfoDao extends BaseMapper<TrayInfo> {
    @Delete("delete from tray_info")
    public void deleteAll();

    @Select("SELECT DISTINCT tray_name FROM tray_info ORDER BY tray_name")
    public List<TrayInfo> getNames();

    @Select("SELECT DISTINCT tray_type FROM tray_info WHERE tray_name = #{trayName}")
    public List<TrayInfo> getTypes(String trayName);

}
