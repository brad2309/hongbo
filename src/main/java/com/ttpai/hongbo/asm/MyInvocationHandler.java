package com.ttpai.hongbo.asm;

import java.lang.reflect.Method;

public interface MyInvocationHandler {

	Object invoke(Method method, Object[] args);
	
}
