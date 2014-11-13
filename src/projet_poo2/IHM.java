package projet_poo2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class IHM extends JFrame implements KeyListener
{
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenuItem item1 = new JMenuItem("Ouvrir");
	
	public IHM()
	{
		super();
		
		this.setTitle("Project");
		this.setPreferredSize(new Dimension(200,200));
		this.addKeyListener(this);
		this.menuBar.add(menu1);
		this.menu1.add(item1);
		item1.addActionListener(new ActionListener(){
		      
			public void actionPerformed(ActionEvent event)
			{
				openFileLocation();
		    }
		});
		this.setJMenuBar(menuBar);
	}
	
	public void ouvrirImage(String filename)
	{
		Picture pic=new Picture(filename);
		ImageIcon ic = new ImageIcon(pic.image);
		JLabel jl=new JLabel(ic);
		this.setSize(new Dimension(pic.width,pic.height));
		this.add(jl);
		
	}
	
	public void openFileLocation(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    "JPG & PNG & GIF Images", "jpg", "png","gif","jpeg");
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(IHM.this);
		if(chooser.getSelectedFile() != null)
		{
			ouvrirImage(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isControlDown() && e.getKeyChar() != 'o' && e.getKeyCode() == 79) {
			openFileLocation();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}