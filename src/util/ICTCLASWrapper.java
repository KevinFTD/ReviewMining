package util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ICTCLAS.I3S.AC.ICTCLAS50;

public class ICTCLASWrapper {
	
	private static ICTCLAS50 ictclas;
	
	static {
		ictclas = new ICTCLAS50();
		String argu = ".";
		
		try{
			if (ictclas.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
	
	public POSNode[] posTagging(String sent){
		ArrayList<POSNode> posResult = new ArrayList<POSNode>();
		
		try{
			byte[] nativeBytes = ictclas.ICTCLAS_ParagraphProcess(sent.getBytes("GB2312"), 0, 1);
			String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
			
			String[] tmp=nativeStr.split(" ");
			for(int i=0;i<tmp.length;i++){
				int spliter=tmp[i].indexOf('/');
				if(spliter==-1) continue;
				posResult.add( new POSNode(tmp[i].substring(0, spliter), tmp[i].substring(spliter+1)) );
			}
		} catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return posResult.toArray(new POSNode[0]);		
	}
}
