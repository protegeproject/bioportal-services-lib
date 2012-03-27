package org.ncbo.stanford.bean.ontology;

/**
 * Bean class used to decode the "data" element from a successful
 * REST call to a BioPortal ontology service
 *
 * @author csnyulas
 *
 */
public class Data {
	private OntologyBean ontologyBean;

    public OntologyBean getOntologyBean() {
        return ontologyBean;
    }

    public void setOntologyBean(OntologyBean ontologyBean) {
        this.ontologyBean = ontologyBean;
    }

}
