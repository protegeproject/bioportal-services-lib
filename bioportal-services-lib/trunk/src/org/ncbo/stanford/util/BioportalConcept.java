package org.ncbo.stanford.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ncbo.stanford.bean.concept.ClassBean;
import org.ncbo.stanford.bean.concept.Data;
import org.ncbo.stanford.bean.concept.InstanceBean;
import org.ncbo.stanford.bean.concept.Success;

import com.thoughtworks.xstream.XStream;


public class BioportalConcept {
    private final Logger log = Logger.getLogger(BioportalConcept.class.getName());

	private final XStream xstream;

	public BioportalConcept() {
		xstream = XStreamUtil.createXStream();
		xstream.alias("success", Success.class);
		xstream.alias("data", Data.class);
		xstream.alias("classBean", ClassBean.class);
		xstream.alias("instanceBean", InstanceBean.class);
		xstream.alias("instanceType", ArrayList.class);
	}

	public ClassBean getConceptProperties(String conceptURL) {
	    if (conceptURL == null) { return null; }
	    URL url = null;
	    try {
           url = new URL(conceptURL);
        } catch (MalformedURLException e) {
            log.warning("Malformed URL: " + conceptURL);
            return null;
        }
        return getConceptProperties(url);

	}

	public ClassBean getConceptProperties(URL conceptURL) {
	    if (conceptURL == null) { return null; }
		InputStream is = null;
		try {
			is = getInputStream(conceptURL);
		} catch (IOException e) {
			log.log(Level.WARNING, "Exception caught talking to bioportal", e);
		}
		if (is == null) { return null; }
		Success success =  (Success) xstream.fromXML(is);
		if (success == null) { return null; }
		return success.getData().getClassBean();
	}

	public static InputStream getInputStream(URL url) throws IOException{
		if(url.getProtocol().equals("http")) {
			URLConnection conn;
			conn = url.openConnection();

			conn.setConnectTimeout(ApplicationProperties.getUrlConnectTimeout() * 1000);
            conn.setReadTimeout(ApplicationProperties.getUrlConnectReadTimeout() * 1000);

			conn.setRequestProperty("Accept", "application/rdf+xml");
			conn.addRequestProperty("Accept", "text/xml");
			conn.addRequestProperty("Accept", "*/*");
			return conn.getInputStream();
		}
		else {
			return url.openStream();
		}
	}

}
