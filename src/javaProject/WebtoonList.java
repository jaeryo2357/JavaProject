package javaProject;


import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class WebtoonList extends JPanel{
	
	URLtoGet getjson;
	String getresult;
	
	public WebtoonList() {
		
		
		//getjson=new URLtoGet("");
		//getresult=getjson.GetRespon();  
		setLayout(new GridLayout(0,4));
		setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10)); //상하좌우 10씩 띄우기

		
		for(int i=0;i<10;i++) //받은 getresult의 배열 길이만큼 반복문
		{
			add(new WebtoonImage("",""));
		}
		
		
	}

}
