package org.ncbo.stanford.util;

/*
 * Very limited replacement for the protege application properties class - no compiler errors.
 */
public class ApplicationProperties {
	public static final String APPLICATION_PROPERTIES_CLASS = "edu.stanford.smi.protege.util.ApplicationProperties";

    public static int getUrlConnectReadTimeout() {
    	try {
    		Object o = Class.forName(APPLICATION_PROPERTIES_CLASS).getMethod("getUrlConnectReadTimeout").invoke(null);
    		if (o instanceof Integer) {
    			return (Integer) o;
    		}
    	}
    	catch (Exception e) {
    		; // this is ok...
    	}
    	return 10;
    }

    public static int getUrlConnectTimeout() {
    	try {
    		Object o = Class.forName(APPLICATION_PROPERTIES_CLASS).getMethod("getUrlConnectTimeout").invoke(null);
    		if (o instanceof Integer) {
    			return (Integer) o;
    		}
    	}
    	catch (Exception e) {
    		; // this is ok...
    	}
    	return 10;
    }
}
