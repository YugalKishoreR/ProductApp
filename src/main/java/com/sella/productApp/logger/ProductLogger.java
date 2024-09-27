package com.sella.productApp.logger;

import it.sella.util.Log4Debug;
import it.sella.util.Log4DebugFactory;

public class ProductLogger {

	private Log4Debug LOGGER = null;

	public ProductLogger(Class<?> paramClass) {
    	this.LOGGER = Log4DebugFactory.getLog4Debug( paramClass );
	}

	public void debug(Object... obj1) {
		LOGGER.debug(getString(obj1));
	}

	public void debug(Object obj, Throwable th) {
		LOGGER.debug(getString(obj));
	}

	public void error(Object... obj) {
		LOGGER.debug(getString(obj));
	}

	public void error(Object obj, Throwable th) {
		LOGGER.debug(obj, th);
	}

	private String getString(Object... paramArrayOfObject) {
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramArrayOfObject.length; i++) {
			localStringBuilder.append(paramArrayOfObject[i]);
		}
		return localStringBuilder.toString();
	}

}
