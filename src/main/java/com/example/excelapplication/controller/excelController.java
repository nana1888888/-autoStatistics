package com.example.excelapplication.controller;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import  com.example.excelapplication.service.baseService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import static org.apache.poi.poifs.crypt.HashAlgorithm.ripemd128;
import static org.apache.poi.poifs.crypt.HashAlgorithm.ripemd256;

@Controller
public class excelController {
    @Autowired
    private baseService baseservice;
    @RequestMapping("/test.do")
    public void sample(HttpServletRequest request, HttpServletResponse response) throws Exception {
         try {
             Calendar cal = Calendar.getInstance();
             int month = cal.get(Calendar.MONTH) + 1;
             String monthString = month+"월";
             System.out.println(month);
            String filename = "";

            request.setCharacterEncoding("EUC-KR");

             File file = new File("C:\\Users\\NH\\Desktop\\project\\excelApplication\\excelApplication\\src\\main\\resources\\excel\\sample1.xlsx");
            InputStream fis = new FileInputStream(file);

            XSSFWorkbook form_wb = new XSSFWorkbook(fis);
           CellStyle style = form_wb.createCellStyle();
             FormulaEvaluator evaluator = form_wb.getCreationHelper().createFormulaEvaluator();
            XSSFSheet form_sheet = form_wb.getSheetAt(0).getWorkbook().getSheet("2023년");
             XSSFDataFormat format = form_wb.createDataFormat();
            //양식에 넣어줄 데이터

             int f = baseservice.selectedCount();

            double date;

             date =  (double) f;



            //스타일 설정
/*
            style.setBorderTop(BorderStyle.THIN);
             style.setBorderLeft(BorderStyle.THIN);
             style.setBorderRight(BorderStyle.THIN);
             style.setBorderBottom(BorderStyle.THIN);
             style.setAlignment(HorizontalAlignment.RIGHT);

             */



             //스타일
           /* form_sheet.getRow(가로).getCell(3).setCellStyle(style);
            form_sheet.getRow(가로).getCell(6).setCellStyle(style);
            form_sheet.getRow(가로).getCell(9).setCellStyle(style);*/



  int g=5;
  while (true) {

      String dd = form_sheet.getRow(g).getCell(0).getStringCellValue();
      //경기도 합계
      if (dd.equals("경기도")) {
          System.out.println("멈춤");
          for (int i = 0; i <= 13; i++) {
              String aa = form_sheet.getRow(g+1).getCell(i).getStringCellValue();
              System.out.println("while문 루프 ->" + i + "번째");
              if (aa.equals(monthString)) {
                  System.out.println("멈춤");
                  form_sheet.getRow(g+2).getCell(i).setCellValue(date);
                  g = g + 5;
              }

          }
          dd = form_sheet.getRow(g).getCell(0).getStringCellValue();
      } if (dd.equals("충청도")) {
          System.out.println("멈춤");
          for (int i = 0; i <= 13; i++) {
            String  aa = form_sheet.getRow(g+1).getCell(i).getStringCellValue();
              System.out.println("서울 ->" + i + "번째");
              if (aa.equals(monthString)) {
                  System.out.println("멈춤");
                  form_sheet.getRow(g+2).getCell(i).setCellValue(date);
                  g = g + 5;
              }

          }
          dd = form_sheet.getRow(g).getCell(0).getStringCellValue();}

      if (dd.equals("서울")) {
          System.out.println("멈춤");
          for (int i = 0; i <= 13; i++) {
              String  aa = form_sheet.getRow(g+1).getCell(i).getStringCellValue();
              System.out.println("서울 ->" + i + "번째");
              if (aa.equals(monthString)) {
                  System.out.println("멈춤");
                  form_sheet.getRow(g+2).getCell(i).setCellValue(date);
                  break;
              }

          }break;

      }



  }
/*
             //데이터
             // Row_가로 |  Cell_세로
             form_sheet.getRow(7).getCell(2).setCellValue(date);
             form_sheet.getRow(7).getCell(3).setCellValue(date);
             form_sheet.getRow(7).getCell(4).setCellValue(date);
*/

             form_sheet.getRow(7).getCell(11);
             //수식 안덮어쓰기 _ formula 셀 자동 계산
             evaluator.evaluateAll();


            filename = "result_excel.xlsx";
            response.setContentType("ms-vnd/excel;charset=EUC-KR");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);




            form_wb.write(response.getOutputStream());
            form_wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
