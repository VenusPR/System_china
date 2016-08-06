package lyons.order.service;

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
     * ��ѯ��ǰ���ж����б�
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
        return dao.queryOrderByKeyName(condition);
    }
}
