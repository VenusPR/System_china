package lyons.page;

import java.util.ArrayList;

import lyons.dao.GoodsDao;
import lyons.entity.Goods;
import lyons.tools.QueryPrint;
import lyons.tools.ScannerChoice;


public final class GoodsPage
{

	/*
	 * 1.�����Ʒ ��ʵ�֣� ��У�飡
	 */
			public static void  addGoodsPage()
			{
				
				System.out.println("\t����ִ�������Ʒ����\n");
				
				System.out.println("\nՈݔ�������Ʒ-����");
				String goodsName = ScannerChoice.ScannerChoSting();
				
				System.out.println("\nՈݔ�������Ʒ-�۸�");
				double goodsPrice = ScannerChoice.ScannerInfo();
			
				System.out.println("\nՈݔ�������Ʒ-����");
				int goodsNumber = ScannerChoice.ScannerInfoInt();
				
				Goods goods  = new Goods(goodsName,goodsPrice,goodsNumber); //������Ʒ����
				boolean bool = new GoodsDao().addGoods(goods);
				
					if (bool)
					{
						System.out.println("\n\t!���ѳɹ������Ʒ�����ݿ�!");			
					}else {
						System.out.println("�����Ʒʧ��");	
					}
					//�û�ѡ������� �����Ʒ �����һ����
					ScannerChoice.changedInfoNext("addGoodsPage");
			}

	/*
	 * 2.������Ʒ ��ʵ�֣�
	 *  //ǰ�벿�ִ����Ȼ���ֶ�Σ�������Ʒ��Ϣ��ɾ����Ʒ���ؼ��ֲ�ѯ��Ʒ�����Ȼ�����ϡ�//������
	 */
			public static void  upateGoodsPage()
			{
				System.out.println("\t����ִ�� ������Ʒ ����\n");
				System.out.println("��������Ҫ���ĵ���Ʒ����");
				
					//���ò�����Ʒ��������ʾ��Ҫ���ĵ���Ʒ
					int gid = QueryPrint.query("upateGoodsPage"); //���ص��ǲ�ѯ����Ϣ���
		
					//���� ������Ʒ
				System.out.println("\n--------��ѡ����Ҫ���ĵ�����\n");
				System.out.println("\t1.������Ʒ-����");
				System.out.println("\t2.������Ʒ-�۸�");
				System.out.println("\t3.������Ʒ-����");
				System.out.println("\n������ѡ��,���߰�0������һ���˵�.");
					 boolean boolNext = true;
						 do
						{
							 String choice = ScannerChoice.ScannerChoString();
							 if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice) || "3".equals(choice))
							 { 
								 boolNext = false;
								 int info = Integer.parseInt(choice);
								 switch (info)
								 {
								 case 0:
									 MainPage.MaintenancePage();
									 break;
								 case 1:
									 System.out.println("��������Ʒ-������");
									 String gname = ScannerChoice.ScannerInfoString();
									 Goods goodsName = new Goods(gid,gname);
									 boolean boolName = new GoodsDao().updateGoods(1, goodsName);
									 if (boolName)
									 {
										 System.out.println("\n\t�����ɹ�������Ʒ�������ݿ⣡��\n");
									 }else 
									 	{
										 	System.err.println("\n\t����������Ʒ��ʧ������");
									 	}
									 ScannerChoice.changedInfoNext("upateGoodsPage");
									 break;
								 case 2:
									 System.out.println("��������Ʒ-�¼۸� ");
									 double gprice = ScannerChoice.ScannerInfo();
									 Goods  goodsPrice = new Goods(gid,gprice);
									 boolean boolPrice = new GoodsDao().updateGoods(2,goodsPrice);
									 
									 if (boolPrice)
									 {
										 System.out.println("\n\t�����ɹ�������Ʒ�۸������ݿ⣡��\n");
									 }else 
									 	{
										 	System.err.println("\n\t����������Ʒ�۸�ʧ������");
									 	}
									 ScannerChoice.changedInfoNext("upateGoodsPage");
									 break;
								 case 3:
									 System.out.println("��������Ʒ-������ ");
									 int gNum = ScannerChoice.ScannerInfoInt();	
									 Goods  goodsNum= new Goods(gid,gNum);
									 boolean boolNum = new GoodsDao().updateGoods(3,goodsNum);
									 if (boolNum)
									 {
										 System.out.println("\n\t�����ɹ�������Ʒ���������ݿ⣡��\n");
									 }else 
									 	{
										 	System.err.println("\n\t����������Ʒ����ʧ������");
									 	}
									 ScannerChoice.changedInfoNext("upateGoodsPage");
									 break;
								 default:
									 System.out.println("��������ȷ��ѡ��");
								 break;
								 }
							 }
							 System.err.println("����������");
							System.out.println("������ѡ��,���߰�0������һ���˵�.");
						 } while (boolNext);	
			}
	
	/*
	 * 3.ɾ����Ʒ���� ��ʵ�֣�
	 */
			public static void deleteGoodsPage()
			{
				System.out.println("\t����ִ�� ɾ����Ʒ ����\n");
				System.out.println("��������Ҫɾ������Ʒ����");
				
				//���ò�����Ʒ��������ʾ��Ҫɾ������Ʒ
				int gid = QueryPrint.query("deleteGoodsPage"); //���ص��ǲ�ѯ����Ϣ���
				
				//ȷ���Ƿ����ɾ����
					boolean bool = true;
					do
					{
						System.out.println("\nȷ��ɾ������Ʒ��Y/N");
						String choice = ScannerChoice.ScannerNext();
							if ("y".equals(choice) || "Y".equals(choice))
							{
								bool = false;//����do-whileѭ��
									//���Єh��-���ݿ����
									boolean boolDeleteGoods = new GoodsDao().deleteGoods(gid);//�{�Äh������
									
									if (boolDeleteGoods)
									{
										System.err.println("\t�����ѳɹ��h������Ʒ����\n");
									}else 
										{
											System.err.println("\n\t�����h������Ʒʧ������");
										}
									ScannerChoice.changedInfoNext("deleteGoodsPage"); //
							}else if ("N".equals(choice) || "n".equals(choice)) 
									{
										bool = false;
										MainPage.MaintenancePage();
									}
						System.out.println("\t!!��������,����������!!\n");
					} while (bool);
				
				
			}
	
	/*
	 * 4.��ѯ��Ʒ����   ��ʵ�֣�
	 * ��Ʒ���������ѯ������Ʒ�۸������ѯ������ؼ��ֲ�ѯ��Ʒ
	 */
			public static void queryGoodsPage()
			{
				System.out.println("\t\t  ����ִ�в�ѯ��Ʒ����\n");
				System.out.println("\t\t1.������Ʒ �������� ��ѯ");
				System.out.println("\t\t2.������Ʒ �۸����� ��ѯ");
				System.out.println("\t\t3.������Ʒ  �ؼ���  ��ѯ");
				System.out.println("\n������ѡ��,���߰�0������һ���˵�.");
					
				//˼����1. ����û����������֣���ô�죿	���ܴ���while������
				//�û����ҵ���Ϣ�����ݿ����ޣ������жϣ����Ѿ�������Ȼ���᷽ܽ����
				//�û�ѡ���������ʾ��Ϣ
					 boolean bool = true;
						 do
						{
							 String  info = ScannerChoice.ScannerChoString();
							 if ("0".equals(info) || "1".equals(info) || "2".equals(info) || "3".equals(info))
							 { 
								 bool = false;
								 int choice = Integer.parseInt(info);
								 switch (choice)
								 {
								 case 0:
									 MainPage.MaintenancePage();
									 break;
								 case 1:
								 case 2:
								 case 3:
									 if (choice == 3)//���û�ʹ��3�����ؼ��ֲ�ѯ��ʱ����Ҫ��ӡ����Ŀ��
									 {
										 System.out.println("\t\t����ִ����Ʒ  �ؼ���  ��ѯ����\n");
										 System.out.println("\n��������Ʒ�ؼ���");
									 }
									 //���ò�ѯ����
									 ArrayList<Goods> goodsList = new GoodsDao().queryGoods(choice);
									 if (goodsList == null || goodsList.size() <= 0) //�ܺõĽ�����ж϶�̬�����Ƿ�Ϊ�յ�����
									 {
										 System.err.println("\n\t!!����ѯ����Ʒ������!!\n");
										 queryGoodsPage();
									 } else
									 	  {
												 if (choice == 1) //��ӡĿ¼����Ҫ���ڹ��ܺ����У���Ӱ�������������
												 {	
													 System.out.println("\t\t\t\t\t��Ʒ���� �������� �б�\n\n");
												 }else if (choice == 2) {
													 System.out.println("\t\t\t\t\t��Ʒ���� �۸����� �б�\n\n");
												 }else{
													 System.out.println("\t\t\t\t\t�������ҵ���Ʒ����\n\n");
												 }
												 System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
												 
												 //�������飨����û����ҵ���Ϣ�� 
												 for (int i = 0,length = goodsList.size(); i < length; i++)
												 {
													 Goods goods = goodsList.get(i);
													 System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
													 int gnum = goods.getGnum();
													 if (gnum ==0)
													 {
														 System.out.println("\t\t����Ʒ���ۿգ�");
													 }else if (gnum<10)
													 {
														 System.out.println("\t\t����Ʒ�Ѳ���10��");
													 }else 
													 {
														 System.out.println("\t\t-");
													 }
													 System.out.println("\t");
											  }
												 	System.out.println("---------------------");
													boolean boolNext = true;
													 do
													{
														 System.out.println("���� 0 ������һ���˵�");
														 String choiceNext = ScannerChoice.ScannerChoString();
														
														 if ("0".equals(choiceNext))
														{
															 MainPage.MaintenancePage();
															 bool = false;
														}
														 System.err.println("��������");
													} while (boolNext);
									 	  }
									 break;
								 default:
								 break;
								 }
							 }
							 System.err.println("��������");
							 System.out.println("������ѡ��,���߰�0������һ���˵�.");
						} while (bool);
	
					 //�û�ѡ��������ѯ�����һ��

						System.out.println("\n\n���� 0 ������һ���˵�");
						boolean boolNext = true;
						 do
						{
							 String choice = ScannerChoice.ScannerChoString();
								
							 if ("0".equals(choice))
							 { 
								 boolNext = false;
								 queryGoodsPage();
							 }
							System.err.println("!��������!");
							System.out.println("������ 0 ������һ���˵�");
						} while (boolNext);	
				}

	/*
	 * 5.չʾ������Ʒ���� ��ʵ�֣�
	 */
	public static void displayGoodsPage()
	{
		System.out.println("\t\t\t\t\t������Ʒ�б�\n\n");
		ArrayList<Goods> goodsList = new GoodsDao().displayGoods();
		
		if (goodsList.size() <= 0)
		{
			System.err.println("�����Ϊ�գ�");
			MainPage.MaintenancePage();
		}else 
			{
				System.out.println("\t��Ʒ���\t\t��Ʒ����\t\t��Ʒ�۸�\t\t��Ʒ����\t\t��ע\n");
				for (int i = 0,length = goodsList.size(); i < length; i++) //�����ظ�����������˷���Դ��
				{
					Goods goods = goodsList.get(i);
					System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+" $\t\t"+goods.getGnum());
					
					int gNum = goods.getGnum();
					if (gNum==0)
					{
						System.out.println("\t\t����Ʒ���ۿգ�");
					}else if (gNum<10) 
					{
						System.out.println("\t\t����Ʒ�Ѳ���10��");
					}else
					{
						System.out.println("\t\t-");
					}
					System.out.println("\t");
				}
				//��һ��
				System.out.println("---------------------");
				boolean bool = true;
				do
				{
					System.out.println("���� 0 ������һ���˵�");
					String choice = ScannerChoice.ScannerChoString();
					
					if (choice.equals("0"))
					{
						MainPage.MaintenancePage();
						bool = false;
					}
					System.out.println("��������");
				} while (bool);
			}
	
	}
}
