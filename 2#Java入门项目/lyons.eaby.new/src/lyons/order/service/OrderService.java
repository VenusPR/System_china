package lyons.order.service;

import java.util.List;

import lyons.order.entity.Order;
import lyons.service.publicService;

/**
 * 
 * �����ӿ�
 * @author  lyons(zhanglei)
 */
public interface OrderService extends publicService
{
    /** �����û�������Ʒ�ؼ��ֲ�ѯ���� **/
    public List<Order> orderListByKeyName(Order order);
    
    /** ��ѯ��ǰ�û������б� **/
    public List<Order> orderList(String userName);
    
    /** ��ѯ�����û������б� **/
    public List<Order> orderAllList();
    
    
    /** ������ƷΨһ��ʶɾ������ **/
    public void deleteOrderOneById(int id);
}
