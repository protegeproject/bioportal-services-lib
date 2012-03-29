package org.ncbo.stanford;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.ncbo.stanford.bean.concept.ClassBean;
import org.ncbo.stanford.util.BioportalConcept;

public class BioportalConceptTest {

	public void test() {
		BioportalConcept c = new BioportalConcept();
		String urlStr = "http://rest.bioontology.org/bioportal/concepts/39002/BRO:Resource";  //production
		//String urlStr = "http://stagerest.bioontology.org/bioportal/concepts/39002/BRO:Resource"; //stage
		// String urlStr = "http://rest.bioontology.org/bioportal/concepts/44333/root";
		try {
			ClassBean cb = c.getConceptProperties(new URL(urlStr));
			System.out.println(cb.getFullId() + " " + cb.getId() + " " + cb.getLabel() );
			System.out.println("Synonyms: " + cb.getSynonyms());
			Map<Object, Object> relationsMap = cb.getRelations();
			for (Object obj : relationsMap.keySet()) {
				System.out.println(obj + ": " + relationsMap.get(obj));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
