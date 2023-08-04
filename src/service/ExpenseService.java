package service;

import test.Test;
import utils.ConfigDb;
import vo.Expense;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseService {
    ConfigDb configDb = new ConfigDb();

    private Connection DB = configDb.connectionMysql();

    RecordService recordService = new RecordService();

    //显示所有费用信息
    public void displayExpense() throws SQLException {
        //sql脚本编写
        String sql = "SELECT * FROM expense;";

        //预编译
        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //预编译
        ResultSet res = ptmt.executeQuery(sql);
        List<Expense> expenseList = new ArrayList<>();

        while (res.next()) {
            Expense expense1 = new Expense();
            expense1.setId(res.getInt("id"));
            expense1.setOid(res.getInt("oid"));
            expense1.setSort(res.getString("sort"));
            expense1.setSum(res.getDouble("sum"));
            expense1.setTime(res.getString("time"));
            System.out.println(expense1);
            expenseList.add(expense1);
        }
    }

    //费用信息添加
    public int addExpense(Expense expense) throws SQLException {

        //sql脚本编写
        String sql = "INSERT INTO expense(oid,sort,sum ,time) VALUES (?,?,?,?);";

        //预编译
        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        ptmt.setInt(1,expense.getOid());
        ptmt.setString(2, expense.getSort());
        ptmt.setDouble(3, expense.getSum());
        ptmt.setString(4,expense.getTime());


        //执行
        boolean res = ptmt.execute();
        if (!res) {
            System.out.println("费用信息添加成功");
            String s = "用户"+AdminService.username+"在订单表中添加了新的信息：编号："+expense.getId()+
                    " 订单编号："+expense.getOid()+" 分类："+expense.getSort()+" 时间："+expense.getTime();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("费用信息添加失败");
            return -1;
        }
    }

    //费用信息查找
    public int selectExpense(Expense expense) throws SQLException {

        //sql脚本编写
        String sql = "select * from expense where id=" + expense.getId() ;

        //数据库链接
        Statement stmt = this.DB.createStatement();

        //预编译
        ResultSet res = stmt.executeQuery(sql);
        List<Expense> expenseList = new ArrayList<>();

        while (res.next()) {
            Expense expense1 = new Expense();
            expense1.setId(res.getInt("id"));
            expense1.setOid(res.getInt("oid"));
            expense1.setSort(res.getString("sort"));
            expense1.setSum(res.getDouble("sum"));
            expense1.setTime(res.getString("time"));
            System.out.println(expense1);
            expenseList.add(expense1);
        }
        return 0;
    }

    //费用信息删除
    public int deleteExpense(Expense expense) throws SQLException {

        String sql1 = "select * from expense where id=" + expense.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re= stmt.executeQuery(sql1);
        while (re.next()) {
            expense.setOid(re.getInt("oid"));
            expense.setTime(re.getString("time"));
            expense.setSum(re.getDouble("sum"));
            expense.setSort(re.getString("sort"));
        }

        //sql脚本编写
        String sql = "DELETE FROM expense WHERE id ="+expense.getId();//根据名称

        //预编译
        PreparedStatement dtmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行


        boolean res = dtmt.execute();
        if (!res) {
            System.out.println("费用信息删除成功");
            String s = "用户"+AdminService.username+"在订单表中删除了信息：编号："+expense.getId()+
                    " 订单编号："+expense.getOid()+" 分类："+expense.getTime()+" 时间："+expense.getTime();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("费用信息删除失败");
            return -1;
        }
    }

    //费用信息修改
    public int update(Expense expense,int i) throws SQLException, ParseException {

        String sql1 = "select * from expense where id=" + expense.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re= stmt.executeQuery(sql1);
        while (re.next()) {
            expense.setOid(re.getInt("oid"));
            expense.setTime(re.getString("time"));
            expense.setSum(re.getDouble("sum"));
            expense.setSort(re.getString("sort"));
        }
        Scanner sc = new Scanner(System.in);
        boolean res=true;
        String sql="";
        String record="";
        switch (i){
            case 1:{
                sql = "UPDATE expense SET id = ? WHERE id = ?";
                System.out.println("请输入新的费用编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = expense.getId();
                utmt.setInt(1,a);
                utmt.setInt(2,b);
                res = utmt.execute();
                record="用户"+AdminService.username+"对编号为"+b+"的费用信息进行了修改：将编号"+b+"修改为"+a;
                break;
            }
            case 2:{
                sql = "UPDATE expense SET oid = ? WHERE id = ?";
                System.out.println("请输入新的订单编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                utmt.setInt(1,a);
                utmt.setInt(2,expense.getId());
                res = utmt.execute();
                record="用户"+AdminService.username+"对编号为"+expense.getId()+"的费用信息进行了修改：将订单编号"+expense.getOid()+"修改为"+a;
                break;
            }
            case 3:{
                sql = "UPDATE expense SET sort = ? WHERE id = ?";
                System.out.println("请输入新的费用分类：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                String a = sc.next();
                utmt.setString(1,a);
                utmt.setInt(2,expense.getId());
                res = utmt.execute();
                record="用户"+AdminService.username+"对编号为"+expense.getId()+"的费用信息进行了修改：将分类"+expense.getSort()+"修改为"+a;
                break;
            }
            case 4:{
                sql = "UPDATE expense SET sum = ? WHERE id = ?";
                System.out.println("请输入新的金额：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                double a = sc.nextDouble();
                utmt.setDouble(1,a);
                utmt.setInt(2,expense.getId());
                res = utmt.execute();
                record="用户"+AdminService.username+"对编号为"+expense.getId()+"的费用信息进行了修改：将金额"+expense.getSum()+"修改为"+a;
                break;
            }
            case 5:{
                sql = "UPDATE expense SET time = ? WHERE id = ?";
                System.out.println("请输入新的时间：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                String a = sc.next();
                utmt.setString(1,a);
                utmt.setInt(2,expense.getId());
                res = utmt.execute();
                record="用户"+AdminService.username+"对编号为"+expense.getId()+"的费用信息进行了修改：将时间"+expense.getTime()+"修改为"+a;
                break;
            }
            default:
                Test.start();

        }
        //预编译

        if (!res) {
            System.out.println("费用信息修改成功");
            recordService.insertRecord(record);
            return 1;
        } else {
            System.out.println("费用信息修改失败");
            return -1;
        }
    }
}
