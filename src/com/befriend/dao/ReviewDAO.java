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
    public List<Review> Allu(int userid);

    // ����userid newsid �������
    public void save(Review review);

    // ����username �� newsid ��ѯ 
    public List<Review> unid(int userid, int newsid);

    // ɾ������
    public void remove(Review review);

    // ����id��ѯ
    public Review byid(int reviewid,int userid);
}
