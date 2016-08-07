package lyons.user.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyons.entity.Login;

/**
 * 
 * �û�������
 * 
 * @author  lyons(zhanglei)
 */
public class UserService extends HttpServlet
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1001;

    /**
     * 
     * �ж��û��Ƿ��½
     * 
     * @param request
     * @param response
     * @return �û���user
     * @throws ServletException
     * @throws IOException
     */
    public String isLogin(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException
    
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Login username = null;
        
        HttpSession session = request.getSession(true);
        username = (Login)session.getAttribute("loginBean");
       
        if (username==null)
        {
            username = new Login();
            session.setAttribute("username", username);
        }
          //�ж��û��Ƿ��½
          String user = "";
          user = username.getUsername();//��½�ߵ��û���
          if (user.equals("userNull"))
          {
              out.print("<br>");
              out.print("<center>" +
                      		"<font color=#008B8B> �͹٣�</font>" +
                      		"<a href=/lyons.eaby.new/jsp/join/login.jsp><font color=red size=6>��½</font></a>" +
                      		"<font color=#008B8B> ֮����ܲ���Ŷ  </font>" +
              		   "</center>");
              return "";
          }
          return user;
    }
    
}
