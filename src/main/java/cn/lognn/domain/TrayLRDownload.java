package cn.lognn.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import lombok.Data;

import java.util.Date;


@Data
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 49)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER, verticalAlignment = VerticalAlignmentEnum.CENTER,
        borderLeft = BorderStyleEnum.THIN, borderRight = BorderStyleEnum.THIN,
        borderTop = BorderStyleEnum.THIN, borderBottom = BorderStyleEnum.THIN)
public class TrayLRDownload {
    @ExcelProperty({"2F TrayClean 领入数据", "日期"})
    @DateTimeFormat("yyyy年MM月dd日")
    private Date date;
    @ExcelProperty({"2F TrayClean 领入数据", "型号"})
    private String name;

    @ExcelProperty({"2F TrayClean 领入数据", "类型"})
    private String type;

    @ExcelProperty({"2F TrayClean 领入数据", "数量"})
    private int number;

    @ExcelProperty({"2F TrayClean 领入数据", "备注"})
    private String notes;

}
