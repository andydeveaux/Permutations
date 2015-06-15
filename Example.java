/*
	PermutationGenerator Example
	Author: Andy Deveaux
	Description: Shows an example of how to use the PermutationGenerator object
*/

public class Example
{
	public static void main(String[] args)
	{
		PermutationGenerator gen = new PermutationGenerator("aabcdeeeeeefg", 4);
		System.out.println("Character pool: " + gen.getCharacterPool() + "\n");
		
		int total = gen.getTotal();
		System.out.println(total + " permutations");
		
		for (int i=0; i<total; i++)
		{
			System.out.println((int)i+1 + ". " + gen.getPermutation());
			gen.next();
		}
	}
}