package menu;

public class Menu {
    public static void mainMenu(){
        System.out.println("***************************************");
        System.out.println("*               主菜单                 *");
        System.out.println("***************************************");
        System.out.println("*            1.货物信息管理             *");
        System.out.println("*            2.订单信息管理             *");
        System.out.println("*            3.入库信息管理             *");
        System.out.println("*            4.出库信息管理             *");
        System.out.println("*            5.费用信息管理             *");
        System.out.println("*            6.供应商信息管理            *");
        System.out.println("*            7.用户信息管理              *");
        System.out.println("*            8.日志显示                 *");
        System.out.println("*            9.退出                    *");
        System.out.println("***************************************");
    }

    public static void adminMenu(){
        System.out.println("***************************************");
        System.out.println("*               用户管理                *");
        System.out.println("***************************************");
        System.out.println("*            1.显示所有用户信息          *");
        System.out.println("*            2.增加用户                *");
        System.out.println("*            3.返回主菜单               *");
        System.out.println("***************************************");
    }
    public static void productMenu(){
        System.out.println("***************************************");
        System.out.println("*               货物管理                *");
        System.out.println("***************************************");
        System.out.println("*            1.显示所有货物信息          *");
        System.out.println("*            2.查找货物信息             *");
        System.out.println("*            3.修改货物信息             *");
        System.out.println("*            4.删除货物信息             *");
        System.out.println("*            5.增加货物信息             *");
        System.out.println("*            6.货物信息统计             *");
        System.out.println("*            7.返回主菜单               *");
        System.out.println("***************************************");
    }

    public static void orderMenu(){
        System.out.println("***************************************");
        System.out.println("*               订单管理                *");
        System.out.println("***************************************");
        System.out.println("*            1.显示所有订单信息          *");
        System.out.println("*            2.查找订单信息             *");
        System.out.println("*            3.修改订单信息             *");
        System.out.println("*            4.删除订单信息             *");
        System.out.println("*            5.增加订单信息             *");
        System.out.println("*            6.返回主菜单               *");
        System.out.println("***************************************");
    }

    public static void inMenu(){
        System.out.println("***************************************");
        System.out.println("*               入库信息管理             *");
        System.out.println("***************************************");
        System.out.println("*            1.显示所有商品入库信息        *");
        System.out.println("*            2.查找商品入库信息           *");
        System.out.println("*            3.修改商品入库信息           *");
        System.out.println("*            4.删除商品入库信息           *");
        System.out.println("*            5.增加商品入库信息           *");
        System.out.println("*            6.返回主菜单               *");
        System.out.println("***************************************");
    }
    public static void outMenu(){
        System.out.println("***************************************");
        System.out.println("*              出库信息管理              *");
        System.out.println("***************************************");
        System.out.println("*            1.显示所有商品出库信息        *");
        System.out.println("*            2.查找商品出库信息           *");
        System.out.println("*            3.修改商品出库信息           *");
        System.out.println("*            4.删除商品出库信息           *");
        System.out.println("*            5.增加商品出库信息           *");
        System.out.println("*            6.返回主菜单               *");
        System.out.println("***************************************");
    }

    public static void supplierMenu(){
        System.out.println("****************************************");
        System.out.println("*               供应商管理                *");
        System.out.println("****************************************");
        System.out.println("*            1.显示所有供应商信息          *");
        System.out.println("*            2.查找供应商信息             *");
        System.out.println("*            3.修改供应商信息             *");
        System.out.println("*            4.删除供应商信息             *");
        System.out.println("*            5.增加供应商信息             *");
        System.out.println("*            6.返回主菜单                *");
        System.out.println("****************************************");
    }
    public static void expenseMenu(){
        System.out.println("***************************************");
        System.out.println("*               费用管理                *");
        System.out.println("***************************************");
        System.out.println("*            1.显示所有费用信息          *");
        System.out.println("*            2.查找费用信息             *");
        System.out.println("*            3.修改费用信息             *");
        System.out.println("*            4.删除费用信息             *");
        System.out.println("*            5.增加费用信息             *");
        System.out.println("*            6.返回主菜单               *");
        System.out.println("***************************************");
    }
    public static void updateProductMenu(){
        System.out.println("***************************************");
        System.out.println("*             货物信息修改               *");
        System.out.println("***************************************");
        System.out.println("*            1.修改名称                 *");
        System.out.println("*            2.修改分类                 *");
        System.out.println("*            3.修改仓库号                *");
        System.out.println("*            4.修改库存数量              *");
        System.out.println("*            5.修改货物单价               *");
        System.out.println("*            6.修改货物编号               *");
        System.out.println("*            7.返回上一级               *");
        System.out.println("***************************************");
    }
    public static void updateOrderMenu(){
        System.out.println("***************************************");
        System.out.println("*             订单信息修改               *");
        System.out.println("***************************************");
        System.out.println("*            1.修改供应商编号            *");
        System.out.println("*            2.修改总金额               *");
        System.out.println("*            3.修改时间                 *");
        System.out.println("*            4.修改订单编号              *");
        System.out.println("*            5.返回上一级               *");
        System.out.println("***************************************");
    }
    public static void updateSupplierMenu(){
        System.out.println("***************************************");
        System.out.println("*             供应商信息修改             *");
        System.out.println("***************************************");
        System.out.println("*            1.修改供应商编号            *");
        System.out.println("*            2.修改供应商名称            *");
        System.out.println("*            3.修改供应商地址            *");
        System.out.println("*            4.修改供应商电话            *");
        System.out.println("*            5.修改供应商分类            *");
        System.out.println("*            6.返回上一级               *");
        System.out.println("***************************************");
    }
    public static void updateIn(){
        System.out.println("***************************************");
        System.out.println("*              入库信息修改              *");
        System.out.println("***************************************");
        System.out.println("*            1.修改入库编号              *");
        System.out.println("*            2.修改订单编号              *");
        System.out.println("*            3.修改货物编号              *");
        System.out.println("*            4.修改入库数量              *");
        System.out.println("*            5.修改入库时间              *");
        System.out.println("*            6.返回上一级               *");
        System.out.println("***************************************");
    }
    public static void updateOut(){
        System.out.println("***************************************");
        System.out.println("*              出库信息修改             *");
        System.out.println("***************************************");
        System.out.println("*            1.修改出库编号              *");
        System.out.println("*            2.修改货物编号              *");
        System.out.println("*            3.修改出库数量              *");
        System.out.println("*            4.修改出库时间              *");
        System.out.println("*            5.返回上一级               *");
        System.out.println("***************************************");
    }
    public static void updateExpense(){
        System.out.println("***************************************");
        System.out.println("*             费用信息修改              *");
        System.out.println("***************************************");
        System.out.println("*            1.修改编号                 *");
        System.out.println("*            2.修改订单编号              *");
        System.out.println("*            3.修改费用分类              *");
        System.out.println("*            4.修改金额                 *");
        System.out.println("*            5.修改时间                 *");
        System.out.println("*            6.返回上一级               *");
        System.out.println("***************************************");
    }
    public static void statsProduct(){
        System.out.println("***************************************");
        System.out.println("*             货物信息统计              *");
        System.out.println("***************************************");
        System.out.println("*            1.按价格统计               *");
        System.out.println("*            2.按分类统计               *");
        System.out.println("*            3.按供应商统计              *");
        System.out.println("*            4.返回上一级               *");
        System.out.println("***************************************");
    }

}
