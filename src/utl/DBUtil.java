package utl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



public class DBUtil {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static {
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getResourceAsStream("/db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver = p.getProperty("driver");
		url = p.getProperty("url");
		username = p.getProperty("username");
		password = p.getProperty("password");
	}
	public static Connection getConnection() {
		try {
			// 1.����������
			Class.forName(driver);
			// 2.��ȡ���ݿ����Ӷ���(DriverManager-����������)
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

    /**
     * �ر����ݿ����
     * @param con
     * @param pst
     * @param rs
     */
	public static void close(Connection con, PreparedStatement pst, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pst != null) {
			try {
				pst.close();
				pst = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
