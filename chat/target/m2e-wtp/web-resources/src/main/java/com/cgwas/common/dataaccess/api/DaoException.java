package com.cgwas.common.dataaccess.api;

/**
 * 
 * @remark [TODO]
 * @extend [TODO]
 * @author [dingwei.tang]
 * @date [2016年8月16日 上午10:18:49]
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 4640181036015959754L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

}
