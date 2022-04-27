package com.milkbasket.rest.services.backofficelog.report.bean;

import java.io.Serializable;

import com.milkbasket.core.framework.websupport.base.bean.CsvBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with VIEW
 *
 * @author iTuple
 * @version $Id: $Id
 */
@ApiModel(description = "App Error Log Response")
public class AppErrorLogViewBean extends AppLogBaseViewBean implements Serializable, CsvBean {

	private static final long serialVersionUID = 201806281746440893L;

	/**
	 * Constant
	 * <code>HEADER="ID,User ID,User Name,User Type,Error,Modu"{trunked}</code>
	 */
	public static final String HEADER = "App Error Log ID,User ID,User Name,Error,Module,Date Time,Data";

	@ApiModelProperty(value = "error of v appErrorLog. Non-mandatory input field.", allowEmptyValue = true)
	private String error;

	@ApiModelProperty(value = "module of v appErrorLog. Non-mandatory input field.", allowEmptyValue = true)
	private String module;

	/**
	 * <p>
	 * Setter for the field <code>error</code>.
	 * </p>
	 *
	 * @param error
	 *            of v appErrorLog.
	 */
	public void setError(final String error) {
		this.error = error;
	}

	/**
	 * <p>
	 * Getter for the field <code>error</code>.
	 * </p>
	 *
	 * @return error of v appErrorLog.
	 */
	public String getError() {
		return error;
	}

	/**
	 * <p>
	 * Setter for the field <code>module</code>.
	 * </p>
	 *
	 * @param module
	 *            of v appErrorLog.
	 */
	public void setModule(final String module) {
		this.module = module;
	}

	/**
	 * <p>
	 * Getter for the field <code>module</code>.
	 * </p>
	 *
	 * @return module of v appErrorLog.
	 */
	public String getModule() {
		return module;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * csvRow.
	 * </p>
	 */
	@Override
	public String csvRow() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getCellValue(getId()));
		sb.append(',').append(getCellValue(getCustomerId()));
		sb.append(',').append(getCellValue(getCustomerName()));
		sb.append(',').append(getCellValue(getError()));
		sb.append(',').append(getCellValue(getModule()));
		sb.append(',').append(getCellValue(getDateTime()));
		sb.append(',').append(getCellValue(escapeQuotes(getDataJson())));
		return sb.toString();
	}

	private static String escapeQuotes(final String text) {
		if (text == null) {
			return "";
		}
		return text.replace(',', ';').replace('"', '\'');
	}

}
