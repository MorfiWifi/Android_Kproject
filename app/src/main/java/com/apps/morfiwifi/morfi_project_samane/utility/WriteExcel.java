package com.apps.morfiwifi.morfi_project_samane.utility; /**
 * Created by Wifimorfi on 6/23/2018.
 */
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Property;
import android.widget.Switch;
import android.widget.Toast;

import com.apps.morfiwifi.morfi_project_samane.models.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import static android.support.v4.content.ContextCompat.checkSelfPermission;


public class WriteExcel {

    private static WritableCellFormat timesBoldUnderline;
    private static WritableCellFormat times;
    private static int REQUEST_CODE = 103; // for Storage permission
    private String inputFile;
    private static String[] property = {"نام" , "نام خانوادگی" , "نام کاربری" , "رمز" , "تاریخ تولد" , "کد ملی" , "نام پدر"};

    public static  ArrayList<String> Users_prop (List<User> userList){
        ArrayList<String> props = new ArrayList<String>();
        for (User user: userList) {
            for (int i = 0; i < property.length; i++) {
                switch (i){
                    case 0:
                        props.add(user.getFName());
                        break;
                    case 1:
                        props.add(user.getLName());
                        break;
                    case 2:
                        props.add(user.getUserName());
                        break;
                    case 3:
                        props.add(user.getPass());
                        break;
                    case 4:
                        props.add("NON_YET");
                        break;
                    case 5:
                        props.add(user.getKaet_meli());
                        break;
                    case 6:
                        props.add("NON_YET");
                        break;
                        default:
                            props.add("UNKNOWN");
                }
            }
        }

        return  props;
    }

    public static void  Export_Active_Queue (List<User> userList , AppCompatActivity context ){
        try {
            boolean have_permission = false;


            if (userList == null){
                Toast.makeText(context, "خالی", Toast.LENGTH_SHORT).show();
            }

            if (checkSelfPermission( context , android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("Storage Write","Permission is granted");
                //File write logic here
                have_permission = true;
                //return true;
            }

            if (have_permission){

                File file = new File("//sdcard//Download//" , "Excel_export.xls"); // Auto Gen
                WorkbookSettings wbSettings = new WorkbookSettings();

                wbSettings.setLocale(new Locale("en", "EN"));

                WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
                workbook.createSheet("Report", 0);
                WritableSheet excelSheet = workbook.getSheet(0);
                // ************************************************

                // Lets create a times font
                WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
                // Define the cell format
                times = new WritableCellFormat(times10pt);
                // Lets automatically wrap the cells
                times.setWrap(true);

                // create create a bold font with unterlines
                WritableFont times10ptBoldUnderline = new WritableFont(
                        WritableFont.TIMES, 10, WritableFont.BOLD, false,
                        UnderlineStyle.SINGLE);
                timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
                // Lets automatically wrap the cells
                timesBoldUnderline.setWrap(true);

                CellView cv = new CellView();
                cv.setFormat(times);
                cv.setFormat(timesBoldUnderline);
//        cv.setAutosize(true);

                // Write a few headers
                addCaption(excelSheet, 0, 0, "Property");
                addCaption(excelSheet, 1, 0, "Value");
                // ************************************************


                int index = 1; // start index
                ArrayList<String> data = Users_prop(userList);

                for (User user :userList) {
                    for (int i = 0; i < property.length; i++) {
                        // First column
                        addLabel(excelSheet, 0, index, property[i]);
                        // Second column
                        addLabel(excelSheet, 1, index, data.get(index-1));
                        index ++;
                    }
                }



                workbook.write();
                workbook.close();

                Toast.makeText(context, file.getPath() + file.getName(), Toast.LENGTH_SHORT).show();

            }else {
                // Requesting Permission

                Toast.makeText(context, "پس از دادن دسترسی دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);



            }

        }catch (Exception e){
            Toast.makeText(context, "خطا در خروجی گرفتن", Toast.LENGTH_SHORT).show();
            Init.Terminal(e.getMessage());
        }




    }
    public static void  Export_User_Queue (List<User> userList , AppCompatActivity context ){
        try {
            boolean have_permission = false;


            if (userList == null){
                Toast.makeText(context, "خالی", Toast.LENGTH_SHORT).show();
            }

            if (checkSelfPermission( context , android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("Storage Write","Permission is granted");
                //File write logic here
                have_permission = true;
                //return true;
            }

            if (have_permission){

                File file = new File("//sdcard//Download//" , "Excel_export.xls"); // Auto Gen
                WorkbookSettings wbSettings = new WorkbookSettings();

                wbSettings.setLocale(new Locale("en", "EN"));

                WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
                workbook.createSheet("Report", 0);
                WritableSheet excelSheet = workbook.getSheet(0);
                // ************************************************

                // Lets create a times font
                WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
                // Define the cell format
                times = new WritableCellFormat(times10pt);
                // Lets automatically wrap the cells
                times.setWrap(true);

                // create create a bold font with unterlines
                WritableFont times10ptBoldUnderline = new WritableFont(
                        WritableFont.TIMES, 10, WritableFont.BOLD, false,
                        UnderlineStyle.SINGLE);
                timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
                // Lets automatically wrap the cells
                timesBoldUnderline.setWrap(true);

                CellView cv = new CellView();
                cv.setFormat(times);
                cv.setFormat(timesBoldUnderline);
//        cv.setAutosize(true);

                // Write a few headers
                addCaption(excelSheet, 0, 0, "Property");
                addCaption(excelSheet, 1, 0, "Value");
                // ************************************************


                int index = 1; // start index
                ArrayList<String> data = Users_prop(userList);

                for (User user :userList) {
                    for (int i = 0; i < property.length; i++) {
                        // First column
                        addLabel(excelSheet, 0, index, property[i]);
                        // Second column
                        addLabel(excelSheet, 1, index, data.get(index-1));
                        index ++;
                    }
                }



                workbook.write();
                workbook.close();

                Toast.makeText(context, file.getPath() + file.getName(), Toast.LENGTH_SHORT).show();

            }else {
                // Requesting Permission

                Toast.makeText(context, "پس از دادن دسترسی دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                


            }

        }catch (Exception e){
            Toast.makeText(context, "خطا در خروجی گرفتن", Toast.LENGTH_SHORT).show();
            Init.Terminal(e.getMessage());
        }




    }

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void write() throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet);

        workbook.write();
        workbook.close();
    }

    private void createLabel(WritableSheet sheet)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
//        cv.setAutosize(true);

        // Write a few headers
        addCaption(sheet, 0, 0, "Header 1");
        addCaption(sheet, 1, 0, "This is another header");


    }

    private void createContent(WritableSheet sheet) throws WriteException,
            RowsExceededException {
        // Write a few number
        for (int i = 1; i < 10; i++) {
            // First column
            addNumber(sheet, 0, i, i + 10);
            // Second column
            addNumber(sheet, 1, i, i * i);
        }
        // Lets calculate the sum of it
        StringBuffer buf = new StringBuffer();
        buf.append("SUM(A2:A10)");
        Formula f = new Formula(0, 10, buf.toString());
        sheet.addCell(f);
        buf = new StringBuffer();
        buf.append("SUM(B2:B10)");
        f = new Formula(1, 10, buf.toString());
        sheet.addCell(f);

        // now a bit of text
        for (int i = 12; i < 20; i++) {
            // First column
            addLabel(sheet, 0, i, "Boring text " + i);
            // Second column
            addLabel(sheet, 1, i, "Another text");
        }
    }

    private static void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private static void addNumber(WritableSheet sheet, int column, int row,
                           Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private static void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);

    }

    public static void main(String[] args) throws WriteException, IOException {
        WriteExcel test = new WriteExcel();
        test.setOutputFile("c:/Temp/lars.xls");
        test.write();
        System.out
                .println("Please check the result file under c:/temp/lars.xls ");
    }
}