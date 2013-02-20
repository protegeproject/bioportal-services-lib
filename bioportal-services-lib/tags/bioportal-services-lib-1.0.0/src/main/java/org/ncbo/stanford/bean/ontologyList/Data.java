package org.ncbo.stanford.bean.ontologyList;

import java.util.List;

import org.ncbo.stanford.bean.ontology.OntologyBean;

/**
 * Bean class used to decode the "data" element from a successful
 * REST call to a BioPortal ontology service that returns a list of ontologies
 *
 * @author jnair
 *
 */
public class Data {
	private List<OntologyBean> list;

	public void setClassBean(List<OntologyBean> list) {
		this.list = list;
	}

	public List<OntologyBean> getClassBeanList() {
		return list;
	}
}
