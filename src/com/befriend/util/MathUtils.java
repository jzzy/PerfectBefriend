package com.befriend.util;

import java.util.List;

/**
 * 数学工具类
 * @author STerOTto
 *
 */
public class MathUtils
{
	/**
	 * 求最大值
	 * @param array
	 * @return
	 */
	public static int max(int [] array)
	{
		int max = Integer.MIN_VALUE;
		for (int i : array)
		{
			max = Math.max(max, i);
		}
		return max;
	}
	
	/**
	 * 求最小值
	 * @param array
	 * @return
	 */
	public static int min(int [] array)
	{
		int min = Integer.MAX_VALUE;
		for (int i : array)
		{
			min = Math.min(min, i);
		}
		return min;
	}
	
	/**
	 * 求两个向量的夹角
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sim(List<Double> v1,List<Double> v2)
	{
		if(v1.size() != v2.size())
			return 0d;
		else
		{
			double inner = 0d;
			double normV1 = 0d;
			double normV2 = 0d;
			for (int i = 0; i < v1.size(); i++)
			{
				inner += v1.get(i)*v2.get(i);
				normV1 += v1.get(i)*v1.get(i);
				normV2 += v2.get(i)*v2.get(i);
			}
			return inner/Math.sqrt(normV1*normV2);
		}
	}
	
	
	public static final double MAX_W = 5d;
	public static final double SPEED = 0.1d;
	/**
	 * 计算权值
	 * @param x
	 * @return
	 */
	public static double getWeight(long x)
	{
		return MAX_W*(1-1/Math.log(SPEED*x+Math.E));
		
	}

}
