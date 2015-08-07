package com.befriend.dao;

import java.util.List;
/**
 * ���۷���
 */
import com.befriend.entity.Review;

public interface ReviewDAO {

    public List<Review> Alln(int newsid);

 
    public List<Review> Allu(int userid);

   
    public void save(Review review);


    public List<Review> unid(int userid, int newsid);

    
    public void remove(Review review);

  
    public Review byid(int reviewid,int userid);
}
