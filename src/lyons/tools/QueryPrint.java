package lyons.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lyons.dao.GoodsDao;
import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Goods;
import lyons.entity.SalesMan;

public final class QueryPrint
{
	/*
	 * ��ȷ��ѯ����С����
	 * �ɵ��ô˴���ĺ����� ������Ʒ	GoodsPage.upateGoodsPage()
	 * 				     ɾ����Ʒ  GoodsPage.deleteGoodsPage()
	 * 	@return int gid				  
	 */
		public static int query(String oper) //��������ȷ���˺���������.���a�Ż��ˣ�
		{
			int gid = -1;	//�������ز�ѯ������Ϣ��gid		
			int shoppingGid = -1;								//�������������ð�����Ʒ��-ѡ����Ʒ����
			String shopping = ScannerChoice.ScannerInfoString(); //����������������Ʒ����-����Ϊ����������ȷ���Һ���
			ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(shoppingGid,shopping);  //�{�� ��ȷ��ѯ���� QueryPrint.queryGoodsKey() �_���Ñ���Ҫ����������
			    if (goodsList == null || goodsList.size() <= 0) //�жϽ�������޴���Ʒ
				{
						System.err.println("\t�������޴���Ʒ ����");
						
						//�{��ѡ������ѡ����һ��
						ScannerChoice.changedInfoNext(oper);
						
				}else	//�鵽�д���Ʒ��ʵ�ֽ��� ������Ʒ ��Ϣ������
				{		//��Щ�ظ��Ĵ����������£�����
					Goods goods = goodsList.get(0);
					
					System.out.println("\t\t\t\t\t��Ʒ�б�\n\n");
					System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
					System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
					if (goods.getGnum()==0)
					{
						System.out.println("\t\t����Ʒ���ۿ�");
					}else if (goods.getGnum()<10)
							{
								System.out.println("\t\t����Ʒ�Ѳ���10��");
							}else {
								System.out.println("\t\t-");
							}
					gid =	goods.getGid(); //����Ʒ��ŷ��ظ�������
					
				}
		 return gid;
		} 
	
	/*
	 * ģ����ѯ����С����
	 * �ɵ��ô˴���ĺ����� �������	GoodsPage.shoppingSettlement()
	 * 					  
	 */
		public static int querySettlement() 
		{
			int gid = -1;	//�������ش�����Ϣ��gid����ʱ������Ʒ���ۿգ����ط�ʽ���Ǻܺô��޸ģ�
			ArrayList<Goods> goodsSettlement= new GoodsDao().queryGoods(0);  //�{�� ģ����ѯ���� GoodsDao.queryGoods() �_���Ñ���Ҫ����������
			    if (goodsSettlement == null || goodsSettlement.size() <= 0) //�жϽ�������޴���Ʒ
				{
						System.err.println("\t�������޴���Ʒ ����\n");
						gid = -3; //����ֵ�����޴���Ʒ		
				}else	//�鵽�д���Ʒ��ʵ�ֽ��� ������Ʒ ��Ϣ������
					{		//��Щ�ظ��Ĵ����������£�����
						System.out.println("\t\t\t\t\t��Ʒ�б�\n\n");
						System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
						for (int i = 0; i < goodsSettlement.size(); i++)
						{
							Goods goods = goodsSettlement.get(i);
							if (goods.getGnum() > 0)
							{
								System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
									
								if (goods.getGnum()==0)
								{
									System.out.println("\t\t����Ʒ���ۿ�");
								}else if (goods.getGnum()<10)
										{
											System.out.println("\t\t����Ʒ�Ѳ���10��");
										}else 
											{
												System.out.println("\t\t-");
											}
									if (goodsSettlement.size()==1)
									{
										gid = goods.getGid(); //����Ʒ��ŷ��ظ�������,
									}else 
										{
										gid = -2;
										}
							}
						}
					}
		 return gid; //����Ʒ��������ֻ��һ��ʱ������Ʒgid�ţ���Ʒ���ۿ�ʱ���� -1. >1��ʱ����-2 . ���޴���Ʒʱ����-3
		} 
	

	/*
	 *
	 * ��ȷ��ѯ��Ʒ����  //�����Σ����Ƶ�ģ����ѯ�������½����ĺ������󶯵��ˡ� ֻ��sql����������д��
	 */
		public ArrayList<Goods> queryGoodsKey(int  gId,String gName) 
		{										
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet 		rs 	 	 = null;
			ArrayList<Goods> goodsList = new ArrayList<Goods>();
			conn = DbConn.getconn();	
			
				//��Ʒ ��ȷ ��ѯ��Ʒ-����ʵ��
				String sql = "SELECT * FROM GOODS WHERE GID=? OR GNAME=?"; 
				   try
				   {
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, gId);
						pstmt.setString(2, gName);
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
			
			return goodsList;
		
		}	
		
	/*
	 * ��ȷ��ѯ�ۻ�Ա��Ϣ����
	 * �����ߣ� ɾ���ۻ�Ա������SalesManPage.deleteSalesManPage()
	 */
	
		public ArrayList<SalesMan> querySalesMan(String sName)
		{
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet 		rs 	 	 = null;
			ArrayList<SalesMan> SalesManList = new ArrayList<SalesMan>();
			conn = DbConn.getconn();	
			String sql = "SELECT * FROM SALESMAN WHERE SNAME=?";  //��Ȼ����ֱ�Ӹ� % .ֻ���������ַ����ķ�ʽ
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
}
