
public class Hermite {

	int dimenX;
	int dimenZ;
	int maxder;
	double [] nodes;

	
	double [] Znodes;
	double [][] differences;
	
	
	//maxder je broj najveceg izvoda uvecan za jedan
	public Hermite(double[] nodes, int maxder, String[][]functions) {
		super();
		this.maxder=maxder;
		dimenX=nodes.length;
		dimenZ=0;
		this.nodes=new double [dimenX];
		for( int i=0;i<dimenX;i++)
		{
			
				this.nodes[i]=nodes[i];
			
			
		}
		
		
		
		//ukupne visestrukosti - broj cvorova koje koristimo npr: x0 x0 x0 x1 x1 x2...
		for( int i=0;i<maxder;i++)
		{
			for( int j=0;j<dimenX;j++)
			{
				if(!functions[i][j].equals("*"))
					dimenZ++;
			}
		}
		
		Znodes=new double[dimenZ];
		differences=new double[dimenZ][dimenZ];
		
		
		//unos clanova i f^(0)
		int index=0;
		for( int i=0;i<dimenX;i++)
		{
			for( int j=0;j<maxder;j++)
			{
				if(!functions[j][i].equals("*"))
					{
					Znodes[index]=nodes[i];
					differences[index][0]=Double.parseDouble(functions[0][i]);
					index++;
					}
			}
		}
		

		
		
		// i bira kolonu
		for(int i=1;i<dimenZ;i++)
		{

			//pocinje od prvih podijeljenih razlika
			for(int j=0;j<dimenZ-i;j++)
			{
				//da ne dijelimo sa 0
				if(Znodes[i+j]==Znodes[j])
				{
					// i bira koji je izvod iz functions
					index=findElInArray(Znodes[j]);
					
					double temp= Double.parseDouble(functions[i][index]);
					differences[j][i]=temp/faktorijel(i);
				}
				else
				{
					differences[j][i]=differences[j+1][i-1]-differences[j][i-1];
					differences[j][i]/=(Znodes[i+j]-Znodes[j]);
					
				}
				
				
				
				
			}
			
			
		}
		

		
		
	
		
	}
	
	
	
	private int findElInArray(double el)
	{
		int r=0;
		for(int i=0;i<dimenX;i++)
		{
			if(el==nodes[i])	
				r=i;
		}
		return r;
	}
	
	private double faktorijel(double n)
	{
		if (n<=0)
			return 1;
		return n*faktorijel(n-1);
	}
	
	
	
	
	public double calcValue(double x)
	{
		double res=differences[0][0];
		for(int j=1;j<dimenZ;j++)
		{
			double t=differences[0][j];
			for(int i=0;i<j;i++)
			{
				t*=(x-Znodes[i]);
			}
			res+=t;
		}
		return res;
	}
	
	public String funcToString()
	
	{
		String end="";
		if(differences[0][0]!=0)
			end=""+differences[0][0];
		for(int j=1;j<dimenZ;j++)
		{
			if(differences[0][j]!=0)
			
			{
				if(differences[0][j]>0)
					end+="+";
				end+=""+differences[0][j];
				
				for(int i=0;i<j;i++)
				{
					if(Znodes[i]!=0)
						end+="*(x-"+Znodes[i]+")";
					else
						end+="*x";
				}
			}
		}
		return end;
	}
	
	
	
	
	
}
