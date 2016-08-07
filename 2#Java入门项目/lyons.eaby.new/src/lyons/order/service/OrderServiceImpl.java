package lyons.order.service;

import java.util.ArrayList;
import java.util.List;

import lyons.dao.OrderDao;
import lyons.order.entity.Order;

/**
 * 
 * �����ӿ�ʵ����
 * @author  lyons(zhanglei)
 */
public class OrderServiceImpl implements OrderService
{
    
    OrderDao dao = new OrderDao();
    
    /**
     * ��ѯ�����û����ж����б�
     * @return
     */
    @Override
    public List<Order> orderAllList()
    {
        return dao.queryOrderAllList();
    }
    
    /**
     * ��ѯ��ǰ�û����ж����б�
     * @param userName
     * @return
     */
    @Override
    public List<Order> orderList(String userName)
    {
        return dao.queryOrderList(userName);
    }
    
    /**
     * �����û���+�ؼ��ֲ�ѯ����
     * @param condition
     * @return
     */
    @Override
    public List<Order> orderListByKeyName(Order condition)
    {
        //����һ����ѯ����Ϊ����ܲ�ѯ�����򷵻ؿն���
        if (!((condition.getUserName()==null||"".equals(condition.getUserName().trim()))&&
            (condition.getKeyWord()==null||"".equals(condition.getKeyWord().trim()))))
            {
                return dao.queryOrderByKeyName(condition);
            }
        
        return new ArrayList<Order>();
    }
    
    /**
     * ɾ�����������С�����
     * @param userName
     * @return
     */
    @Override
    public void deleteOrderOneById(int id)
    {
       dao.deleteOrderOneById(id);
    }
    
}
