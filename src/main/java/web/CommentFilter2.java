package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentFilter2 implements Filter {
	/**
	 * 容器删除过滤器之前会调用destory方法。只会调用一次。
	 */
	public void destroy() {
		System.out.println("filter2的destroy方法调用了");
	}

	/**
	 * 容器在创建好request，response对象后，会调用doFilter方法，并把参数传进来。
	 * 也就是说可以在过滤器中访问request，response对象。
	 * FilterChain：过滤器链。里面也有doFilter(req,res);只有调用此方法，才会继续往调用。否则，请求处理完毕。
	 * 
	 * 过度设计带来副作用，要强转。与协议无关的方法。
	 * 不管有多少过滤器，用到的请求对象和相应对象都是一样的。
	 */

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("filter2 doFilter方法开始执行。。。。");
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		String content = req.getParameter("content");
		// 查看是否有敏感字
		if (content.length()>10) {
			out.println("评论次数过多");
		} else {
			//没有敏感字，执行此方法，会继续往后调用。
			arg2.doFilter(arg0, arg1);
		}
		System.out.println("filter2 doFilter方法执行完毕。。。。");
		
	}

	/**
	 * :容器启动就会调用！
	 * 容器启动之后，会立刻创建过滤器实例（只会创建一个实例），接下来，会调用该实例的init方法。 （容器会将FilterConfig对象作为参数传递过来）
	 * FilterConfig提供了getInitParamter方法来访问初始化参数。
	 */
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("filter2的init方法被调用了");
	}

	public CommentFilter2() {
		System.out.println("fileter2的构造器实例");
	}

}
