package javaProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLtoGet {

	private String url;
	private StringBuffer param;
	
	public URLtoGet(String url)
	{
		this.url=url;
		
	}
	
	public void addPram(String key,String value)
	{
		if(!param.toString().equals("?"))
			this.param.append("&");
		
		this.param.append(String.format("%s=%s",key,value));
	}
	
	public void setUrl(String url)
	{
		this.url=url;
	}
	
	public String GetRespon() {
	    URL targeturl = null;
	    HttpURLConnection conn = null;
	    String jsonData = "";
	    BufferedReader br = null;
	    StringBuffer sb = null;
	    String returnText = "";
	 
	    try {
	        targeturl = new URL(url);
	 
	        conn = (HttpURLConnection)targeturl.openConnection();
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestMethod("GET");
	        conn.connect();
	 
	        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	 
	        sb = new StringBuffer();
	 
	        while ((jsonData = br.readLine()) != null) {
	            sb.append(jsonData);
	        }
	 
	        returnText = sb.toString();
	 
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (br != null) br.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    return returnText;
	}


}
