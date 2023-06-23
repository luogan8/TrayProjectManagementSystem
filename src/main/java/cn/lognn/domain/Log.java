package cn.lognn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tray_log")
public class Log {
    private Long id;
    private Date datetime;
    private String user;
    private String type;
}
