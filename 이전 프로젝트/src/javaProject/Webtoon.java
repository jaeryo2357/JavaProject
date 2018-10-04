package javaProject;

import java.awt.*;

import javax.swing.*;

public class Webtoon extends JFrame {

	
	public Webtoon(int index)  //나중에 이 인덱스를 가지고 url 통신을 하겠다.
	{
		setTitle("testWebtoon");//보고자하는 웹툰 제목?
		
		
		setSize(800,600);
		
		Container c=getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(new BorderLayout());
		
		ImageIcon ic=new ImageIcon("./image/webtoonTest.png");
		JLabel testLabel=new JLabel(ic);
		JLabel testLabel2=new JLabel(ic);
		
	
	
		JPanel test= new JPanel();
		test.setLayout(new GridLayout(2,1));
		// 웹툰 당 이미지 여러개를 스크롤 형식으로 나눠야 하므로 panel에 Scrollpane이 여러개 들어가는 지 확인
		test.add(testLabel);
		test.add(testLabel2);
		
		JScrollPane scrollPane1=new JScrollPane(test);
		
		c.add(scrollPane1,BorderLayout.CENTER);
		 
	
		
		setVisible(true);
	}
}
