package com.milkbasket.rest.services.param.bean;

import java.io.Serializable;

import com.milkbasket.core.framework.validator.annotation.Mandatory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * ParamBean class.
 * </p>
 *
 * @author tanuja
 * @version $Id: $Id
 */
@ApiModel(description = "Param Request")
public class ParamBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3768214054879328630L;

	@ApiModelProperty(position = 2, value = "<br/><br/>NOTES:" + "<br/>  # Value for the param key.", required = true, allowEmptyValue = false)
	@Mandatory
	private String paramValue;

	/**
	 * <p>
	 * Getter for the field <code>paramValue</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * <p>
	 * Setter for the field <code>paramValue</code>.
	 * </p>
	 *
	 * @param paramValue
	 *            a {@link java.lang.String} object.
	 */
	public void setParamValue(final String paramValue) {
		this.paramValue = paramValue;
	}

	/**
	 * <p>
	 * newInstance.
	 * </p>
	 *
	 * @return a {@link com.milkbasket.rest.services.param.bean.ParamBean} object.
	 */
	public static ParamBean newInstance() {
		return new ParamBean();
	}

}
