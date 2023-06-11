package cn.lognn.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 49)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER, verticalAlignment = VerticalAlignmentEnum.CENTER,
        borderLeft = BorderStyleEnum.HAIR, borderRight = BorderStyleEnum.HAIR,
        borderTop = BorderStyleEnum.HAIR, borderBottom = BorderStyleEnum.HAIR)
@TableName("tray_info")
public class TrayInfoDownload {

    @ExcelProperty({"2F TrayClean 库存信息", "型号"})
    private String trayName;

    @ExcelProperty({"2F TrayClean 库存信息", "类型"})
    private String trayType;

    @ExcelProperty({"2F TrayClean 库存信息", "物料号"})
    private String trayNumber;

    @ExcelProperty({"2F TrayClean 库存信息", "线内数量"})
    private int trayInside;

    @ExcelProperty({"2F TrayClean 库存信息", "线外数量"})
    private int trayOutside;


}
