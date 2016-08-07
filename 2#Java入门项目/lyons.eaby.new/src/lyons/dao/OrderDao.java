package lyons.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import lyons.db.DbAccess;
import lyons.order.entity.Order;

public class OrderDao
{
    List<Order> orderList =  new ArrayList<Order>();
    DbAccess dbAccess = new DbAccess();
    SqlSession sqlSession = null;
    
    
    /**
     * 
     * ��ѯ��ǰ�û�ȫ�������б�-orderForm.sql
     * 
     * @return ��Ʒ�б�����
     * 
     */
    public List<Order> queryOrderList(String userName)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            return sqlSession.selectList("Order.queryOrderList",userName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
        return null;
    }
    
    /**
     * 
     * ��ѯ�����б�-orderForm.sql
     * �����û���+�ؼ��ֲ�ѯ
     * @return ��Ʒ�б�����
     * 
     */
    public List<Order> queryOrderByKeyName(Order condition)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            return sqlSession.selectList("Order.queryOrderByKeyName", condition);//�����װ���ݶ������
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
        return null;
    }
    
    /**
     * 
     * ��ѯ�����û������б�
     * @return
     */
    public List<Order> queryOrderAllList()
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            return sqlSession.selectList("Order.queryOrderAllList");//�����װ���ݶ������
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
        return null;
    }
    
    
    
    
    
    /**
     * 
     * ������ƷΨһ��ʶɾ������
     * @param id
     */
    public void deleteOrderOneById(int id)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Order.deleteOrderOneById",id);//�����װ���ݶ������
            sqlSession.commit();//ɾ����Ҫ�ύ
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
    }
    
    
    /*
     * �ر����ݿ����ӻỰ
     */
    private void sqlSesionClose()
    {
        if (sqlSession != null)
        {
            sqlSession.close();
        }
    }
    
    
    
    
//    public static void main(String[] args)
//    {
//       List<Order> orderList =null;
//       Order order = new Order();
//       OrderDao orderDao = new OrderDao();
//       
//       order.setKeyWord("Ь");
//       order.setUserName("lyons");
//       orderList = orderDao.queryOrderByKeyName(order);
//       System.out.println(orderList.size());
//       
//       for (int i = 0,num=orderList.size(); i < num; i++)
//       {
//           System.out.print("������"+orderList.get(i).getCommodity_name());
//           System.out.println("\t\t�û�����"+orderList.get(i).getUserName());
//                
//       }
//       
//       
//    }
    
}
