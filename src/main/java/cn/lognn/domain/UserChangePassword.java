package cn.lognn.domain;


import lombok.Data;

@Data
public class UserChangePassword {

    private String userId;
    private String oldPassword;
    private String newPassword;
}
