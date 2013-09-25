/**
 * 
 */
package edu.unc.ils.mrc.hive.exception;

/**
 * General HIVE checked exception
 * @author Mike Conway - DICE 
 *
 */
public class HiveException extends Exception {

	private static final long serialVersionUID = 3418065388129022834L;

	/**
	 * 
	 */
	public HiveException() {
	}

	/**
	 * @param arg0
	 */
	public HiveException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public HiveException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public HiveException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public HiveException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}