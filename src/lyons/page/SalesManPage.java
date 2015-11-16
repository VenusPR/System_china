package lyons.page;

import java.util.ArrayList;

import lyons.dao.SalesManDao;
import lyons.entity.SalesMan;
import lyons.tools.QueryPrint;
import lyons.tools.ScannerChoice;

public class SalesManPage
{
	/*
	 * 1.����ۻ�Ա���� ��ʵ�֣�
	 */
	public static void  addSalesManPage()
	{
			System.out.println("\t����ִ������ۻ�Ա����\n");
			
			System.out.println("\n����ۻ�Ա-����");
			String sName = ScannerChoice.ScannerChoSting();
			
			System.out.println("\n����ۻ�Ա-����");
			String sPssswd = ScannerChoice.ScannerChoSting();
			
			SalesMan salesMan = new SalesMan(sName,sPssswd);//�����ۻ�Ա����
			boolean bool = new SalesManDao().addSalesMan(salesMan);
			
				if (bool)
				{
					System.out.println("\n\t!���ѳɹ�����ۻ�Ա�����ݿ�!");
				}else {
					System.out.println("����ۻ�Աʧ��");	
				}
			ScannerChoice.choiceSalesManNext("addSalesMan");
	}
	
	/*
	 * 2.�����ۻ�Ա���� ��ʵ�֣�
	 */
	public static void updateSalesManPage()
	{
		System.out.println("\t����ִ�и����ۻ�Ա����\n");
		System.out.println("��������Ҫ���ĵ��ۻ�Ա����");
		String sName = ScannerChoice.ScannerInfoString();

		//���þ�ȷ�����ۻ�Ա����
		ArrayList<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
			if (salesManList.size() <= 0)
			{
				System.err.println("\t�������޴��ˣ���");
				ScannerChoice.choiceSalesManNext("updateSalesMan"); //ѡ����һ��
			}else 
				{
					//��ʾ��Ҫ���ĵ��ۻ�Ա��Ϣ
					System.out.println("\t\t\t�ۻ�Ա��Ϣ\n\n");
					System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");
					
					SalesMan salesMan = salesManList.get(0); //����ľ�ȷ�����У�ֻ�ܷ���һ���������������
					System.out.println("\t"+salesMan.getSId()+"\t\t\t"+salesMan.getSName()+"\t\t\t"+salesMan.getSPassWord());
					System.out.println();
					
					//ѡ������ۻ�Ա����
					System.out.println("\n--------��ѡ����Ҫ���ĵ�����\n");
					System.out.println("\t1.�����ۻ�Ա-����");
					System.out.println("\t2.�����ۻ�Ա-����");
					boolean boolNext = true;
					do
					{
						String choice = ScannerChoice.ScannerChoString();
						if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice))
						{
							int info = Integer.parseInt(choice);
							boolNext = false;
								switch (info)
								{
								case 0:
										MainPage.salesManManagementPage();
									break;
								case 1:
										System.out.println("�����ۻ�Ա-������");
										String sNewName = ScannerChoice.ScannerInfoString();
										
										SalesMan salesManName = new SalesMan(salesMan.getSId(),sNewName,null);
										boolean boolsName = new SalesManDao().updateSalesMan(1, salesManName);
										
										if (boolsName)
										{
											System.out.println("\n\t�����ɹ������ۻ�Ա���������ݿ⣡��\n");
										}else {
											System.err.println("\n\t���������ۻ�Ա����ʧ������");
										}
										ScannerChoice.choiceSalesManNext("updateSalesMan");
									break;
								case 2:
										System.out.println("�����ۻ�Ա-������");
										String sNewPasswd = ScannerChoice.ScannerInfoString();
										
										SalesMan salesManPasswd = new SalesMan(salesMan.getSId(),null,sNewPasswd);
										boolean boolsPasswd = new SalesManDao().updateSalesMan(2, salesManPasswd);
										
										if (boolsPasswd)
										{
											System.out.println("\n\t�����ɹ������ۻ�Ա���������ݿ⣡��\n");
										}else {
											System.err.println("\n\t���������ۻ�Ա����ʧ������");
										}
										ScannerChoice.choiceSalesManNext("updateSalesMan");
									break;
								default:
									break;
								}
						}
						System.out.println("\t!��������!");
						System.out.println("\n��ѡ��ѡ��.���߰� 0 ������һ���˵�.");
					} while (boolNext);
		
				}
	}

	/*
	 * 3.ɾ���ۻ�Ա����
	 */
	public static void deleteSalesManPage()
	{
		
		System.out.println("\t����ִ�� ɾ���ۻ�Ա ����\n");
		System.out.println("��������Ҫɾ�����ۻ�Ա����");
		String sName = ScannerChoice.ScannerInfoString();
		
		//���þ�ȷ�����ۻ�Ա����
		ArrayList<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
			if (salesManList.size() <= 0)
			{
				System.err.println("\t�������޴��ˣ���");
				ScannerChoice.choiceSalesManNext("deleteSalesMan"); //ѡ����һ��
			}else 
				{
					//��ʾ��Ҫɾ�����ۻ�Ա��Ϣ
					System.out.println("\t\t\tɾ���ۻ�Ա��Ϣ\n\n");
					System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");
					
					for (int i = 0,length = salesManList.size(); i < length; i++)
					{
						SalesMan salesMan = salesManList.get(i);
						System.out.println("\t"+salesMan.getSId()+"\t\t\t"+salesMan.getSName()+"\t\t\t"+salesMan.getSPassWord());
						System.out.println();
					}
					//ȷ���Ƿ����ɾ����
					boolean bool = true;
					do
					{
						System.out.println("\nȷ��ɾ�����ۻ�Ա��Y/N");
						String choice = ScannerChoice.ScannerNext();
						if ("y".equals(choice) || "Y".equals(choice))
						{
							bool = false;//����do-whileѭ��
							//���Єh��-���ݿ����
							
							boolean boolDeleteSalesMan = new SalesManDao().deleteSalesMan(sName);//�{�Äh������
							
							if (boolDeleteSalesMan)
							{
								System.err.println("\t�����ѳɹ��h�����ۻ�Ա����\n");
							}else 
							{
								System.err.println("\t�����h�����ۻ�Աʧ������");
							}
							ScannerChoice.choiceSalesManNext("deleteGoods"); //ѡ����һ��
						}else if ("N".equals(choice) || "n".equals(choice)) 
						{
							bool = false;
							MainPage.salesManManagementPage();
						}
						System.err.println("\t!!��������,����������!!");
					} while (bool);
				}
	}
	
	
	/*
	 * 4.��ѯ�ۻ�Ա���� ��ʵ�֣�
	 */
		public static void querySalesManPage()
		{
			System.out.println("\t\t  ����ִ�в�ѯ�ۻ�Ա����\n");
			System.out.println("Ҫ��ѯ���ۻ�Ա�ؼ���");
			String sName = ScannerChoice.ScannerInfoString();
			
			ArrayList<SalesMan> salesManList = new SalesManDao().querySalesMan(sName);
				
				if (salesManList.size() <=0)
				{
					System.err.println("\t��û����Ա���ϲ�ѯ������");
				}else 
					{
						System.out.println("\t\t\t�����ۻ�Ա�б�\n\n");
						System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");
						
						for (int i = 0,length = salesManList.size(); i < length; i++)
						{
							SalesMan salesMan = salesManList.get(i);
							System.out.println("\t"+salesMan.getSId()+"\t\t\t"+salesMan.getSName()+"\t\t\t"+salesMan.getSPassWord());
							System.out.println();
						}
					}
				ScannerChoice.choiceSalesManNext("querySalesMan"); //���������߱���������˭�ڵ��ã���ȷ��һ�²���ѡ��
		}
	/*
	 * 5.��ʾ�����ۻ�Ա���� ��ʵ�֣�
	 */
	public static void displaySalesManPage()
	{
		ArrayList<SalesMan> salesManList = new SalesManDao().displaySalesMan();
		//SalesManList Ϊ�յ����û�п���//���Ż���
		if (salesManList.size() <= 0)
		{
			System.err.println("\t�����ۻ�Ա�б�Ϊ�գ���");
			MainPage.salesManManagementPage();
		}else 
			{
				System.out.println("\t\t\t�����ۻ�Ա�б�\n\n");
				System.out.println("\t�ۻ�Ա���\t\t�ۻ�Ա����\t\t�ۻ�Ա����");
				
				for (int i = 0,length = salesManList.size(); i < length; i++)
				{
					SalesMan salesMan = salesManList.get(i);
					System.out.println("\t"+salesMan.getSId()+"\t\t\t"+salesMan.getSName()+"\t\t\t"+salesMan.getSPassWord());
					System.out.println();
				}
				//����Ĵ������ȫ��д����,���Ǳ����û�����0. ����û����������֣���ô�죿
				boolean bool = true;
				do
				{
					System.out.println("\n\n���� 0 ������һ���˵�");
					String choice = ScannerChoice.ScannerChoString();
					
					if (choice.equals("0"))
					{
						MainPage.salesManManagementPage();
						bool = false;
					}
					System.err.print("\t��������");
				} while (bool);
			}
	}
}
