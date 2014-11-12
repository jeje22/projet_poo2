package projet_poo2;

import java.awt.FileDialog;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IHM extends JFrame
{
	
	public IHM()
	{
		super();
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "JPG & PNG & GIF Images", "jpg", "png","gif","jpeg");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(this);
	    if(returnVal == JFileChooser.APPROVE_OPTION)
	    {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	    }
	}
}
