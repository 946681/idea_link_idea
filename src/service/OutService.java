package service;

import test.Test;
import utils.ConfigDb;
import vo.Output;
import vo.Product;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutService {
    ConfigDb configDb = new ConfigDb();

    private Connection DB = configDb.connectionMysql();

    RecordService recordService = new RecordService();

    //显示所有出库信息
    public void displayOut() throws SQLException {
        //sql脚本编写
        String sql = "SELECT * FROM outbound;";

        //预编译
        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //预编译
        ResultSet res = ptmt.executeQuery(sql);
        List<Output> outputList = new ArrayList<>();

        while (res.next()) {
            Output output1 = new Output();
            output1.setId(res.getInt("id"));
            output1.setPid(res.getInt("pid"));
            output1.setNumber(res.getInt("number"));
            output1.setOutTime(res.getString("outtime"));
            System.out.println(output1);
            outputList.add(output1);
        }
    }

    //出库信息添加
    public int addOut(Output output) throws SQLException, ParseException {
        String sql2 = "SELECT * FROM product WHERE id=" + output.getPid();
        Statement stmt = this.DB.createStatement();
        ResultSet res1 = stmt.executeQuery(sql2);
        Product product = new Product();
        while (res1.next()) {
            product.setNum(res1.getInt("number"));
        }

        if (product.getNum() < output.getNumber()) {
            System.out.println("剩余库存量不足");
            Test.start();
        }

        String sql1 = "UPDATE product SET number = ? WHERE id = ?";
        PreparedStatement utmt = this.DB.prepareStatement(sql1);
        utmt.setInt(1, product.getNum() - output.getNumber());
        utmt.setInt(2, output.getPid());
        boolean res2=utmt.execute();
        //sql脚本编写
        String sql = "INSERT INTO outbound(pid,number ,outtime) VALUES (?,?,?);";

        //预编译
        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        ptmt.setInt(1, output.getPid());
        ptmt.setInt(2, output.getNumber());
        ptmt.setString(3, output.getOutTime());


        //执行
        boolean res = ptmt.execute();
        if (!res) {
            System.out.println("出库信息添加成功");
            String s = "用户" + AdminService.username + "在出库表中添加了新的信息：编号：" + output.getId() +
                    " 货物编号：" + output.getPid() + " 数量：" + output.getNumber() + " 时间：" + output.getOutTime();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("出库信息添加失败");
            return -1;
        }
    }


    //出库信息查找
    public int selectOut(Output output) throws SQLException {

        //sql脚本编写
        String sql = "select * from outbound where id=" + output.getId();

        //数据库链接
        Statement stmt = this.DB.createStatement();

        //预编译
        ResultSet res = stmt.executeQuery(sql);
        List<Output> outputList = new ArrayList<>();

        while (res.next()) {
            Output output1 = new Output();
            output1.setId(res.getInt("id"));
            output1.setPid(res.getInt("pid"));
            output1.setNumber(res.getInt("number"));
            output1.setOutTime(res.getString("outtime"));
            System.out.println(output1);
            outputList.add(output1);
        }
        return 0;
    }

    //出库信息删除
    public int deleteOut(Output output) throws SQLException {
        String sql1 = "SELECT * FROM outbound WHERE id =" + output.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re = stmt.executeQuery(sql1);
        while (re.next()) {
            output.setPid(re.getInt("pid"));
            output.setNumber(re.getInt("number"));
            output.setOutTime(re.getString("outtime"));
        }

        String sql = "DELETE FROM outbound WHERE id =" + output.getId();//根据名称

        PreparedStatement dtmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行


        boolean res = dtmt.execute();
        if (!res) {
            System.out.println("出库信息删除成功");
            String s = "用户" + AdminService.username + "在出库表中删除了信息：编号：" + output.getId() +
                    " 货物编号：" + output.getPid() + " 数量：" + output.getNumber() + " 时间：" + output.getOutTime();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("出库信息删除失败");
            return -1;
        }
    }

    //出库信息修改
    public int update(Output output, int i) throws SQLException, ParseException {
        String sql1 = "SELECT * FROM outbound WHERE id =" + output.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re = stmt.executeQuery(sql1);
        while (re.next()) {
            output.setPid(re.getInt("pid"));
            output.setNumber(re.getInt("number"));
            output.setOutTime(re.getString("outtime"));
        }

        Scanner sc = new Scanner(System.in);
        boolean res = true;
        String sql = "";
        String s = "";
        switch (i) {
            case 1: {
                sql = "UPDATE outbound SET id = ? WHERE id = ?";
                System.out.println("请输入新的编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = output.getId();
                utmt.setInt(1, sc.nextInt());
                utmt.setInt(2, output.getId());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将出库表中编号为" + output.getId() +
                        "的编号进行了修改：" + b + "修改为" + a;
                break;
            }
            case 2: {
                sql = "UPDATE outbound SET pid = ? WHERE id = ?";
                System.out.println("请输入新的货物编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = output.getPid();
                utmt.setInt(1, sc.nextInt());
                utmt.setInt(2, output.getId());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将出库表中编号为" + output.getId() +
                        "的货物编号进行了修改：" + b + "修改为" + a;
                break;
            }
            case 3: {
                sql = "UPDATE outbound SET number = ? WHERE id = ?";
                System.out.println("请输入新的出库数量：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = output.getPid();
                utmt.setInt(1, sc.nextInt());
                utmt.setInt(2, output.getNumber());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将出库表中编号为" + output.getId() +
                        "的出库数量进行了修改：" + b + "修改为" + a;
                break;
            }
            case 4: {
                sql = "UPDATE outbound SET outtime = ? WHERE id = ?";
                System.out.println("请输入新的时间：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                String a = sc.next();
                String b = output.getOutTime();
                utmt.setString(1, sc.next());
                utmt.setInt(2, output.getId());
                res = utmt.execute();
                s = "用户" + AdminService.username + "将出库表中编号为" + output.getId() +
                        "的出库时间进行了修改：" + b + "修改为" + a;
                break;
            }
            default:
                Test.start();

        }
        //预编译

        if (!res) {
            System.out.println("出库信息修改成功");
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("出库信息修改失败");
            return -1;
        }
    }
}
