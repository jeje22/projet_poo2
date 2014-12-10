package projet_poo2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
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
	/////////////
	private JMenu menu2 = new JMenu("Calcul");
	private JMenuItem item2 = new JMenuItem("Corrélation");
	
	private JMenu menu3=new JMenu("3D");
	private JMenuItem item3 = new JMenuItem("Lancer");
	
	String pathopendirectory = ".";
	////////////
	protected static ArrayList<ArrayList<Point>> reference=new ArrayList<ArrayList<Point>>();
	
	private Image3D img3D;
	
	private JLabel[] jltab=new JLabel[2];
	protected static Picture[] pictures = new Picture[2];
	int cpt=0;
	
	public IHM()
	{
		super();
		
		reference.add(new ArrayList<Point>());
		reference.add(new ArrayList<Point>());
		
		this.setTitle("Project");
		this.getContentPane().setPreferredSize(new Dimension(200,200));
		this.addKeyListener(this);
		this.menuBar.add(menu1);
		this.menu1.add(item1);
		item1.addActionListener(new ActionListener(){
		      
			public void actionPerformed(ActionEvent event)
			{
				try {
					openFileLocation();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		
		/////
		this.menuBar.add(menu2);
		this.menu2.add(item2);
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				Calcul.correlation();
				if(reference.get(0).size() >= 4 && reference.get(1).size() >= 4)
				{
					System.out.println("lancer fonction corrélation");
					//// fonction corrélation de la classe Calcul
					/// Calcul.correlation()
				}
			
			}
		});
		
		this.menuBar.add(menu3);
		this.menu3.add(item3);
		item3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Lancement Image 3D");
				//if(img3D==null)
				//{
					Image3D.lancer();
				//}
			}
			
		});
		
		this.setJMenuBar(menuBar);
	}
	
	public void ouvrirImage(String filename) throws IOException
	{
		Picture pic=new Picture(filename);
		System.out.println(pic.width+"     "+pic.height);
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize().getWidth()+"       "+Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		if(pic.width>Toolkit.getDefaultToolkit().getScreenSize().getWidth() || pic.height>Toolkit.getDefaultToolkit().getScreenSize().getHeight())
		{
			System.out.println("resize");
			this.setLocation(0, 0);
			pic=pic.resize();
			Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
			this.getContentPane().setPreferredSize(new Dimension(dim_screen.width, dim_screen.height));
		}
		else
		{
			this.getContentPane().setPreferredSize(new Dimension(pic.width*2,pic.height));
		}
		
		ImageIcon ic = new ImageIcon(pic.image);
		JLabel jl=new JLabel(ic);
		this.setResizable(false);
		
		
		//this.getContentPane().setPreferredSize(new Dimension(576*2,720));
		
		
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
		if(cpt == 0)
		{
			//première image en rouge
			
			pictures[cpt] = pic;
			
		}
		else
		{
	
			pictures[cpt] = pic;
			
		}
		cpt=(cpt+1)%2;
		System.out.println("Frame size = " + this.getSize());
	}
	
	public void openFileLocation() throws IOException{
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    "JPG & PNG & GIF Images", "jpg", "png","gif","jpeg");
		chooser.setFileFilter(filter);
		if(!pathopendirectory.equals("."))
		{
			chooser.setCurrentDirectory(new File(pathopendirectory));
		}
		chooser.showOpenDialog(IHM.this);
		if(chooser.getSelectedFile() != null)
		{
			pathopendirectory = chooser.getSelectedFile().getAbsolutePath();
			ouvrirImage(chooser.getSelectedFile().getAbsolutePath());
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		if (e.isControlDown() && e.getKeyChar() != 'o' && e.getKeyCode() == 79) {
			try {
				openFileLocation();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent arg0) {
		
		System.out.println("cpt: " + cpt + " "+ arg0.getX()+" "+arg0.getY());
		System.out.println(arg0.getComponent().toString());
		Component c = arg0.getComponent();
		if(c.equals(jltab[0]))
		{
			System.out.println("image 1");
			reference.get(0).add(new Point(arg0.getX(), arg0.getY()));
		}
		else
		{
			System.out.println("image2");
			reference.get(1).add(new Point(arg0.getX(), arg0.getY()));
			
		
		}

		
		
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