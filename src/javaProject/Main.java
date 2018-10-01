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
		
		
		
		
       //왼쪽 위 로고 넣기
		ImageIcon ILogo=new ImageIcon("./image/Logo.png");
		JButton Logo= new JButton(ILogo);
		Logo.setBackground(Color.white);

		Logo.setBorderPainted(false);// 버튼 테두리 지우기
		Logo.setSize(120,80);
		Logo.setLocation(40,30);
		
		// 검색창과 버튼 넣기
		JTextField search =new JTextField(10);

		search.setSize(170,50);
		search.setLocation(500,30);
		
		JButton searchButton=new JButton("검색");
		searchButton.setBackground(new Color(255,170,2));//로고와 같은색
		searchButton.setSize(75,50);
		searchButton.setForeground(Color.WHITE);
		searchButton.setLocation(680,30);
		
		
		c.add(Logo);
		c.add(search);
		c.add(searchButton);
		
		
		//  장르 검색 탭
		JPanel genre=new JPanel();
		genre.setLayout(new GridLayout(1,0));
		genre.setBackground(new Color(255,170,2));//로고와 같은색
		genre.setSize(850,100);
		genre.setLocation(220,150);
		
		
		//출처: http://unikys.tistory.com/210 [All-round programmer]
	 			
		JButton RomanceButton=new JButton("로맨스");
		//RomanceButton.setSize(180,100);
		RomanceButton.setBackground(new Color(255,170,2));//로고와 같은색
		RomanceButton.setBorderPainted(false);// 버튼 테두리 지우기
		RomanceButton.setForeground(Color.WHITE);//글자 흰색
		RomanceButton.setFont(new Font(RomanceButton.getFont().getName(),Font.BOLD , 28));
		//출처: https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton
		
		
		JButton ThrillerButton=new JButton("스릴러");
		//ThrillerButton.setSize(180,100);
		ThrillerButton.setBackground(new Color(255,170,2));//로고와 같은색
		ThrillerButton.setBorderPainted(false);// 버튼 테두리 지우기
		ThrillerButton.setForeground(Color.WHITE);
		ThrillerButton.setFont(new Font(RomanceButton.getFont().getName(),Font.BOLD , 28));
		
		JButton ActionButton=new JButton("액션");
		//ActionButton.setSize(300,200);
		ActionButton.setBackground(new Color(255,170,2));//로고와 같은색
		ActionButton.setBorderPainted(false);// 버튼 테두리 지우기
		ActionButton.setForeground(Color.WHITE);
	    ActionButton.setFont(new Font(RomanceButton.getFont().getName(),Font.BOLD, 28));
	    
		genre.add(RomanceButton);
		genre.add(ThrillerButton);
		genre.add(ActionButton);
		
		
		c.add(genre);
		//realmain.add(new WebtoonList());
		 
		//c.add(realmain);
		
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

