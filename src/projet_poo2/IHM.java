package projet_poo2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
	
	private ArrayList<ArrayList<Point>> reference=new ArrayList<ArrayList<Point>>();
	
	private JLabel[] jltab=new JLabel[2];
	int cpt=0;
	
	public IHM()
	{
		super();
		
		this.reference.add(new ArrayList<Point>());
		this.reference.add(new ArrayList<Point>());
		
		this.setTitle("Project");
		this.getContentPane().setPreferredSize(new Dimension(200,200));
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
		if(pic.width>Toolkit.getDefaultToolkit().getScreenSize().getWidth() || pic.height>Toolkit.getDefaultToolkit().getScreenSize().getHeight())
		{
			pic=pic.resize();
		}
		
		ImageIcon ic = new ImageIcon(pic.image);
		JLabel jl=new JLabel(ic);
		this.setResizable(false);
		this.getContentPane().setPreferredSize(new Dimension(pic.width*2,pic.height));
		this.pack();
		
		if(jltab[cpt]!=null)
		{
			this.remove(jltab[cpt]);
		}
		
		jltab[cpt]=jl;
		if(cpt==0)
		{
			this.getContentPane().add(jl,BorderLayout.WEST);
		}
		else
		{
			this.getContentPane().add(jl, BorderLayout.EAST);
		}
		SwingUtilities.updateComponentTreeUI(this);
		this.jltab[cpt].addMouseListener(this);
		cpt=(cpt+1)%2;
		System.out.println("Frame size = " + this.getSize());
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
		reference.get(cpt).add(new Point(arg0.getX(), arg0.getY()));
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