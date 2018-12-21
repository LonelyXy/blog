package main.java.service;

import main.java.pojo.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

	//获取所有类别以及类别总数  key存放类别   value存放值
	Map<String,Integer> getSortAndCount();

	//获取所有的文章
	List<Article> getArticle();

	//通过某个属性获取文章
	List<Article> getArticle(String column, String value);

	//获取阅读排行
	List<Article> getVisitRank();

	//获取文章的数量 或 分类的数量
	int getCount(String search_key);

	Map<String,String> getSortAndAirticle(String sort_name);


	Article getPreviousArticle(String time);

	Article getNextArticle(String time);

	//添加浏览次数
	void addVisit(Integer integer);

	//增加点赞
    void updateStar(Integer id);
}