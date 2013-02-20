package org.ncbo.stanford.bean.search;

import java.util.List;


public class SearchResultListBean {
	private List<SearchBean> searchResultList;
	private List<OntologyHitBean> ontologyHitList;
	private String classAttr;
		
	public List<OntologyHitBean> getOntologyHitList() {
		return ontologyHitList;
	}
	public void setOntologyHitList(List<OntologyHitBean> ontologyHitList) {
		this.ontologyHitList = ontologyHitList;
	}
	public String getClassAttr() {
		return classAttr;
	}
	public void setClassAttr(String classAttr) {
		this.classAttr = classAttr;
	}
	public void setSearchResultList(List<SearchBean> searchResultList) {
		this.searchResultList = searchResultList;
	}
	public List<SearchBean> getSearchResultList() {
		return searchResultList;
	} 
	
} 
