package cn.lognn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
//tray领入
@Data
@TableName("tray_enterlog")
public class TrayEnter {
    private Integer id;
    private String date;
    private String name;
    private String type;
    private Integer number;
    private String notes;
    private String user;
    //操作记录
    //private String operator;

}
