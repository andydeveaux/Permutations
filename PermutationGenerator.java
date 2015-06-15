/*
	Permutation Generator Class v1.0.0
	Author: Andy Deveaux
	Description: A class for generating all possible string permutations given a set character pool and string size
	Usage: - Create a new PermutationGenerator object
		   - Set the character pool and permutation size
		   - Use the next() method to progress to the next permutation.
		   - Use getPermutation() to return the string.
		   
	<LICENSE>
	    Copyright (C) 2011 Andy Deveaux
	
	    This program is free software: you can redistribute it and/or modify
	    it under the terms of the GNU General Public License as published by
	    the Free Software Foundation, either version 3 of the License, or
	    (at your option) any later version.
	
	    This program is distributed in the hope that it will be useful,
	    but WITHOUT ANY WARRANTY; without even the implied warranty of
	    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	    GNU General Public License for more details.
	
	    You should have received a copy of the GNU General Public License
	    along with this program.  If not, see <http://www.gnu.org/licenses/>.	
	</LICENSE>
*/

public class PermutationGenerator
{
	// constants
	public static final String DEFAULT_CHARPOOL = "abcd";
	public static final int DEFAULT_PERMSIZE = 4;
	
	// fields
	private String mCharpool;			// character pool for permutations
	private short[] mPositions;			// numeric array which represents the permutation string, each element being a position
										// within the character pool string
	private String mPermutation;			// current generated permutation
	
	// constructors
	public PermutationGenerator()
	{
		this(DEFAULT_CHARPOOL, DEFAULT_PERMSIZE);
	}
	
	public PermutationGenerator(String character_pool, int size)
	{
		setCharacterPool(character_pool);
		setPermutationSize(size);
	}
	
	// methods
	// public
	public boolean setCharacterPool(String character_pool)
	{
		if (character_pool.length() < 2)
		{
			mCharpool = DEFAULT_CHARPOOL;
			return false;
		}
		
		// remove any duplicate entries from the string
		String refined_charpool = "";
		int charpool_length = character_pool.length();
		
		char c;
		for (int i=0; i<charpool_length; i++)
		{
			c = character_pool.charAt(i);
			if (refined_charpool.indexOf(c) == -1)
				refined_charpool += c;
		}
		
		mCharpool = refined_charpool;
		return true;
	}
	
	public String getCharacterPool()
	{
		return mCharpool;
	}
	
	public boolean setPermutationSize(int size)
	{
		if (size < 1)
			return false;
		
		mPositions = null;				// destroy any old instances
		mPositions = new short[size];
		resetFromPos(0);				// initialize the positions array
		generate();						// don't forget to generate the string otherwise it will return null
		
		return true;
	}
	
	public int getTotal()
	{
		// formula for getting the number of permutations possible is n^r, where n = number of objects to choose from, r = number of selected objects
		return (int)Math.pow(mCharpool.length(), mPositions.length);
	}
		
	public void next()
	{
		// do a for loop to determine where to increment and reset from
		boolean loop = true;
		for (int i=mPositions.length-1; loop && i>-1; i--)
		{
			if (mPositions[i] != mCharpool.length()-1)
			{
				mPositions[i] += 1;
				resetFromPos(i+1);
				loop = false;
			}
		}
		
		generate();
	}
	
	public String getPermutation()
	{
		return mPermutation;
	}
	
	// private
	private void resetFromPos(int pos)
	{
		for (int i=pos; i<mPositions.length; i++)
			mPositions[i] = 0;
	}
	
	private void generate()
	{
		mPermutation = "";		// clear the string
		
		for (int i=0; i<mPositions.length; i++)
		{
			mPermutation += mCharpool.substring(mPositions[i], mPositions[i]+1);
		}
	}
}