package main.java.web.servlet;

import main.java.pojo.Article;
import main.java.service.ArticleService;
import main.java.service.CommentService;
import main.java.service.TagService;
import main.java.service.impl.ArticleServiceImpl;
import main.java.service.impl.CommentServiceImpl;
import main.java.service.impl.TagServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取前端传过来的文章ID
		String id= request.getParameter("id");
		//声明一个文章服务层对象
		ArticleService articleService =  new ArticleServiceImpl();
		//通过ID去获取到对应的文章
		Article article = articleService.getArticle("id",id).get(0);
		//将获取到的文章放到request域里面
		request.setAttribute("article",article);
		//文章的所有标签
		TagService tagService = new TagServiceImpl();
		//将获取到的当前文章的标签放到request域里面
		request.setAttribute("article_tags",tagService.getTagById(id));
		//获取上一篇文章
		request.setAttribute("article_pre", articleService.getPreviousArticle(article.getTime()));
		//获取下一篇文章
		request.setAttribute("article_next", articleService.getNextArticle(article.getTime()));
		//加载文章评论
		CommentService commentService = new CommentServiceImpl();
		//将获取到的当前文章的评论放到request域里面
		request.setAttribute("comment",commentService.loadComment(article.getId()));
		
		request.getRequestDispatcher("/page/article.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);


	}

}
