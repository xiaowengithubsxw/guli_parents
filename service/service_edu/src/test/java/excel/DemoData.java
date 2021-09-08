package excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author Bai Xu
 * @Date 2021/8/28 14:55
 * @Version 1.0
 */
@Data
public class DemoData {
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer son;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String  name;
}
