/*
	PermutationGenerator Example
	Author: Andy Deveaux
	Description: Shows an example of how to use the PermutationGenerator object, writes the results to a file
*/

// packages
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;

public class Example2
{
	private static BufferedWriter writer = null;

	public static void openFile(String filename)
	{		
		try
		{
			writer = new BufferedWriter(new FileWriter(filename));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void closeFile()
	{
		try
		{
			writer.flush();
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		openFile("generated.txt");
		
		PermutationGenerator gen = new PermutationGenerator();
		gen.setCharacterPool("abcd");
						
		int limit = 7;
		for (int i=0; i<limit; i++)
		{
			gen.setPermutationSize(i+1);
			int total = gen.getTotal();
			for (int j=0; j<total; j++)
			{
				String perm = gen.getPermutation();
				
				try
				{
					writer.write(perm + "\r\n");
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
				System.out.println(gen.getPermutation());
				gen.next();
			}
		}
		
		closeFile();
	}
}