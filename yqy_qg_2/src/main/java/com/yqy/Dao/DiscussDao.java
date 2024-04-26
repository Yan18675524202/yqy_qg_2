package com.yqy.Dao;

import com.yqy.pojo.Discussion;

import java.util.ArrayList;

public interface DiscussDao {

public ArrayList<Discussion> SelectByLessonId(int id);

public int insertDiscussion(Discussion discussion);

public void deleteDiscussionByName(String name);

public int updateDiscussion(Discussion discussion);


}
