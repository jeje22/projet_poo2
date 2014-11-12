package projet_poo2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class IHM extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenuItem item1 = new JMenuItem("Ouvrir");
	
	public IHM()
	{
		super();
		
		this.setTitle("Project");
		this.setSize(1000,500);
		this.menuBar.add(menu1);
		this.menu1.add(item1);
		item1.addActionListener(new ActionListener(){
		      
			public void actionPerformed(ActionEvent event){
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & PNG & GIF Images", "jpg", "png","gif","jpeg");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(new IHM());
			    if(returnVal == JFileChooser.APPROVE_OPTION)
			    {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
			    }
		    }
		});
		this.setJMenuBar(menuBar);
	}
}


/*
package projet_poo2;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class IHM extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenuItem item1 = new JMenuItem("Ouvrir");
	
	public IHM(){
		super();
		this.setTitle("Project");
		this.setSize(1000,500);
		this.menuBar.add(menu1);
		this.menu1.add(item1);
		this.setJMenuBar(menuBar);
	}
	
	private static final long serialVersionUID = 1L;

}
*/
