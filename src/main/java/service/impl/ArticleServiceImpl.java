package main.java.service.impl;

import main.java.dao.ArticleDao;
import main.java.dao.impl.ArticleDaoImpl;
import main.java.pojo.Article;
import main.java.service.ArticleService;
import main.java.utils.ArticleUtils;
import main.java.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xyLonely
 * @date 2018/12/19 16:56
 */
public class ArticleServiceImpl implements ArticleService {

    ArticleDao articleDao = new ArticleDaoImpl();

    @Override
    public Map getSortAndCount() {
        return articleDao.getColumAndCount();
    }

    @Override
    public List<Article> getArticle() {
        List<Article> list = articleDao.getAllArticle();
        List list1 = ArticleUtils.cutContent(list);
        List list2 = ArticleUtils.cutTime(list1);
        return list2;
    }

    @Override
    public List<Article> getArticle(String column, String value) {
        return articleDao.getArticleByColumn(column, value);
    }

    @Override
    public List<Article> getVisitRank() {
        List<Article> list = articleDao.getVisitRank();
        if(list.size()>10){
            for(int i = 10;i<list.size();i++){
                list.remove(i);
            }
        }
        for(int y=0;y<list.size();y++){
            Article a = list.get(y);
            if(a.getTitle().length()>20){
                a.setTitle(StringUtils.cutString(a.getTitle(), 0, 19)+"...");
            }
        }
        return list;
    }

    @Override
    public int getCount(String search_key) {
        return articleDao.getCount(search_key);
    }

    @Override
    public Map getSortAndAirticle(String sort_name) {
        Map<String,List<Article>> map = new HashMap();
        List<Article> articleBySort = null;
        if (sort_name.equals("all") || StringUtils.isEmpty(sort_name)) {
            //获取所有的文章类别
            List<String> sorts = articleDao.getAllSort();
            for (int i = 0; i < sorts.size(); i++) {
                String sort = sorts.get(i);
                //通过文章类别来查找相应的文章
                articleBySort = articleDao.getArticleByColumn("sort", sort);
                ArticleUtils.cutTime(articleBySort);
                map.put(sort, articleBySort);
            }
        } else {
            // 获取单个分类
            articleBySort = articleDao.getArticleByColumn("sort", sort_name);
            ArticleUtils.cutTime(articleBySort);
            map.put(sort_name, articleBySort);
        }
        return map;
    }

    @Override
    public Article getPreviousArticle(String time) {
        return articleDao.getANearArticle(time, 1);
    }

    @Override
    public Article getNextArticle(String time) {
        return articleDao.getANearArticle(time, 2);
    }

    @Override
    public void addVisit(Integer article_id) {
        articleDao.addVisit(article_id);

    }

    @Override
    public void updateStar(Integer id) {
        articleDao.updateStar(id);
    }
}
