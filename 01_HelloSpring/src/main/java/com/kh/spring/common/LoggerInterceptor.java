package com.kh.spring.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger=LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(logger.isDebugEnabled()) //디버그 확인
		{
			logger.debug("==================start==================");
			logger.debug(request.getRequestURI());
			logger.debug("------------------------end-----------------------");
		}
		
		if(request.getSession().getAttribute("userId")==null){
			request.setAttribute("msg", "로그인 하십시오");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return false;
		}
		
		
		return super.preHandle(request, response, handler); //정상적으로 다음으로 넘어가는 구문
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug("==================view==================");
			logger.debug("------------------------end-----------------------");
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("==================end===================");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
