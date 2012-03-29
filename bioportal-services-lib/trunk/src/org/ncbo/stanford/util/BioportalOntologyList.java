package org.ncbo.stanford.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ncbo.stanford.bean.acl.OntologyAclEntry;
import org.ncbo.stanford.bean.acl.UserAclEntry;
import org.ncbo.stanford.bean.ontology.OntologyBean;
import org.ncbo.stanford.bean.ontologyList.Data;
import org.ncbo.stanford.bean.ontologyList.Success;

import com.thoughtworks.xstream.XStream;


/**
 * Class providing direct access information about a list of ontologies
 * in BioPortal, especially by providing XML deserialization
 * methods for results of the BioPortal ontology services.
 *
 * @author csnyulas
 */
public class BioportalOntologyList {
    private Logger log = Logger.getLogger(BioportalOntologyList.class.getName());

	private XStream xstream;

	public BioportalOntologyList() {
		xstream = XStreamUtil.createXStream();
		xstream.alias("success", Success.class);
		xstream.alias("data", Data.class);
		xstream.alias("ontologyBean", OntologyBean.class);
		xstream.alias("ontologyEntry", OntologyAclEntry.class);
        xstream.alias("userEntry", UserAclEntry.class);
	}

	public List<OntologyBean> getOntologyProperties(URL conceptURL) {
		InputStream is = null;
		try {
			is = getInputStream(conceptURL);
		} catch (IOException e) {
			log.log(Level.WARNING, "IO Exception when accessing BioPortal. URL: " + conceptURL, e);
			return null;
		}
		if (is == null) { return null; }
		Success success =  (Success) xstream.fromXML(is);
		if (success == null) { return null; }
		return success.getData().getClassBeanList();
	}

	public static InputStream getInputStream(URL url) throws IOException{
		if(url.getProtocol().equals("http")) {
			URLConnection conn;
			conn = url.openConnection();
			conn.setRequestProperty("Accept", "application/rdf+xml");
			conn.addRequestProperty("Accept", "text/xml");
			conn.addRequestProperty("Accept", "*/*");
			return conn.getInputStream();
		}
		else {
			return url.openStream();
		}
	}


	public static void main(String[] args) {
		BioportalOntologyList c = new BioportalOntologyList();
		// String urlStr = "http://rest.bioontology.org/bioportal/ontologies";
		String urlStr = "http://stagerest.bioontology.org/bioportal/ontologies";

		try {
			List<OntologyBean> obList = c.getOntologyProperties(new URL(urlStr));
			OntologyBean ob;
			List<String> ontologyList = new ArrayList<String>(0);

			for (Iterator<OntologyBean> it = obList.iterator(); it.hasNext ();) {
				ob = it.next ();
				System.out.println(ob.getOntologyId() + " " + ob.getId() + " " + ob.getDisplayLabel());
				ontologyList.add(ob.getDisplayLabel() + " (" + ob.getId() + ")");
				boolean isView = ob.isView();
				System.out.println("Is view: " + isView);
				if (isView) {
					System.out.println("\tIs view on ontology version ids: " + ob.getViewOnOntologyVersionId());
				}
				List<Integer> hasViews = ob.getHasViews();
				if ( hasViews != null && !hasViews.isEmpty()) {
					System.out.println("Has views: ");
					for (Integer viewId : hasViews) {
						System.out.println("\t" + viewId);
					}
				}
				else {
					System.out.println("Has NO views defined!");
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
