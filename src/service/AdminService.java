package service;

import test.Method;
import test.Test;
import utils.ConfigDb;
import vo.Admin;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    ConfigDb configDb = new ConfigDb();

    private Connection DB = configDb.connectionMysql();

    RecordService recordService = new RecordService();

    public  int addAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO admin(username,password ) VALUES (?,?);";

        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        ptmt.setString(1, admin.getUsername());
        ptmt.setString(2, admin.getPassword());

        //执行
        boolean res = ptmt.execute();
        if (!res) {
            System.out.println("用户信息添加成功");
            String s = "用户"+AdminService.username+"添加了新用户";
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("用户信息添加失败");
            return -1;
        }

    }
    public void displayAdmin() throws SQLException {
        String sql = "SELECT * FROM admin;";
        PreparedStatement ptmt = this.DB.prepareStatement(sql);

        ResultSet res = ptmt.executeQuery(sql);
        List<Admin> adminList = new ArrayList<>();

        while (res.next()) {
            Admin admin1 = new Admin();
            admin1.setStatus(res.getInt("status"));
            admin1.setId(res.getInt("id"));
            admin1.setUsername(res.getString("username"));
            admin1.setPassword(res.getString("password"));
            System.out.println(admin1);
            adminList.add(admin1);
        }
    }
    //查找
    public static String password="";
    public static String username="";
    public void selectAdmin() throws ParseException, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入用户名：");
        AdminService.username=sc.next();
        System.out.print("请输入密码：");
        AdminService.password=sc.next();

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?;";
        PreparedStatement ptmt = this.DB.prepareStatement(sql);
        ptmt.setString(1,username);
        ptmt.setString(2,password);

        ResultSet res = ptmt.executeQuery();

        if(res.next()){
            System.out.println("登录成功");
            String s = "用户"+AdminService.username+"登录";
            recordService.insertRecord(s);
            Test.start();
        }else {
            System.out.println("用户名或密码错误，请重新输入");
            this.selectAdmin();
        }
    }
    //判断权限
    public void judgeAdmin() throws SQLException, ParseException {
        String sql = "SELECT * FROM admin WHERE username = ? AND status = ?";
        PreparedStatement ptmt = this.DB.prepareStatement(sql);
        ptmt.setString(1,AdminService.username);
        ptmt.setInt(2,1);
        ResultSet res = ptmt.executeQuery();
        if(res.next()){
            Method.admin();
        }else{
            System.out.println("没有此权限");
            Test.start();
        }


    }
}
