package lyons.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * �������ݿ�
 * @author ����
 *
 */
public class DbConn
{
	public static  Connection getconn()
	{
		Connection conn = null;
		
		String user   = "scott";
		String passwd = "tiger";
		String url = "jdbc:oracle:thin:@localhost:1521:inspur";
		
		//�Ѽ���������
		
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");   //driver����ĵ�������ɶ��ţ���bug�˷���5Сʱ+���գ�
					conn = DriverManager.getConnection(url,user,passwd);
				}catch (SQLException e)
				{
					e.printStackTrace();
				}
				catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
		return conn;
	}

}
