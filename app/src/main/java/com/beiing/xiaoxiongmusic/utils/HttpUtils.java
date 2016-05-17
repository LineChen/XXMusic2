package com.beiing.xiaoxiongmusic.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

	//获得网络数据字节数组
	public static byte[] getDataBytes(String path) throws IOException{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5000);
		conn.setDoInput(true);
		
		conn.connect();
		InputStream inputStream = null;
		ByteArrayOutputStream bos = null;
		if(conn.getResponseCode()==200)
		{
			inputStream = conn.getInputStream();
			bos = new ByteArrayOutputStream();
			byte[] arr = new byte[1024];
			int len = 0;
			while((len = inputStream.read(arr))!=-1)
			{
				bos.write(arr,0,len);
			}
		}		
		return bos.toByteArray();
 	}
	
	//得到JSON数据
	public static String getJsonStr(byte[] bytes, String charset) throws UnsupportedEncodingException{
		return new String(bytes, charset);
	}
	
}













