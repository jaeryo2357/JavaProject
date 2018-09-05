package javaProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;

public class Webtoon extends JFrame {

	public Webtoon(int index)  //나중에 이 인덱스를 가지고 url 통신을 하겠다.
	{
		setTitle("testWebtoon");//보고자하는 웹툰 제목?
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료시 frame 쓰레드도 종료
		setSize(800,600);
		
		Container c=getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(new FlowLayout());
		
		ImageIcon ic=new ImageIcon("./image/ad.png");
		JLabel testLabel=new JLabel(ic);
		
		c.add(testLabel);
		
		 
	
		
		setVisible(true);
	}
}
