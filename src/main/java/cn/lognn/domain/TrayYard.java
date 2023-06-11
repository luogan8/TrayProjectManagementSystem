package cn.lognn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//堆场
@Data
@TableName("tray_yard")
public class TrayYard {
    private Integer id;
    private String date;
    private String name;
    private String type;
    private Integer state;  //0+   1-
    private Integer number;
    private String notes;

}
