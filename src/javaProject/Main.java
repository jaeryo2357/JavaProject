package javaProject;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {

	private WebtoonButtonPanel panel= new WebtoonButtonPanel();
	public Main(){
		setTitle("Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료시 frame 쓰레드도 종료
		setSize(800,600);
		
		Container c=getContentPane();
		//c.setBackground(Color.WHITE);
		c.setLayout(new BorderLayout(10,10));
		
		
		
		ImageIcon ic=new ImageIcon("./image/ad.png");
		JLabel testLabel=new JLabel(ic);
		
		c.add(testLabel,BorderLayout.CENTER);
		
		
		c.add(panel,BorderLayout.SOUTH);
		    
		 
		
	
		
		setVisible(true);
	}
	
	class WebtoonButtonPanel extends JPanel{   //여기서 URL 통신으로 동적으로 버튼을 생성해야함
		
		public WebtoonButtonPanel(){
			
			
			ImageIcon ic=new ImageIcon("./image/test.png");
			JLabel testLabel=new JLabel(ic);
			JButton black=new JButton(ic);
		JButton blank=new JButton(ic);
		add(blank);
		add(black);
		   ActionListener listener=new ActionListener() {   //이벤트추가 -> 버튼을 누르면 해당 웹툰이 보여지게
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		new Webtoon(10);
		    	}
		    };
		    blank.addActionListener(listener);
		}
	}
	
	
	public static void main(String args[])
	{
		new Main();
		
	}
}

