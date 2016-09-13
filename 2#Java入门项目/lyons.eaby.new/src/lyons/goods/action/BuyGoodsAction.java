package lyons.goods.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.goods.entity.Goods;
import lyons.goods.service.GoodsServiceImpl;
import lyons.user.entity.Login;
import lyons.user.service.UserService;
import lyons.util.Iconst;

import com.sun.rowset.CachedRowSetImpl;

@SuppressWarnings("serial")
public class BuyGoodsAction extends HttpServlet
{
    String userName, backNews;
    
    LinkedList<String> car;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        // �ж��Ƿ��½
        userName = (new UserService()).isLogin(request, response).trim();
        if ("".equals(userName) || userName == null){return;}
        
        // ��ģ����ֱ����ȡ���ﳵ��Ϣ
        HttpSession session = request.getSession(true);
        Login loginBean = (Login)session.getAttribute("loginBean");
        car = loginBean.getCar();
        GoodsServiceImpl goodsService = new GoodsServiceImpl();// ��ȡ��Ʒ�������
        
        String backNews = goodsService.BuyGoods(userName,car);// ��������
        messShopping(request,response,backNews);     // ��ӡ������
        car.clear();
        
        //�������и����м�����    ���ϸ�������ʵ�ַ�ʽ
        updateInfo(request, response);
    }
    
    /**
     * 
     * �����ݿ��и����м����� <������ϸ����>
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    private void updateInfo(HttpServletRequest request, HttpServletResponse response)
        throws IOException
    {
        CachedRowSetImpl rowSet = null;// �м�����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Goods goods = null;
        
        HttpSession session = request.getSession(true);
        goods = (Goods)session.getAttribute("goods");
        // ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if (goods == null)
        {
            goods = new Goods();
            session.setAttribute("goods", goods);
        }
        
        conn = DbConn.getConn();
        
        String sqlListClear = "select * from commodity";
        try
        {
            pstmt = conn.prepareStatement(sqlListClear);
            rs = pstmt.executeQuery();
            System.out.println("3ִ�����ݿ����");
            while (rs.next())
            {
                rowSet = new CachedRowSetImpl();
                rowSet.populate(rs);
                // goods.setRowSet(rowSet); ����ע�͵���
                System.out.println("3�Ѿ������ݿ��л�ȡ��ֵ���������м�");
            }
        }
        catch (SQLException e)
        {
            System.out.println("GoodsDao.java k=3 �ٴβ�ѯʱ�����쳣��" + e);
            PrintWriter out = response.getWriter();
            out.print(e + "<br>");
            out.print("����" + "");
            out.print("<a href=/lyons.eaby.new/jsp/shoppingCar/lookShoppingCar.jsp>���ﳵ</a>");
        }
        finally
        {
            DbClose.allClose(pstmt, rs, conn);
        }
        
    }
    
    /**
     * 
     * ��ӡ��Ʒ��������
     * @param request
     * @param response
     * @param mess  ���صĴ�����Ϣ
     * @throws IOException
     */
    public static void messShopping(HttpServletRequest request, HttpServletResponse response, String mess)
        throws IOException
    {
        PrintWriter out = response.getWriter();
        
        out.print(Iconst.buy_goods_success_1 + mess + Iconst.buy_goods_success_2);
    }
}
