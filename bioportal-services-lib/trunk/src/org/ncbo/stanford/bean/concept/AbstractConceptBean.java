package org.ncbo.stanford.bean.concept;

import java.util.List;
import java.util.Map;

import org.ncbo.stanford.util.HTMLUtil;


public abstract class AbstractConceptBean {
	protected String ontologyVersionId;
	protected String id;
	protected String fullId;
	protected String label;
	protected String type;
	protected List<String> synonyms;
	protected List<String> definitions;
	protected List<String> authors;
	protected Byte isObsolete;
	
	protected Map<Object, Object> relations;
	
	public String getOntologyVersionId() {
		return ontologyVersionId;
	}
	public void setOntologyVersionId(String ontologyVersionId) {
		this.ontologyVersionId = ontologyVersionId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullId() {
		return fullId;
	}
	public void setFullId(String fullId) {
		this.fullId = fullId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}
	public List<String> getDefinitions() {
		return definitions;
	}
	public void setDefinitions(List<String> definitions) {
		this.definitions = definitions;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public Byte getIsObsolete() {
		return isObsolete;
	}
	public void setIsObsolete(Byte isObsolete) {
		this.isObsolete = isObsolete;
	}
	public Map<Object, Object> getRelations() {
		return relations;
	}
	public void setRelations(Map<Object, Object> relations) {
		this.relations = relations;
	}
	@Override
	public String toString() {	
		return HTMLUtil.replaceEOF(label);
	}

}
