package service;

import menu.Menu;
import test.Method;
import test.Test;
import utils.ConfigDb;
import vo.Product;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    ConfigDb configDb = new ConfigDb();

    private Connection DB = configDb.connectionMysql();

    RecordService recordService = new RecordService();
    //显示所有货物信息
    public void displayProduct() throws SQLException {
        //sql脚本编写
        String sql = "SELECT * FROM product;";

        //预编译
        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //预编译
        ResultSet res = ptmt.executeQuery(sql);
        List<Product> productListList = new ArrayList<>();

        while (res.next()) {
            Product product1 = new Product();
            product1.setId(res.getInt("id"));
            product1.setName(res.getString("name"));
            product1.setPrice(res.getDouble("price"));
            product1.setNum(res.getInt("number"));
            product1.setSort(res.getString("sort"));
            product1.setWarehouse(res.getInt("warehouse"));
            System.out.println(product1);
            productListList.add(product1);
        }
    }

    //货物信息添加
    public int addProduct(Product product) throws SQLException {

        //sql脚本编写
        String sql = "INSERT INTO product(name,sort,warehouse,number,price) VALUES (?,?,?,?,?);";

        //预编译
        PreparedStatement ptmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        ptmt.setString(1, product.getName());
        ptmt.setString(2, product.getSort());
        ptmt.setInt(3, product.getWarehouse());
        ptmt.setInt(4, product.getNum());
        ptmt.setDouble(5, product.getPrice());


        //执行
        boolean res = ptmt.execute();
        if (!res) {
            System.out.println("货物信息添加成功");
            String s = "用户"+AdminService.username+"在货物表中添加了新的信息：编号："+product.getId()+
                    " 货物名称："+product.getName()+" 分类："+product.getSort()+" 价格："+product.getPrice()
                    +" 仓库号："+product.getWarehouse()+" 数量："+product.getNum();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("货物信息添加失败");
            return -1;
        }
    }

    //货物信息查找
    public int selectProduct(Product product) throws SQLException {

        //sql脚本编写
        String sql = "select * from product where name=" + product.getName();

        //数据库链接
        Statement stmt = this.DB.createStatement();

        //预编译
        //执行给定的sql语句，该语句返回单个ResultSet对象
        ResultSet res = stmt.executeQuery(sql);//开始时光标在第一行之前，通过循环来遍历表的内容

        System.out.println("编号\t名称\t分类\t仓库号\t数量\t价格");
        while (res.next()) {
            int id = res.getInt(1);//得到该行第1列的内容
            String name = res.getString(2);//得到改行第2列的内容
            String sort = res.getString(3);
            int warehouse = res.getInt(4);
            int number = res.getInt(5);
            double price = res.getDouble(6);
            System.out.println(id+"\t"+name+"\t"+sort+"\t"+warehouse+"\t"+number+"\t"+price);
        }
        res.close();
        stmt.close();
        this.DB.close();
        return 0;
    }

    //货物信息删除
    public int deleteProduct(Product product) throws SQLException {

        String sql1 = "SELECT * FROM product WHERE id ="+product.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re= stmt.executeQuery(sql1);
        while (re.next()) {
            product.setName(re.getString("name"));
            product.setSort(re.getString("sort"));
            product.setPrice(re.getDouble("price"));
            product.setWarehouse(re.getInt("warehouse"));
            product.setNum(re.getInt("number"));
        }
        //sql脚本编写
        String sql = "DELETE FROM product WHERE id =" + product.getId();

        //预编译
        PreparedStatement dtmt = this.DB.prepareStatement(sql); //预编译SQL，减少sql执行


        boolean res = dtmt.execute();
        if (!res) {
            System.out.println("货物信息删除成功");
            String s = "用户"+AdminService.username+"在货物表中删除了信息：编号："+product.getId()+
                    " 货物名称："+product.getName()+" 分类："+product.getSort()+" 价格："+product.getPrice()
                    +" 仓库号："+product.getWarehouse()+" 数量："+product.getNum();
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("货物信息删除失败");
            return -1;
        }
    }

    //货物信息修改
    public int update(Product product, int i) throws SQLException, ParseException {
        String sql1 = "SELECT * FROM product WHERE id ="+product.getId();
        Statement stmt = this.DB.createStatement();
        ResultSet re= stmt.executeQuery(sql1);
        while (re.next()) {
            product.setName(re.getString("name"));
            product.setSort(re.getString("sort"));
            product.setPrice(re.getDouble("price"));
            product.setWarehouse(re.getInt("warehouse"));
            product.setNum(re.getInt("number"));
        }
        Scanner sc = new Scanner(System.in);
        String sql = "";
        String s="";
        boolean res = true;
        switch (i) {
            case 1: {
                sql = "UPDATE product SET name = ? WHERE id = ?";
                System.out.println("请输入新的货物名称：");
                String a=sc.next();
                String b = product.getName();
                PreparedStatement utmt = this.DB.prepareStatement(sql);
                utmt.setString(1, a);
                utmt.setInt(2, product.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将货物表中编号为"+product.getId()+
                        "的货物名称进行了修改："+b+"修改为"+a;
                break;
            }
            case 2: {
                sql = "UPDATE product SET sort = ? WHERE id = ?";
                System.out.println("请输入新的分类：");
                PreparedStatement utmt = this.DB.prepareStatement(sql);
                String a = sc.next();
                String b = product.getSort();
                utmt.setString(1, a);
                utmt.setInt(2, product.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将货物表中编号为"+product.getId()+
                        "的货物分类进行了修改："+b+"修改为"+a;
                break;
            }
            case 3: {
                sql = "UPDATE product SET warehouse = ? WHERE id = ?";
                System.out.println("请输入新的仓库号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql);
                int a = sc.nextInt();
                int b = product.getWarehouse();
                utmt.setInt(1, a);
                utmt.setInt(2, product.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将货物表中编号为"+product.getId()+
                        "的仓库号进行了修改："+b+"修改为"+a;
                break;
            }
            case 4: {
                sql = "UPDATE product SET number = ? WHERE id = ?";
                System.out.println("请输入新的库存数量：");
                PreparedStatement utmt = this.DB.prepareStatement(sql);
                int a = sc.nextInt();
                int b = product.getNum();
                utmt.setInt(1, a);
                utmt.setInt(2, product.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将货物表中编号为"+product.getId()+
                        "的库存数量进行了修改："+b+"修改为"+a;
                break;
            }
            case 5: {
                sql = "UPDATE product SET price = ? WHERE id = ?";
                System.out.println("请输入新的货物单价：");
                PreparedStatement utmt = this.DB.prepareStatement(sql);
                double a = sc.nextDouble();
                double b = product.getPrice();
                utmt.setDouble(1, a);
                utmt.setInt(2, product.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将货物表中编号为"+product.getId()+
                        "的货物单价进行了修改："+b+"修改为"+a;
                break;
            }
            case 6: {
                sql = "UPDATE product SET id = ? WHERE id = ?";
                System.out.println("请输入新的货物编号：");
                PreparedStatement utmt = this.DB.prepareStatement(sql);
                int a = sc.nextInt();
                int b = product.getId();
                utmt.setInt(1, a);
                utmt.setInt(2, product.getId());
                res = utmt.execute();
                s = "用户"+AdminService.username+"将货物表中编号为"+product.getId()+
                        "的货物编号进行了修改："+b+"修改为"+a;
                break;
            }
            default:
                Test.start();
        }
        if (!res) {
            System.out.println("货物信息修改成功");
            recordService.insertRecord(s);
            return 1;
        } else {
            System.out.println("货物信息修改失败");
            return -1;
        }
    }

    //货物信息统计
    public void statsProduct() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Menu.statsProduct();
        System.out.println("请输入要进行的操作：");
        switch (sc.nextInt()) {
            case 1: {
                double start = 0, end = 0;
                System.out.println("请输入价格起点：");
                start = sc.nextDouble();
                System.out.println("请输入价格终点：");
                end = sc.nextDouble();
                String sql = "SELECT * FROM product WHERE price >= "+start +" AND price <="+end;
                Statement stmt = this.DB.createStatement();

                ResultSet res = stmt.executeQuery(sql);
                List<Product> productListList = new ArrayList<>();

                while (res.next()) {
                    Product product1 = new Product();
                    product1.setId(res.getInt("id"));
                    product1.setName(res.getString("name"));
                    product1.setPrice(res.getDouble("price"));
                    product1.setNum(res.getInt("number"));
                    product1.setSort(res.getString("sort"));
                    product1.setWarehouse(res.getInt("warehouse"));
                    System.out.println(product1.toString());
                    productListList.add(product1);
                }
                break;
            }
            case 2: {
                //分类统计
                System.out.println("请输入要统计的种类：");
                String sort = sc.next();
                String sql = "SELECT * FROM product WHERE sort= '"+sort+"'";
                Statement stmt = this.DB.createStatement();
                ResultSet res = stmt.executeQuery(sql);
                List<Product> productListList = new ArrayList<>();

                while (res.next()) {
                    Product product1 = new Product();
                    product1.setId(res.getInt("id"));
                    product1.setName(res.getString("name"));
                    product1.setPrice(res.getDouble("price"));
                    product1.setNum(res.getInt("number"));
                    product1.setSort(res.getString("sort"));
                    product1.setWarehouse(res.getInt("warehouse"));
                    System.out.println(product1.toString());
                    productListList.add(product1);
                }
                break;
            }
            case 3: {
                System.out.println("请输入供应商名称：");
                String supName=sc.next();
                String sql="SELECT * FROM product WHERE sname = '"+supName+"'";
                Statement stmt = this.DB.createStatement();
                ResultSet res = stmt.executeQuery(sql);
                List<Product> productListList = new ArrayList<>();

                while (res.next()) {
                    Product product1 = new Product();
                    product1.setId(res.getInt("id"));
                    product1.setName(res.getString("name"));
                    product1.setPrice(res.getDouble("price"));
                    product1.setNum(res.getInt("number"));
                    product1.setSort(res.getString("sort"));
                    product1.setWarehouse(res.getInt("warehouse"));
                    System.out.println(product1.toString());
                    productListList.add(product1);
                }
                break;
            }
            case 4: {
                Method.product();
                break;
            }
            default:
                Test.start();
        }
    }
}
