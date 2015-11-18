package lyons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.SalesMan;



/**
 * ������Ա������ʵ��
 * @author ����
 *
 */
public final class SalesManDao
{
	 Connection        conn  = null;
	 PreparedStatement pstmt = null;
	 ResultSet 		rs 	 	 = null;
	
	/*
	 * ǰ̨������½  ��ʵ�֣�
	 * ������û����ڣ����ظ���������һ����Ϣ
	 */
	 	public ArrayList<SalesMan> checkstandLog(String sName)
		{
	 		ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
			conn = DbConn.getconn();
			String sql = "SELECT SID,SPASSWORD FROM SALESMAN WHERE SNAME=?";
					try
					{
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1,sName);
						
						rs 	  = pstmt.executeQuery();
						while (rs.next())
						{
							String sPassWord = rs.getString("spassword");
							int sId = rs.getInt("sId");
							SalesMan salesMan = new SalesMan(sId,sPassWord); //����Goods���󣬲���ֵ��
							salesManInfo.add(salesMan);						//��ӵ��������У�
						}
					} catch (SQLException e1)
					{
						e1.printStackTrace();
					}finally
					{
						DbClose.queryClose(pstmt, rs, conn);
					}
		return salesManInfo;
		}
	/*
	 * 1.����ۻ�Ա- ��ʵ�֣�
	 */
		public boolean addSalesMan(SalesMan salesman)
		{
			boolean bool = false;
			conn = DbConn.getconn();
			String sql = "INSERT INTO SALESMAN(SNAME,SPASSWORD) VALUES(?,?)";
				
				try
				{
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,salesman.getSName());
					pstmt.setString(2,salesman.getSPassWord());
					
					int rs = pstmt.executeUpdate();
					if (rs > 0)
					{
						bool = true;
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
						{
							DbClose.addClose(pstmt,conn);
						}
		return bool;
		}
	
	/*
	 * 2.�����ۻ�Ա  ��ʵ�֣�
	 */
	public  boolean updateSalesMan(int key,SalesMan salesMan)
	{
		
		boolean bool = false;
		conn = DbConn.getconn();
			switch (key)
			{
			case 1:			//		System.out.println("\t\t1.�����ۻ�Ա����");
						String sqlName = "UPDATE SALESMAN SET SNAME=? WHERE SID=?";
						
						try
					{
						pstmt = conn.prepareStatement(sqlName);
						pstmt.setString(1, salesMan.getSName());
						pstmt.setInt(2,salesMan.getSId());
						
						int rs = pstmt.executeUpdate();
						if (rs > 0)
						{
							bool = true;
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally{
								DbClose.addClose(pstmt,conn);
							}
				break;
			case 2:			//		System.out.println("\t\t2.�����ۻ�Ա����");
						String sqlPrice = "UPDATE SALESMAN SET SPASSWORD=? WHERE SID=?";
						
						try
					{
						pstmt = conn.prepareStatement(sqlPrice);
						pstmt.setString(1,salesMan.getSPassWord());
						pstmt.setInt(2, salesMan.getSId());
						
						int rs = pstmt.executeUpdate();
						if (rs > 0)
						{
							bool = true;
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally{
								DbClose.addClose(pstmt,conn);
							}
				break;
			default:
				break;
			}
		return bool;
		
		
	}

	/*
	 * 3.ɾ���ۻ�Ա ��ʵ�֣�
	 */
	public boolean deleteSalesMan(String sName)
	{
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "DELETE FROM SALESMAN WHERE SNAME=?";
		
			try
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,sName);
				int rs = pstmt.executeUpdate();
				if (rs > 0)
				{
					bool = true;
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}finally{
						DbClose.addClose(pstmt,conn);
					}
		return bool;
	}
	
	/*
	 * 4.ģ����ѯ�ۻ�Ա ��ʵ�֣�
	 */
	
	public ArrayList<SalesMan> querySalesMan(String sName)
	{
		ArrayList<SalesMan> SalesManList = new ArrayList<SalesMan>();
		conn = DbConn.getconn();	
	
		sName = "%"+sName+"%";	//���û�����ȡ���ַ������� % ���ţ����ﵽģ����ѯ��Ŀ��.�ַ��� �����ӻ��и�����ķ�ʽ�����Ż����룡
		String sql = "SELECT * FROM SALESMAN WHERE SNAME LIKE ?";  //��Ȼ����ֱ�Ӹ� % .ֻ���������ַ����ķ�ʽ
		   try
		   {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sName);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					int sid = rs.getInt("sid");
					String sname = rs.getString(2);
					String sPassWord = rs.getString(3);
					
					SalesMan salesMan = new SalesMan(sid,sname,sPassWord);
					SalesManList.add(salesMan);
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}finally
					{
						DbClose.queryClose(pstmt, rs, conn);
					}
		return SalesManList;
	}
	
	/*
	 * 5.��ʾ�����ۻ�Ա ��ʵ�֣�
	 */
		public  ArrayList<SalesMan> displaySalesMan()
		{
			ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
			conn = DbConn.getconn(); 
			String sql = "SELECT * FROM SALESMAN";
				
				try
				{
					pstmt = conn.prepareStatement(sql);
					rs =  pstmt.executeQuery();
					while (rs.next())
					{
						int sId = rs.getInt(1);
						String sName = rs.getString(2);
						String sSpassWord = rs.getString(3);
						
						SalesMan salesMan = new SalesMan(sId,sName,sSpassWord);
						salesManList.add(salesMan);
					}
				} catch (SQLException e)
				{
					e.printStackTrace();
				}finally
						{
							DbClose.queryClose(pstmt, rs, conn);
						}
			return salesManList;
		}
	
}
