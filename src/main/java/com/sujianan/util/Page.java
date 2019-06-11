package com.sujianan.util;

import com.sujianan.dao.page.Mapper;

public class Page {
	
	private int pageNo;
	private int totalCount;
	private int pageSize;
	private int rownum;
	private int rn;

	public void initRownumRn(Mapper mapper) {
		int count = mapper.selectCount();
		this.rn = (this.pageNo - 1) * this.pageSize;
		this.rownum = this.pageNo * this.pageSize;
		int y = count % this.pageSize;
		this.totalCount = y > 0 ? count / this.pageSize + 1 : count / this.pageSize;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRounum() {
		return this.rownum;
	}

	public void setRounum(int rownum) {
		this.rownum = rownum;
	}

	public int getRn() {
		return this.rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}
}