package main.java.com.pointsmallsystem.www.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JdbcUtils
 * @Author: xgsardh
 * @Date: 2024.3.19
 */
public class JdbcUtils {
    static InputStream inputStream = null;
    static Properties properties = new Properties();

    //Connect to server
    static {
        try {
            inputStream = JdbcUtils.class.getResourceAsStream("/main/resources/jdbc.properties");
            // This could throw IOException
            properties.load(inputStream);
            // This could throw ClassNotFoundException
            Class.forName(properties.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // This could throw IOException
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  @name        : public static Connection getconntion()throws Exception
     *	@description : Get available connection objects
     *	@param		 : None
     *	@return		 : Connection
     *  @notice      : None
     */
    public static Connection getConnection()throws Exception {
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
    }

    /**
     *  @name        : public static void close(ResultSet set, Statement statement,PreparedStatement preparedStatement, Connection connection)throws Exception
     *	@description : Close Connection
     *	@param		 : ResultSet set, Statement statement,PreparedStatement preparedStatement, Connection connection
     *	@return		 : None
     *  @notice      : None
     */
    public static void close(ResultSet set, Statement statement,PreparedStatement preparedStatement, Connection connection)throws Exception {
        if(set != null) {
            set.close();
        }
        if(statement != null) {
            statement.close();
        }
        if(preparedStatement != null){
            preparedStatement.close();
        }
        if(connection != null) {
            connection.close();
        }
    }

    /**
     *  @name        : public static int update(String sql, Object... params) throws Exception
     *	@description : Close Connection
     *	@param		 : String sql, Object... params
     *	@return		 : int
     *  @notice      : None
     */
    public static int update(String sql, Object... params) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);

            for(int i = 0;i < params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }

            count = pstmt.executeUpdate();

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            //Release resources
            JdbcUtils.close(null,null,pstmt,conn);
        }
        return count;
    }

    /**
     *  @name        : public static <T> T query(String sql, MyHandler<T> handler, Object... params)throws Exception
     *	@description : Search in JdbcUtils
     *	@param		 : String sql, MyHandler<T> handler, Object... params
     *	@return		 : <T>
     *  @notice      : None
     */
    public static <T> T query(String sql, MyHandler<T> handler, Object... params)throws Exception {
        T result = null;
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    // Set SQL parameters
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
            result = handler.handle(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null,null,pstmt,conn);
        }
        return result;
    }


}
