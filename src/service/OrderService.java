package service;

import test.Test;
import utils.ConfigDb;
import vo.PurchaseOrder;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    ConfigDb configDb = new ConfigDb();

    private Connection DB = configDb.connectionMysql();
    RecordService recordService = new RecordService();
    //显示所有订单信息
    public void displayPurchaseOrder() throws SQLException {
        //sql脚本编写
        String sql = "SELECT * FROM purchaseorder;";

        //预编译
        PreparedStatement otmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //预编译
        ResultSet res = otmt.executeQuery(sql);
        List<PurchaseOrder> PurchaseOrderList = new ArrayList<>();

        while (res.next()) {
            PurchaseOrder purchaseOrder1 = new PurchaseOrder();
            purchaseOrder1.setId(res.getInt("id"));
            purchaseOrder1.setSid(res.getInt("sid"));
            purchaseOrder1.setSum(res.getDouble("sum"));
            purchaseOrder1.setTime(res.getString("time"));
            System.out.println(purchaseOrder1);
            PurchaseOrderList.add(purchaseOrder1);
        }
    }

    //订单信息添加
    public int addPurchaseOrder(PurchaseOrder purchaseOrder) throws SQLException {

        //sql脚本编写
        String sql = "INSERT INTO purchaseorder(sid,sum,time) VALUES (?,?,?);";

        //预编译
        PreparedStatement otmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        otmt.setInt(1, purchaseOrder.getSid());
        otmt.setDouble(2, purchaseOrder.getSum());
        otmt.setString(3, purchaseOrder.getTime());


        //执行
        boolean res = otmt.execute();
        if (!res) {
            System.out.println("订单信息添加成功");
            return 1;
        } else {
            System.out.println("订单信息添加失败");
            return -1;
        }
    }

    //订单信息查找,根据产品编号和供应商编号查找
    public int selectPurchaseOrder(PurchaseOrder purchaseOrder) throws SQLException {

        //sql脚本编写
        String sql = "select * from purchaseorder where id=" + purchaseOrder.getId();

        //数据库链接
        Statement stmt = this.DB.createStatement();

        RecordService recordService = new RecordService();
        //预编译
        ResultSet res = stmt.executeQuery(sql);
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();

        while (res.next()) {
            PurchaseOrder purchaseOrder1 = new PurchaseOrder();
            purchaseOrder1.setId(res.getInt("id"));
            purchaseOrder1.setSid(res.getInt("sid"));
            purchaseOrder1.setSum(res.getDouble("sum"));
            purchaseOrder1.setTime(res.getString("time"));
            System.out.println(purchaseOrder1);
            purchaseOrderList.add(purchaseOrder1);
        }
        return 0;
    }

    //订单信息删除
    public int deletePurchaseOrder(PurchaseOrder purchaseOrder) throws SQLException {

        String sql1 = "SELECT * FROM purchaseorder WHERE id ="+purchaseOrder.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re= stmt.executeQuery(sql1);
        while (re.next()) {
            purchaseOrder.setSid(re.getInt("sid"));
            purchaseOrder.setSum(re.getDouble("sum"));
            purchaseOrder.setTime(re.getString("time"));
        }
        //sql脚本编写
        String sql = "DELETE FROM purchaseOrder WHERE id=" + purchaseOrder.getId();

        //预编译
        PreparedStatement dtmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行


        boolean res = dtmt.execute();
        if (!res) {
            System.out.println("订单信息删除成功");
            String s = "用户"+AdminService.username+"在订单表中删除了信息：编号："+purchaseOrder.getId()+
                    " 供应商编号："+purchaseOrder.getSid()+" 总金额："+purchaseOrder.getSum()+" 时间："+purchaseOrder.getTime();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("订单信息删除失败");
            return -1;
        }
    }

    //订单信息修改
    public int update(PurchaseOrder purchaseOrder,int i) throws SQLException, ParseException {
        String sql1 = "SELECT * FROM purchaseorder WHERE id ="+purchaseOrder.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re= stmt.executeQuery(sql1);
        while (re.next()) {
            purchaseOrder.setSid(re.getInt("sid"));
            purchaseOrder.setSum(re.getDouble("sum"));
            purchaseOrder.setTime(re.getString("time"));
        }
        Scanner sc = new Scanner(System.in);
        String sql="";
        boolean res=true;
        String s = "";
        switch (i){
            case 1:{
                sql = "UPDATE purchaseorder SET sid = ? WHERE id = ? ";
                System.out.println("请输入新的供应商编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = purchaseOrder.getSid();
                utmt.setInt(1,a);
                utmt.setInt(2,purchaseOrder.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将订单表中编号为"+purchaseOrder.getId()+
                        "的供应商编号进行了修改："+b+"修改为"+a;
                break;
            }
            case 2:{
                sql = "UPDATE purchaseorder SET sum = ? WHERE id = ? ";
                System.out.println("请输入新的总金额：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                double a = sc.nextDouble();
                double b = purchaseOrder.getSum();
                utmt.setDouble(1,a);
                utmt.setInt(2,purchaseOrder.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将订单表中编号为"+purchaseOrder.getId()+
                        "的总金额进行了修改："+b+"修改为"+a;
                break;
            }
            case 3:{
                sql = "UPDATE purchaseorder SET time = ? WHERE id = ? ";
                System.out.println("请输入新的时间：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                String a = sc.next();
                String b = purchaseOrder.getTime();
                utmt.setString(1, a);
                utmt.setInt(2,purchaseOrder.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将订单表中编号为"+purchaseOrder.getId()+
                        "的时间进行了修改："+b+"修改为"+a;
                break;
            }
            case 4:{
                sql = "UPDATE purchaseorder SET id = ? WHERE id = ? ";
                System.out.println("请输入新的订单编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行
                int a = sc.nextInt();
                int b = purchaseOrder.getId();
                utmt.setInt(1, a);
                utmt.setInt(2,purchaseOrder.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将订单表中编号为"+purchaseOrder.getId()+
                        "的订单编号进行了修改："+b+"修改为"+a;
                break;
            }
            default:
                Test.start();
        }
        if (!res) {
            System.out.println("订单信息修改成功");
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("订单信息修改失败");
            return -1;
        }
    }
}
