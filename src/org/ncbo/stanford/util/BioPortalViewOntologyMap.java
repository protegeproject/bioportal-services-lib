package org.ncbo.stanford.util;

import java.util.HashMap;
import java.util.Map;

import org.ncbo.stanford.bean.ontology.OntologyBean;

public class BioPortalViewOntologyMap {

	private Map<Integer, OntologyBean> viewOntologyMap = new HashMap<Integer, OntologyBean>();
	private Map<Integer, Integer> viewIdOntologyIdMap = new HashMap<Integer, Integer>();

	public static final int NOT_A_VIEW = 0;
	public static final int UNKNOWN = -1;

	
	/**
	 * Returns a BioPortal ontology ID on which the view with id 
	 * <code>viewId</code> is defined.
	 * @param viewId
	 * @return
	 */
	public int getViewOnOntologyId(int viewId) {
		Integer viewOnOntologyId = viewIdOntologyIdMap.get(viewId);
		return (viewOnOntologyId == null ? UNKNOWN : viewOnOntologyId);
	}
	
	/**
	 * Returns the display label of the BioPortal ontology 
	 * on which the view with id <code>viewId</code> is defined.
	 * @param viewId
	 * @return
	 */
	public String getViewOnOntologyDisplayLabel(int viewId) {
		OntologyBean viewOnOntology = viewOntologyMap.get(viewId);
		return (viewOnOntology == null ? "" : viewOnOntology.getDisplayLabel());
	}
	
	/**
	 * Returns an ontology bean corresponding to a BioPortal ontology 
	 * on which the view with id <code>viewId</code> is defined.
	 * @param viewId
	 * @return
	 */
	public OntologyBean getViewOnOntology(int viewId) {
		return viewOntologyMap.get(viewId);
	}

	public void setViewOnOntologyId(int viewId, int ontologyId, OntologyBean ontologyBean) {
		viewIdOntologyIdMap.put(viewId, ontologyId);
		viewOntologyMap.put(viewId, ontologyBean);
	}

	public void reset() {
		viewIdOntologyIdMap.clear();
		viewOntologyMap.clear();
	}

}
