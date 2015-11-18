package lyons.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DbClose
{
	/*
	 * �ر� ��ӹ��� ��Դ
	 */
		public static void addClose(PreparedStatement pstmt, Connection conn)
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*
		 * �ر� ��Ʒ��ѯ ��Ʒ���� �г��ۻ�Ա��Դ
		 */
		public static void queryClose(PreparedStatement pstmt, ResultSet rs, Connection conn)
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
				}
				if (rs != null )
				{
					rs.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
}
