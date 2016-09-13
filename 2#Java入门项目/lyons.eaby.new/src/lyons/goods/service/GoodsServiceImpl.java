package lyons.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lyons.dao.GoodsDaoImpl;
import lyons.goods.entity.Goods;
import lyons.order.entity.Order;
import lyons.order.service.OrderServiceImpl;


/**
 * 
 * ��Ʒ�������߼���
 * 
 * @author  (Lyons)zhanglei
 * 
 */
public class GoodsServiceImpl
{
    Order order;
    Goods goods;
    String[] goodsArr;
    Map<String, Integer> map;
    List<Order> listOrder;
    List<Goods> Listgoods;
    GoodsDaoImpl goodsDaoImpl;
    
    public GoodsServiceImpl()
    {
        goodsDaoImpl = new GoodsDaoImpl();
    }
    
    /**
     * 
     * ��ѯ��Ʒ����ʵ����
     * ��ѯȫ����Ʒ�б�
     * @return
     */
    public List<Goods> queryList()
    {
        return goodsDaoImpl.goodsAllList();
    }
    
    /**
     * 
     * ��ѯ��Ʒ����ʵ����
     * ���ݹؼ��ֲ�ѯ
     * @return
     */
    public List<Goods> queryGoodsByKey(String keyWord)
    {
        return goodsDaoImpl.queryGoodsByKey(keyWord);
    }

    /**
     * 
     * ��ѯ��Ʒ��Ϣ 
     * ���ؼ���||���ࣩor���ؼ���&&���ࣩ
     * @return
     */
    public List<Goods> queryGoodsByKeyClassify(String keyWord, String goodsClassify)
    {
        Goods goodsList = null;
        goodsList = new Goods();
        
        if (((goodsClassify == null || "".equals(goodsClassify.trim()))
                &&(keyWord == null || "".equals(keyWord))))
        {
            return new ArrayList<Goods>(); //�û��ؼ�������඼û�������ʱ�򷵻ؿռ���
        }
        
        if (!(goodsClassify == null || "".equals(goodsClassify.trim())))
        {
            goodsList.setCommodity_id(Integer.parseInt(goodsClassify));
        }
        if (!(keyWord == null || "".equals(keyWord.trim())))
        {
            goodsList.setCommodity_name(keyWord);
        }
        
        return goodsDaoImpl.queryGoodsByKeyClassify(goodsList);
    }
    
    
    
    
    /**
     * 
     * ������Ʒ
     * <������ϸ����>
     * @param car
     * @return
     * 2-��̤�˶�Ь-����-120-800-002.jpg-1- 10-ipad5-����-5900-500-010.jpg-4- 10-ipad5-����-5900-500-010.jpg-4-
     * 
     * commodity_number = goods[0];//��Ʒ���
     * commodity_name = goods[1];
     * commodity_price = Double.parseDouble(goods[3]);
     * commodity_balance = Integer.parseInt(goods[4]) - 1; //����������Ʒ�������ﵽ�����Ч����-1������Ʒ������һ
     */
    public String BuyGoods(String userName,LinkedList<String> car)
    {
        if (car.size() <= 0){return "���ﳵΪ��";}
        
        map = new HashMap<String, Integer>();
        listOrder = new ArrayList<Order>();
        Listgoods = new ArrayList<Goods>();
        
        map.put("Temp", null);
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();//��ȡorder�������
        
        for (int i = 0; i < car.size(); i++)
        {
            order = new Order();
            goods = new Goods();
            goodsArr = car.get(i).split(",");//һ��String��Ʒ����Ϣ�ָһ��������
            for (int j = 0; j < goodsArr.length; j++)
            {
                order.setUserName(userName);
                order.setCommodity_name(goodsArr[1]);
                order.setCommodity_price(Double.parseDouble(goodsArr[3]));
                order.setSum(1);
                
                goods.setCommodity_number(Integer.parseInt(goodsArr[0]));
                System.out.println(goodsArr[0]+"--kkkkkkkkkkkk");
                if (map.get("Temp") != null )//����map����һ�£�Ŀ��:������
                {
                    map.put("Temp", map.get("Temp")-1);//map�Ѿ����ڸ���Ʒ,�ٴγ��ֹ�ֻ�轫������һ����
                    System.out.println(i+"----�ж��ٰ���"+map.get("Temp"));
                }else{
                       map.put("Temp", Integer.parseInt(goodsArr[4])-1);
                       System.out.println(i+"++++�ж��ٰ���"+map.get("Temp"));
                     }
                if (map.get("Temp") <= 0){return "���ݿ�����Ʒ��������";}
                goods.setCommodity_balance(map.get("Temp"));
                
            }
            listOrder.add(order);
            Listgoods.add(goods);
            map.clear();
            
        }
        
        orderServiceImpl.insertOrderBatch(listOrder);
        goodsDaoImpl.updateGoods(Listgoods);
        
        
        return "���ѽ����ﳵ�е���Ʒ��ؼ���";
    }
    
    
}
