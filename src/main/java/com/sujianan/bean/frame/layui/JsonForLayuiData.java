package com.sujianan.bean.frame.layui;

import java.util.List;

public class JsonForLayuiData {
	
	// 节点标题
	private String title;
	// 节点唯一索引，用于对指定节点进行各类操作
	private Integer id;
	// 子节点。支持设定选项同父节点
	private List<JsonForLayuiData> children;
	// 节点是否初始展开，默认 false
	private boolean spread;
	// 节点是否初始为选中状态（如果开启复选框的话），默认 false
	private boolean checked;
	// 节点是否为禁用状态。默认 false
	private boolean disabled;
	
	public JsonForLayuiData() {
		this.title = "";
		this.id = null;
		this.children = null;
		this.spread = false;
		this.checked = false;
		this.disabled = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<JsonForLayuiData> getChildren() {
		return children;
	}

	public void setChildren(List<JsonForLayuiData> children) {
		this.children = children;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
}
