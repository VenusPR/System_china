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

public class ScannerChoice
{
	/*
	 * ��ȡ�û�--ݔ��ͼ����Ϣ
	 * ��Ʒ�۸�
	 * @return double 
	 */
		public static double ScannerInfo()
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("����С�������λ,�����룺");
			String info = sc.next();

			String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";//����С�����2λС��
			if (info.matches(regex))
			{
				return Double.parseDouble(info);
			}else 
				{
					System.err.println("����������");
					ScannerInfo();
				}
			return -1.00;
		}
		/*
		 * ��ȡ�û�--ݔ��ͼ����Ϣ
		 * ��Ʒ����
		 * @return int 
		 */
		public static int ScannerNum()
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("�����룺");
			String num = sc.next();
			
			String regex = "([1-9])|([1-9][0-9]+)";//��Ʒ����
			if (num.matches(regex))
			{
				return Integer.parseInt(num);
			}else 
				{
					System.err.println("����������");
					ScannerNum();
				}
			return 0;
		}
		
		/*
		 * *��ȡ�û�--����ѡ��
		 *        --ѡ��ѡ��
		 *        --�û�����
		 * @return Sting 
		 */
			public static String ScannerInfoString()
			{
				Scanner scanner = new Scanner(System.in);
				System.out.print("�����룺");
				return scanner.next();
			}
		
	/*
	 * ��ȡ�û�-��������Ʒ-��һ��  ��У�飡
	 * ��ȡ�û�-�������Ʒ-��һ��
	 */
		public static void changedInfoNext(String choiceFunction)
		{		
			 do
			{
					System.out.println("�Ƿ��������-��ǰ����:(Y/N)");
					String choice = ScannerChoice.ScannerInfoString();
				
					 if ("y".equals(choice) || "Y".equals(choice)) //��JAVA: Equals�Ƚϵ���ֵ,==�Ƚϵ��ǵ�ַ
						{
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
				 					MainPage.MaintenancePage();
								}
				 	System.out.println("\n������������������.");
			} while (true);
		}
	
		/*
		 * ��ȡ�û�-����-���ۻ�Ա-��һ��
		 * ��ȡ�û�-���-���ۻ�Ա-��һ��
		 * ��ȡ�û�-��ѯ-���ۻ�Ա-��һ��
		 * ��ȡ�û�-ɾ��-���ۻ�Ա-��һ��
		 */
			public static void choiceSalesManNext(String choiceFunction)
			{	
				 do
				{		
						System.out.println("�Ƿ��������-��ǰ����:(Y/N)");
						String choice = ScannerChoice.ScannerInfoString();
					
						 if ( "y".equals(choice) || "Y".equals(choice) ) //��JAVA: Equals�Ƚϵ���ֵ,==�Ƚϵ��ǵ�ַ  .���Ƚϵĳ�������ǰ�棬��ֱ��ָ���쳣
							{
								//�����Ƕ��if-else �����û�ѡ�����������ǰ��������ת��ָ��ҳ�档����Ϊ��ͬ�������ã���ת��ָ��������ͬ��
								if ("updateSalesMan".equals(choiceFunction))
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
										MainPage.salesManManagementPage();
									}
					 	System.err.println("\t��������");
				} while (true);
			}	
			
}
