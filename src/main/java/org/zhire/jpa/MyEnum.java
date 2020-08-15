package org.zhire.jpa;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public enum MyEnum {
    ZERO,
    ONE,
    TWO,
}

@Data
class MyTest {
    int id;
    MyEnum myEnum;
}

class Test {
    public static void main(String[] args) throws Exception {

        if (MyEnum.valueOf("ZERO").equals(MyEnum.ZERO)) {
            System.out.println(true);
        }

        System.out.println(MyEnum.ZERO);
        System.out.println(MyEnum.ONE);
        System.out.println(MyEnum.TWO);
        MyTest myTest = new MyTest();
        myTest.setId(1);
        System.out.println(myTest);
        // 获取连接
        // 1.1 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 1.2 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///MyTest", "root", "1111");
//        // 编写sql ; 可写可不写 不写 数据库会帮你自动加上
//        String sql = "insert into zp_user_business values(1,0,123);";
//        // 创建语句执行者
//        Statement statement = connection.createStatement();
//        // 执行sql并返回结果
//        int i = statement.executeUpdate(sql);
//        // 处理结果
//        if (i > 0) {
//            System.out.println(i);
//            System.out.println("插入成功");
//        } else {
//            System.out.println("插入失败");
//        }
        // 编写sql
        String sql = "select * from zp_user_business;";
        // 创建语句执行者
        Statement statement = connection.createStatement();
        // 执行sql语句并返回结果
        ResultSet resultSet = statement.executeQuery(sql);
        // 处理结果
        while (resultSet.next()) {
            ZpUserBusiness userBusiness = new ZpUserBusiness();
            int id = resultSet.getInt("id");
            userBusiness.setId(Long.parseLong(String.valueOf(id)));
            Integer fromType = resultSet.getInt("from_type");
            userBusiness.setFromType(fromType == 0 ? ZpUserBusiness.FROMTYPE.TEST: ZpUserBusiness.FROMTYPE.WORKS);
            System.out.println(userBusiness);
        }
        // 释放资源
        statement.close();
        connection.close();
    }
}
