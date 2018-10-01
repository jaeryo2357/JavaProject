package javaProject;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {

	public Main(){
		setTitle("Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료시 frame 쓰레드도 종료
		setSize(800,600);
		
		
		Container main= getContentPane();
	
		
		
		JPanel c=new JPanel();//Scrollpane에 담을 최종 패널
		//c.setBackground(Color.WHITE);
		c.setBackground(Color.white);
		c.setLayout(null);
		
		
		/* Panel을 이용해  로고 와 검색창 을 넣으려고 한다.
		 * 
		 *  */
		
	
		ImageIcon ILogo=new ImageIcon("./image/Logo.png");
		JButton Logo= new JButton(ILogo);
		Logo.setBackground(Color.white);

		Logo.setBorderPainted(false);// 버튼 테두리 지우기
		Logo.setSize(120,80);
		Logo.setLocation(40,30);
		JTextField search =new JTextField(10);

		search.setSize(170,40);
		search.setLocation(500,10);
		c.add(Logo);
		c.add(search);
		
		
		
		//   메인 내용
		
		ImageIcon ic=new ImageIcon("./image/ad.png");
		JLabel testLabel=new JLabel(ic);
	 	testLabel.setSize(400, 200);
		
	 	testLabel.setLocation(300,100);;		
		
		//realmain.add(new WebtoonList());
		 
		//c.add(realmain);
		c.add(testLabel);
		JPanel List=new WebtoonList();
		
		JScrollPane s=new JScrollPane(List);
		s.setSize(1000,500);
		s.setLocation(300,300);
		
		c.add(s);
		main.add(c);
	
		
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		new Main();
		
	}
}

