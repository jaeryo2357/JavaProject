package javaProject;


import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WebtoonImage extends JPanel {

	String imageUrl; //필요없을듯
	String explan;
	Image Webimage;
	
	
	public WebtoonImage(String imageURL,String explan) {
		this.imageUrl="http://img.naver.net/static/www/u/2013/0731/nmms_224940510.gif";
		this.explan=explan;
	
		
	

	/*	try {
		URL url=new URL(imageUrl);
		Webimage=ImageIO.read(url);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		*/
		
		
		ImageIcon webImage=new ImageIcon("./image/web.PNG");
		//JButton i=new JButton(new ImageIcon(Webimage));
		JButton i=new JButton(webImage);
		
		add(i);
		
		
		JPanel Jexplan=new explanPanel("test","test");
		
		Jexplan.setSize(100,50);
		add(Jexplan);		
		
		
	}
	
	
}

class explanPanel extends JPanel{
	public explanPanel(String title,String by) {
		
		JTextField Title=new JTextField();
		
		Title.setText(title);
		Title.setSize(100,20);
		add(Title);
		
		
		JTextField By=new JTextField();
		By.setSize(100,20);
		By.setText(by);
		add(By);
	}
}
