package cn.lognn.domain;


import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

@Data
public class NGLogDate {

    @DateTimeFormat("yyyy-MM-dd")
    private String date;
}
