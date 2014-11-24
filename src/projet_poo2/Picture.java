package projet_poo2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Picture
{
	protected BufferedImage image;               // the rasterized image                    // on-screen view
    private String filename;                   // name of file
    protected final int width, height;           // width and height
    
	public Picture(int w, int h)
	{
        width = w;
        height = h;
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        // set to TYPE_INT_ARGB to support transparency
        filename = w + "-by-" + h;
    }
	
	public Picture(String filename)
	{
        this.filename = filename;
        try {
            // try to read from file in working directory
            File file = new File(filename);
            if (file.isFile()) {
                image = ImageIO.read(file);
            }

            // now try to read from file in same directory as this .class file
            else {
                URL url = getClass().getResource(filename);
                if (url == null) { url = new URL(filename); }
                image = ImageIO.read(url);
            }
            width  = image.getWidth(null);
            height = image.getHeight(null);
        }
        catch (IOException e) {
            // e.printStackTrace();
            throw new RuntimeException("Could not open file: " + filename);
        }
    }
	
	public Picture resize()
	{
		//largeur de l'image : 960
		//hauteur de l'image : 1080
		//Picture pic=new Picture(this.filename);
		Dimension ecran=Toolkit.getDefaultToolkit().getScreenSize(); //c'est ds le code de base
		int w = (int)(ecran.width/2);
		int h = ecran.height;
		 GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		 BufferedImage imnew = configuration.createCompatibleImage(w, h);
		Graphics2D g = imnew.createGraphics();
		g.drawImage(image, 0, 0, w, h, 0, 0, image.getWidth(), image.getHeight(), null);
		g.dispose();
		this.image = imnew;
	    System.out.println("width une image =  "+ image.getWidth());
	    System.out.println("height une image = "+ image.getHeight());
	   
		return this;
		
	}
	
	
	

}
