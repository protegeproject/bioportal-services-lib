package org.ncbo.stanford;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ncbo.stanford.bean.ontology.OntologyBean;
import org.ncbo.stanford.util.BioportalOntologyList;

public class BioportalOntologyListTest {
	
	
	public void test() throws MalformedURLException {
		BioportalOntologyList c = new BioportalOntologyList();
		// String urlStr = "http://rest.bioontology.org/bioportal/ontologies";
		String urlStr = "http://stagerest.bioontology.org/bioportal/ontologies";

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
	}
}
