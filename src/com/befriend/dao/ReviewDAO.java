package com.befriend.dao;

import java.util.List;
/**
 * ���۷���
 */
import com.befriend.entity.Review;

public interface ReviewDAO {
    // ����newsid��ѯȫ������
    public List<Review> Alln(int newsid);

    // ����username��ѯȫ������
    public List<Review> Allu(String username);

    // ����userid newsid �������
    public void save(Review review);

    // ����username �� newsid ��ѯ 
    public List<Review> unid(String username, int newsid);

    // ɾ������
    public void remove(Review review);

    // ����id��ѯ
    public Review byid(int reviewid,String username);
}
