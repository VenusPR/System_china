package lyons.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Goods;

public class GoodsDao extends HttpServlet
{
	CachedRowSetImpl rowSet = null;//�м�����
	/**
	 * Constructor of the object.
	 */
	public GoodsDao()
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;chartset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String value = null;
		value = request.getParameter("key");
		int key = Integer.parseInt(value);
		queryGoods(request, response, key);
		System.out.println("����Ƿ���key:"+key);
	}

	public void init() throws ServletException
	{
		// Put your code here
	}
	
	/**
	 * ��Ʒ��ѯ
	 * @param request
	 * @param response
	 * @param key ��ѯ������/int:4(�򵥲�ѯ)
	 * @return ��Ʒ��Ϣ����
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryGoods(HttpServletRequest request, HttpServletResponse response,int key)
			throws ServletException, IOException
	{	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Goods goods = null;
		
		HttpSession session = request.getSession(true);
		goods = (Goods)session.getAttribute("goods");
//		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		if (goods==null)
		{
			goods = new Goods();
			session.setAttribute("goods", goods);
		}
		
		conn = DbConn.getConn();	

		switch (key)
		{
			case 1:
					/*//	key=1��Ʒ ���� �����ѯ
					String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
					try
					{
						pstmt = conn.prepareStatement(sqlGnum);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally
							{
								DbClose.allClose(pstmt, rs, conn);
							}*/
				break;
			case 2:
				/* 	//	key=2
					String sqlGprice = "SELECT * FROM GOODS ORDER BY GPRICE ASC";
					try
					{
						pstmt = conn.prepareStatement(sqlGprice);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally
							{
							DbClose.allClose(pstmt, rs, conn);
							}*/
				break;
			case 3:
			  /*  //	key=3 ������ɺ��ٴδ����ݿ��л�ȡ��Ʒ������Ϣ
                String sqlListClear= "select * from commodity";
                try
                {
                    pstmt = conn.prepareStatement(sqlListClear);
                    rs = pstmt.executeQuery();
                    System.out.println("3ִ�����ݿ����");
                    while (rs.next())
                    {
                        rowSet = new CachedRowSetImpl();
                        rowSet.populate(rs);
                        goods.setRowSet(rowSet);
                        System.out.println("3�Ѿ������ݿ��л�ȡ��ֵ���������м�");
                    }
                } catch (SQLException e)
                {
                    System.out.println("GoodsDao.java k=3 �ٴβ�ѯʱ�����쳣��"+e);
                    PrintWriter out = response.getWriter();
                    out.print(e+"<br>");
                    out.print("����"+"");
                    out.print("<a href=/lyons.eaby/jsp/shoppingCar/lookShoppingCar.jsp>���ﳵ</a>");
                }finally
                        {
                            DbClose.allClose(pstmt, rs, conn);
                        }*/
                break;
			case 4:
					//key=4 �����Ʒ
					String sqlList= "select * from commodity";
					try
					{
						pstmt = conn.prepareStatement(sqlList);
						rs = pstmt.executeQuery();
						System.out.println("4ִ�����ݿ����");
						while (rs.next())
						{
							rowSet = new CachedRowSetImpl();
							rowSet.populate(rs);
							goods.setRowSet(rowSet);
							System.out.println("4�Ѿ������ݿ��л�ȡ��ֵ���������м�");
							request.getRequestDispatcher("/jsp/browse/showGoods.jsp").forward(request, response);
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
						response.sendRedirect("/lyons.eaby/jsp/browse/showGoods.jsp");
					}finally
							{
								DbClose.allClose(pstmt, rs, conn);
							}
					break;
			default:
				break;
		}
	}

}