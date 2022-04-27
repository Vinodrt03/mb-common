package com.milkbasket.rest.services.param.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.milkbasket.core.framework.dbsupport.annotation.Searchable;
import com.milkbasket.core.framework.dbsupport.annotation.Sortable;

@Entity
/**
 * <p>
 * ParamEntity class.
 * </p>
 *
 * @author tanuja
 * @version $Id: $Id
 */
@Table(name = ParamConstants.TABLE_NAME)
@DynamicUpdate
public class ParamEntity implements Serializable, ParamConstants {

	/**
	 *
	 */
	private static final long serialVersionUID = 412886840113125497L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ParamConstants.ID, nullable = false)
	private Long id;

	@Column(name = ParamConstants.PARAM_KEY, nullable = false, length = 100)
	@Searchable
	@Sortable(isdefault = true)
	private String paramKey;

	@Column(name = ParamConstants.PARAM_VALUE, nullable = false)
	private String paramValue;

	@Column(name = ParamConstants.IS_PUBLIC, nullable = false, length = 4, columnDefinition = "default '1'")
	private Integer active;

	@Column(name = ParamConstants.FLAG, nullable = false, length = 4, columnDefinition = "default '1'")
	private Integer flag;

	@Column(name = ParamConstants.IS_EDITABLE, nullable = false, length = 4, columnDefinition = "default '1'")
	private Integer isEditable;

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
	 * Getter for the field <code>paramKey</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getParamKey() {
		return paramKey;
	}

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
	 * Getter for the field <code>active</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getActive() {
		return active;
	}

	/**
	 * <p>
	 * Getter for the field <code>flag</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * <p>
	 * Getter for the field <code>isEditable</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getIsEditable() {
		return isEditable;
	}

}
