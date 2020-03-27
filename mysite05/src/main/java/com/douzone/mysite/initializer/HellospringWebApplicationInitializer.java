package com.douzone.mysite.initializer;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.douzone.mysite.config.AppConfig;
import com.douzone.mysite.config.WebConfig;

public class HellospringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Root Application Context 설정 클래스
		return new Class<?>[] {AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Web Application Context 설정 클래스
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// 서블릿 매핑
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		// filters
		return new Filter[] {new CharacterEncodingFilter("UTF-8", true)};
	}

	@Override
	protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
		// create Dispatcher Servlet
		DispatcherServlet dispatcherServlet = (DispatcherServlet)super.createDispatcherServlet(servletAppContext);
		// Exception Handler가 없으면 exception 던진다.
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); 
		return dispatcherServlet;
	}
	
	
}
