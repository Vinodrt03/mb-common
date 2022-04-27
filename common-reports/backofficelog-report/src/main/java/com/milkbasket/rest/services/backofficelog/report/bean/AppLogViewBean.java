package com.milkbasket.rest.services.backofficelog.report.bean;

import java.io.Serializable;

import com.milkbasket.core.framework.websupport.base.bean.CsvBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to be returned with VIEW
 *
 * @version $Id: $Id
 * @author Sanjay Wadhwa
 */
@ApiModel(description = "App Log Response")
public class AppLogViewBean extends AppLogBean implements Serializable, CsvBean {

	private static final long serialVersionUID = 201806271705490595L;

	/**
	 * Constant <code>HEADER="ID,User ID,User Name,Date Time,"{trunked}</code>
	 */
	public static final String HEADER = "App Log ID,Customer ID,Customer Name,Date Time,Action,Data";

	@ApiModelProperty(value = "action of v appLog. Non-mandatory input field.", allowEmptyValue = true)
	private String action;

	/**
	 * <p>
	 * Setter for the field <code>action</code>.
	 * </p>
	 *
	 * @param action
	 *            of v appLog.
	 */
	public void setAction(final String action) {
		this.action = action;
	}

	/**
	 * <p>
	 * Getter for the field <code>action</code>.
	 * </p>
	 *
	 * @return action of v appLog.
	 */
	public String getAction() {
		return action;
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
		sb.append(',').append(getCellValue(getDateTime()));
		sb.append(',').append(getCellValue(getAction()));
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
