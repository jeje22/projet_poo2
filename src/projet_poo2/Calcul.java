package projet_poo2;

import java.awt.Color;

public class Calcul
{
	public static boolean correlation()
	{
		boolean res=false;
		//int N=IHM.pictures[0].width/2;
		//int P=IHM.pictures[0].height/2;
		
		int[][] rgbArray1 = new int[IHM.pictures[0].width][IHM.pictures[0].height];
		int[][] rgbArray2 = new int[IHM.pictures[1].width][IHM.pictures[1].height];;
		float[][] int_values1=new float[IHM.pictures[0].width][IHM.pictures[0].height];
		float[][] int_values2=new float[IHM.pictures[1].width][IHM.pictures[1].height];
		
		float int_moy1=0;
		float int_moy2=0;
		
		for(int i=0;i<IHM.pictures[0].width;i++)
		{
			for(int j=0;i<IHM.pictures[0].height;j++)
			{
				rgbArray1[i][j]=IHM.pictures[0].image.getRGB(i,j);
				
				rgbArray2[i][j]=IHM.pictures[1].image.getRGB(i,j);
				
				Color c=new Color(rgbArray1[i][j]);
				System.out.println(j+" "+c.getRed());
				float[] hsb_values=new float[3];
				Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsb_values);
				
				int_values1[i][j]=hsb_values[1];
				
				int_moy1+=hsb_values[1];
				
				c=new Color(rgbArray2[i][j]);
				Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsb_values);
				
				int_values2[i][j]=hsb_values[1];
				
				int_moy2+=hsb_values[1];
			}
		}
		
		int_moy1/=IHM.pictures[0].width*IHM.pictures[0].height;
		int_moy2/=IHM.pictures[0].width*IHM.pictures[0].height;
		
		float r=0;
		float haut=0;
		float bas1=0;
		float bas2=0;
		
		for(int i=0;i<int_values1.length;i++)
		{
			for(int j=0;j<int_values1[i].length;j++)
			{
				haut+=(int_values1[i][j]-int_moy1)*(int_values2[i][j]-int_moy2);
			}
		}
		
		for(int i=0;i<int_values1.length;i++)
		{
			for(int j=0;j<int_values1[i].length;j++)
			{
				bas1+=(int_values1[i][j]-int_moy1)*(int_values1[i][j]-int_moy1);
			}
		}
		
		for(int i=0;i<int_values2.length;i++)
		{
			for(int j=0;j<int_values2[i].length;j++)
			{
				bas2+=(int_values2[i][j]-int_moy2)*(int_values2[i][j]-int_moy2);
			}
		}
		
		bas1=(float) Math.sqrt(bas1);
		bas2=(float) Math.sqrt(bas2);
		
		r=haut/(bas1*bas2);
		
		System.out.println(r);
		
		
		
		/*int K=(2*N+1)*(2*P+1);
		
		Point x=new Point(0,0);
		Point y=new Point(0,0);
		if(IHM.reference.get(0).size()==IHM.reference.get(1).size())
		{
			int c_x=(1/K);
			int c_y=(1/K);
			for(int k=0;k<IHM.reference.get(0).size();k++)
			{
				int reste_x=0;
				int reste_y=0;
				
				int val1_x=IHM.reference.get(0).get(k).x;
				int val1_y=IHM.reference.get(0).get(k).y;
				
				int val2_x=IHM.reference.get(1).get(k).x;
				int val2_y=IHM.reference.get(1).get(k).y;
				
				System.out.println("x1 :"+val1_x+" y1 :"+val1_y+" x2 :"+val2_x+" y2 :"+val2_y);
				
				for(int i=-N;i<=N;i++)
				{
					reste_x+=((val1_x-N+i)-(N+val1_x))*((val2_x-N+i)-(val2_x+N));
					
				}
				for(int j=-P;j<=P;j++)
				{
					reste_y+=((val1_y-P+j)-(P+val1_y))*((val2_y-P+j)-(val2_y+P));
					//System.out.println(((val1_x-N+i)-(N+val1_x))*((val2_x-N+i)-(val2_x+N))+"  "+((val1_y-P+j)-(P+val1_y))*((val2_y-P+j)-(val2_y+P)));
				}
				
				System.out.println("reste x vaut "+reste_x+" reste y vaut "+reste_y);
				System.out.println("reste x vaut "+reste_x/K+" reste y vaut "+reste_y/K);
			}
			
		}
		else
		{
			System.out.println("erreur : le nombre de point n'est pas le même sur les 2 images");
		}*/
		
		
		return res;
	}
}
