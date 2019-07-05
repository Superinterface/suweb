package com.sujianan.bean.frame.layui;

import java.util.List;

public class JsonForLayuiRoot {

	// 指向容器选择器
	private String elem;
	// 数据源
	private List<JsonForLayuiData> data;
	// 设定实例唯一索引，用于基础方法传参使用。
	private String id;
	// 是否显示复选框
	private boolean showCheckbox;
	/*
	 * 是否开启节点的操作图标。默认 false。 若为 true，则默认显示“改删”图标 若为
	 * 数组，则可自由配置操作图标的显示状态和顺序，目前支持的操作图标有：add、update、del，如： edit: ['add', 'update',
	 * 'del']
	 */
	private boolean edit;
	// 是否开启手风琴模式，默认 false
	private boolean accordion;
	// 是否仅允许节点左侧图标控制展开收缩。默认 false（即点击节点本身也可控制）。
	// 若为 true，则只能通过节点左侧图标来展开收缩
	private boolean onlyIconControl;
	// 是否允许点击节点时弹出新窗口跳转。默认 false，若开启，需在节点数据中设定 link 参数（值为 url 格式）
	private boolean isJump;
	// 是否开启连接线。默认 true，若设为 false，则节点左侧出现三角图标。
	private boolean showLine;
	/*
	 * 自定义各类默认文本，目前支持以下设定： text: { defaultNodeName: '未命名' //节点默认名称 ,none: '无数据'
	 * //数据为空时的提示文本 }
	 */
//	private Object text;

	public JsonForLayuiRoot() {
		this.elem = "";
		this.id = "";
		this.showCheckbox = false;
		this.edit = false;
		this.accordion = false;
		this.onlyIconControl = false;
		this.isJump = false;
		this.showLine = true;
//		this.text = null;
	}

	public String getElem() {
		return elem;
	}

	public void setElem(String elem) {
		this.elem = elem;
	}

	public List<JsonForLayuiData> getData() {
		return data;
	}

	public void setData(List<JsonForLayuiData> data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isShowCheckbox() {
		return showCheckbox;
	}

	public void setShowCheckbox(boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	public boolean getEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public boolean isAccordion() {
		return accordion;
	}

	public void setAccordion(boolean accordion) {
		this.accordion = accordion;
	}

	public boolean isOnlyIconControl() {
		return onlyIconControl;
	}

	public void setOnlyIconControl(boolean onlyIconControl) {
		this.onlyIconControl = onlyIconControl;
	}

	public boolean isJump() {
		return isJump;
	}

	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}

	public boolean isShowLine() {
		return showLine;
	}

	public void setShowLine(boolean showLine) {
		this.showLine = showLine;
	}

//	public Object getText() {
//		return text;
//	}
//
//	public void setText(Object text) {
//		this.text = text;
//	}

}
