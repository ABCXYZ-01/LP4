import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.math.*;
public class FuzzySet 
{

	static void FuzzyUnion(HashMap<Integer, Double> A,HashMap<Integer, Double> B)
	{
		HashMap<Integer,Double> union=new HashMap<Integer,Double>();
		for(Entry<Integer, Double> m1 : A.entrySet())
		{ 
			for(Entry<Integer, Double> m2 : B.entrySet())
				{ 
				
				Double value1=m1.getValue();
				Double value2=m2.getValue();
				
				if(value1>value2)
				{
					union.put(m1.getKey(),m1.getValue());
				}
				else
				{
					union.put(m2.getKey(),m2.getValue());  
				}
				
			}
		
		}
		
		System.out.println("Union of fuzzy set");  
		for(Entry<Integer, Double> m3 : union.entrySet())
		{    
			System.out.println(m3.getKey()+" "+m3.getValue());    
		}
	}

	static void FuzzyIntersection(HashMap<Integer, Double> A,HashMap<Integer, Double> B)
	{
		HashMap<Integer,Double> inter=new HashMap<Integer,Double>();//Creating HashMap 
		for(Entry<Integer, Double> m1 : A.entrySet())
		{ 
			for(Entry<Integer, Double> m2 : B.entrySet())
			{ 
				
				Double value1=m1.getValue();
				Double value2=m2.getValue();
				
				if(value1<value2)
				{
					inter.put(m1.getKey(),m1.getValue());
				}
				else
				{
					inter.put(m2.getKey(),m2.getValue());  
				}
				
			}
		
		}
		
		System.out.println("Intersection of fuzzy set");  
		for(Entry<Integer, Double> m3 : inter.entrySet())
		{    
		 	System.out.println(m3.getKey()+" "+m3.getValue());    
		}
	 
	}

	static void FuzzyComplement(HashMap<Integer, Double> A)
	{
		HashMap<Integer,Double> compl=new HashMap<Integer,Double>();//Creating HashMap
		for(Entry<Integer, Double> m1 : A.entrySet())
		{ 
			compl.put(m1.getKey(),1-m1.getValue());
		}
		System.out.println("Complement of the fuzzy set");
		for(Entry<Integer, Double> m3 : compl.entrySet())
		{
			System.out.println(m3.getKey()+" "+m3.getValue());
		}
	}

	static void FuzzyDifference(HashMap<Integer, Double> A,HashMap<Integer, Double> B)
	{
		HashMap<Integer,Double> diff=new HashMap<Integer,Double>();//Creating HashMap
		HashMap<Integer,Double> compl=new HashMap<Integer,Double>();//Creating HashMap
		for(Entry<Integer, Double> m1 : A.entrySet())
		{ 
			compl.put(m1.getKey(),1-m1.getValue());
		}
		
		for(Entry<Integer, Double> m : B.entrySet()){    
		System.out.println(m.getKey()+" "+m.getValue());    
		}  
		
	
		for(Entry<Integer, Double> m : A.entrySet()){    
		System.out.println(m.getKey()+" "+m.getValue());    
		}    

		for(Entry<Integer, Double> m1 : B.entrySet())
		{ 
			for(Entry<Integer, Double> m2 : compl.entrySet())
			{ 
				
				Double value1=m1.getValue();
				Double value2=m2.getValue();

				if(value1<value2)
				{
					diff.put(m1.getKey(),m1.getValue());
				}
				else
				{
					diff.put(m2.getKey(),m2.getValue());  
				}
				
			}
		
		}
		
		System.out.println("Difference of fuzzy set");  
		for(Entry<Integer, Double> m3 : diff.entrySet())
		{    
		 	System.out.println(m3.getKey()+" "+m3.getValue());    
		}
	 
	}

	static HashMap<Integer, Double> EnterInput()
	{
		HashMap<Integer, Double> A = new HashMap<Integer,Double>();
		System.out.println("Enter the Number of Elements");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int key;
		Double value;
		for(int i=0;i<n;i++)
		{	
			System.out.println("Enter Key");
			key = sc.nextInt();
			System.out.println("Enter Value");
			value = sc.nextDouble();
			A.put(key,value);
		}
		return A;
	}

	static void CartesianProduct(HashMap<Integer, Double> A,HashMap<Integer, Double> B)
	{
		for(Entry<Integer, Double> m1 : A.entrySet())
		{ 
			for(Entry<Integer, Double> m2 : B.entrySet())
			{ 	
				System.out.println("("+m1.getKey()+","+m2.getKey()+")  "+ Math.min(m1.getValue(),m2.getValue()));
			}
		
		}
	 
	}

	static void MinMaxComposition()
	{
		System.out.println("Min-max composition\n");
		   Scanner sc=new Scanner(System.in);
		
		double x[][]=new double[2][2];
		System.out.println("enter the values of first Matrix"); 
			   for( int i=0;i<2;i++)
			   {
				   for(int j=0;j<2;j++)
				   {
					   x[i][j]=sc.nextDouble();
				   }
			   }
		 
		   
		   double y[][]=new double[2][3];
		   System.out.println("enter the values of second Matrix"); 
				   for(int i=0;i<2;i++)
				   {
					   for(int j=0;j<3;j++)
					   {
						   y[i][j]=sc.nextDouble();
					   }
				   }
				   
			System.out.println("first Matrix:");
				   for(int i=0;i<2;i++)
				   {
					   for(int j=0;j<2;j++)
					   {
						   System.out.print(x[i][j]+" ");
					   }
					   System.out.println();
				   }
				   
			System.out.println("Second Matrix:");
				for(int i=0;i<2;i++)
			    {
				   for(int j=0;j<3;j++)
				   {
					   System.out.print(y[i][j]+" ");
				   }
				   System.out.println();
			   }
			   
			double z[][]=new double[2][3];
			double []min_array = new double[2];
			 for(int i=0;i<z.length;i++)
			 {
				for(int j=0;j<z[i].length;j++)
				{
					z[i][j] = 0;
					for(int k=0;k<2;k++)
					{	
						if(x[i][k] <= y[k][j])
						{
							min_array[k] = x[i][k];
						}
						else
						{
							min_array[k] = y[k][j];
						}
						z[i][j] = Math.max(min_array[0], min_array[1]);
					}
				}
			 }
			   
			 System.out.println("Max-min composition Resultant Matrix is:");

			   for(int i=0;i<2;i++)
			   {
				   for(int j=0;j<3;j++)
				   {
					   System.out.print(z[i][j]+" ");
				   }
				   System.out.println();
			   }	
			   
	}


	public static void main(String args[])
	{
		HashMap<Integer,Double> A=new HashMap<Integer,Double>();//Creating HashMap    
		
		HashMap<Integer,Double> B=new HashMap<Integer,Double>();//Creating HashMap    
		
		int choice;
		Scanner sc = new Scanner(System.in);
		System.out.println("Fuzzy set A");
		A = EnterInput();
		System.out.println("Fuzzy set B");  
		B = EnterInput();
		do
		{
			
			System.out.println("1. Union");
			System.out.println("2. Intersection");
			System.out.println("3. Complement");
			System.out.println("4. Difference");
			System.out.println("5. Cartesian Product");
			System.out.println("6. Max-Min Composition");
			System.out.println("7. Exit");
			System.out.println("Enter the Number");
			choice = sc.nextInt();
			switch(choice)
			{
				case 1: 
				FuzzyUnion(A,B);
					break;
				
				case 2: 
				FuzzyIntersection(A,B);
					break;
				
				case 3:
				FuzzyComplement(A);
					break;

				case 4: 
				FuzzyDifference(A, B);
					break;

				case 5:	A = EnterInput();
					    B = EnterInput();
						CartesianProduct(A, B);
					break;
				
				case 6: MinMaxComposition();
					break;

				case 7:
					break;
				default:
					System.out.println("Select another Choice");
			}
		}while(choice != 7);

		sc.close();
	}	
}
