package com.milkbasket.rest.services.template.report.bean;

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
@ApiModel(description = "Message Template Response")
public class TemplateViewBean implements Serializable, CsvBean {

	private static final long serialVersionUID = 201806131252180585L;

	/**
	 * Constant
	 * <code>HEADER="Id,Type,Name,Subject,Text,Manual,Module"{trunked}</code>
	 */
	public static final String HEADER = "Template ID,Type,Name,Subject,Text,Manual,Module,Active";

	@ApiModelProperty(value = "id of messageTemplate. Accept numeric characters only.")
	private Long id;

	@ApiModelProperty(value = "Template Type. Accepts fixed values.")
	private String type;

	@ApiModelProperty(value = "Template name.")
	private String name;

	@ApiModelProperty(value = "Template Subject.")
	private String subject;

	@ApiModelProperty(value = "Template Text.")
	private String text;

	@ApiModelProperty(value = "Manual flag which is either 0(False) or 1(True). Accept numeric characters only.")
	private Integer manual;

	@ApiModelProperty(value = "Manual flag which is either 0(False) or 1(True).")
	private String manualValue;

	@ApiModelProperty(value = "Template Module. Required field. Accepts fixed values for Msg Template Module.")
	private String module;

	@ApiModelProperty(value = "Template Module. Required field. Accepts fixed values for Msg Template Module.")
	private String moduleValue;

	@ApiModelProperty(value = "Active flag which is either 0(False) or 1(True). Accept numeric characters only.")
	private Integer active;

	@ApiModelProperty(value = "Active flag which is either 0(False) or 1(True).")
	private String activeValue;

	/**
	 * <p>
	 * Getter for the field <code>manualValue</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getManualValue() {
		return manualValue;
	}

	/**
	 * <p>
	 * Setter for the field <code>manualValue</code>.
	 * </p>
	 *
	 * @param manualValue
	 *            a {@link java.lang.String} object.
	 */
	public void setManualValue(final String manualValue) {
		this.manualValue = manualValue;
	}

	/**
	 * <p>
	 * Getter for the field <code>moduleValue</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getModuleValue() {
		return moduleValue;
	}

	/**
	 * <p>
	 * Setter for the field <code>moduleValue</code>.
	 * </p>
	 *
	 * @param moduleValue
	 *            a {@link java.lang.String} object.
	 */
	public void setModuleValue(final String moduleValue) {
		this.moduleValue = moduleValue;
	}

	/**
	 * <p>
	 * Getter for the field <code>activeValue</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getActiveValue() {
		return activeValue;
	}

	/**
	 * <p>
	 * Setter for the field <code>activeValue</code>.
	 * </p>
	 *
	 * @param activeValue
	 *            a {@link java.lang.String} object.
	 */
	public void setActiveValue(final String activeValue) {
		this.activeValue = activeValue;
	}

	/**
	 * <p>
	 * Setter for the field <code>id</code>.
	 * </p>
	 *
	 * @param id
	 *            of v messageTemplate.
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * <p>
	 * Getter for the field <code>id</code>.
	 * </p>
	 *
	 * @return id of v messageTemplate.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>
	 * Setter for the field <code>type</code>.
	 * </p>
	 *
	 * @param type
	 *            a {@link java.lang.String} object.
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * <p>
	 * Getter for the field <code>type</code>.
	 * </p>
	 *
	 * @return Template Type. Accepts fixed values
	 */
	public String getType() {
		return type;
	}

	/**
	 * <p>
	 * Setter for the field <code>name</code>.
	 * </p>
	 *
	 * @param name
	 *            a {@link java.lang.String} object.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * Getter for the field <code>name</code>.
	 * </p>
	 *
	 * @return Template name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * Setter for the field <code>subject</code>.
	 * </p>
	 *
	 * @param subject
	 *            a {@link java.lang.String} object.
	 */
	public void setSubject(final String subject) {
		this.subject = subject;
	}

	/**
	 * <p>
	 * Getter for the field <code>subject</code>.
	 * </p>
	 *
	 * @return Template Subject. Mandatory if Template Type is EMAIL.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * <p>
	 * Setter for the field <code>text</code>.
	 * </p>
	 *
	 * @param text
	 *            a {@link java.lang.String} object.
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * <p>
	 * Getter for the field <code>text</code>.
	 * </p>
	 *
	 * @return Template Text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * <p>
	 * Setter for the field <code>manual</code>.
	 * </p>
	 *
	 * @param manual
	 *            a {@link java.lang.Integer} object.
	 */
	public void setManual(final Integer manual) {
		this.manual = manual;
	}

	/**
	 * <p>
	 * Getter for the field <code>manual</code>.
	 * </p>
	 *
	 * @return Manual flag which is either 0(False) or 1(True)
	 */
	public Integer getManual() {
		return manual;
	}

	/**
	 * <p>
	 * Setter for the field <code>module</code>.
	 * </p>
	 *
	 * @param module
	 *            a {@link java.lang.String} object.
	 */
	public void setModule(final String module) {
		this.module = module;
	}

	/**
	 * <p>
	 * Getter for the field <code>module</code>.
	 * </p>
	 *
	 * @return Template Module. Required field. Accepts fixed values for Msg
	 *         Template Module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * <p>
	 * Setter for the field <code>active</code>.
	 * </p>
	 *
	 * @param active
	 *            a {@link java.lang.Integer} object.
	 */
	public void setActive(final Integer active) {
		this.active = active;
	}

	/**
	 * <p>
	 * Getter for the field <code>active</code>.
	 * </p>
	 *
	 * @return Active flag which is either 0(False) or 1(True).
	 */
	public Integer getActive() {
		return active;
	}

	/** {@inheritDoc} */
	@Override
	public String csvRow() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getCellValue(getId()));
		sb.append(',').append(getCellValue(getType()));
		sb.append(',').append(getCellValue(getName()));
		sb.append(',').append(getCellValue(getSubject()));
		sb.append(',').append(getCellValue(getText()));
		sb.append(',').append(getCellValue(getManualValue()));
		sb.append(',').append(getCellValue(getModuleValue()));
		sb.append(',').append(getCellValue(getActiveValue()));
		return sb.toString();
	}

}
