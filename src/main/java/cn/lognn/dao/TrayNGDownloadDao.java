package cn.lognn.dao;

import cn.lognn.domain.TrayNGDownload;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface TrayNGDownloadDao extends BaseMapper<TrayNGDownload> {
    @Select("select * from tray_nglog where date like #{date}")
    public List<TrayNGDownload> getByDate(String date);
}
