package lyons.goods;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        //���ﳵ�Ƿ�Ϊ��
        if (car.size()!=0)
        {
            /**
             * д�������ˣ���һ�������ݿ���д���ɾ��
             */
            for (int i = 0; i < car.size(); i++)
            {
                car.get(4);
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
