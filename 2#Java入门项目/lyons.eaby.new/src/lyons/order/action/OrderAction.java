package lyons.order.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyons.order.entity.Order;
import lyons.order.service.OrderServiceImpl;
import lyons.user.service.UserService;

public class OrderAction extends HttpServlet
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 002;
    

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        
        String value = "";
        value = request.getParameter("key");
        int key = Integer.parseInt(value);
        System.out.println("����Ƿ���key:"+key);
        
        String keyWord = "";
        String queryUserName = "";
        keyWord = request.getParameter("keyWord");
        queryUserName = request.getParameter("queryUserName");
        
        queryCondition(key,queryUserName,keyWord,request,response);//key �����ѯ������keyWord����Ҫ��ѯ�Ĺؼ���
        
    }
    
    public void queryCondition(int key, String keyWord,String queryUserName,HttpServletRequest request, HttpServletResponse response) 
         throws IOException, ServletException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        //�ж��Ƿ��½
        String user = "";
        user = (new UserService()).isLogin(request, response).trim();
        if ( "".equals(user) || user == null ){return;}
          
        OrderServiceImpl orderService = new OrderServiceImpl();//��ȡ�����������
        List<Order> orderList = new ArrayList<Order>();
        Order order = null;
        order = (Order)session.getAttribute("order");
        if (order==null)
        {
            order = new Order();
            session.setAttribute("order", order);
        }
        switch (key)
        {
            case 1:
                    //key=1 ��ѯ��ǰ�û����ж����б�
                
                    //����ѯ��Ϣ��װ��������
                    order = new Order();
                    //��ѯ����
                    orderList = orderService.orderList(user);
                  
                    if(orderList.size()>0)
                    {
                        order.setOrderList(orderList);
                        session.setAttribute("orderList", order);
                        request.getRequestDispatcher("/jsp/order/lookOrderForm.jsp").forward(request, response);
                    }else 
                        {
                            out.print("<br><br><br><center>");
                            out.print("<font color=green> ��,���Ķ����ǿյ��� </font>");
                            out.print("<a href=/lyons.eaby/lyons.dao/GoodsDao?key=4><font color=red size=6>Go Shopping</font></a>");
                            out.print("</center>");     
                        }
                    
                break;
            case 3:
                    //key=3 ����������ѯ���� �û���+��Ʒ�ؼ���(δʹ��)
                
                    //����ѯ��Ϣ��װ��������
                    order = new Order();
                    order.setUserName(queryUserName);
                    order.setKeyWord(keyWord);
                    
                    //��ѯ����
                    orderList = orderService.orderListByKeyName(order);
                  
                    System.out.println("--�鿴����ִ�����ݿ����--");
                    if(orderList.size()>0)
                    {
                        order.setOrderList(orderList);
                        session.setAttribute("orderListByKeyName", order);
                        request.getRequestDispatcher("/jsp/order/lookOrderForm.jsp").forward(request, response);
                    }else 
                        {
                            out.print("<br><br><br><center>");
                            out.print("<font color=green> ��,���ݴ˴����������Ķ����ǿյ��� </font>");
                            out.print("<a href=/lyons.eaby/lyons.dao/GoodsDao?key=4><font color=red size=6>Go Shopping</font></a>");
                            out.print("</center>");     
                        }
                  
                    break;
            default:
                break;
        }
    }  
}
