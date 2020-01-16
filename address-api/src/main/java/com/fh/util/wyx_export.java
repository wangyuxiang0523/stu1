package com.fh.util;


import com.fh.annotation.Export;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class wyx_export {
    public static void export(List list, HttpServletResponse response){
        Object o = list.get(0);
        Class<?> aClass = o.getClass();
        Export annotation = aClass.getAnnotation(Export.class);
        String title = annotation.title();
        XSSFWorkbook workbook =new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(title);
        XSSFRow row = sheet.createRow(0);
        Field[] declaredFields = aClass.getDeclaredFields();
        int num = 0;
        for (Field field:declaredFields){
            Export annotation1 = field.getAnnotation(Export.class);
            if(annotation1 != null){
                String name = annotation1.name();
                XSSFCell cell = row.createCell(num);
                cell.setCellValue(name);
                num++;
            }
        }
        for (int i=0;i<list.size();i++){
            Object o1 = list.get(i);
            XSSFRow row1 = sheet.createRow(i + 1);
            int cellnum=0;
            for (int j=0;j<declaredFields.length;j++){
                Field declaredField = declaredFields[j];
                Export annotation1 = declaredFields[j].getAnnotation(Export.class);
                if(annotation1 !=null){
                    XSSFCell cell = row1.createCell(cellnum);
                    try {
                        declaredField.setAccessible(true);
                        Object o2 = declaredField.get(o1);
                        if(o2 != null){
                            Class<?> type = declaredField.getType();
                            if(type == Date.class){
                                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                                Date date= (Date) o2;
                                String format = sim.format(date);
                                cell.setCellValue(format);
                            }else {
                                cell.setCellValue(o2.toString());
                            }
                        }else {
                            cell.setCellValue("");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    cellnum++;
                }
            }
        }
      response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=\""+ UUID.randomUUID().toString()+".xlsx\"");

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
