package com.milkbasket.rest.services.param.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.bean.ErrorBean;
import com.milkbasket.core.framework.common.exception.ValidationException;
import com.milkbasket.core.framework.common.utils.LoggingUtils;
import com.milkbasket.core.framework.websupport.base.BaseController;
import com.milkbasket.rest.services.param.bean.ParamBean;
import com.milkbasket.rest.services.param.entity.ParamEntity;
import com.milkbasket.rest.services.param.service.ParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * ParamController class.
 * </p>
 *
 * @author Tanuja
 * @version $Id: $Id
 */
@Api(tags = "Param Services")
public class ParamController implements BaseController {
	@Autowired
	private ParamService paramService;

	/**
	 * <p>
	 * update.
	 * </p>
	 *
	 * @param Id
	 *            a {@link java.lang.Long} object.
	 * @param param
	 *            a {@link com.milkbasket.rest.services.param.bean.ParamBean}
	 *            object.
	 */
	@ApiOperation(value = "update existing param with provided value", nickname = "updateParam", notes = "<br/>  #  On success, update should return updated template record")
	@PatchMapping("/params/{id}/value")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable final Long id, @Valid @RequestBody final ParamBean param) {
		final ParamEntity entity = paramService.find(id);
		if (entity.getIsEditable().equals(0)) {
			throw new ValidationException("Not an editable param", ErrorBean.withError("Not an editable param"));
		}
		final String oldParams = entity.getParamValue();
		entity.setParamValue(param.getParamValue());
		paramService.update(entity);
		LoggingUtils.logAdminAction("update_settings", "id", id, "param_key", entity.getParamKey(), "old_param_value", oldParams, "param_value",
				entity.getParamValue());
	}

}
