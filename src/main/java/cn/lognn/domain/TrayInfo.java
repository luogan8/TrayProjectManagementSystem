package cn.lognn.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("tray_info") //绑定table
public class TrayInfo {

    // @TableField 字段映射
    // @TableField(value="pwd",select = false)  字段不做查询
    private Integer id;
    private String trayName;
    private String trayType;
    private String trayNumber;
    private Integer trayInside;
    private Integer trayOutside;
    private Integer deleted;
    private String user;
    //数据库不存在字段
//    @TableField(exist = false, select = false)
//    private String passWord;
}
