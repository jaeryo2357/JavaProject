package javaProject;

import javax.swing.*;

public class Main extends JFrame {

	public Main(){
		setTitle("Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 종료시 frame 쓰레드도 종료
		setSize(300,300);
		setVisible(true);
	}
	
	
	public static void main(String args[])
	{
		new Main();
	}
}
