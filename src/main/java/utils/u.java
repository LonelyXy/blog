package main.java.utils;

import java.sql.Connection;

/**
 * @author xyLonely
 * @date 2018/12/19 15:39
 */
public class u {

    public static void main(String[] args) {
        Connection connection = Utils_c3p0.getConnection();
        System.out.println(connection);
    }

}
