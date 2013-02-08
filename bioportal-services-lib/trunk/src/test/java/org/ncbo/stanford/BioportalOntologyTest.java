package org.ncbo.stanford;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import junit.framework.Assert;

import org.ncbo.stanford.bean.ontology.OntologyBean;
import org.ncbo.stanford.util.BioPortalServerConstants;
import org.ncbo.stanford.util.BioportalOntology;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BioportalOntologyTest {
	
	@Parameters({"ontologyId", "displayLabel", "debug" })
	@Test
	public void basicTest(int ontologyId, String displayLabel, boolean debug) throws MalformedURLException {
		BioportalOntology c = new BioportalOntology();

		String urlStr = BioPortalServerConstants.BP_REST_BASE + "ontologies/" + ontologyId + "?" + BioPortalServerConstants.BP_PRODUCTION_PROTEGE_API_KEY;
		OntologyBean ob = c.getOntologyProperties(new URL(urlStr));
		Assert.assertEquals(displayLabel, ob.getDisplayLabel());
		if (debug) {
			showOntology(ob);
		}
	}
	
	private void showOntology(OntologyBean ob) {
		System.out.println(ob.getOntologyId() + " " + ob.getId() + " " + ob.getDisplayLabel());
		System.out.println("Download = " + ob.getDownloadLocation());
		boolean isView = ob.isView();
		System.out.println("Is view: " + isView);
		if (isView) {
			System.out.println("\tIs view on ontology version ids: " + ob.getViewOnOntologyVersionId());
		}
		List<Integer> hasViews = ob.getHasViews();
		if ( ! hasViews.isEmpty()) {
			System.out.println("Has views: ");
			for (Integer viewId : hasViews) {
				System.out.println("\t" + viewId);
			}
		}
		else {
			System.out.println("Has NO views defined!");
		}
	}
}
