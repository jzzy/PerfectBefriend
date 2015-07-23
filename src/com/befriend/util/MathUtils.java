package com.befriend.util;

public class MathUtils
{
	public static int max(int [] array)
	{
		int max = Integer.MIN_VALUE;
		for (int i : array)
		{
			max = Math.max(max, i);
		}
		return max;
	}
	
	public static int min(int [] array)
	{
		int min = Integer.MAX_VALUE;
		for (int i : array)
		{
			min = Math.min(min, i);
		}
		return min;
	}

}
