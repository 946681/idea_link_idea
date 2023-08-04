package service;

import test.Test;
import utils.ConfigDb;
import vo.Input;
import vo.Product;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InService {
    ConfigDb configDb = new ConfigDb();

    private Connection DB = configDb.connectionMysql();
    RecordService recordService = new RecordService();

    //显示所有入库信息
    public void displayIn() throws SQLException {
        //sql脚本编写
        String sql = "SELECT * FROM storage;";

        //预编译
        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //预编译
        ResultSet res = ptmt.executeQuery(sql);
        List<Input> inputList = new ArrayList<>();

        while (res.next()) {
            Input input1 = new Input();
            input1.setId(res.getInt("id"));
            input1.setPid(res.getInt("pid"));
            input1.setOid(res.getInt("oid"));
            input1.setNumber(res.getInt("number"));
            input1.setInTime(res.getString("intime"));
            System.out.println(input1);
            inputList.add(input1);
        }
    }

    //入库信息添加
    public int addIn(Input input) throws SQLException, ParseException {
        String sql1 = "SELECT * FROM product WHERE id=" + input.getPid();
        Statement stmt = this.DB.createStatement();
        ResultSet res1 = stmt.executeQuery(sql1);
        Product product = new Product();

        while (res1.next()) {
            product.setNum(res1.getInt("number"));
        }
        String sql2 = "UPDATE product SET number = ?  WHERE id = ?";
        PreparedStatement utmt = this.DB.prepareStatement(sql2);

        int temp = product.getNum() + input.getNumber();
        System.out.println(temp);
        utmt.setInt(1, temp);
        utmt.setInt(2, input.getPid());
        boolean res2 = utmt.execute();
        if (!res2) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }


        //sql脚本编写
        String sql = "INSERT INTO storage(pid,oid,number ,intime) VALUES (?,?,?,?);";

        //预编译
        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        ptmt.setInt(1, input.getPid());
        ptmt.setInt(2, input.getOid());
        ptmt.setInt(3, input.getNumber());
        ptmt.setString(4, input.getInTime());


        //执行
        boolean res = ptmt.execute();
        if (!res) {
            System.out.println("入库信息添加成功");
            String s = "用户" + AdminService.username + "在入库表中添加了新的信息：编号：" + input.getId() +
                    " 货物编号：" + input.getPid() + " 订单编号：" + input.getOid() + " 数量：" + input.getNumber()
                    + " 时间：" + input.getInTime();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("入库信息添加失败");
            return -1;
        }


    }


    //入库信息查找
    public int selectIn(Input input) throws SQLException {

        //sql脚本编写
        String sql = "select * from storage where id=" + input.getId();

        //数据库链接
        Statement stmt = this.DB.createStatement();

        //预编译
        ResultSet res = stmt.executeQuery(sql);
        List<Input> inputList = new ArrayList<>();

        while (res.next()) {
            Input input1 = new Input();
            input1.setId(res.getInt("id"));
            input1.setPid(res.getInt("pid"));
            input1.setOid(res.getInt("oid"));
            input1.setNumber(res.getInt("number"));
            input1.setInTime(res.getString("intime"));
            System.out.println(input1);
            inputList.add(input1);
        }
        return 0;
    }

    //入库信息删除
    public int deleteIn(Input input) throws SQLException {
        String sql1 = "SELECT * FROM storage WHERE id =" + input.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re = stmt.executeQuery(sql1);
        while (re.next()) {
            input.setPid(re.getInt("pid"));
            input.setNumber(re.getInt("number"));
            input.setInTime(re.getString("intime"));
            input.setOid(re.getInt("oid"));
        }
        //sql脚本编写
        String sql = "DELETE FROM storage WHERE id =" + input.getId();//根据名称

        //预编译
        PreparedStatement dtmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行


        boolean res = dtmt.execute();
        if (!res) {
            System.out.println("入库信息删除成功");
            String s = "用户" + AdminService.username + "在入库表中删除了信息：编号：" + input.getId() + " 货物编号：" + input.getPid() +
                    " 数量：" + input.getNumber() + " 时间：" + input.getInTime() + " 订单编号" + input.getOid();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("入库信息删除失败");
            return -1;
        }
    }

    //入库信息修改
    public int update(Input input, int i) throws SQLException, ParseException {
        String sql1 = "SELECT * FROM storage WHERE id =" + input.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re = stmt.executeQuery(sql1);
        while (re.next()) {
            input.setPid(re.getInt("pid"));
            input.setNumber(re.getInt("number"));
            input.setInTime(re.getString("intime"));
            input.setOid(re.getInt("oid"));
        }
        Scanner sc = new Scanner(System.in);
        boolean res = true;
        String sql = "";
        String s = "";
        switch (i) {
            case 1: {
                sql = "UPDATE storage SET id = ? WHERE id = ?";
                System.out.println("请输入新的编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = input.getId();
                utmt.setInt(1, a);
                utmt.setInt(2, input.getId());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将入库表中编号为" + input.getId() +
                        "的编号进行了修改：" + b + "修改为" + a;
                break;
            }
            case 2: {
                sql = "UPDATE storage SET oid = ? WHERE id = ?";
                System.out.println("请输入新的订单编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = input.getOid();
                utmt.setInt(1, a);
                utmt.setInt(2, input.getId());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将入库表中编号为" + input.getId() +
                        "的订单编号进行了修改：" + b + "修改为" + a;
                break;
            }
            case 3: {
                sql = "UPDATE storage SET pid = ? WHERE id = ?";
                System.out.println("请输入新的货物编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = input.getPid();
                utmt.setInt(1, a);
                utmt.setInt(2, input.getId());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将入库表中编号为" + input.getId() +
                        "的货物编号进行了修改：" + b + "修改为" + a;
                break;
            }
            case 4: {
                sql = "UPDATE storage SET number = ? WHERE id = ?";
                System.out.println("请输入新的入库数量：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = input.getNumber();
                utmt.setInt(1, a);
                utmt.setInt(2, input.getId());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将入库表中编号为" + input.getId() +
                        "的入库数量进行了修改：" + b + "修改为" + a;
                break;
            }
            case 5: {
                sql = "UPDATE storage SET intime = ? WHERE id = ?";
                System.out.println("请输入新的时间：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                String a = sc.next();
                String b = input.getInTime();
                utmt.setString(1, a);
                utmt.setInt(2, input.getId());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将入库表中编号为" + input.getId() +
                        "的入库时间进行了修改：" + b + "修改为" + a;
                break;
            }
            default:
                Test.start();

        }
        //预编译

        if (!res) {
            System.out.println("入库信息修改成功");
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("入库信息修改失败");
            return -1;
        }
    }
}
