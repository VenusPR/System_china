package lyons.goods.service;

import java.util.List;

import lyons.goods.entity.Goods;

/**
 * 
 * ��Ʒ�ӿ�
 * 
 * @author  (Lyons)zhanglei
 * 
 */
public interface GoodsService
{
    /** ��ѯ��Ϣ�б� **/
    public List<Goods> queryList();
    
    /** ���ݹؼ��ֲ�ѯ��Ʒ��Ϣ **/
    public List<Goods> queryGoodsByKey(String condition);
}
