package org.ncbo.stanford;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.ncbo.stanford.bean.search.Page;
import org.ncbo.stanford.bean.search.SearchBean;
import org.ncbo.stanford.bean.search.SearchResultListBean;
import org.ncbo.stanford.util.BioPortalServerConstants;
import org.ncbo.stanford.util.BioportalSearch;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicTests {
	private Logger logger = Logger.getLogger(BasicTests.class.getCanonicalName());

	@Test
	public void testBioportalSearch() throws MalformedURLException, IOException {
		BioportalSearch sd = new BioportalSearch();
		String urlStr = BioPortalServerConstants.BP_REST_BASE + "search/Heart?" + BioPortalServerConstants.BP_PRODUCTION_PROTEGE_API_KEY;
		Page p = sd.getSearchResults(new URL(urlStr));
		SearchResultListBean data = p.getContents();
		boolean found = false;
		for (SearchBean searchBean : data.getSearchResultList()) {
			logger.fine(searchBean.getPreferredName() + "\t" + searchBean.getRecordType() + "\t" + searchBean.getContents() +
					"\t" + searchBean.getOntologyDisplayLabel());
			if (searchBean.getOntologyDisplayLabel().equals("SNOMED Clinical Terms") && searchBean.getPreferredName().equals("Entire heart")) {
				Assert.assertTrue(searchBean.getRecordType().equals("csynonym"));
				found = true;
			}
		}
		Assert.assertTrue(found);
	}
	
}
