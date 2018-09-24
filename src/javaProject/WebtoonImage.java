package javaProject;


import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
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
		
		this.setSize(200, 100);
		
		this.setLayout(new GridLayout(1,2));
		
		try {
		URL url=new URL(imageUrl);
		Webimage=ImageIO.read(url);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		JButton i=new JButton(new ImageIcon(Webimage));
		add(i);
		
		add(new explanPanel("test","test"));
		
		
		
		
	}
	
	
}

class explanPanel extends JPanel{
	public explanPanel(String title,String by) {
		setLayout(new GridLayout(2,1));
		
		JTextField Title=new JTextField();
		Title.setText(title);
		add(Title);
		
		
		JTextField By=new JTextField();
		By.setText(by);
		add(By);
	}
}
