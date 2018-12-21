package main.java.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

public class Utils_c3p0 {
	private static ComboPooledDataSource cpds = null;
	    static {
	    	cpds = new ComboPooledDataSource("mysql");
	    }
	    public static Connection getConnection() {
	    	if(cpds!=null) {
	    		try {
					return cpds.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	return null;
	    }
	    public static int executeUpdate(String sql,Object...params) {
	    	Connection connection = getConnection();
	    	PreparedStatement pStatement = null;
	    	try {
				pStatement = connection.prepareStatement(sql);
				for (int i = 0; i < params.length; i++) {
					pStatement.setObject(i+1, params[i]);
				}
				return pStatement.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally {
				Utils_c3p0.close(null, pStatement, connection);
			}
	    }
	    public static void close(ResultSet resultSet,Statement statement,Connection connection) {
	    	if(resultSet!=null) {
	    		try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	if(statement!=null) {
	    		try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	if(connection!=null) {
	    		try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
	    }

}
