package org.ncbo.stanford.bean.search;

public class OntologyHitBean {
	private int ontologyVersionId;
	private int ontologyId;
	private String ontologyDisplayLabel;
	private int numHits;
	
	public int getOntologyVersionId() {
		return ontologyVersionId;
	}
	public void setOntologyVersionId(int ontologyVersionId) {
		this.ontologyVersionId = ontologyVersionId;
	}
	public int getOntologyId() {
		return ontologyId;
	}
	public void setOntologyId(int ontologyId) {
		this.ontologyId = ontologyId;
	}
	public String getOntologyDisplayLabel() {
		return ontologyDisplayLabel;
	}
	public void setOntologyDisplayLabel(String ontologyDisplayLabel) {
		this.ontologyDisplayLabel = ontologyDisplayLabel;
	}
	public int getNumHits() {
		return numHits;
	}
	public void setNumHits(int numHits) {
		this.numHits = numHits;
	}
	
}
