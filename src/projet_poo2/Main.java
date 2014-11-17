package projet_poo2;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		JFrame jf = new IHM();
		jf.setLocation(100, 100);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout());
		jf.setVisible(true);
	}
}
