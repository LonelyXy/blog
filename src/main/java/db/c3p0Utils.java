package main.java.db;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * C3P0数据库连接池的使用
 *配置文件 src/c3p0-config.xml
 */
public class c3p0Utils {

	  private static c3p0Utils instance;
	  
	  private static ComboPooledDataSource  dataSource;
	  
	  private c3p0Utils() throws Exception {
		   dataSource  = new ComboPooledDataSource("mysql");
	  }

	  /**
	   * 获取实例
	   * @return
	   */
	  public static final c3p0Utils getInstance() {
	        if (instance == null) {
	            try {
	                instance = new c3p0Utils();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return instance;
	    }

	  /**
	   * 获取数据库连接
	   * @return
	   */
	  public synchronized final Connection getConnection() {
	        Connection conn = null;
	        try {
	            conn = dataSource.getConnection();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }
	  	
	
}
