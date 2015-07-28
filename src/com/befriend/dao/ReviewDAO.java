package com.befriend.dao;

import java.util.List;
/**
 * 评论方法
 */
import com.befriend.entity.Review;

public interface ReviewDAO {
    // 根据newsid查询全部评论
    public List<Review> Alln(int newsid);

    // 根据username查询全部评论
    public List<Review> Allu(int userid);

    // 根据userid newsid 添加评论
    public void save(Review review);

    // 根据username 和 newsid 查询 
    public List<Review> unid(int userid, int newsid);

    // 删除评论
    public void remove(Review review);

    // 根据id查询
    public Review byid(int reviewid,int userid);
}
