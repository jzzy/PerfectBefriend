package com.befriend.action;

import java.util.ArrayList;
import java.util.List;


public class Main
{
	
	public static final double e = Math.E;
	public static void main(String [] args)
	{
		int i = 0;
		for(i = 0;i<=10;i++)
		{
			System.out.println("点击次数为"+i+"时，权值为:"+5*(1-1/Math.log(0.1*i+e)));
		}
		i=20;
		System.out.println("点击次数为"+i+"时，权值为:"+5*(1-1/Math.log(0.1*i+e)));
		i=50;
		System.out.println("点击次数为"+i+"时，权值为:"+5*(1-1/Math.log(0.1*i+e)));
		i=100;
		System.out.println("点击次数为"+i+"时，权值为:"+5*(1-1/Math.log(0.1*i+e)));
		i=500;
		System.out.println("点击次数为"+i+"时，权值为:"+5*(1-1/Math.log(0.1*i+e)));
		i=1000;
		System.out.println("点击次数为"+i+"时，权值为:"+5*(1-1/Math.log(0.1*i+e)));
		i= 10000;
		System.out.println("点击次数为"+i+"时，权值为:"+5*(1-1/Math.log(0.1*i+e)));
		List<Double> v1 = new ArrayList<Double>()
				{
					private static final long serialVersionUID = 1L;

					{
						add(1d);
						add(0d);
						add(4d);
						add(3d);
						add(5d);
						add(0d);
			
					}
				};
		List<Double> v2 = new ArrayList<Double>()
				{
					private static final long serialVersionUID = 1L;

					{
						add(0d);
						add(3d);
						add(4d);
						add(0d);
						add(5d);
						add(5d);
	
					}
				};
				List<Double> v3 = new ArrayList<Double>()
						{
							private static final long serialVersionUID = 1L;

							{
								add(0d);
								add(3d);
								add(4d);
								add(0d);
								add(5d);
								add(5d);
			
							}
						};
		
		System.out.println(sim(v1, v2));
		System.out.println(sim(v3, v2));
		for(int ii = 0;ii<v3.size() ; ii++)
		{
			v3.set(ii, 9d);
		}
		System.out.println("v3:"+v3);
		
	}
	
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
	

}
