package main.java.dao;

import main.java.pojo.Comment;

import java.util.List;

public interface CommentDao {

	/**
	 * 删除评论
	 * 
	 * @param comment_id
	 * @return
	 */
	boolean deleteComment(int comment_id);

	/**
	 * 新的评论
	 * 
	 * @param comment
	 * @return
	 */
	boolean addComment(Comment comment);

	/**
	 * 点赞或者鄙视
	 * 
	 * @param id
	 * @param star_or_diss
	 * @return
	 */
	int star_diss(int id, int star_or_diss);


	//通过文章的Id获取到对应的文章
	List<Comment> getComment(int article_id);

}