package com.milkbasket.rest.services.common.communication.template.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.utils.LoggingUtils;
import com.milkbasket.core.framework.websupport.base.BaseController;
import com.milkbasket.rest.services.communication.entity.TemplateEntity;
import com.milkbasket.rest.services.common.communication.template.bean.TemplateBean;
import com.milkbasket.rest.services.common.communication.template.mapper.TemplateMapper;
import com.milkbasket.rest.services.common.communication.template.service.TemplateService;
import com.milkbasket.rest.services.common.communication.template.validator.TemplateValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * TemplateController class.
 * </p>
 *
 * @author SanjeevJha
 * @version $Id: $Id
 */
@Api(tags = { "Template Services" }, description = "Manages Template related services")
public class TemplateController implements BaseController {

	// private static final AppLogger _LOGGER =
	// LoggingManager.getLogger(TemplateController.class);

	@Autowired
	private TemplateService templateService;

	@Autowired
	@SuppressWarnings("unused")
	private TemplateMapper templateMapper;

	@Autowired
	private TemplateValidator validator;

	/**
	 * <p>
	 * findAll.
	 * </p>
	 *
	 * @param name
	 *            a {@link java.lang.String} object.
	 * @param type
	 *            a {@link java.lang.String} object.
	 * @param module
	 *            a {@link java.lang.String} object.
	 * @param active
	 *            a {@link java.lang.Integer} object.
	 * @param manual
	 *            a {@link java.lang.Integer} object.
	 * @param request
	 *            a {@link javax.servlet.http.HttpServletRequest} object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "List all templates in application store. Allowed values in field prameter : name , type", nickname = "findTemplateList", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  #  If the search result has no issues, then a list should be returned else Error Response."
			+ "<br/>  #  Search can be done using Parameter such as : name, type, module, active, manual." + "<br/>  #  Default Sort : name")
	@GetMapping("/message-templates")
	public List<TemplateBean> findAll(@RequestParam(required = false) final String name, @RequestParam(required = false) final String type,
			@RequestParam(required = false) final String module, @RequestParam(required = false) final Integer active,
			@RequestParam(required = false) final Integer manual, final HttpServletRequest request) {
		final List<TemplateEntity> results = templateService.findAll(getSearchParams(request));
		final List<TemplateBean> beans = convertEntityListToBeanList(results, TemplateBean.class);
		resetParams(beans);
		return beans;
	}

	/**
	 * <p>
	 * findOne.
	 * </p>
	 *
	 * @param id
	 *            a {@link java.lang.Long} object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.common.communication.template.bean.TemplateBean}
	 *         object.
	 */
	@ApiOperation(value = "get one template with provided id.", nickname = "findOneTemplate")
	@GetMapping("/message-templates/{id}")
	public TemplateBean findOne(@PathVariable final Long id) {
		final TemplateBean bean = convertEntityToBean(templateService.find(id), TemplateBean.newInstance());
		bean.resetParams();
		return bean;
	}

	/**
	 * <p>
	 * findOne.
	 * </p>
	 *
	 * @param type
	 *            a {@link java.lang.String} object.
	 * @param name
	 *            a {@link java.lang.String} object.
	 * @return a
	 *         {@link com.milkbasket.rest.services.common.communication.template.bean.TemplateBean}
	 *         object.
	 */
	@ApiOperation(value = "get one template with provided type and name.", nickname = "findOneTemplateByTypeAndName")
	@GetMapping("/message-templates/types/{type}/names/{name}")
	public TemplateBean findOne(@PathVariable final String type, @PathVariable final String name) {
		final TemplateBean bean = convertEntityToBean(templateService.findOnTypeAndName(type, name), TemplateBean.newInstance());
		bean.resetParams();
		return bean;
	}

	/**
	 * <p>
	 * save.
	 * </p>
	 *
	 * @param template
	 *            a
	 *            {@link com.milkbasket.rest.services.common.communication.template.bean.TemplateBean}
	 *            object.
	 * @param errors
	 *            a {@link org.springframework.validation.Errors} object.
	 * @return a {@link java.lang.Long} object.
	 */
	@ApiOperation(value = "create new template", nickname = "createTemplate")
	@PostMapping("/message-templates")
	@ResponseStatus(HttpStatus.CREATED)
	public Long save(@Valid @RequestBody final TemplateBean template, final Errors errors) {

		validator.validateTemplate(template, errors);
		checkError(errors);
		final TemplateEntity entity = templateService.save(convertBeanToEntity(TemplateEntity.newInstance(), template));
		LoggingUtils.logAdminAction(TemplateEntity.Events.create_msg_template.toString(), entity.getId());
		return entity.getId();

	}

	/**
	 * <p>
	 * update.
	 * </p>
	 *
	 * @param id
	 *            a {@link java.lang.Long} object.
	 * @param template
	 *            a
	 *            {@link com.milkbasket.rest.services.common.communication.template.bean.TemplateBean}
	 *            object.
	 * @param errors
	 *            a {@link org.springframework.validation.Errors} object.
	 */
	@ApiOperation(value = "update existing template with provided id", nickname = "updateTemplate")
	@PutMapping("/message-templates/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable final Long id, @Valid @RequestBody final TemplateBean template, final Errors errors) {

		template.setId(id);
		validator.validateTemplate(template, errors);
		checkError(errors);
		final TemplateEntity entity = templateService.find(id);
		templateService.update(convertBeanToEntity(entity, template));
		LoggingUtils.logAdminAction(TemplateEntity.Events.update_msg_template.toString(), entity.getId());

	}

	/**
	 * <p>
	 * delete.
	 * </p>
	 *
	 * @param id
	 *            a {@link java.lang.Long} object.
	 * @return a {@link java.lang.Long} object.
	 */
	@ApiOperation(value = "Soft delete existing template with provided id", nickname = "deleteTemplate")
	@DeleteMapping("/message-templates/{id}")
	public Long delete(@PathVariable final Long id) {

		templateService.delete(id);
		LoggingUtils.logAdminAction(TemplateEntity.Events.delete_msg_template.toString(), id);
		return id;
	}

	/**
	 * <p>
	 * autocomplete.
	 * </p>
	 *
	 * @param request
	 *            a {@link javax.servlet.http.HttpServletRequest} object.
	 * @param text
	 *            a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "Autocomplete template in application store.", nickname = "autocompleteTemplate", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  #  If the search result has no issues, then a list of string will be returned else failure bean."
			+ "<br/>  #  Autocomplete : name")

	@GetMapping("/message-templates/autocomplete")
	public List<TemplateBean> autocomplete(final HttpServletRequest request, @RequestParam(value = "text", required = true) final String text) {
		final List<TemplateBean> beans = convertEntityListToBeanList(templateService.autocomplete(text), TemplateBean.class);
		resetParams(beans);
		return beans;
	}

	/**
	 * <p>
	 * findAllWIthDeleted.
	 * </p>
	 *
	 * @param name
	 *            a {@link java.lang.String} object.
	 * @param type
	 *            a {@link java.lang.String} object.
	 * @param module
	 *            a {@link java.lang.String} object.
	 * @param active
	 *            a {@link java.lang.Integer} object.
	 * @param manual
	 *            a {@link java.lang.Integer} object.
	 * @param request
	 *            a {@link javax.servlet.http.HttpServletRequest} object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "Find All including deleted template results in application store.", nickname = "findAllTemplateList", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  #  If the search result has no issues, then a list will be returned."
			+ "<br/>  #  Search can be done using Parameter such as : name, type, module, active, manual." + "<br/>  #  Default Sort : name")
	@GetMapping("/message-templates/all")
	public List<TemplateBean> findAllWithDeleted(@RequestParam(required = false) final String name, @RequestParam(required = false) final String type,
			@RequestParam(required = false) final String module, @RequestParam(required = false) final Integer active,
			@RequestParam(required = false) final Integer manual, final HttpServletRequest request) {
		final List<TemplateEntity> results = templateService.findAllWithDeleted(getSearchParams(request));

		final List<TemplateBean> beans = convertEntityListToBeanList(results, TemplateBean.class);
		resetParams(beans);
		return beans;
	}

	/**
	 * <p>
	 * validateIfTemplateIsManual.
	 * </p>
	 *
	 * @param type
	 *            a {@link java.lang.String} object.
	 * @param name
	 *            a {@link java.lang.String} object.
	 * @return a {@link java.lang.Boolean} object.
	 */
	@ApiOperation(value = "Validate if template is manual based on provided template type and name [B2B]", nickname = "validateIfTemplateIsManual")
	@GetMapping("/message-templates/types/{type}/names/{name}/manual")
	public Boolean validateIfTemplateIsManual(@PathVariable final String type, @PathVariable final String name) {
		return templateService.validateIfTemplateIsManual(type, name);
	}

	private void resetParams(final List<TemplateBean> beans) {
		beans.stream().forEach(TemplateBean::resetParams);
	}

}
