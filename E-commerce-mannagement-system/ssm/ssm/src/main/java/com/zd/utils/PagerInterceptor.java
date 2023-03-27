package com.zd.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 分页拦截器（在这里组装URL地址）
 * @author Administrator
 *
 */
public class PagerInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 获取请求的url地址
		String url = request.getRequestURL().toString();
		if (url.indexOf("/list") != -1) {
			url+="?a=a";
			// 构建查询的参数(分页需要带着查询参数走)
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if (!name.equals("a")&&!name.equals("index")) {
					url += "&" + name + "=" + request.getParameter(name);	
				}
			}
			//System.out.println(url);
			modelAndView.addObject("url", url);
		}

		if (url.indexOf("/findByPagers") != -1) {
			url+="?a=a";
			// 构建查询的参数(分页需要带着查询参数走)
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if (!name.equals("a")&&!name.equals("index")) {
					url += "&" + name + "=" + request.getParameter(name);
				}
			}
			//System.out.println(url);
			modelAndView.addObject("url", url);
		}
		if (url.indexOf("/findByName") != -1) {
			url+="?a=a";
			// 构建查询的参数(分页需要带着查询参数走)
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if (!name.equals("a")&&!name.equals("index")) {
					url += "&" + name + "=" + request.getParameter(name);
				}
			}
			//System.out.println(url);
			modelAndView.addObject("url", url);
		}
		if (url.indexOf("/findByTabId") != -1) {
			url+="?a=a";
			// 构建查询的参数(分页需要带着查询参数走)
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if (!name.equals("a")&&!name.equals("index")) {
					url += "&" + name + "=" + request.getParameter(name);
				}
			}
			//System.out.println(url);
			modelAndView.addObject("url", url);
		}
	}

}
