package com.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
   
	private static final String driver = "com.mysql.jdbc.Driver"; // 数据库驱动
	//连接数据库的url
	private static final String url="jdbc:mysql://localhost:3306/51shopping?useUnicode=true&characterEncoding=UTF-8"; 
	private static final String username="root";// 用户名
	private static final String password="";// 密码
    
	private static Connection conn=null;
	
	//静态代码块加载驱动
	static 
	{
		try
		{
			Class.forName(driver);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//单例模式取得数据库连接对象
	public static Connection getConnection() throws Exception
	{
		if(conn == null)
		{
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		return conn;
	}
	
	public static void main(String[] args) {
		
		try
		{
		   Connection conn = DBHelper.getConnection();
		   if(conn!=null)
		   {
			   System.out.println("连接成功");
		   }
		   else
		   {
			   System.out.println("连接失败");
		   }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
