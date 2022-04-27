package com.milkbasket.rest.services.masterdata.report.bean;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.websupport.base.bean.CsvBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// TODO: Auto-generated Javadoc
/**
 * Bean to be returned with VIEW.
 *
 * @author iTuple
 * @version $Id: $Id
 */
@ApiModel(description = "Master Data Response")
public class MasterDataViewBean implements Serializable, CsvBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 201901162321010618L;

	/** The Constant HEADER. */
	public static final String HEADER = "Master Key,Data Key,Data Value";

	/** The master key. */
	@ApiModelProperty(value = "masterKey of v masterData.", allowEmptyValue = false)
	private String masterKey;

	/** The data key value. */
	@ApiModelProperty(value = "list of master data.")
	private List<DataKeyValue> dataKeyValue;

	/**
	 * Gets the data key value.
	 *
	 * @return the data key value
	 */
	public List<DataKeyValue> getDataKeyValue() {
		return dataKeyValue;
	}

	/**
	 * Sets the data key value.
	 *
	 * @param dataKeyValue
	 *            the new data key value
	 */
	public void setDataKeyValue(final List<DataKeyValue> dataKeyValue) {
		this.dataKeyValue = dataKeyValue;
	}

	/**
	 * Sets the master key.
	 *
	 * @param masterKey
	 *            of v masterData.
	 */
	public void setMasterKey(final String masterKey) {
		this.masterKey = masterKey;
	}

	/**
	 * Gets the master key.
	 *
	 * @return masterKey of v masterData.
	 */
	public String getMasterKey() {
		return masterKey;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.milkbasket.core.framework.websupport.base.bean.CsvBean#csvRow()
	 */
	/**
	 * <p>
	 * csvRow.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	@Override
	public String csvRow() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getCellValue(getMasterKey()));
		if (CollectionUtils.isNotEmpty(dataKeyValue)) {
			final Iterator<DataKeyValue> iterator = dataKeyValue.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				final DataKeyValue dataKeyValue = iterator.next();
				if (count > 0) {
					sb.append(getCellValue(""));
				}
				sb.append(',').append(getCellValue(dataKeyValue.getDataKey()));
				sb.append(',').append(getCellValue(dataKeyValue.getDataValue()));
				if (iterator.hasNext()) {
					sb.append(System.lineSeparator());
				}
				count++;
			}
		} else {
			sb.append(",").append("");
		}
		return sb.toString();
	}

}
