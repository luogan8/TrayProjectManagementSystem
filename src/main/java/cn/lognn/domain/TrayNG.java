package cn.lognn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//tray报废
@Data
@TableName("tray_nglog")
public class TrayNG {
    private Integer id;
    private String date;
    private String name;
    private String type;
    private Integer number;
    private String notes;

}
