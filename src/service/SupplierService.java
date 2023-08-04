package service;

import test.Test;
import utils.ConfigDb;
import vo.Supplier;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupplierService {
    ConfigDb configDb = new ConfigDb();

    private Connection DB = configDb.connectionMysql();

    RecordService recordService = new RecordService();

    //显示所有供应商信息
    public void displayProduct() throws SQLException {
        //sql脚本编写
        String sql = "SELECT * FROM supplier;";

        //预编译
        PreparedStatement stmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //预编译
        ResultSet res = stmt.executeQuery(sql);
        List<Supplier> supplierList = new ArrayList<>();

        while (res.next()) {
            Supplier supplier1 = new Supplier();
            supplier1.setId(res.getInt("id"));
            supplier1.setName(res.getString("name"));
            supplier1.setAddress(res.getString("address"));
            supplier1.setTelephone(res.getString("telephone"));
            supplier1.setSort(res.getString("sort"));
            System.out.println(supplier1.toString());
            supplierList.add(supplier1);
        }
    }

    //供应商信息添加
    public int addSupplier(Supplier supplier) throws SQLException {

        //sql脚本编写
        String sql = "INSERT INTO supplier(name,address,telephone,sort) VALUES (?,?,?,?);";

        //预编译
        PreparedStatement stmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        stmt.setString(1, supplier.getName());
        stmt.setString(2, supplier.getAddress());
        stmt.setString(3, supplier.getTelephone());
        stmt.setString(4, supplier.getSort());


        //执行
        boolean res = stmt.execute();
        if (!res) {
            System.out.println("供应商信息添加成功");
            String s = "用户"+AdminService.username+"在供应商表中添加了信息：名称："+supplier.getName()+" 电话："
                    + supplier.getTelephone()+" 地址："+supplier.getAddress()+" 分类："+supplier.getSort();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("供应商信息添加失败");
            return -1;
        }
    }

    //供应商信息查找
    public int selectSupplier(Supplier supplier) throws SQLException {

        String sql = "select * from supplier where id= ?";
        //采用PrepareStatement进行预处理
        PreparedStatement preparedStatement = this.DB.prepareStatement(sql);
        preparedStatement.setInt(1,supplier.getId());

        ResultSet res = preparedStatement.executeQuery(sql);

        System.out.println("编号\t名称\t地址\t电话\t分类");
        while (res.next()) {
            int id = res.getInt(1);
            String name = res.getString(2);
            String address = res.getString(3);
            String telephone = res.getString(4);
            String sort = res.getString(5);
            System.out.println(id+"\t"+name+"\t"+address+"\t"+telephone+"\t"+sort);
        }
        return 0;
    }

    //供应商信息删除
    public int deleteSupplier(Supplier supplier) throws SQLException {
        String sql1 = "SELECT * FROM supplier WHERE id ="+supplier.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re= stmt.executeQuery(sql1);
        while (re.next()) {
            supplier.setName(re.getString("name"));
            supplier.setSort(re.getString("sort"));
            supplier.setTelephone(re.getString("telephone"));
            supplier.setAddress(re.getString("address"));
        }
        //sql脚本编写
        String sql = "DELETE FROM supplier WHERE id ="+supplier.getId();//根据名称

        //预编译
        PreparedStatement stmt1 = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行


        boolean res = stmt1.execute();
        if (!res) {
            System.out.println("供应商信息删除成功");
            String s = "用户"+AdminService.username+"在供应商表中删除了信息：编号："+supplier.getId()+
                    " 货物名称："+supplier.getName()+" 分类："+supplier.getSort()+" 电话："+supplier.getTelephone()
                    +" 地址："+supplier.getAddress();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("供应商信息删除失败");
            return -1;
        }
    }

    //供应商信息修改
    public int update(Supplier supplier,int i) throws SQLException, ParseException {
        String sql1 = "SELECT * FROM supplier WHERE id ="+supplier.getId();
        Statement stmt1 = this.DB.createStatement();
        ResultSet re= stmt1.executeQuery(sql1);
        while (re.next()) {
            supplier.setName(re.getString("name"));
            supplier.setSort(re.getString("sort"));
            supplier.setTelephone(re.getString("telephone"));
            supplier.setAddress(re.getString("address"));
        }
        String s ="";
        Scanner sc = new Scanner(System.in);
        String sql="";
        boolean res=true;
        switch (i){
            case 1:{
                sql = "UPDATE supplier SET id = ? WHERE id = ?";
                System.out.println("请输入新的编号：");
                PreparedStatement stmt = this.DB.prepareStatement(sql);
                int a = sc.nextInt();
                int b = supplier.getId();
                stmt.setInt(1,a);
                stmt.setInt(2,supplier.getId());
                res = stmt.execute();
                s = "用户"+AdminService.username+"将供应商表中编号为"+supplier.getId()+
                        "的供应商编号进行了修改："+b+"修改为"+a;
                break;
            }
            case 2:{
                sql = "UPDATE supplier SET name = ? WHERE id = ?";
                System.out.println("请输入新的供应商名称：");
                PreparedStatement stmt = this.DB.prepareStatement(sql);
                String a = sc.next();
                String b = supplier.getName();
                stmt.setString(1,a);
                stmt.setInt(2,supplier.getId());
                res = stmt.execute();
                s = "用户"+AdminService.username+"将供应商表中编号为"+supplier.getId()+
                        "的供应商名称进行了修改："+b+"修改为"+a;
                break;
            }
            case 3:{
                sql = "UPDATE supplier SET address = ? WHERE id = ?";
                System.out.println("请输入新的地址：");
                PreparedStatement stmt = this.DB.prepareStatement(sql);
                String a = sc.next();
                String b = supplier.getAddress();
                stmt.setString(1,a);
                stmt.setInt(2,supplier.getId());
                res = stmt.execute();
                s = "用户"+AdminService.username+"将供应商表中编号为"+supplier.getId()+
                        "的供应商地址进行了修改："+b+"修改为"+a;
                break;
            }
            case 4:{
                sql = "UPDATE supplier SET telephone = ? WHERE id = ?";
                System.out.println("请输入新的电话号码：");
                PreparedStatement stmt = this.DB.prepareStatement(sql);
                String a = sc.next();
                String b = supplier.getTelephone();
                stmt.setString(1,a);
                stmt.setInt(2,supplier.getId());
                res = stmt.execute();
                s = "用户"+AdminService.username+"将供应商表中编号为"+supplier.getId()+
                        "的供应商电话进行了修改："+b+"修改为"+a;
                break;
            }
            case 5:{
                sql = "UPDATE supplier SET sort = ? WHERE id = ?";
                System.out.println("请输入新的分类：");
                PreparedStatement stmt = this.DB.prepareStatement(sql);
                String a = sc.next();
                String b = supplier.getSort();
                stmt.setString(1,sc.next());
                stmt.setInt(2,supplier.getId());
                res = stmt.execute();
                s = "用户"+AdminService.username+"将供应商表中编号为"+supplier.getId()+
                        "的供应商分类进行了修改："+b+"修改为"+a;
                break;
            }
            default:
                Test.start();
        }
        if (!res) {
            System.out.println("供应商信息修改成功");
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("供应商信息修改失败");
            return -1;
        }
    }


}
