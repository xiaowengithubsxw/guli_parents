package excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Bai Xu
 * @Date 2021/8/28 15:00
 * @Version 1.0
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //1.设置写入文件夹地址和excel文件名称
        String fileName = "D:\\BaiduNetdiskDownload\\excel\\write.xlsx";
        //2.调用easyexcel里面的方法实现写操作
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());
    }
    //创建方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data=new DemoData();
            data.setName("lucy"+i);
            data.setSon(i);
            list.add(data);
        }
        return list;
    }
    @Test
    public void read(){
        String fileName = "D:\\BaiduNetdiskDownload\\excel\\write.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }
}
