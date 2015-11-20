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

public final class MainPage extends ScannerChoice
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
				boolean flag = true;
				 do
				{
					 String choice = ScannerChoString();
						
					 if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice) || "3".equals(choice))
					 { 
						 flag = false;
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
				} while (flag);
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
			
			boolean flag = true;
			 do
			{
				String choice = ScannerChoString();
					
					if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice) || 
						"3".equals(choice) || "4".equals(choice) || "5".equals(choice))
						{
							flag = false;
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
			}while(flag);
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
			
			boolean flag = true;
			 do
			{
				 String choice = ScannerChoString();
				 if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice))
					{
					 	flag = false;
					 	int info = Integer.parseInt(choice);
							switch (info)
							{
							case 0:
								flag = false;
								mianPage();
								break;
							case 1:
								flag = false;
								int logTimes = 2;//ʣ���½����,�����λ���
								boolean flagLog = true;
								
								do
								{
									System.out.println("---�û���---");
									String sName = ScannerInfoString();
									System.out.println("---����---");
									String sPssWord = ScannerInfoString();
									
									ArrayList<SalesMan> salesManInfo = new SalesManDao().checkstandLog(sName); //�����ݿ��л�ȡ�û�����
									
									if (salesManInfo == null || salesManInfo.size() == 0) //û�д��û��������
									{
										if (logTimes == 0)
										{
											flagLog = false;
											System.out.println("------------------");
											System.err.println("\t�������ѱ�ǿ���˳�ϵͳ����");
											System.exit(1);			//�˳����򣬷���ֵ�������
										}
										System.err.println("\t!!�û�����������!!\n");
										System.out.println("\nʣ���½������"+logTimes);
										logTimes--;
									}else 
										{
											SalesMan salesMan = salesManInfo.get(0); //�˵أ�ֻ������һ����ֵ��ֻ����1�μ���
										
											if (sPssWord.equals(salesMan.getSPassWord())) //��֤���룬��½�ɹ��ˣ���
											{
												flagLog = false;
												System.out.println("\t  ---�˻��ɹ���½---");
												shoppingSettlementPage(salesMan.getSId()); //��ȡӪҵԱ�ı�ţ���ӪҵԱ�ı�Ŵ����������
											}else 
												{
													if (logTimes == 0)
													{
														flagLog = false;
														System.out.println("------------------");
														System.err.println("\t�������ѱ�ǿ���˳�ϵͳ����");
														System.exit(1);			//�˳����򣬷���ֵ�������
													}
													System.err.println("\t!!�������!!\n");
													System.out.println("\nʣ���½������"+logTimes);
													logTimes--;
												}
										}
								} while (flagLog);
								break;
							case 2:
								flag = false;
								System.out.println("------------------");
								System.out.println("���Ѿ��˳�ϵͳ!");
								System.exit(-1);			//�˳����򣬷���ֵ�������
								break;
							default:
							break;
							}
					}
				 System.err.println("!��������!");
				 System.out.println("��������� 0 ������һ���˵�");
			}while(flag);
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
			
			boolean flag = true;
			 do
			{
				String choice = ScannerChoString();
					
					if ("0".equals(choice) || "1".equals(choice) || "2".equals(choice))
						{
							flag = false;
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
			}while(flag);
		}
	
	/*
	 * ����������  ��ʵ�֣�
	 */
		public static void shoppingSettlementPage(int salesManSid)
		{
			System.out.println("\n\t*******�������*******\n");
			
			boolean pressShopping = true; //��Ҫ�ǵ����û�������ȥ��������
			do
			{
				System.out.println("�� S ��ʼ�������.�� 0 �����˻���¼����");
				String choNext = ScannerInfoString();
				if ("0".equals(choNext))
				{
					pressShopping = false;
					checkstandLogPage();
					
				}else if ("s".equals(choNext) || "S".equals(choNext)) 
						{
							pressShopping = false;
							System.out.println("\n--��������Ʒ�ؼ���--");
							
							int gid = QueryPrint.querySettlement();//����Ʒ��������ֻ��һ��ʱ������Ʒgid�ţ���Ʒ���ۿ�ʱ���� -1. >1��ʱ����-2 . ���޴���Ʒʱ����-3
							
							switch (gid)
							{
							case -3:
								//�޴���Ʒ,����ѭ��
								pressShopping = true;
								break;
							case -1:
								pressShopping = true;
								System.err.println("\t--��Ǹ������Ʒ���ۿ�--");
								break;
								
							default:
								System.out.println("--����Ʒ���ѡ����Ʒ--");
							 		
							// Ĭ���û�����ı����int����
							 	//����gid�����þ�ȷ��ѯ��Ʒ
							 	int shoppingGid = ScannerInfoInt();
								
							 	ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(shoppingGid,null);
								if (goodsList == null || goodsList.size() == 0) //�жϽ�������޴���Ʒ
								{
									System.err.println("\t�������޴���Ʒ ����\n");
									pressShopping = true;
								}else 
									{
										Goods goods = goodsList.get(0); //��ȡ��һ����ֵ��������ʵ����
										int gNum = goods.getGnum();		//��Ʒ����
										double gPrice = goods.getGprice();      //��ȡ��Ʒ�۸�
										
										System.out.println("--�����빺������--");
										boolean flag = true;
										do
										{
											int choicegoodsNum = ScannerInfoInt();//��ȡ�û�Ҫ���������
											
											if (choicegoodsNum > gNum)
											{
												//���ڲֿ���Ŀ�ˣ�
												System.err.println("\t�����ֿⴢ�����㣡��");
												System.out.println("--���������빺������--");
											}else 
											{
												flag = false;
												double allPrice = choicegoodsNum*gPrice; //double �� int �����㣬java����ĺ��ã�BigDecimal��������ϵͳ��java�����ÿ���
												System.out.println("\t\t\t  ���ﳵ����\n");
												System.out.println("\t\t��Ʒ����\t��Ʒ����\t��������\t�ܼ�\n");
												System.out.println("\t\t"+goods.getGname()+"\t"+gPrice+" $\t"+choicegoodsNum+"\t"+allPrice+" $\n");
												
												boolean flagshopping = true;
												do
												{
													System.out.println("ȷ�Ϲ���Y/N");
													String choShopping = ScannerChoSting(); 
													if (choShopping.equals("y") || choShopping.equals("Y"))
													{
														System.out.println("\n�ܼۣ�"+allPrice+" $");
														System.out.println("\nʵ�ʽɷѽ��");
														
														boolean flagBalance = true; //����û��Ƿ��ɷѳ��㣡
														do
														{
															double amount = ScannerInfo();
															double balance = amount-allPrice;  //�û���Ǯ�빺����Ʒ�ܼۼ�Ĳ��
															if (balance < 0)
															{
																System.err.println("\t�������ɽ��㣡��");
																System.out.println("\n������������ɽ��($)");
															}else{
																flagBalance = false;
																
																
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
														} while (flagBalance);	
														
													}else if (choShopping.equals("N") || choShopping.equals("n")) 
													{
														shoppingSettlementPage(salesManSid);
													}
													System.err.println("\t������ȷ�Ϲ������򣡣�");
												} while (flagshopping);
											}
										} while (flag);
									}					
								break;
							}
						}else
							{
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
		
		boolean flag = true;
		 do
		{
			String choice = ScannerChoString();
				
				if (choice.equals("0") || choice.equals("1") || choice.equals("2") || 
					choice.equals("3") || choice.equals("4") || choice.equals("5"))
					{
						flag = false;
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
		}while(flag);
	}
}

