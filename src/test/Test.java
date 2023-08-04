package test;

import menu.Menu;
import service.AdminService;
import service.RecordService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws SQLException, ParseException {
        Method.login();
    }
    public static void start() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Menu.mainMenu();
        System.out.println("请输入要进行的操作：");
        int i = sc.nextInt();
        switch (i){
            case 1:{
                Method.product();
                break;
            }
            case 2:{
                Method.order();
                break;
            }
            case 3:{
                Method.in();
                break;
            }
            case 4:{
                Method.out();
                break;
            }
            case 5:{
                Method.expense();
                break;
            }
            case 6:{
                Method.supplier();
                break;
            }
            case 7:{
                AdminService adminService = new AdminService();
                adminService.judgeAdmin();
                break;
            }
            case 8:{
                RecordService recordService = new RecordService();
                recordService.displayRecord();
            }
            case 9:{
                String s = "用户"+AdminService.username+"退出";
                RecordService recordService = new RecordService();
                recordService.insertRecord(s);
                System.exit(0);
            }
        }
    }
}
