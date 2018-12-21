package main.java.dao.impl;

import main.java.dao.ArticleDao;
import main.java.pojo.Article;
import main.java.utils.Utils_c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 文章服务类
 *
 */
public class ArticleDaoImpl implements ArticleDao {


	@Override
	public Map getColumAndCount() {
		String sql = " select sort,count(sort) as counts  from t_article  group by sort";
		Connection connection = Utils_c3p0.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		Map map = null;
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			map = new HashMap();
			while (resultSet.next()) {
				map.put(resultSet.getString("sort"), resultSet.getInt("counts"));
			}
			Utils_c3p0.close(resultSet,pStatement,connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public void addVisit(int article_id) {
        String sql = "update t_article set visit = visit+1 where id = ?";
        Object[] o = { article_id };
        Utils_c3p0.executeUpdate(sql, o);
	}


	@Override
	public Article getANearArticle(String time, int less_or_more) {
        Connection connection = Utils_c3p0.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        Article article = null;
		String sql = null;
		if (less_or_more == 1) {
			sql = " SELECT  * FROM t_article WHERE TIME< '" + time + "'  ORDER BY TIME DESC ";
		} else if (less_or_more == 2) {
			sql = " SELECT  * FROM t_article WHERE TIME > '" + time + "'  ORDER BY TIME ";
		}
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				article = new Article(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("sort"),
                        resultSet.getString("time"),
                        resultSet.getInt("star"),
                        resultSet.getInt("comment"),
                        resultSet.getInt("visit"),
                        resultSet.getString("content"));
			}
            Utils_c3p0.close(resultSet,pStatement,connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return  article;

	}




	@Override
	public List<String> getAllSort() {
		Connection connection = Utils_c3p0.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = " select distinct(sort) from t_article order by sort";
		List<String> list = new ArrayList<>();
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			Utils_c3p0.close(resultSet,pStatement,connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Article addArticle(Article a) {
		return null;
	}

	@Override
	public boolean deleteArticle(String id) {
		return false;
	}

	@Override
	public List getAllArticle() {
		String sql = " select * from t_article";
		Connection connection = Utils_c3p0.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		List<Article> list= new ArrayList<>();
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Article article = new Article(resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("author"),
						resultSet.getString("sort"),
						resultSet.getString("time"),
						resultSet.getInt("star"),
						resultSet.getInt("comment"),
						resultSet.getInt("visit"),
						resultSet.getString("content"));
				list.add(article);
			}
			Utils_c3p0.close(resultSet,pStatement,connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	//增加点赞数量
	@Override
	public void updateStar(Integer id) {
		String sql = "update t_article set star = star+1 where id = ?";
		Object[] o = {id};
		Utils_c3p0.executeUpdate(sql,o);
	}

	@Override
	public List getVisitRank() {
		String sql = " select * from t_article ORDER BY visit DESC ";
		Connection connection = Utils_c3p0.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		List<Article> list= new ArrayList<>();
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Article article = new Article(resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("author"),
						resultSet.getString("sort"),
						resultSet.getString("time"),
						resultSet.getInt("star"),
						resultSet.getInt("comment"),
						resultSet.getInt("visit"),
						resultSet.getString("content"));
				list.add(article);
			}
			Utils_c3p0.close(resultSet,pStatement,connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Article> getArticleByColumn(String column, String value) {
		Connection connection = Utils_c3p0.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		List<Article> list= new ArrayList<>();
		String sql = "select * from t_article where " + column + " = ?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, value);
			resultSet = pStatement.executeQuery();
			// bean实例化
			while (resultSet.next()) {
				Article article = new Article(resultSet.getInt("id"),
						resultSet.getString("title"),
						resultSet.getString("author"),
						resultSet.getString("sort"),
						resultSet.getString("time"),
						resultSet.getInt("star"),
						resultSet.getInt("comment"),
						resultSet.getInt("visit"),
						resultSet.getString("content"));
				list.add(article);
			}
			// 关闭连接
			Utils_c3p0.close(resultSet,pStatement,connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getCount(String search_key) {
		Connection connection = Utils_c3p0.getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql;
		if (search_key.equals("servlet")) {
			sql = "SELECT COUNT(id) FROM t_article";
		} else {
			sql = "SELECT COUNT(DISTINCT(sort)) FROM t_article";
		}
		int result = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			Utils_c3p0.close(resultSet,pStatement,connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int star_article(int id) {
		return 0;
	}

	@Override
	public boolean updateSort(String old_sort, String new_sort) {
		return false;
	}

	@Override
	public boolean delelteSort(String sort) {
		return false;
	}
}


