package com.milkbasket.rest.services.common.communication.scheduled.bean;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CancelFutureEventsRequestBean implements Serializable {

	private static final long serialVersionUID = 225461795151313549L;

	@ApiModelProperty(position = 0, value = "<br/><br/>NOTES:"
			+ "<br/>  # Source from future_events table.", readOnly = true, allowEmptyValue = false)
	private String source;

	@ApiModelProperty(position = 1, value = "<br/><br/>NOTES:"
			+ "<br/>  # identifier from future_events table.", readOnly = true, allowEmptyValue = true)
	private String identifier;

	@ApiModelProperty(position = 2, value = "<br/><br/>NOTES:"
			+ "<br/>  # Date from future_events table.", readOnly = true, allowEmptyValue = true)
	private String date;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static CancelFutureEventsRequestBean newInstance() {
		return new CancelFutureEventsRequestBean();
	}

}
