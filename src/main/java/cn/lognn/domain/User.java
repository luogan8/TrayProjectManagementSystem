package cn.lognn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tray_user")
public class User {
    private Integer id;
    private String userId;
    private String name;
    private Integer grade;
    private String password;
    private Integer deleted;
}
