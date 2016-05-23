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
        String userName = "myNull";
        userName = loginBean.getUsername();
        LinkedList<String> car = null;
        car = loginBean.getCar();
        
        //���ﳵ�Ƿ�Ϊ�գ������ݿ���ɾ�����������
        if (car.size()!=0)
        {
            boolean flag = true;//�����Ƿ�ȫ������ɹ�
            
            Connection        conn  = null;
            PreparedStatement pstmtCommodity = null;
            PreparedStatement pstmtOrder = null;
            
            conn = DbConn.getConn();
            
            //����Ʒ�м����ݱ�����������
            for (int i = 0,m=car.size(); i < m; i++)
            {
                String[] goods = null;
                goods = car.get(i).split(",");
                
                String sqlCommodity = null;
                String sqlOrder = null;
                sqlCommodity = "update Commodity set commodity_balance=? where commodity_number=?";
                sqlOrder = "insert into sqlOrder(username,commodity_name,commodity_price,sum) values(?,?,?,?)";
                
                try
                {
                    pstmtCommodity = conn.prepareStatement(sqlCommodity);
                    pstmtOrder = conn.prepareStatement(sqlOrder);
                    
                    pstmtOrder.setString(1,userName);
                    pstmtOrder.setInt(4,1);//Ĭ������Ϊ1������������ѡ��������
                    
                    //����Ʒ���о������ݱ�����������,����Ӧ  �޸�sqlCommodity��д��sqlOrder ��sql���ռλ��
                    for (int j = 0,n=goods.length; j < n; j++)
                    {
                        switch (j)
                        {
                            case 1:
                                    String commodity_number = null;
                                    commodity_number = goods[1];
                                    pstmtCommodity.setString(2,commodity_number);
                                break;
                            case 2:
                                    pstmtOrder.setString(2,goods[2]); 
                                break;
                            case 3:
                                    pstmtOrder.setInt(3,Integer.parseInt(goods[3])); 
                                break;
                            case 4:
                                    int commodity_balance = Integer.parseInt(goods[4]) - 1;//Ŀǰ��Ĭ��ÿ���޸�һ��
                                    if (commodity_balance >= 0)
                                    {
                                        pstmtCommodity.setInt(2,commodity_balance);
                                    }else 
                                        {
                                            String failNumber = "���ݿ�����Ʒ����";
                                            failShopping(request,response,failNumber);
                                            request.getRequestDispatcher("#").forward(request, response);//
                                            return;
                                        }
                                break;
                            default:
                                break;
                        }
                        
                        int rsCommodity = pstmtCommodity.executeUpdate();
                        int rsOrder = pstmtOrder.executeUpdate();
                        if (!(rsCommodity > 0 && rsOrder > 0))//����ʧ��
                        {
                            flag = false;
                            String failError = "�����ݿ�Խ�ʱ�����쳣";
                            failShopping(request,response,failError);
                        }else 
                            {
                                String successBackNews = "���ѽ����ﳵ�е���Ʒ��ؼ���";
                                loginBean.setBackNews(successBackNews);
                                request.getRequestDispatcher("/jsp/order/lookOrderForm.jsp").forward(request, response);
                            }
                        
                    }
                    
                } catch (SQLException e)
                {
                    backNews = "���û����ѱ�ע��"+"<br>"+e;
                    userBean.setBackNews(backNews);
                    request.getRequestDispatcher("/jsp/join/register.jsp").forward(request, response);
                }finally
                {
                    DbClose.close(pstmtOrder,pstmtCommodity, conn);//
                }
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
