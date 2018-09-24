package javaProject;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {

	public Main(){
		setTitle("Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료시 frame 쓰레드도 종료
		setSize(800,600);
		
		Container c=getContentPane();
		//c.setBackground(Color.WHITE);
		c.setLayout(new BorderLayout(10,10));
		
		
		/* Panel을 이용해  로고 와 검색창 을 넣으려고 한다.
		 * 
		 *  */
		
		JPanel mainTop=new JPanel();
		
		JButton Logo= new JButton("LogoTest");
		
		
		JTextField search =new JTextField(10);
		
		mainTop.add(Logo);
		mainTop.add(search);
		
		c.add(mainTop,BorderLayout.NORTH);
		
		
		
		ImageIcon ic=new ImageIcon("./image/ad.png");
		JLabel testLabel=new JLabel(ic);
		
		c.add(testLabel,BorderLayout.CENTER);
		
		
		c.add(new WebtoonList(),BorderLayout.SOUTH);
		    
		 	
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		new Main();
		
	}
}

