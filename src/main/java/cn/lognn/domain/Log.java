package cn.lognn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@TableName("user_action")
public class Log {
    private Long id;
    private String name;
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String datetime;
    private String user;
    private String changetype;
    private Integer number;
}
