package cn.lognn.dao;

import cn.lognn.domain.TrayLRDownload;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface TrayLRDownloadDao extends BaseMapper<TrayLRDownload> {
    @Select("select * from tray_enterlog where name = #{name} and number != 0")
    public List<TrayLRDownload> getByName(String name);

}
