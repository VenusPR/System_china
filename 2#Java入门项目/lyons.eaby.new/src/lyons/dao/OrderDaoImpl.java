package lyons.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import lyons.db.DbAccess;
import lyons.order.entity.Order;

/**
 * ����ά����
 * 
 * orderForm.sql
 */
public class OrderDaoImpl implements OrderDao
{
    List<Order> orderList =  new ArrayList<Order>();
    OrderDao orderDao = null;
    DbAccess dbAccess = new DbAccess();
    SqlSession sqlSession = null;
    
    
    /**
     * 
     * ��ѯ��ǰ�û�ȫ�������б�-orderForm.sql
     * 
     * @return ��Ʒ�б�����
     * 
     */
    public List<Order> queryOrderListByuserName(String userName)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            return orderDao.queryOrderListByuserName(userName);
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
            orderDao = sqlSession.getMapper(OrderDao.class);
            return orderDao.queryOrderByKeyName(condition);//�����װ���ݶ������
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
            orderDao = sqlSession.getMapper(OrderDao.class);
            return orderDao.queryOrderAllList();
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
     * ������ƷΨһ��ʶɾ����������
     * @param id
     */
    public void deleteOrderOneById(int id)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            orderDao.deleteOrderOneById(id);
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
    
    /**
     * 
     * ����ɾ������
     * @param ids
     */
    public void deleteOrderBatch(List<Integer> ids)
    {
        
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            orderDao.deleteOrderBatch(ids);
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
    
    
}
