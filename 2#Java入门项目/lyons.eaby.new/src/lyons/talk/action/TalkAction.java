package lyons.talk.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TalkAction extends HttpServlet
{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html;chartset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        System.out.println(request.getParameter("content"));
        ;
        System.out.println("================");
        
        out.print("fghj"+"�㷢��");
        
        out.flush();
        out.close();
        
        
    }
    
    public String query()
    {
        System.out.println("-=-=====welcome to Talk.Action-==============");
        
        
        return "��������ǲ�������";
    }
    
}
