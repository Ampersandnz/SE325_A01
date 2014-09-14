package com.mlo450.se325.a01.logging;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAdvice implements MethodInterceptor {

	private static final Logger _LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object [] args = invocation.getArguments();
		String args_string = "";
		Method method = invocation.getMethod();
		Object target = invocation.getThis();
		
		for (Object arg: args) {
			args_string = args_string + arg.toString() + ", ";
		}

		_LOGGER.info("Method " + method.getName() + " was just called on target object " + 
				target.getClass().getName() + " with arguments " + args_string);
		
		Object returnValue = invocation.proceed();

		_LOGGER.info(target.getClass().getName() + " successfully completed " + method.getName() + ".");
		
		return returnValue;
	}
}
