package lyons.entity;
/**
 * ��Ʒ����
 * ���ع��캯��-�M����N��������
 * @author ����
 */
public final class Goods
{
	 	//���ݿ�Goods�����I
		private int gid;
		private String gname;
		private double gprice;
		private int gnum;

	/*
	 * ����-�����Ʒ-��Ϣ
	 */
	public Goods(String gname,double gprice,int gum)
	{
		this.gname  = gname;
		this.gprice = gprice;
		this.gnum 	= gum;
	}
	/*
	 * ����-չʾ������Ʒ-����
	 */
	public Goods(int gid,String gname,double gprice,int gum)
	{
		this.gid	= gid;
		this.gname  = gname;
		this.gprice = gprice;
		this.gnum 	= gum;
	}
	
	/*
	 * ���ݱ��-����-��Ʒ��Ϣ���캯��
	 * �������� int
	 */
	public  Goods(int gid,int gnum)
	{
		this.gid	= gid;
		this.gnum 	= gnum;
	}
	/*
	 * ���ݱ��-����-��Ʒ��Ϣ���캯��
	 * �������� double
	 */
	public  Goods(int gid,double gprice)
	{
		this.gid	= gid;
		this.gprice = gprice;
	}
	
	/*
	 * ���ݱ��-����-��Ʒ��Ϣ���캯��
	 * �������� int
	 */
	public  Goods(int gid,String gname)
	{
		this.gid	= gid;
		this.gname  = gname;
	}

	//����-get��set-���������ĵĴ��a�C������
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
		public double getGprice()
		{
			return gprice;
		}
		public void setGprice(double gprice)
		{
			this.gprice = gprice;
		}
		public int getGnum()
		{
			return gnum;
		}
		public void setGnum(int gnum)
		{
			this.gnum = gnum;
		}						
}
