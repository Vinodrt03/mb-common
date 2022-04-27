package com.milkbasket.rest.services.common.event.bean;

import java.io.Serializable;

public class EventRequestBean implements Serializable {

	private static final long serialVersionUID = -4593009881014288288L;

	private String eventName;
	private Object eventData;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(final String eventName) {
		this.eventName = eventName;
	}

	public Object getEventData() {
		return eventData;
	}

	public void setEventData(final Object eventData) {
		this.eventData = eventData;
	}

}
