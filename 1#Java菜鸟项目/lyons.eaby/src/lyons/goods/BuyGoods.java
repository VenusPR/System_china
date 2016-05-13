package lyons.goods;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Login;

public class BuyGoods extends HttpServlet
{
    
    /**
     * Constructor of the object.
     */
    public BuyGoods()
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
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //��ģ����ֱ����ȡ���ﳵ��Ϣ
        HttpSession session = request.getSession(true);
        Login loginBean = (Login)session.getAttribute("loginBean");
        LinkedList<String> car = null;
        car = loginBean.getCar();
        
        //���ﳵ�Ƿ�Ϊ�գ������ݿ���ɾ�����������
        if (car.size()!=0)
        {
           /* Connection        conn  = null;
            PreparedStatement pstmt = null;
            
            conn = DbConn.getConn();*/
            
            //����Ʒ���ݱ�����������,����Ӧ  �޸�sqlCommodity��д��sqlOrder
            for (int i = 0,m=car.size(); i < m; i++)
            {
                String[] goods = null;
                goods = car.get(i).split(",");
                    
         /*       String sqlCommodity = "update Commodity set commodity_balance=? where commodity_number=?";
                String sqlOrder = "";
                try
                {
                    pstmt = conn.prepareStatement(sqlCommodity);
                    
                    pstmt.setString(1,username);
                    pstmt.setString(2,userpass); 
                    pstmt.setString(3,phone);
                    pstmt.setString(4,address);
                    pstmt.setString(5,realname);
                    
                    int rs = pstmt.executeUpdate();
                    if (rs > 0)
                    {
                        backNews = "ע��ɹ�";
                        userBean.setBackNews(backNews);
                        request.getRequestDispatcher("/jsp/join/register.jsp")
                                                                    .forward(request, response);
                    }
                } catch (SQLException e)
                {
                    backNews = "���û����ѱ�ע��"+"<br>"+e;
                    userBean.setBackNews(backNews);
                    request.getRequestDispatcher("/jsp/join/register.jsp")
                                                                    .forward(request, response);
                }finally
                {
                    DbClose.close(pstmt, conn);
                }*/
                    
                    
                    
                System.out.println();
            }
            
            
            
            //����ɹ����������ģ���е�����
            car.clear();
        }
        return;
     
    }

    public void init()
        throws ServletException
    {
        // Put your code here
    }
    
}
