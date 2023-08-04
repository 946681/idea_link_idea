package test;

import menu.Menu;
import service.*;
import vo.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Method {

    //货物
    public static void product() throws SQLException, ParseException {
        Menu.productMenu();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要进行的操作：");
        int i = sc.nextInt();
        ProductService productService = new ProductService();
        Product product = new Product();
        switch (i){
            case 1:{
                productService.displayProduct();
                Method.product();
                break;
            }
            case 2:{
                System.out.println("请输入要查找货物的名称：");
                String s = sc.next();
                product.setName("'"+s+"'");
                productService.selectProduct(product);
                Method.product();
                break;
            }
            case 3:{
                Menu.updateProductMenu();
                System.out.println("请输入要修改货物的编号：");

                product.setId(sc.nextInt());
                productService.selectProduct(product);
                System.out.println("请输入要进行的操作：");
                productService.update(product,sc.nextInt());
                Method.product();
                break;
            }
            case 4:{
                System.out.println("请输入要删除的货物的编号：");
                product.setId(sc.nextInt());
                productService.deleteProduct(product);
                Method.product();
                break;
            }
            case 5:{
                System.out.println("请输入货物的名称：");
                product.setName(sc.next());
                System.out.println("请输入货物的分类：");
                product.setSort(sc.next());
                System.out.println("请输入货物的仓库号：");
                product.setWarehouse(sc.nextInt());
                System.out.println("请输入货物的数量：");
                product.setNum(sc.nextInt());
                System.out.println("请输入货物的单价：");
                product.setPrice(sc.nextDouble());
                productService.addProduct(product);
                Method.product();
                break;
            }
            case 6:{//统计stats
                productService.statsProduct();
                break;
            }
            default:{
                Test.start();
            }
        }
    }
    //订单
    public static void order() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Menu.orderMenu();
        System.out.println("请输入要进行的操作：");
        OrderService orderService = new OrderService();
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        switch (sc.nextInt()){
            case 1:{
                //显示所有订单信息
                orderService.displayPurchaseOrder();
                Method.order();
                break;
            }
            case 2:{
                //查找
                System.out.println("请输入要查找的订单编号：");
                purchaseOrder.setId(sc.nextInt());
                orderService.selectPurchaseOrder(purchaseOrder);
                Method.order();
                break;
            }
            case 3:{
                //修改
                System.out.println("请输入要修改的订单编号：");
                purchaseOrder.setId(sc.nextInt());
                orderService.selectPurchaseOrder(purchaseOrder);
                Menu.updateOrderMenu();
                System.out.println("请输入要进行的操作：");
                orderService.update(purchaseOrder,sc.nextInt());
                Method.order();
                break;
            }
            case 4:{
                //删除
                System.out.println("请输入要删除订单的货物编号：");
                purchaseOrder.setId(sc.nextInt());
                orderService.deletePurchaseOrder(purchaseOrder);
                Method.order();
                break;
            }
            case 5:{
                //增加
                System.out.println("请输入供应商编号：");
                purchaseOrder.setSid(sc.nextInt());
                System.out.println("请输入总金额：");
                purchaseOrder.setSum(sc.nextDouble());
                System.out.println("请输入时间：");
                purchaseOrder.setTime(sc.next());
                orderService.addPurchaseOrder(purchaseOrder);
                Method.order();
                break;
            }
            default:Test.start();
        }
    }
    //供应商
    public static void supplier() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Menu.supplierMenu();
        System.out.println("请输入要进行的操作：");
        SupplierService supplierService = new SupplierService();
        Supplier supplier = new Supplier();
        switch (sc.nextInt()){
            case 1:{
                supplierService.displayProduct();
                Method.supplier();
                break;
            }
            case 2:{
                System.out.println("请输入要查找供应商的编号：");
                supplier.setId(sc.nextInt());
                supplierService.selectSupplier(supplier);
                Method.supplier();
                break;
            }
            case 3:{
                System.out.println("请输入要修改的供应商的编号：");
                supplier.setId(sc.nextInt());
                supplierService.selectSupplier(supplier);
                Menu.updateSupplierMenu();
                System.out.println("请输入要进行的操作：");
                supplierService.update(supplier,sc.nextInt());
                Method.supplier();
                break;
            }
            case 4:{
                System.out.println("请输入要删除的供应商的编号：");
                supplier.setId(sc.nextInt());
                supplierService.deleteSupplier(supplier);
                Method.supplier();
                break;
            }
            case 5:{
                System.out.println("请输入供应商名称：");
                supplier.setName(sc.next());
                System.out.println("请输入供应商地址：");
                supplier.setAddress(sc.next());
                System.out.println("请输入供应商电话：");
                supplier.setTelephone(sc.next());
                System.out.println("请输入供应商分类：");
                supplier.setSort(sc.next());
                supplierService.addSupplier(supplier);
                Method.supplier();
                break;
            }
            default:
                Method.supplier();
        }
    }
    //费用
    public static void expense() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Menu.expenseMenu();
        System.out.println("请输入要进行的操作：");
        Expense expense = new Expense();
        ExpenseService expenseService = new ExpenseService();
        switch (sc.nextInt()){
            case 1:{
                expenseService.displayExpense();
                Method.expense();
                break;
            }
            case 2:{
                System.out.println("请输入要查找费用信息的编号：");
                expense.setId(sc.nextInt());
                expenseService.selectExpense(expense);
                Method.expense();
                break;
            }
            case 3:{
                System.out.println("请输入要修改的费用编号：");
                expense.setId(sc.nextInt());
                expenseService.selectExpense(expense);
                Menu.updateExpense();
                System.out.println("请输入要进行的操作：");
                expenseService.update(expense,sc.nextInt());
                Method.expense();
                break;
            }
            case 4:{
                System.out.println("请输入要删除的费用的编号：");
                expense.setId(sc.nextInt());
                expenseService.deleteExpense(expense);
                Method.expense();
                break;
            }
            case 5:{
                System.out.println("请输入订单编号");
                expense.setOid(sc.nextInt());
                System.out.println("请输入分类：");
                expense.setSort(sc.next());
                System.out.println("请输入金额：");
                expense.setSum(sc.nextDouble());
                System.out.println("请输入时间：");
                expense.setTime(sc.next());
                expenseService.addExpense(expense);
                Method.expense();
                break;
            }
            default:
                Test.start();
        }
    }
    //入库
    public static void in() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Menu.inMenu();
        System.out.println("请输入要进行的操作：");
        Input input = new Input();
        InService inService = new InService();
        switch (sc.nextInt()){
            case 1:{
                inService.displayIn();
                Method.in();
                break;
            }
            case 2:{
                System.out.println("请输入要查找的入库信息的编号：");
                input.setId(sc.nextInt());
                inService.selectIn(input);
                Method.in();
                break;
            }
            case 3:{
                System.out.println("请输入要修改的入库信息的编号：");
                input.setId(sc.nextInt());
                inService.displayIn();
                Menu.updateIn();
                System.out.println("请输入要进行的操作：");
                inService.update(input,sc.nextInt());
                Method.in();
                break;
            }
            case 4:{
                System.out.println("请输入要删除的入库信息的编号：");
                input.setId(sc.nextInt());
                inService.deleteIn(input);
                Method.in();
                break;
            }
            case 5:{
                System.out.println("请输入订单编号");
                input.setOid(sc.nextInt());
                System.out.println("请输入货物编号：");
                input.setPid(sc.nextInt());
                System.out.println("请输入库数量：");
                input.setNumber(sc.nextInt());
                System.out.println("请输入入库时间：");
                input.setInTime(sc.next());
                inService.addIn(input);
                Method.in();
                break;
            }
            default:
                Test.start();
        }
    }
    //出库
    public static void out() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Menu.outMenu();
        System.out.println("请输入要进行的操作：");
        Output output = new Output();
        OutService outService = new OutService();
        switch (sc.nextInt()){
            case 1:{
                outService.displayOut();
                Method.out();
                break;
            }
            case 2:{
                System.out.println("请输入要查找的出库信息的编号：");
                output.setId(sc.nextInt());
                outService.selectOut(output);
                Method.out();
                break;
            }
            case 3:{
                System.out.println("请输入要修改的出库信息的编号：");
                output.setId(sc.nextInt());
                outService.displayOut();
                Menu.updateOut();
                System.out.println("请输入要进行的操作：");
                outService.update(output,sc.nextInt());
                Method.out();
                break;
            }
            case 4:{
                System.out.println("请输入要删除的出库信息的编号：");
                output.setId(sc.nextInt());
                outService.deleteOut(output);
                Method.out();
                break;
            }
            case 5:{
                System.out.println("请输入货物编号：");
                output.setPid(sc.nextInt());
                System.out.println("请输出库数量：");
                output.setNumber(sc.nextInt());
                System.out.println("请输入出库时间：");
                output.setOutTime(sc.next());
                outService.addOut(output);
                Method.out();
                break;
            }
            default:
                Test.start();
        }
    }
    //用户
    public static void admin() throws SQLException, ParseException {
        Scanner sc = new Scanner(System.in);
        Menu.adminMenu();
        AdminService adminService = new AdminService();
        Admin admin = new Admin();
        System.out.println("请输入要进行的操作：");
        switch (sc.nextInt()){
            case 1:{
                adminService.displayAdmin();
                Method.admin();
                break;
            }
            case 2:{
                System.out.println("请输入用户名：");
                admin.setUsername(sc.next());
                System.out.println("请输入密码：");
                admin.setPassword(sc.next());
                adminService.addAdmin(admin);
                Method.admin();
                break;
            }
            case 3:{
                Test.start();
                break;
            }
        }
    }
    public static void login() throws ParseException, SQLException {
        System.out.println("****************************************");
        System.out.println("*               登录界面                 *");
        System.out.println("****************************************");
        AdminService adminService = new AdminService();
        adminService.selectAdmin();

    }

}
