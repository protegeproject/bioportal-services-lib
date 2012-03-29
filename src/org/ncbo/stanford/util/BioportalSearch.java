package org.ncbo.stanford.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.ncbo.stanford.bean.search.Data;
import org.ncbo.stanford.bean.search.OntologyHitBean;
import org.ncbo.stanford.bean.search.Page;
import org.ncbo.stanford.bean.search.SearchBean;
import org.ncbo.stanford.bean.search.SearchResultListBean;
import org.ncbo.stanford.bean.search.Success;

import com.thoughtworks.xstream.XStream;


public class BioportalSearch {

	private XStream xstream;

	public BioportalSearch() {
		xstream = XStreamUtil.createXStream();
		xstream.alias("success", Success.class);
		xstream.alias("contents", SearchResultListBean.class);
		xstream.alias("page", Page.class);
		xstream.alias("data", Data.class);
		xstream.alias("searchBean", SearchBean.class);
		xstream.alias("ontologyHitBean", OntologyHitBean.class);
	}

	public Page getSearchResults(URL searchURL) throws IOException {
		InputStream is = null;
		try {
			is = getInputStream(searchURL);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		if (is == null) { return null; }
		Success success =  (Success) xstream.fromXML(is);
		if (success == null) { return null; }
		if (success.getData() == null) { return null;}
		return success.getData().getPage();
	}

	public static InputStream getInputStream(URL url) throws IOException{
		return url.openStream();
	}

}
