package projet_poo2;

public class Calcul
{
	public static boolean correlation()
	{
		boolean res=false;
		int N=IHM.pictures[0].width/2-1;
		int P=IHM.pictures[0].height/2-1;
		
		int K=(2*N+1)*(2*P+1);
		
		
		if(IHM.reference.get(0).size()==IHM.reference.get(1).size())
		{
			for(int k=0;k<IHM.reference.get(0).size();k++)
			{
				int reste=0;
				int c=(1/K)+0;
				
				int val1_x=IHM.reference.get(0).get(k).x;
				int val1_y=IHM.reference.get(0).get(k).y;
				
				int val2_x=IHM.reference.get(1).get(k).x;
				int val2_y=IHM.reference.get(1).get(k).y;
				
				for(int i=-N;i<=N;i++)
				{
					for(int j=P;j<=P;j++)
					{
						reste+=val_1_x-N+i;
					}
				}
			}
		}
		else
		{
			System.out.println("erreur : le nombre de point n'est pas le même sur les 2 images");
		}
		
		
		return res;
	}
}
