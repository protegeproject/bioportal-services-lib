package org.ncbo.stanford.bean.search;

public class Page {
	private int pageNum;
	private int numPages;
	private int pageSize;
	private int numResultsPage;
	private int numResultsTotal;
	private SearchResultListBean contents;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPages() {
		return numPages;
	}
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	public int getNumResultsPage() {
		return numResultsPage;
	}
	public void setNumResultsPage(int numResultsPage) {
		this.numResultsPage = numResultsPage;
	}
	public int getNumResultsTotal() {
		return numResultsTotal;
	}
	public void setNumResultsTotal(int numResultsTotal) {
		this.numResultsTotal = numResultsTotal;
	}
	public SearchResultListBean getContents() {
		return contents;
	}
	public void setContents(SearchResultListBean contents) {
		this.contents = contents;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	
}
