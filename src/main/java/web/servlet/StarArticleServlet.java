package main.java.web.servlet;

import main.java.service.ArticleService;
import main.java.service.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xyLonely
 * @date 2018/12/21 15:19
 */
@WebServlet("/StarArticleServlet")
public class StarArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =  request.getParameter("id");
        System.out.println("我进来了");
        ArticleService articleService = new ArticleServiceImpl();
        articleService.updateStar(Integer.valueOf(id));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
