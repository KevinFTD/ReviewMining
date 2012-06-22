package tagging;
import ICTCLAS.I3S.AC.ICTCLAS50;
import java.util.*;
import java.io.*; 

public class ICTCLAS {
	public static void main(String[] args)
	{
		try
		{
			ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
			//�ִ�������·��
			String argu = ".";
			//��ʼ��
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false){
				System.out.println("Init Fail!");
				return;
			}
			else{ 
				System.out.println("Init Succeed!"); 
			}
	
			String sInput="������س�Ů���������ܹ���ϲ���������б����ڷǵ�֮��";
			byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(sInput.getBytes("GB2312"), 0, 1);
			System.out.println(nativeBytes.length);
			String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
			System.out.println("The result is ��" + nativeStr);
			testICTCLAS50.ICTCLAS_Exit();
		}catch (Exception ex){}
	}
}

