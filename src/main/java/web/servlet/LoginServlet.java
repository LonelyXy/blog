package main.java.web.servlet;

import main.java.pojo.User;
import main.java.service.ArticleService;
import main.java.service.TagService;
import main.java.service.UserService;
import main.java.service.impl.ArticleServiceImpl;
import main.java.service.impl.TagServiceImpl;
import main.java.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login->index.jsp->init data
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User userx = (User) session.getAttribute("user");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService userService = new UserServiceImpl();
		User user = userService.login(username, password);
		if(user==null&&userx==null){
			session.setAttribute("msg","用户名或者密码错误");
			response.sendRedirect("login.jsp");
		}else if(userx!=null&&user==null){
			ini(request);
			request.getRequestDispatcher("/page/main.jsp").forward(request, response);
		}else{
			session = request.getSession();
			session.setAttribute("user", user);
			ini(request);
			request.getRequestDispatcher("/page/main.jsp").forward(request, response);

		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	//封装一个初始化的方法
	protected  void  ini(HttpServletRequest request){
		//初始化分类列表
		ArticleService articleService =  new ArticleServiceImpl();
		request.setAttribute("sort_count_map", articleService.getSortAndCount());
		//初始化阅读排行
		request.setAttribute("visit_rank", articleService.getVisitRank());
		//初始化文章列表
		request.setAttribute("article_list", articleService.getArticle());
		//初始化标签列表
		TagService tagService = new TagServiceImpl();
		request.setAttribute("tag_list", tagService.getAllTag());
		//初始化侧边栏 日志、分类、标签的个数
		request.setAttribute("article_number", articleService.getCount("servlet"));
		request.setAttribute("sort_number", articleService.getCount("sort"));
		request.setAttribute("tags_number", tagService.getTagCount());
	}

}
