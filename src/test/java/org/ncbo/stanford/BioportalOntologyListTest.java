package org.ncbo.stanford;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.ncbo.stanford.bean.ontology.OntologyBean;
import org.ncbo.stanford.util.BioPortalServerConstants;
import org.ncbo.stanford.util.BioportalOntologyList;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BioportalOntologyListTest {
	
    
    @Parameters({"ontologyId", "displayLabel"})
	@Test
	public void test(int ontologyId, String displayLabel) throws MalformedURLException {
		BioportalOntologyList c = new BioportalOntologyList();
		// String urlStr = "http://rest.bioontology.org/bioportal/ontologies";
		String urlStr = "http://stagerest.bioontology.org/bioportal/ontologies" + "?" + BioPortalServerConstants.BP_PRODUCTION_PROTEGE_API_KEY;;

		List<OntologyBean> obList = c.getOntologyProperties(new URL(urlStr));
		Assert.assertTrue(obList.size() > 100);
		OntologyBean matchedOntology = null;
		for (OntologyBean ob : obList) {
		    if (ob.getDisplayLabel().equals(displayLabel)) {
		        Assert.assertNull(matchedOntology);
		        Assert.assertEquals((int) ob.getId(), ontologyId);
		        matchedOntology = ob;
		    }
		}
		Assert.assertNotNull(matchedOntology);
	}
}
