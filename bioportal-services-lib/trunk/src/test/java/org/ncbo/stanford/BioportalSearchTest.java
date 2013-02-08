package org.ncbo.stanford;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.ncbo.stanford.bean.search.Page;
import org.ncbo.stanford.bean.search.SearchBean;
import org.ncbo.stanford.bean.search.SearchResultListBean;
import org.ncbo.stanford.util.BioPortalServerConstants;
import org.ncbo.stanford.util.BioportalSearch;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BioportalSearchTest {

    @Parameters({"searchTerm", "ontologyDisplayLabel", "preferredName", "recordType", "debug"})
    @Test
    public void testBioportalSearch(String searchTerm, 
                                    String ontologyDisplayLabel,
                                    String preferredName,
                                    String recordType,
                                    boolean debug) throws MalformedURLException, IOException {
        BioportalSearch sd = new BioportalSearch();
        String urlStr = BioPortalServerConstants.BP_REST_BASE + "search/" + searchTerm + "?" + BioPortalServerConstants.BP_PRODUCTION_PROTEGE_API_KEY;
        Page p = sd.getSearchResults(new URL(urlStr));
        SearchResultListBean data = p.getContents();
        boolean found = false;
        for (SearchBean searchBean : data.getSearchResultList()) {
            if (debug) {
                System.out.println(searchBean.getPreferredName() 
                        + "\t" + searchBean.getRecordType() + "\t" + searchBean.getContents() 
                        + "\t" + searchBean.getOntologyDisplayLabel());
            }
            if (searchBean.getOntologyDisplayLabel().equals(ontologyDisplayLabel) 
                   && searchBean.getPreferredName().equals(preferredName)) {
                Assert.assertTrue(searchBean.getRecordType().equals(recordType));
                found = true;
                if (debug) { System.out.println("**************** my record **********************"); }
            }
        }
        Assert.assertTrue(found);
    }
    
}
