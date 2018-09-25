package javaProject;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class WebtoonList extends JPanel{
	
	URLtoGet getjson;
	String getresult;
	
	public WebtoonList() {
		//getjson=new URLtoGet("");
		//getresult=getjson.GetRespon();  
		setLayout(new GridLayout(0,4));
		
		for(int i=0;i<4;i++) //받은 getresult의 배열 길이만큼 반복문
		{
			add(new WebtoonImage("",""));
		}
		
		
	}

}
