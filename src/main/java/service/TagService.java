package main.java.service;

import main.java.pojo.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author xyLonely
 * @date 2018/12/20 9:43
 */
public interface TagService {

    //获取所有的标签列表(排除重复的)
   List<Tag> getAllTag();

   //获取标签的总数
   int getTagCount();

   //获取标签以及相关联的文章
   Map getTagAndArticle();

   //通过文章ID获取到对应的标签D
   List<Tag> getTagById(String id);
}
