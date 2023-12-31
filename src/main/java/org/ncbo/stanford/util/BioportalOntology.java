package org.ncbo.stanford.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ncbo.stanford.bean.acl.OntologyAclEntry;
import org.ncbo.stanford.bean.acl.UserAclEntry;
import org.ncbo.stanford.bean.ontology.Data;
import org.ncbo.stanford.bean.ontology.OntologyBean;
import org.ncbo.stanford.bean.ontology.Success;

import com.thoughtworks.xstream.XStream;


/**
 * Class providing direct access information about an ontology
 * in BioPortal, especially by providing XML deserialization
 * methods for results of the BioPortal ontology services.
 *
 * @author csnyulas
 */
public class BioportalOntology {
    private Logger log = Logger.getLogger(BioportalOntology.class.getName());

	private XStream xstream;

	public BioportalOntology() {
		xstream = XStreamUtil.createXStream();
		xstream.alias("success", Success.class);
		xstream.alias("data", Data.class);
		xstream.alias("ontologyBean", OntologyBean.class);
		xstream.alias("ontologyEntry", OntologyAclEntry.class);
		xstream.alias("userEntry", UserAclEntry.class);
	}

	public OntologyBean getOntologyProperties(URL conceptURL) {
		InputStream is = null;
		try {
			is = getInputStream(conceptURL);
		} catch (IOException e) {
			log.log(Level.WARNING, "Exception caught talking to bioportal", e);
		}
		if (is == null) { return null; }
		Success success =  (Success) xstream.fromXML(is);
		if (success == null) { return null; }
		return success.getData().getOntologyBean();
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
