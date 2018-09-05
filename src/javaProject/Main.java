package javaProject;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {

	public void TestMain(){
		setTitle("Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료시 frame 쓰레드도 종료
		setSize(1600,900);
		
		Container c=getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(new FlowLayout());
		
		ImageIcon ic=new ImageIcon("./image/ad.png");
		JLabel testLabel=new JLabel(ic);
		
		c.add(testLabel);
		
		  JButton readFile = new JButton("Read File");
		 


		   JButton exit = new JButton("Exit");
		  


		 JButton   stats = new JButton("Stats");
		


		 JButton   blank = new JButton("Clear");
		 
		

		 c.add(readFile); 
		    c.add(exit);
		    c.add(stats);
		    c.add(blank);
		    
		    ActionListener listener=new ActionListener() {   //이벤트추가 -> 버튼을 누르면 해당 웹툰이 보여지게
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		new Webtoon(10);
		    	}
		    };
		    blank.addActionListener(listener);
		
	
		
		setVisible(true);
	}
	
	
	public static void main(String args[])
	{
		Main c=new Main();
		c.TestMain();
	}
}
