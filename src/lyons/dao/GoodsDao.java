package lyons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Goods;
import lyons.entity.Gsales;
import lyons.tools.ScannerChoice;

public final class GoodsDao 
{
		 Connection        conn  = null;
		 PreparedStatement pstmt = null;
		 ResultSet 		rs 	 	 = null;
	
	/*
	 * 1.�����Ʒ�����ݿ�-����ʵ�� ��ʵ�֣�
	 */
	public boolean addGoods(Goods goods)
	{
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "INSERT INTO GOODS(GNAME,GPRICE,GNUM) VALUES(?,?,?)";
			
			try
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,goods.getGname());
				pstmt.setDouble(2,goods.getGprice());
				pstmt.setInt(3,goods.getGnum());
				
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
	 * 2.������Ʒ  ��ʵ�֣�
	 */
	public boolean updateGoods(int key,Goods goods) //�������ͣ�keyѡ��ִ����һ����choiceGid : ��Ҫִ�е���Ʒ��gid��š�
	{
		boolean bool = false;
		conn = DbConn.getconn();
			switch (key)
			{
			case 1:			//		System.out.println("\t\t1.������Ʒ����");
						String sqlName = "UPDATE GOODS SET GNAME=? WHERE GID=?";
						
						try
					{
						pstmt = conn.prepareStatement(sqlName);
						pstmt.setString(1, goods.getGname());
						pstmt.setInt(2,goods.getGid());
						
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
			case 2:			//		System.out.println("\t\t2.������Ʒ�۸�");
						String sqlPrice = "UPDATE GOODS SET GPRICE=? WHERE GID=?";
						
						try
					{
						pstmt = conn.prepareStatement(sqlPrice);
						pstmt.setDouble(1, goods.getGprice());
						pstmt.setInt(2,goods.getGid());
						
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
			case 3:			//		System.out.println("\t\t3.������Ʒ����");
						String sqlNum = "UPDATE GOODS SET GNUM=? WHERE GID=?";
							
							try
						{
							pstmt = conn.prepareStatement(sqlNum);
							pstmt.setInt(1, goods.getGnum());
							pstmt.setInt(2,goods.getGid());
							
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
	 * 3.�h����Ʒ  ��ʵ�֣�
	 */
		public boolean deleteGoods(int gid)
		{
			boolean bool = false;
			conn = DbConn.getconn();
			String sql = "DELETE FROM GOODS WHERE GID=?";
			
				try
				{
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1,gid);
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
	 *  4.��ѯ��Ʒ����--����ʵ��  ��ʵ�֣�
	 * ��Ʒ���������ѯ������Ʒ�۸������ѯ������ؼ��ֲ�ѯ��Ʒ
	 * 
	 * �������ͣ�����һ���˵�GoodsPage.queryGoodsPage()�н����û�ѡ��Ĳ�ѯ��ʽ
	 *			�����˺����Ĳ������ò���������Ϊif-else������ѡ��ִ�е���䣡
	 */
		public ArrayList<Goods> queryGoods(int key) //��ȡ�û���ѯ��ѡ����ȷ������һ��sql,�����������!Ҳ�������ڲ���ʵ��
		{											//���뻹��̫���ӣ���취��try-catch����һ��������������
			ArrayList<Goods> goodsList = new ArrayList<Goods>();
			conn = DbConn.getconn();	
	
			switch (key)
			{
				case 1:
						//4.1��Ʒ ���� �����ѯ-����ʵ��
						String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
						try
						{
							pstmt = conn.prepareStatement(sqlGnum);
							rs = pstmt.executeQuery();
							while (rs.next())
							{
								int gid = rs.getInt("gid");
								String gname = rs.getString(2);
								double gprice = rs.getDouble(3);
								int gnum = rs.getInt(4);
								
								Goods goods = new Goods(gid,gname,gprice,gnum);
								goodsList.add(goods);
							}
						} catch (SQLException e)
						{
							e.printStackTrace();
						}finally
								{
									DbClose.queryClose(pstmt, rs, conn);
								}
					break;
				case 2:
					 	//4.2��Ʒ �۸� �����ѯ-����ʵ��
						String sqlGprice = "SELECT * FROM GOODS ORDER BY GPRICE ASC";
						try
						{
							pstmt = conn.prepareStatement(sqlGprice);
							rs = pstmt.executeQuery();
							while (rs.next())
							{
								int gid = rs.getInt("gid");
								String gname = rs.getString(2);
								double gprice = rs.getDouble(3);
								int gnum = rs.getInt(4);
								
								Goods goods = new Goods(gid,gname,gprice,gnum);
								goodsList.add(goods);
							}
						} catch (SQLException e)
						{
							e.printStackTrace();
						}finally
								{
									DbClose.queryClose(pstmt, rs, conn);
								}
					break;
				default:
						//4.3��Ʒ �ؼ��� ��ѯ��Ʒ-����ʵ��0
						String nameGet = ScannerChoice.ScannerInfoString();
						
//						String sqlGname = "SELECT * FROM GOODS WHERE GNAME LIKE '%'||?||'%'";//Ӧ������д���������ַ���������
						
						String gName = "%"+nameGet+"%";							//���û�����ȡ���ַ������� % ���ţ����ﵽģ����ѯ��Ŀ��
						String sqlGname = "SELECT * FROM GOODS WHERE GNAME LIKE ?";  //��Ȼ����ֱ�Ӹ� % .ֻ���������ַ����ķ�ʽ
						   try
						   {
								pstmt = conn.prepareStatement(sqlGname);
								pstmt.setString(1, gName);
								rs = pstmt.executeQuery();
								while (rs.next())
								{
									int gid = rs.getInt("gid");
									String gname = rs.getString(2);
									double gprice = rs.getDouble(3);
									int gnum = rs.getInt(4);
									
									Goods goods = new Goods(gid,gname,gprice,gnum);
									goodsList.add(goods);
								}
							} catch (SQLException e)
							{
								e.printStackTrace();
							}finally
									{
										DbClose.queryClose(pstmt, rs, conn);
									}
					break;
			}
			return goodsList;
		}
	
	/*
	 * 5.��ʾ������Ʒ-����ʵ�� ��ʵ�֣�
	 */
	public ArrayList<Goods> displayGoods()
	{
		ArrayList<Goods> goodsList = new ArrayList<Goods>(); 
		conn = DbConn.getconn();
		String sql = "SELECT * FROM GOODS";
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
				
				while (rs.next())
				{
					int gid = rs.getInt(1);
					String gname = rs.getString(2);
					double gprice = rs.getDouble("gprice"); //��˫����,��ȻҲ���������ֱ�ʾ����Ҫ�������Լ������ַ�ʽ
					int gnum = rs.getInt(4);
					
					Goods goods = new Goods(gid,gname,gprice,gnum);	//����Goods���󣬲���ֵ��
					goodsList.add(goods);						//��ӵ��������У�
				}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
				{
					DbClose.queryClose(pstmt, rs, conn);
				}
		return goodsList;	//��Ҫ���ظ�������ֵ
		
	}

	/*
	 * �������
	 * 
	 * ѡ�е���Ʒ�����ݿ��н���ɾ������¼��
	 * 
	 * ����ط�ʵ��������sales�������Ʒ���ݣ�
	 * ����ʱ�Լ��ô�ӡ�Ƿ�����ɹ���
	 */
	
		public boolean  shoppingSettlement(Gsales gSales)
		{
			boolean bool = false;
			conn = DbConn.getconn();
			String sql = "INSERT INTO GSALES(GID,SID,SNUM) VALUES(?,?,?)";
				
				try
				{
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1,gSales.getGId());
					pstmt.setInt(2,gSales.getSId());
					pstmt.setInt(3,gSales.getSNum());
			
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
		
}