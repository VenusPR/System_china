package lyons.user.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lyons.user.service.UserService;

/**
 * ��½����
 * @author Lyons(zhanglei)
 *
 */

@SuppressWarnings("serial")
public class LoginAction extends HttpServlet 
{
    Map<String, String> userMap;
    UserService userService;
    
    @Override
    public void init() throws ServletException
    {
        userMap = new HashMap<String, String>();
        userService = new UserService();
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
        request.setCharacterEncoding("UTF-8");
	    
		userMap.put("username", request.getParameter("username"));
		userMap.put("userpass", request.getParameter("userpass"));
		userMap.put("isCookie", request.getParameter("isCookie"));
		
		userService.userLogin(request,response,userMap);
		
		
		
		
//		handleCookies(request,response,username,userpass,cookies);//����cookies��Ϣ
		
		
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		conn = DbConn.getConn();
//		
//		String sql = "select * from vip where username=? and userpass=?";
//		try
//		{
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, username);
//			pstmt.setString(2, userpass);
//			rs = pstmt.executeQuery();
//			if (rs.next())
//			{
//				//��½�ɹ�
//				success(request,response,username);
//				request.getRequestDispatcher("/jsp/join/landing.jsp").forward(request, response);
//			}else 
//				{
//					String backNews = "�û��������������";
//					fail(request, response, backNews);
//				}
//		} catch (SQLException e)
//		{
//			String backNews = "��¼ʧ��"+e;
//			fail(request, response, backNews);
//		}finally
//			{
//				DbClose.allClose(pstmt, rs, conn);
//			}
	}
	
//	/**
//	 * �����û�cookies��Ϣ
//	 * @param request
//	 * @param response
//	 * @param username
//	 * @param userpass
//	 */
//	public void handleCookies(HttpServletRequest request,HttpServletResponse response, 
//			String name,String pass,String isCookie)throws ServletException, IOException
//	{
//		if ("isCookie".equals(isCookie))//�û�ѡ���˼�ס����
//		{
//			String username = URLEncoder.encode(name,"UTF-8");//���룬���cookie�޷������ַ���������
//			String userpass = URLEncoder.encode(pass,"UTF-8");
//			
//			Cookie nameCookie = new Cookie("username",username );//�������½ʱ��name��Ӧ�ļ�ֵ��
//			Cookie passCookie = new Cookie("userpass",userpass );
//			
//			nameCookie.setPath("/");//���õ�cookie�Ĵ洢·������Ҫ����Ȼȡ����ֵ
//			passCookie.setPath("/");
//			nameCookie.setMaxAge(864000); //������������ʮ�� ��λ��
//			passCookie.setMaxAge(864000);
//			response.addCookie(nameCookie); //������Ϣ
//			response.addCookie(passCookie); 
//		}else 
//			{
//			//�û�δѡ���ס���룬ɾ��������п��ܴ��ڵ�cookie
//				Cookie[] cookies = null;
//				cookies = request.getCookies();
//				if (cookies!=null&&cookies.length>0)
//				{
//					for (Cookie c : cookies)
//					{
//						if ("username".equals(c.getName())||"userpass".equals(c.getName()))
//						{
//							c.setMaxAge(0);//����cookieʧЧ
//							c.setPath("/");//�������
//							response.addCookie(c);
//						}
//					}
//				}
//			}
//	}
	
/*	*//**
	 * ��½�ɹ��������û���Ϣ
	 *//*
	public void success(HttpServletRequest request,
			HttpServletResponse response, String username)
	{
		User loginBean = null;
		HttpSession session = request.getSession(true);
		
		try
		{
			loginBean = (User) session.getAttribute("loginBean");//��ȡsession�п��ܴ��ڵ�loginBean����
			if (loginBean == null)
			{
				loginBean = new User();
				session.setAttribute("loginBean", loginBean);//ע��jsp��ȡʱ��Ҫ�õ���name����������
				session.setMaxInactiveInterval(600);//ʮ���ӵĴ���� ��λ����
				loginBean = (User) session.getAttribute("loginBean");
			}
			
			String name = loginBean.getUsername();
			if (username.equals(name))
			{
				loginBean.setBackNews(username + "���ѵ�½�������ٴε�¼");
				loginBean.setUsername(username);
			} else
				{
					loginBean.setBackNews(username + "��½�ɹ�");
					loginBean.setUsername(username);
				}
		} catch (Exception e)
		{
			String backNews = "��¼ʧ��"+e;
			fail(request, response, backNews);
		}
	
	}*/
	
/*	*//**
	 * ��½ʧ��
	 *//*
	public void fail(HttpServletRequest request,
			HttpServletResponse response,String backNews)
	{
		try
		{
			PrintWriter out = response.getWriter();
			out.print(backNews+"<br>");
			out.print("����"+"<a href=/lyons.eaby.new/jsp/join/login.jsp>��½����</a>");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}*/
}