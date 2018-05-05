package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CommentServlet() {
       
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommentServlet service方法开始执行。。。。");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String content=request.getParameter("content");
		out.println("你的评论是："+content);
		System.out.println("CommentServlet service方法执行完毕。。");
		//out.close(); 整个流程执行完，容器会自动关闭流。
	}

}
