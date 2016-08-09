package lyons.goods.service;

import java.util.ArrayList;
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
        
        return goodsDao.queryGoodsByKeyClassify(goodsList);
    }
    
    
    
}
