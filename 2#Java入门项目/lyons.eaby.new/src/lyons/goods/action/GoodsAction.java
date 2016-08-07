package lyons.goods.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import lyons.goods.entity.Goods;
import lyons.goods.service.GoodsServiceImpl;
import lyons.user.service.UserService;

/**
 * 
 * ������Ʒ
 * @author  lyons(zhanglei)
 * 
 */
public class GoodsAction extends HttpServlet
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 001;

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
        
        //�ж��Ƿ��½
        String user = "";
        user = (new UserService()).isLogin(request, response).trim();
        if ( "".equals(user) || user == null ){return;}
        
        String value = "";
        value = request.getParameter("key");
        int key = Integer.parseInt(value);
        
        String keyWord = "";
        keyWord = request.getParameter("keyWord");
        queryCondition(key,keyWord,request,response);//key �����ѯ������keyWord����Ҫ��ѯ�Ĺؼ���
        
    }

    public void queryCondition(int key, String keyWord,HttpServletRequest request, HttpServletResponse response) 
         throws IOException, ServletException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);

        List<Goods> goodsList = new ArrayList<Goods>();
        GoodsServiceImpl goodsService = new GoodsServiceImpl();//��ȡ��Ʒ�������
        Goods goods = null;
        goods = (Goods)session.getAttribute("goods");
        if (goods==null)
        {
            goods = new Goods();
            session.setAttribute("goods", goods);
        }
        switch (key)
        {
            case 1:
                   //key=1 ����Ա��ѯ����
                    
                break;
            case 2:
                
                    //key=2 ���չؼ��ֲ�ѯ ��Ʒ��Ϣ
                    goodsList = goodsService.queryGoodsByKey(keyWord);
                    if(goodsList.size()>0)
                    {
                        goods.setGoodsList(goodsList);
                        session.setAttribute("goods", goods);
                        System.out.println("2�Ѿ������ݿ��л�ȡ��ֵ���������м�");
                        request.getRequestDispatcher("/jsp/browse/showGoods.jsp").forward(request, response);
                    }else 
                        {
                            out.print("<br><br><br><center>");
                            out.print("<font color=green> ��,��ѯ������.�����ؼ����ٴ� </font>");
                            out.print("<a href=/lyons.eaby.new/jsp/browse/searchByKeyWord.jsp><font color=red size=6>��ѯ</font></a>");
                            out.print("</center>");     
                        }
                    break;
            case 3:
                    break;
            case 4:
                        //��ѯ��Ʒ�б�
                        goodsList = goodsService.queryList();
                        if(goodsList.size()>0)
                        {
                           goods.setGoodsList(goodsList);
                           session.setAttribute("goods", goods);
                           request.getRequestDispatcher("/jsp/browse/showGoods.jsp").forward(request, response);
                        }else 
                            {
                                out.print("<br><br><br><center>");
                                out.print("<font color=green> ��,���һ�û�ϻ��� </font>");
                                out.print("<a href=/lyons.eaby.new/lyons.dao/GoodsDao?key=4><font color=red size=6>������ҳ</font></a>");
                                out.print("</center>");     
                            }
                 
                    break;
            default:
                break;
        }
        
    }
    
}
