package lyons.goods.entity;

/**
 * 
 * ��Ʒ���ʵ����
 * 
 * @author  lyons(zhanglei)
 */
public class Classify
{
    private int gid;      //��Ʒ�������
    private String gname; //��Ʒ����
    
    public Classify(){};
    
    public int getGid()
    {
        return gid;
    }
    public void setGid(int gid)
    {
        this.gid = gid;
    }
    public String getGname()
    {
        return gname;
    }
    public void setGname(String gname)
    {
        this.gname = gname;
    }
}
