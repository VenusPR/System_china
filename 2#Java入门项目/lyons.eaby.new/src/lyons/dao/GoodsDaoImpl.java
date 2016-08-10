package lyons.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import lyons.db.DbAccess;
import lyons.goods.entity.Goods;

/**
 * ��Ʒά����-ѧϰ����ӿڱ��
 * 
 * commodity.sql
 */
public class GoodsDaoImpl implements GoodsDao
{

	
	List<GoodsDao> goodsList = new ArrayList<GoodsDao>();
    Goods goods = new Goods();
    GoodsDao goodsDao = null;
    
	DbAccess dbAccess = new DbAccess();
	SqlSession sqlSession = null;
	
	/**
     * 
     * ��ѯ��Ʒ�б�-commodity.sql
     * ���ݣ��ؼ���||���ࣩor���ؼ���&&���ࣩ
     * 
     * �˲�ѯ�������ٸ�Ϊ�ӿ�ʽ��̣���Ϊ֪ʶ����
     * 
     * @return ��Ʒ�б�����
     * 
     */
    public List<Goods> queryGoodsByKeyClassify(Goods goodsList)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            return sqlSession.selectList("lyons.dao.GoodsDao.queryGoodsByKeyClassify", goodsList);//�ռ���.��ѯ���id
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
	 * ��ѯ��Ʒ�б�-commodity.sql
	 * @return ��Ʒ�б�����
	 * 
	 */
	public List<Goods> goodsAllList()
    {
	    try
        {
            sqlSession = dbAccess.getSqlSession();
            goodsDao = sqlSession.getMapper(GoodsDao.class);
            return goodsDao.goodsAllList();
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
     * ��ѯ��Ʒ�б�-commodity.sql
     * ���ݹؼ��ֲ�ѯ
     * @return ��Ʒ�б�����
     * 
     */
	public List<Goods> queryGoodsByKey(String keyWord)
	{
	    try
        {
            sqlSession = dbAccess.getSqlSession();
            goodsDao =  sqlSession.getMapper(GoodsDao.class);
            return goodsDao.queryGoodsByKey(keyWord);
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
     * ɾ��������Ʒ
     * By Goods ID
     */
    public void deleteOneGoodsById(int goodsId)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            
            sqlSession.getMapper(GoodsDao.class).deleteOneGoodsById(goodsId);
            sqlSession.commit();//Ĭ�ϲ��Զ��ύ����Ҫ�ֹ��ύ
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
     * ����ɾ����Ʒ
     */
    public void deleteGoodsByMany()
    {
        
    }
    
    /**
     * ������Ʒ
     */
    public void addGoods()
    {
        
    }
    
    /**
     * ������Ʒ
     */
    public void updateGoods()
    {
        
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
	
	
//	public static void main(String[] args)
//    {
//       GoodsDao gDaoCopy = new GoodsDao();
//       List<Goods> goodsList = gDaoCopy.queryGoods();
//       for (int i = 0,num=goodsList.size(); i < num; i++)
//       {
//           System.out.print("������"+goodsList.get(i).getCommodity_name());
//           System.out.println("\t\t�۸�"+goodsList.get(i).getCommodity_balance());
//                
//       }
//       
//       
//    }
	
	
	
	
	
	
	
	
}