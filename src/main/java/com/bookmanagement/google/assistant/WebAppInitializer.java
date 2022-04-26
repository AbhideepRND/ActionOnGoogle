package com.bookmanagement.google.assistant;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext jsonContext = getJsonContext();
		servletContext.addListener(new ContextLoaderListener(jsonContext));
		ServletRegistration.Dynamic dispatcherJson = servletContext.addServlet("DispatcherServlet",
				new DispatcherServlet(jsonContext));
		dispatcherJson.setLoadOnStartup(1);
		dispatcherJson.addMapping("*.json");

	}

	private AnnotationConfigWebApplicationContext getJsonContext(){
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.bookmanagement.google.assistant.resource");
		return context;
	}

}
