package main.java.service.impl;

import main.java.dao.TagDao;
import main.java.dao.impl.TagDaoImpl;
import main.java.pojo.Article;
import main.java.pojo.Tag;
import main.java.service.ArticleService;
import main.java.service.TagService;
import main.java.utils.ArticleUtils;

import java.util.*;

/**
 * @author xyLonely
 * @date 2018/12/20 9:42
 */
public class TagServiceImpl implements TagService {

    TagDao tagDao = new TagDaoImpl();

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public int getTagCount() {
        return tagDao.getAllTag().size();
    }

    @Override
    public Map getTagAndArticle() {
        ArticleService articleService = new ArticleServiceImpl();
        //key存放标签 value存放相关联文章
        Map map = new HashMap();
        //获取所有不重复的标签
        List<Tag> tag_list = tagDao.getAllTag();
        //遍历不重复的标签
        for (Tag tag : tag_list) {
            //查询标签对应的所有标签的集合
            List<Tag> list = tagDao.getTagByColumn("tag", tag.getTag());
            //创建一个集合存放标签所对应的文章
            List<Article> article_list = new ArrayList<>();
            //遍历相同标签
            for (Tag tag1 : list) {
                //这个集合只有一个元素     查询所有的相同标签对应的文章
                List<Article> result = articleService.getArticle("id", String.valueOf(tag1.getId()));
                Article article = ArticleUtils.cutTime(result.get(0));
                article_list.add(article);
            }
            map.put(tag.getTag(), article_list);
        }
        return map;
    }

    @Override
    public List<Tag> getTagById(String id) {
        return tagDao.getTagByColumn("id", id);
    }
}
