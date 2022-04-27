package com.milkbasket.rest.services.common.communication.scheduled.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.milkbasket.core.framework.common.constants.CommonFormats;
import com.milkbasket.core.framework.validator.annotation.DateFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bean to schedule related data
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@ApiModel(description = "Future Events Request Data")
public class FutureEventsRequestBean implements Serializable {
	private static final long serialVersionUID = 245461795051313549L;

	@ApiModelProperty(position = 0, value = "<br/><br/>NOTES:"
			+ "<br/>  # Database Unique Id which is an auto generated field.", readOnly = true, allowEmptyValue = true)
	private Long id;

	@ApiModelProperty(position = 2, value = "<br/><br/>NOTES:" + "<br/>  # status of sms.", required = false, allowEmptyValue = true)
	private Integer status;

	@ApiModelProperty(position = 3, value = "<br/><br/>NOTES:" + "<br/>  # Mobile number to which the sms is required to be sent."
			+ "<br/><br/>VALIDATION SCENARIOS:" + "<br/>  # Mobile number should have valid format" + "<br/><br/>POSSIBLE VALIDATION CODES:"
			+ "<br/>   # IsPhoneCollection, Mandatory", required = true, allowEmptyValue = false)
	// @IsPhoneCollection
	private List<String> numbers;

	@ApiModelProperty(position = 1, value = "<br/><br/>NOTES:" + "<br/>  # Communication Template Id using which the actual sms will be sent"
			+ "<br/><br/>VALIDATION SCENARIOS:" + "<br/>  # This should be a valid and active template id" + "<br/><br/>POSSIBLE VALIDATION CODES:"
			+ "<br/>  # ValidColumnValue, Mandatory", required = true, allowEmptyValue = false)
	private String templateName;

	@ApiModelProperty(position = 2, value = "<br/><br/>NOTES:"
			+ "<br/>  # Data which will be used by by the communication template to resolve and generate actual message to be sent.", required = false, allowEmptyValue = true)
	private Map<String, Object> contextData;

	@ApiModelProperty(position = 2, value = "<br/><br/>NOTES:" + "<br/>  # type of communication.", required = false, allowEmptyValue = true)
	private String type;

	private String source;

	private String identifier;

	@ApiModelProperty(value = "Date for which sms to be sent.", allowEmptyValue = true)
	@DateFormat(pattern = CommonFormats.DATE_FORMAT_WITH_TIME)
	private String dateTime;

	@ApiModelProperty(value = "Type as broadcast or message. Mandatory input field.", allowEmptyValue = false)
	private String pushType;

	@ApiModelProperty(value = "<br/><br/>NOTES:"
			+ "<br/>  # User Ids to which the push is required to be sent.", required = false, allowEmptyValue = true)
	private List<Long> userIds;

	@ApiModelProperty(value = "Link of push message. Non-mandatory input field.", allowEmptyValue = true)
	private String link;

	private String message;

	private String title;

	public FutureEventsRequestBean() {
	}

	public String getLink() {
		return link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getPushType() {
		return pushType;
	}

	public void setPushType(final String pushType) {
		this.pushType = pushType;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(final List<Long> userIds) {
		this.userIds = userIds;
	}

	/**
	 * <p>
	 * Getter for the field <code>id</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Long} object.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>
	 * Setter for the field <code>id</code>.
	 * </p>
	 *
	 * @param id
	 *            a {@link java.lang.Long} object.
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Getter for the field <code>templateName</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * <p>
	 * Setter for the field <code>templateName</code>.
	 * </p>
	 *
	 * @param templateName
	 *            a {@link java.lang.String} object.
	 */
	public void setTemplateName(final String templateName) {
		this.templateName = templateName;
	}

	/**
	 * <p>
	 * Getter for the field <code>contextData</code>.
	 * </p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	public Map<String, Object> getContextData() {
		return contextData;
	}

	/**
	 * <p>
	 * Setter for the field <code>contextData</code>.
	 * </p>
	 *
	 * @param contextData
	 *            a {@link java.util.Map} object.
	 */
	public void setContextData(final Map<String, Object> contextData) {
		this.contextData = contextData;
	}

	/**
	 * <p>
	 * Getter for the field <code>numbers</code>.
	 * </p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<String> getNumbers() {
		return numbers;
	}

	/**
	 * <p>
	 * Setter for the field <code>numbers</code>.
	 * </p>
	 *
	 * @param numbers
	 *            a {@link java.util.List} object.
	 */
	public void setNumbers(final List<String> numbers) {
		this.numbers = numbers;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(final Integer status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(final String dateTime) {
		this.dateTime = dateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * <p>
	 * newInstance.
	 * </p>
	 *
	 * @return a
	 *         {@link FutureEventsRequestBean}
	 *         object.
	 */
	public static FutureEventsRequestBean newInstance() {
		return new FutureEventsRequestBean();
	}
}
