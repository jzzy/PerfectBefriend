package com.befriend.dao;

import java.util.List;

import com.befriend.entity.Attention;
import com.befriend.entity.Collect;
import com.befriend.entity.Support;

public interface CollectDAO {

	public List<Collect> Alln(int newsid);

	public List<Collect> Allu(int userid);

	public void save(Collect collect);
	public void update(Collect collect);
	public Collect unid(int userid, int newsid);

	public void remove(Collect collect);

	public List<Support> Frequency(int comefrom, int objectid);

	public List<Support> ILikeToo(int comefrom, int userid);

	public void save(Support st);

	public Support Whether(int comefrom, int userid, int objectid);

	public void remove(Support st);
	public List<Attention> Frequency_A(int comefrom, int objectid);

	public List<Attention> ILikeToo_A(int comefrom, int userid);

	public void save(Attention at);

	public Attention Whether_A(int comefrom, int userid, int objectid);

	public void remove(Attention at);

}
