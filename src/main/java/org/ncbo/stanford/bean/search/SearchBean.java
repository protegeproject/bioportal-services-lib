package org.ncbo.stanford.bean.search;

public class SearchBean {
	private int ontologyVersionId;
	private int ontologyId;
	private String ontologyDisplayLabel;
	private String recordType;
	private String conceptId;
	private String conceptIdShort;
	private String preferredName;
	private String contents;
	private String objectType;

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
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getConceptId() {
		return conceptId;
	}
	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}
	public String getConceptIdShort() {
		return conceptIdShort;
	}
	public void setConceptIdShort(String conceptIdShort) {
		this.conceptIdShort = conceptIdShort;
	}
	public String getPreferredName() {
		return preferredName;
	}
	public void setPreferredName(String preferredName) {
		this.preferredName = preferredName;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setOntologyVersionId(int ontologyVersionId) {
		this.ontologyVersionId = ontologyVersionId;
	}
	public int getOntologyVersionId() {
		return ontologyVersionId;
	}
	public String getObjectType() {
        return objectType;
    }
	public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

}
