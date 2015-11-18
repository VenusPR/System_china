package lyons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Gsales;

/**
 * ��Ʒ�������
 * @author ����
 */
public final class GsalesDao
{
	/*
	 * ÿ��������Ʒ�б�
	 *
	 */
	
	
	public ArrayList<Gsales> dailyGsales()
	{
		Connection        conn  = null;
	    PreparedStatement pstmt = null;
		ResultSet 		rs 	 	 = null;
		 
		ArrayList<Gsales> GsalesList = new ArrayList<Gsales>(); 
		conn = DbConn.getconn();

		//�ҵ��죡�����о������sql���ˣ�trunc(sdate) =trunc(sysdate)
		//sql: gname,gprice,gnum, allSum (������Ʒ�������ܺ�)
		//oracle�����ͼ�sql�ļ���
		String sql = "select gname,gprice,gnum, allSum from goods, (select gid as salesid,sum(snum) as allSum from gsales where trunc(sdate) =trunc(sysdate) group by gid) where gid = salesid"; 
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
				
				while (rs.next())
				{
					String gName = rs.getString(1);
					double gPrice = rs.getDouble(2);
					int gNum = rs.getInt(3);
					int allSnum = rs.getInt("allSum");
					
					Gsales Gsales = new Gsales(gName,gPrice,gNum,allSnum);	//����Gsales���󣬲���ֵ��
					GsalesList.add(Gsales);						//��ӵ��������У�
				}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
				{
					DbClose.queryClose(pstmt,rs,conn);
				}
		return GsalesList;	//��Ҫ���ظ�������ֵ:gid����������Ʒ�ܺ�
		
	}

}
