package lyons.tools;

/**
 * ���N��ɲ������ ѡ����һ��
 * �Լ������x�����
 * @author ����
 *
 */

import java.util.Scanner;

import lyons.page.GoodsPage;
import lyons.page.MainPage;
import lyons.page.SalesManPage;

public final class ScannerChoice
{
	/*
	 * ��ȡ�û�--����ѡ��
	 *         ѡ��ѡ��
	 * @return int
	 */
		public static int ScannerCho()
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("����ѡ��");
			int info = scanner.nextInt();//��ȡ��һ����Ϣ
			System.out.println();
			return info;
		}
		/*
		 * ��ȡ�û�--����ѡ��
		 *         ѡ��ѡ��
		 * @return String
		 */
		public static String ScannerChoString()
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("����ѡ��");
			String info = scanner.next();//��ȡ��һ����Ϣ
			return info;
		}
	/*
	 * ��ȡ�û�--����ѡ��
	 *         ѡ��ѡ��
	 * @return String
	 */
			public static String ScannerChoSting()
			{
				Scanner scanner = new Scanner(System.in);
				System.out.print("����ѡ��");
				String info = scanner.next();//��ȡ��һ����Ϣ
				return info;
			}
		
	/*
	 * ��ȡ�û�--����ѡ��
	 * 		   Y/N ѡ��
	 * @return Srting
	 */
		public static String ScannerNext()
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("����ѡ��");
			String info = scanner.next();//��ȡ��һ����Ϣ
			return info;
		}
	/*
	 * ��ȡ�û�--ݔ��ͼ����Ϣ
	 * @return double 
	 */
		public static double ScannerInfo()
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("�����룺");
			double info = scanner.nextDouble();//��ȡ��һ����Ϣ
			return info;
		}
		/*
		 * ��ȡ�û�--ݔ��ͼ����Ϣ
		 * @return int 
		 */
		public static int ScannerInfoInt()
		{
			Scanner scanner = new Scanner(System.in);
			System.out.print("�����룺");
			int info = scanner.nextInt();//��ȡ��һ����Ϣ
			
			return info;
		}
		
		/*
		 * ��ȡ�û�--ݔ��ͼ����Ϣ
		 * @return Sting 
		 */
			public static String ScannerInfoString()
			{
				Scanner scanner = new Scanner(System.in);
				System.out.print("�����룺");
				String info = scanner.next();//��ȡ��һ����Ϣ
				return info;
			}
		
	/*
	 * ��ȡ�û�-��������Ʒ-��һ��  ��У�飡
	 * ��ȡ�û�-�������Ʒ-��һ��
	 */
		public static void changedInfoNext(String choiceFunction)
		{		
			boolean boolNext = true;
			 do
			{
					System.out.println("�Ƿ��������-��ǰ����:(Y/N)");
					String choice = ScannerChoice.ScannerNext();
				
					 if ("y".equals(choice) || "Y".equals(choice)) //��JAVA: Equals�Ƚϵ���ֵ,==�Ƚϵ��ǵ�ַ
						{
							 boolNext = false;
							//�����Ƕ��if-else �����û�ѡ�����������ǰ��������ת��ָ��ҳ�档����Ϊ��ͬ�������ã���ת��ָ��������ͬ��
							if ("upateGoodsPage".equals(choiceFunction))
								{
									 GoodsPage.upateGoodsPage();
								}else if ("deleteGoodsPage".equals(choiceFunction)) 
										{
											GoodsPage.deleteGoodsPage();
										}else if ("addGoodsPage".equals(choiceFunction))
												 {
			 										GoodsPage.addGoodsPage();
		 					 					 }
								//�����Ƕ�׽���
						}else if ("N".equals(choice) || "n".equals(choice)) 
								{
									boolNext = false;
				 					MainPage.MaintenancePage();
								}
				 	System.out.println("\n������������������.");
			} while (boolNext);
		}
	
		/*
		 * ��ȡ�û�-����-���ۻ�Ա-��һ��
		 * ��ȡ�û�-���-���ۻ�Ա-��һ��
		 * ��ȡ�û�-��ѯ-���ۻ�Ա-��һ��
		 * ��ȡ�û�-ɾ��-���ۻ�Ա-��һ��
		 */
			public static void choiceSalesManNext(String choiceFunction)
			{	
				boolean boolNext = true;
				 do
				{		
						System.out.println("�Ƿ��������-��ǰ����:(Y/N)");
						String choice = ScannerChoice.ScannerNext();
					
						 if ( "y".equals(choice) || "Y".equals(choice) ) //��JAVA: Equals�Ƚϵ���ֵ,==�Ƚϵ��ǵ�ַ  .���Ƚϵĳ�������ǰ�棬��ֱ��ָ���쳣
							{
								 boolNext = false;
								//�����Ƕ��if-else �����û�ѡ�����������ǰ��������ת��ָ��ҳ�档����Ϊ��ͬ�������ã���ת��ָ��������ͬ��
								if ("updateSalesMan".equals(choiceFunction)) //equalsʱ����������ǰ�棬���Ч�ʣ�
									{
										 SalesManPage.updateSalesManPage();
									}else if ("deleteSalesMan".equals(choiceFunction)) 
											{
												SalesManPage.deleteSalesManPage();
											}else if ("addSalesMan".equals(choiceFunction))
													 {
														SalesManPage.addSalesManPage();
			 					 					 }else if ("querySalesMan".equals(choiceFunction)) 
			 					 					 		{
			 					 						 		SalesManPage.querySalesManPage();	
			 					 					 		}  
								//�����Ƕ�׽���
							}else if ("N".equals(choice) || "n".equals(choice)) 
									{
										boolNext = false;
										MainPage.salesManManagementPage();
									}
					 	System.err.println("\t��������");
				} while (boolNext);
			}	
			
			
}
