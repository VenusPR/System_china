package lyons.page;
/**
 * �̳��������ϵͳ               
 * @author ����
 * @version 1.0
 */

import java.util.ArrayList;

import lyons.dao.GoodsDao;
import lyons.dao.SalesManDao;
import lyons.entity.Goods;
import lyons.entity.Gsales;
import lyons.entity.SalesMan;
import lyons.tools.QueryPrint;
import lyons.tools.ScannerChoice;

public final class MainPage
{

	/*
	 * ��ں���
	 * @param args
	 */
		public static void main(String[] args)
		{
			MainPage.mianPage();
		}
	/*
	 * ������ ��ʵ�֣���У�飡
	 * @param args
	 */
		public static void  mianPage()
		{
			
			System.out.println("***************************\n");
			System.out.println("\t 1.��Ʒά��\n");
			System.out.println("\t 2.ǰ̨����\n");
			System.out.println("\t 3.��Ʒ����\n");
			System.out.println("***************************");
			
			System.out.println("\n������ѡ��,���߰�0�˳�.");
			{
				boolean bool = true;
				 do
				{
					 String choice = ScannerChoice.ScannerChoString();
						
					 if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice) || "3".equals(choice))
					 { 
						 bool = false;
						 int info = Integer.parseInt(choice);
						 switch (info)
						 {
						 case 0:
							 System.out.println("------------------");
							 System.out.println("���Ѿ��˳�ϵͳ!");
							 System.exit(1);			//�˳����򣬷���ֵ�������
							 break;
						 case 1:
							 MaintenancePage();
							 break;
						 case 2:
							 checkstandLogPage();
							 break;
						 case 3:
							 commodityManagementPage();
							 break;
						 default:
						 break;
						 }
					 }
					 System.err.println("!��������!");
					 System.out.println("����ѡ����߰�0�˳�.");
				} while (bool);
			}
		}
		
	/*
	 * 1.��Ʒά������ ��У�飡
	 */
		public static void MaintenancePage()
		{
			
			System.out.println("***************************\n");
			System.out.println("\t 1.�����Ʒ\n");
			System.out.println("\t 2.������Ʒ\n");
			System.out.println("\t 3.ɾ����Ʒ\n");
			System.out.println("\t 4.��ѯ��Ʒ\n");
			System.out.println("\t 5.��ʾ������Ʒ\n");
			System.out.println("***************************");
			
			System.out.println("\n������ѡ��,���߰� 0 ������һ���˵�.");
			
			boolean bool = true;
			 do
			{
				String choice = ScannerChoice.ScannerChoString();
					
					if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice) || 
						"3".equals(choice) || "4".equals(choice) || "5".equals(choice))
						{
							bool = false;
							int info = Integer.parseInt(choice);
							switch (info)
							{
							case 0:
								mianPage();
								break;
							case 1:
								GoodsPage.addGoodsPage();
								break;
							case 2:
								GoodsPage.upateGoodsPage();
								break;
							case 3:
								GoodsPage.deleteGoodsPage();
								break;
							case 4:
								GoodsPage.queryGoodsPage();
								break;
							case 5:
								GoodsPage.displayGoodsPage();
								break;
							default:
								break;
							}
						}
					System.err.println("!��������!");
					System.out.println("��������� 0 ������һ���˵�.");
			}while(bool);
		}

	/*
	 * 2.ǰ̨������½����  ��ʵ�֣�
	 */
		public static void checkstandLogPage()
		{
			System.out.println("\n*******��ӭʹ���̳��������ϵͳ*******\n");
			System.out.println("\t 1.��¼ϵͳ\n");
			System.out.println("\t 2.�˳�\n");
			System.out.println("-----------------------------");
			System.out.println("������ѡ��,���߰� 0 ������һ���˵�.");
			
			boolean bool = true;
			 do
			{
				 String choice = ScannerChoice.ScannerChoString();
				 if (choice.equals("0") || choice.equals("1") || choice.equals("2"))
					{
					 	bool = false;
					 	int info = Integer.parseInt(choice);
							switch (info)
							{
							case 0:
								bool = false;
								mianPage();
								break;
							case 1:
								bool = false;
								int logTimes = 2;//ʣ���½����,�����λ���
								boolean boolLog = true;
								
								do
								{
									System.out.println("---�û���---");
									String sName = ScannerChoice.ScannerInfoString();
									System.out.println("---����---");
									String sPssWord = ScannerChoice.ScannerInfoString();
									
									ArrayList<SalesMan> salesManInfo = new SalesManDao().checkstandLog(sName); //�����ݿ��л�ȡ�û�����
									
									if (salesManInfo == null || salesManInfo.size() <= 0) //û�д��û��������
									{
										if (logTimes<=0)
										{
											boolLog = false;
											System.out.println("------------------");
											System.err.println("\t�������ѱ�ǿ���˳�ϵͳ����");
											System.exit(1);			//�˳����򣬷���ֵ�������
										}
										System.err.println("\t!!�û�����������!!\n");
										System.out.println("\nʣ���½������"+logTimes);
										logTimes--;
									}else {
											SalesMan salesMan = salesManInfo.get(0); //ֻ������һ����ֵ
										
											if (sPssWord.equals(salesMan.getSPassWord())) //��֤���룬��½�ɹ��ˣ���
											{
												boolLog = false;
												System.out.println("\t  ---�˻��ɹ���½---");
												shoppingSettlementPage(salesMan.getSId()); //��ȡӪҵԱ�ı�ţ���ӪҵԱ�ı�Ŵ����������
											}else {
												if (logTimes<=0)
												{
													boolLog = false;
													System.out.println("------------------");
													System.err.println("\t�������ѱ�ǿ���˳�ϵͳ����");
													System.exit(1);			//�˳����򣬷���ֵ�������
												}
												System.err.println("\t!!�������!!\n");
												System.out.println("\nʣ���½������"+logTimes);
												logTimes--;
											}
									}
								} while (boolLog);
								break;
							case 2:
								bool = false;
								System.out.println("------------------");
								System.out.println("���Ѿ��˳�ϵͳ!");
								System.exit(1);			//�˳����򣬷���ֵ�������
								break;
							default:
							break;
							}
					}
				 System.err.println("!��������!");
				 System.out.println("��������� 0 ������һ���˵�");
			}while(bool);
		}

	/*
	 * 3.��Ʒ�������  ��ʵ�֣�
	 */
		public static void commodityManagementPage()
		{
			System.out.println("***************************\n");
			System.out.println("\t 1.�ۻ�Ա����\n");
			System.out.println("\t 2.�г����������б�\n");
			System.out.println("***************************");
			
			System.out.println("\n������ѡ��,���߰� 0 ������һ���˵�.");
			
			boolean bool = true;
			 do
			{
				String choice = ScannerChoice.ScannerChoString();
					
					if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice))
						{
							bool = false;
							int info = Integer.parseInt(choice);
							switch (info)
							{
							case 0:
								mianPage();
								break;
							case 1:
								salesManManagementPage();
								break;
							case 2:
								GsalesPage.dailySaleGoodsPage();
								break;
							default:
								break;
							}
						}
					System.err.println("!��������!");
					System.out.println("��������� 0 ������һ���˵�.");
			}while(bool);
		}
	
	/*
	 * ����������  ��ʵ�֣�
	 */
		public static void shoppingSettlementPage(int salesManSid)
		{
			System.out.println("\n\t*******�������*******\n");
			
//			int choNext = ScannerChoice.ScannerCho();
			
			
			boolean pressShopping = true; //��Ҫ�ǵ����û�������ȥ��������
			do
			{
				System.out.println("�� S ��ʼ�������.�� 0 �����˻���¼����");
				String choNext = ScannerChoice.ScannerInfoString();
				if (choNext.equals("0"))
				{
					pressShopping = false;
					checkstandLogPage();
					
				}else if (choNext.equals("s") || choNext.equals("S")) 
						{
							pressShopping = false;
							System.out.println("\n--��������Ʒ�ؼ���--");
							
//							String operShopping  = "shoppingSettlementPage";
							int gid = QueryPrint.querySettlement();//����Ʒ��������ֻ��һ��ʱ����gid�ţ�û�з�������ʱ���� -1. >1��ʱ����-2 . ���޴���Ʒʱ����-3
							
							switch (gid)
							{
							case -3:
								//�޴���Ʒ���{��ѡ������ѡ����һ��
								pressShopping = true;
								break;
							case -1:
								System.err.println("\t--��Ǹ������Ʒ���ۿ�--");
								//��Ʒ��Ʒ���{��ѡ������ѡ����һ��
								pressShopping = true;
								break;
								
							default:
								System.out.println("--����Ʒ���ѡ����Ʒ--");
							 		
							// Ĭ���û�������ȷ�ı��
							 	//����gid�����þ�ȷ��ѯ��Ʒ
							 	int shoppingGid = ScannerChoice.ScannerInfoInt();
							 	String shoppingName = null;
								ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(shoppingGid,shoppingName);
								
								if (goodsList == null || goodsList.size() <= 0) //�жϽ�������޴���Ʒ
								{
									System.err.println("\t�������޴���Ʒ ����\n");
									
									//�{��ѡ������ѡ����һ��
									pressShopping = true;
								}else 
								{
									Goods goods = goodsList.get(0); //��ȡ��һ����ֵ��������ʵ����
									int gNum = goods.getGnum();		//��Ʒ����
//									int gId = goods.getGid();			//��ȡ��Ʒid
									double gPrice = goods.getGprice();      //��ȡ��Ʒ�۸�
									String goodsName = goods.getGname();
									
									System.out.println("--�����빺������--");
									
									boolean bool = true;
									do
									{
										int choicegoodsNum=ScannerChoice.ScannerInfoInt();//�û����������
										
										if (choicegoodsNum > gNum)
										{
											//���ڲֿ���Ŀ�ˣ�
											System.err.println("\t�����ֿⴢ�����㣡��");
											System.out.println("--���������빺������--");
										}else 
										{
											bool = false;
											double allPrice = choicegoodsNum*gPrice;
											System.out.println("\t\t\t  ���ﳵ����\n");
											System.out.println("\t\t��Ʒ����\t��Ʒ����\t��������\t�ܼ�\n");
											System.out.println("\t\t"+goodsName+"\t"+gPrice+" $\t"+choicegoodsNum+"\t"+allPrice+" $\n");
											
											boolean boolshopping = true;
											do
											{
												System.out.println("ȷ�Ϲ���Y/N");
												String choShopping = ScannerChoice.ScannerChoSting(); 
												if (choShopping.equals("y") || choShopping.equals("Y"))
												{
													System.out.println("\n�ܼۣ�"+allPrice+" $");
													System.out.println("\nʵ�ʽɷѽ��");
													
													boolean boolBalance = true; //����û��Ƿ��ɷѳ��㣡
													do
													{
														double amount = ScannerChoice.ScannerInfo();
														double balance = amount-allPrice;  //�û���Ǯ�빺����Ʒ�ܼۼ�Ĳ��
														if (balance < 0)
														{
															System.err.println("\t�������ɽ��㣡��");
															System.out.println("\n������������ɽ��($)");
														}else{
															boolBalance = false;
															
															
/*	�����ǹ������������ݿ⣡����������----------------------	//1.��goods������2.����sales������
														��Ʒgid goods.getGid(),��������choicegoodsNum��
														ԭ��Ʒ����gNum������ԱId  salesManSid
														*/
														
															//��sales����в���
															Gsales gSales = new Gsales(goods.getGid(),salesManSid,choicegoodsNum);
															boolean insert = new GoodsDao(). shoppingSettlement(gSales);
															
															//��goods�����
															int goodsNewNum = gNum - choicegoodsNum; //����goods���и���Ʒ����
															Goods newGoods = new Goods(goods.getGid(),goodsNewNum);
															boolean update = new GoodsDao().updateGoods(3,newGoods);
									
																if (update && insert)
																{
																	System.out.println("���㣺"+balance);
																	System.out.println("\nлл���٣���ӭ�´λݹ�");
																}else 
																	{
																		System.err.println("��֧��ʧ�ܣ�"); //�����������һ�������ݿ���������⣡
																	}
															shoppingSettlementPage(salesManSid);//�����ת�����������ҳ��
//	�����������������ݿ⣡����������-----------------------------------
															 }
													} while (boolBalance);	
													
												}else if (choShopping.equals("N") || choShopping.equals("n")) 
												{
													shoppingSettlementPage(salesManSid);
												}
												System.err.println("\t������ȷ�Ϲ������򣡣�");
											} while (boolshopping);
										}
									} while (bool);
								}					
								break;
							}
						}else{
								System.err.println("\t!!������Ϸ��ַ�!!\n");
							 }
			} while (pressShopping);
		}

	/*
	 * �ۻ�Ա������� ��ʵ�֣�
	 */
	public static void salesManManagementPage()
	{

		System.out.println("***************************\n");
		System.out.println("\t 1.����ۻ�Ա\n");
		System.out.println("\t 2.�����ۻ�Ա\n");
		System.out.println("\t 3.ɾ���ۻ�Ա\n");
		System.out.println("\t 4.��ѯ�ۻ�Ա\n");
		System.out.println("\t 5.��ʾ�����ۻ�Ա\n");
		System.out.println("***************************");
		
		System.out.println("\n������ѡ��,���߰� 0 ������һ���˵�.");
		
		boolean bool = true;
		 do
		{
			String choice = ScannerChoice.ScannerChoString();
				
				if (choice.equals("0") || choice.equals("1") || choice.equals("2") || 
					choice.equals("3") || choice.equals("4") || choice.equals("5"))
					{
						bool = false;
						int info = Integer.parseInt(choice);
						switch (info)
						{
						case 0:
							commodityManagementPage();
							break;
						case 1:
							SalesManPage.addSalesManPage();
							break;
						case 2:
							SalesManPage.updateSalesManPage();
							break;
						case 3:
							SalesManPage.deleteSalesManPage();
							break;
						case 4:
							SalesManPage.querySalesManPage();
							break;
						case 5:
							SalesManPage.displaySalesManPage();
							break;
						default:
							break;
						}
					}
				System.err.println("\t!��������!");
				System.out.println("��������� 0 ������һ���˵�.");
		}while(bool);
	}
}

