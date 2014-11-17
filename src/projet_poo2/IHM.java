package projet_poo2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;


public class IHM extends JFrame implements KeyListener,MouseListener
{
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Fichier");
	private JMenuItem item1 = new JMenuItem("Ouvrir");
	
	private ArrayList<Point> reference=new ArrayList<Point>();
	
	private JLabel[] jltab=new JLabel[2];
	int cpt=0;
	
	public IHM()
	{
		super();
		
		this.setTitle("Project");
		this.setPreferredSize(new Dimension(200,200));
		this.addKeyListener(this);
		this.addMouseListener(this);
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
		this.setMinimumSize(new Dimension(pic.width*2,pic.height));
		this.setSize(new Dimension(pic.width*2,pic.height));
		
		if(jltab[cpt]!=null)
		{
			this.remove(jltab[0]);
		}
		
		jltab[cpt]=jl;
		if(cpt==0)
		{
			this.add(jl,BorderLayout.WEST);
		}
		else
		{
			this.add(jl, BorderLayout.EAST);
		}
		SwingUtilities.updateComponentTreeUI(this);
		cpt=(cpt+1)%2;
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

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.isControlDown() && e.getKeyChar() != 'o' && e.getKeyCode() == 79) {
			openFileLocation();
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent arg0) {
		reference.add(new Point(arg0.getX(), arg0.getY()));
		System.out.println(arg0.getX()+" "+arg0.getY());
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}