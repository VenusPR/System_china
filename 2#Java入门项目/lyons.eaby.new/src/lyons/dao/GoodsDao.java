package lyons.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import lyons.db.DbAccess;
import lyons.goods.entity.Goods;
import lyons.order.entity.Order;

/**
 * ��Ʒά����
 * commodity.sql
 */
public class GoodsDao
{

	
	List<Goods> GoodsCopy = new ArrayList<Goods>();
	DbAccess dbAccess = new DbAccess();
	SqlSession sqlSession = null;
	
	/**
	 * 
	 * ��ѯ��Ʒ�б�-commodity.sql
	 * @return ��Ʒ�б�����
	 * 
	 */
	public List<Goods> queryGoods()
    {
	    try
        {
            sqlSession = dbAccess.getSqlSession();
            
            //ִ��sql
            return sqlSession.selectList("Goods.goodsAllList");
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
            return sqlSession.selectList("Goods.queryGoodsByKey", keyWord);//�����������Ϊ���������ж����װ����
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
            
            //ִ��sql
            sqlSession.delete("Goods.deleteOneGoodsById", goodsId);
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