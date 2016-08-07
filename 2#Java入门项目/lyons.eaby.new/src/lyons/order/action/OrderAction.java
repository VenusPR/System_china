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
        /**
         *      jsp������key����˵��
         * 1����ѯ��ǰ�û����ж����б�
         * 2����ѯ�����û������б�
         * 3������������ѯ���� �û���+��Ʒ�ؼ���(δʹ��)
         * 4��ɾ����������
         * 5��
         * 6��
         */
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //�ж��Ƿ��½
        String user = "";
        user = (new UserService()).isLogin(request, response).trim();
        if ( "".equals(user) || user == null ){return;}
        
        String id = "";                         //��ƷΨһ��ʶid 
        String key = "";                        //ѡ���ѯ����
        String keyWord = "";                    //��ѯ�Ĺؼ���
        String queryUserName = "";              //��ѯ���û���
        id = request.getParameter("id");
        key = request.getParameter("key");
        keyWord = request.getParameter("keyWord");
        queryUserName = request.getParameter("queryUserName");
        
        String str[] = {id,key,keyWord,queryUserName,user};  
        queryCondition(str,request,response);//key �����ѯ������keyWord����Ҫ��ѯ�Ĺؼ���
        
    }
    /**
     * 
     * ��������ѡ���ѯ��ҵ��
     * @param str
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void queryCondition(String str[],HttpServletRequest request, HttpServletResponse response) 
         throws IOException, ServletException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        String idStr = str[0];             //��ƷΨһ��ʶid
        String keyStr = str[1];            //ѡ���ѯ����
        String keyWord = str[2];           //��ѯ�Ĺؼ���
        String queryUserName = str[3];     //��ѯ���û���
        String user = str[4];              //��ǰ�û�
        
        int id = -1;
        if (!(idStr==null || "".equals(idStr)))
        {
            id = Integer.parseInt(idStr);
        }
        int key = Integer.parseInt(keyStr);
          
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
                            out.print("<a href=/lyons.eaby.new/lyons.dao/GoodsDao?key=4><font color=red size=6>Go Shopping</font></a>");
                            out.print("</center>");     
                        }
                    
                break;
            case 2:
                    //��ѯ�����û������б�
                
                    order = new Order();
                    //��ѯ����
                    orderList = orderService.orderAllList();
                  
                    //������û�в�ѯ��ֵ��Ҫ���ض����б�
                    order.setOrderList(orderList);
                    session.setAttribute("orderAllList", order);
                    request.getRequestDispatcher("/jsp/manageGoods/OrderList.jsp").forward(request, response);
                    
                break;
            case 3:
                    //key=3 ����������ѯ���� �û���+��Ʒ�ؼ���(δʹ��)
                
                    //����ѯ��Ϣ��װ��������
                    order = new Order();
                    order.setUserName(queryUserName);
                    order.setKeyWord(keyWord);
                    
                    
                    //��ѯ����
                    orderList = orderService.orderListByKeyName(order);
                    
                    order.setOrderList(orderList);
                    session.setAttribute("orderAllList", order);
                    request.getRequestDispatcher("/jsp/manageGoods/OrderList.jsp").forward(request, response);
                  
                    break;
              case 4:
                      //ɾ����������
                      order = new Order();
                      System.out.println("��ʼɾ��");
                      orderService.deleteOrderOneById(id);       //ɾ������
                      System.out.println("��ʼ��ѯ");
                      orderList = orderService.orderAllList();   //���²�ѯ�����б�
                      System.out.println("��ѯ����");
                    
                      //������û�в�ѯ��ֵ��Ҫ���ض����б�
                      order.setOrderList(orderList);
                      session.setAttribute("orderAllList", order);
                      request.getRequestDispatcher("/jsp/manageGoods/OrderList.jsp").forward(request, response);
                  
                  break;
              case 5:
                  break;
              case 6:
                  break;
            default:
                break;
        }
    }  
}
