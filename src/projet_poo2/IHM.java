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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	
	private JMenu menu2 = new JMenu("Calcul");
	private JMenuItem item2 = new JMenuItem("Corrélation");
	
	private JMenu menu3=new JMenu("3D");
	private JMenuItem item3 = new JMenuItem("Lancer");
	
	private JMenu menu4 = new JMenu("Désattribution");
	private JMenuItem item4_1 = new JMenuItem("Clear image 1");
	private JMenuItem item4_2 = new JMenuItem("Clear image 2");
	private JMenuItem item4_3 = new JMenuItem("Clear All");
	
	String pathopendirectory = ".";
	
	private Image3D img3D;
	
	protected static MyJLabel[] jltab = new MyJLabel[2];
	protected static Picture[] pictures = new Picture[2];
	int cpt=0;
	
	public IHM()
	{
		super();
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
		
		this.menuBar.add(menu4);
		this.menu4.add(item4_1);
		menu4.addSeparator();
		this.menu4.add(item4_2);
		menu4.addSeparator();
		this.menu4.add(item4_3);
		
		item4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				if(jltab[0] != null){
					jltab[0].clearList();
					jltab[0].repaint();
				}
			}
		});
		item4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				if(jltab[1] != null){
					jltab[1].clearList();
					jltab[1].repaint();
				}
			}
		});
		item4_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				if(jltab[0] != null && jltab[1] != null)
				{
					for(MyJLabel jl : jltab)
					{
						jl.clearList();
						jl.repaint();
					}
				}
			}
		});
		
		this.menuBar.add(menu2);
		this.menu2.add(item2);
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				//besoin de changer la reference dans calcul.class
				//jltab[0] ou [1] pour accéder aux listes
				Calcul.correlation();
				if(jltab[0].getSizeList() >= 4 && jltab[1].getSizeList() >= 4)
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
		Picture pic = new Picture(filename);
		System.out.println(pic.width+"     "+pic.height);
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize().getWidth()+"       "+Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		if(pic.width > Toolkit.getDefaultToolkit().getScreenSize().getWidth() 
				|| pic.height > Toolkit.getDefaultToolkit().getScreenSize().getHeight())
		{
			System.out.println("resize");
			pic = pic.resize();
			Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
			this.getContentPane().setPreferredSize(new Dimension(dim_screen.width, dim_screen.height-20));
		}
		else
		{
			this.getContentPane().setPreferredSize(new Dimension(pic.width*2,pic.height));
		}
		
		
		MyJLabel jl = new MyJLabel(pic.image);
		jl.setPreferredSize(new Dimension(pic.image.getWidth(), pic.image.getWidth()));
		jl.setImage();
		
		if(jltab[cpt]!=null)
		{
			this.remove(jltab[cpt]);
		}
		
		jltab[cpt]=jl;
		
		if(cpt==0)
		{
			this.getContentPane().add(jltab[cpt],BorderLayout.WEST);
		}
		else
		{	
			this.getContentPane().add(jltab[cpt], BorderLayout.EAST);
		}
		
		this.jltab[cpt].addMouseListener(this);
		
		
		this.setResizable(false);
		this.pack();
		
		
		SwingUtilities.updateComponentTreeUI(this);
		
		if(cpt == 0)
		{	
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
		//System.out.println(arg0.getComponent().toString());
		Component c = arg0.getComponent();
		if(c.equals(jltab[0]))
		{
			System.out.println("image 1");
			jltab[0].setPoint(new Point(arg0.getX(), arg0.getY()));
			jltab[0].repaint();
		}
		else
		{
			System.out.println("image2");
			jltab[1].setPoint(new Point(arg0.getX(), arg0.getY()));
			jltab[1].repaint();
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