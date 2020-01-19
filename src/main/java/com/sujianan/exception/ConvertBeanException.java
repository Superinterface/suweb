/**
 * 
 */
package com.sujianan.exception;

/**
 * @author	SuperInterface
 * @github	SuperInterface
 * @date	2020年1月15日
 */
public class ConvertBeanException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConvertBeanException() {
		super("转换异常");
	}
	
	public ConvertBeanException(String message) {
		super(message);
	}
	
}
