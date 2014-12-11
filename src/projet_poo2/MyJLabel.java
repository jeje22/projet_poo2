package projet_poo2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JLabel;



public class MyJLabel extends JLabel {

	private static final long serialVersionUID = 1L;
	protected BufferedImage image;
	protected BufferedImage croix;
	protected int widthPar2;
	protected int heightPar2;
	private ArrayList<Point> reference = new ArrayList<Point>();
	
	public MyJLabel(){
		super();
	}
	
	public MyJLabel(String s){
		super(s);
	}
	
	public MyJLabel(Icon i){
		super(i);
	}
	
	public MyJLabel(BufferedImage pic){
		this.image = pic;
	}
	
	public void setImage() {
		try {
			//image = ImageIO.read(new File(path));
			URL url = getClass().getResource("croix.png");
			if(url == null){
				url = new URL("croix.png");
			}
			croix =  ImageIO.read(url);
			widthPar2 = croix.getWidth()/2;
			heightPar2 = croix.getHeight()/2;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPoint(Point p)
	{
		reference.add(p);
	}
	
	public void getPoint(int i)
	{
		reference.get(i);
	}
	
	
	public int getSizeList()
	{
		return reference.size();
	}
	
	public void clearList()
	{
		reference.clear();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	 	if (image != null) {
	 		
	 		//this.setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
		 	g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		 	//g.drawImage(croix, 50, 50, null);
		 	if(!reference.isEmpty())
		 	{
		 		for(Point pi : reference)
		 		{
		 			g.drawImage(croix, pi.x-widthPar2, pi.y-widthPar2, null);
		 		}
		 	}
	 	}
	}
	
}