package lyons.goods.service;

import java.util.List;

import lyons.dao.GoodsDao;
import lyons.goods.entity.Goods;

/**
 * 
 * ��Ʒ�������߼���
 * 
 * @author  (Lyons)zhanglei
 * 
 */
public class GoodsServiceImpl implements GoodsService
{
    GoodsDao goodsDao = new GoodsDao();
    
    /**
     * 
     * ��ѯ��Ʒ����ʵ����
     * ��ѯȫ����Ʒ�б�
     * @return
     */
    @Override
    public List<Goods> queryList()
    {
        return goodsDao.queryGoods();
    }
    
    /**
     * 
     * ��ѯ��Ʒ����ʵ����
     * ���ݹؼ��ֲ�ѯ
     * @return
     */
    @Override
    public List<Goods> queryGoodsByKey(String keyWord)
    {
        return goodsDao.queryGoodsByKey(keyWord);
    }
    
    
    
}
