package com.mlo450.se325.a01.logging;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class LoggingAdvice implements MethodBeforeAdvice {

	private static final Logger _LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);
	
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String args_string = "";
		for (Object arg: args) {
			args_string = args_string + arg.toString() + ", ";
		}
		_LOGGER.info("Method " + method.getName() + " was just called on target object " + 
				target.getClass().getName() + " with arguments " + args_string);
	}
}
