package com.hefei.common.page;

import java.util.List;

public class Pagination<T> {

	public static int DEFAULT_PAGE_SIZE = 20;
	
	private int pageIndex;//当前页 从第1页开始
	
	private int totalPage;//总页数
	
	private int pageSize;//每页记录数
	
	private int totalCount;//总记录数
	
	private List<T> items;
	
	@Override
	public String toString() {
		return "Pagination [pageIndex=" + pageIndex + ", totalPage="
				+ totalPage + ", pageSize=" + pageSize + ", totalCount="
				+ totalCount + ", items.size=" + (items == null?"null":items.size()) + "]";
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(totalCount <= pageSize){
			totalPage = 1;
		}else{
			if(totalCount%pageSize == 0){
				totalPage = totalCount/pageSize;
			}else{
				totalPage = totalCount/pageSize +1;
			}
			
		}
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
}
