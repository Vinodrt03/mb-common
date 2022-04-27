package com.milkbasket.rest.services.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.dbsupport.jdbc.constants.BooleanFlag;
import com.milkbasket.core.framework.utility.CollectionUtils;
import com.milkbasket.core.framework.websupport.base.BaseController;
import com.milkbasket.rest.services.push.common.bean.DeviceBean;
import com.milkbasket.rest.services.push.common.bean.DeviceRequestBean;
import com.milkbasket.rest.services.push.common.bean.UnregisterDevice;
import com.milkbasket.rest.services.communication.entity.DeviceEntity;
import com.milkbasket.rest.services.communication.entity.DeviceEntity_;
import com.milkbasket.rest.services.push.common.mapper.DeviceMapper;
import com.milkbasket.rest.services.push.common.service.DeviceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * DeviceController class.
 * </p>
 *
 * @author Neeraj
 * @version $Id: $Id
 */
@Api(tags = "Push Services")
public class DeviceController implements BaseController {

	// private static final AppLogger _LOGGER =
	// LoggingManager.getLogger(DeviceController.class);

	@Autowired
	private DeviceService deviceService;

	@SuppressWarnings("unused")
	@Autowired
	private DeviceMapper deviceMapper;

	/**
	 * <p>
	 * findAll.
	 * </p>
	 *
	 * @param deviceRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.push.common.bean.DeviceRequestBean}
	 *            object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "List all Device entities available in application store.", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  #  If the search result has no issues, then a list should be returned else Error Response."
			+ "<br/>  #  #  Search can be done using Parameter such as : deviceOs, pushId."
			+ "<br/>  #  Default Sort : ", nickname = "findAllDevices")
	@PostMapping("/devices/find")
	public List<DeviceBean> findAll(@RequestBody final DeviceRequestBean deviceRequestBean) {
		final Map<String, Object> filters = new HashMap<>();
		filters.put(DeviceEntity_.customerId.getName(), deviceRequestBean.getCustomerId());
		filters.put(DeviceEntity_.deviceLogout.getName(), deviceRequestBean.getDeviceLogout());

		final List<DeviceEntity> results = deviceService.findAll(filters);
		return convertEntityListToBeanList(results, DeviceBean.class);
	}

	/**
	 * <p>
	 * findAll.
	 * </p>
	 *
	 * @param deviceRequestBean
	 *            a
	 *            {@link com.milkbasket.rest.services.push.common.bean.DeviceRequestBean}
	 *            object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "List all Device entities available in application store without limit.", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  #  If the search result has no issues, then a list should be returned else Error Response."
			+ "<br/>  #  #  Search can be done using Parameter such as : deviceOs, pushId."
			+ "<br/>  #  Default Sort : ", nickname = "findAllDevicesWithoutLimit")
	@PostMapping("/devices/find/all")
	public List<DeviceBean> findAllWithoutLimit(@RequestBody final DeviceRequestBean deviceRequestBean) {
		final Map<String, Object> filters = new HashMap<>();
		filters.put(DeviceEntity_.customerId.getName(), deviceRequestBean.getCustomerId());
		filters.put(DeviceEntity_.deviceLogout.getName(), deviceRequestBean.getDeviceLogout());

		final List<DeviceEntity> results = deviceService.findAllWithoutAnyLimit(filters);
		return convertEntityListToBeanList(results, DeviceBean.class);
	}

	/**
	 * <p>
	 * findOne.
	 * </p>
	 *
	 * @param id
	 *            a {@link java.lang.Long} object.
	 * @return a {@link com.milkbasket.rest.services.push.common.bean.DeviceBean}
	 *         object.
	 */
	@ApiOperation(value = "get one Device entity with provided id.", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  #  Non soft deleted beans can only be fetched", hidden = true)
	@GetMapping("/devices/{id}")
	public DeviceBean findOne(@PathVariable final Long id) {
		return convertEntityToBean(deviceService.find(id), DeviceBean.newInstance());
	}

	/**
	 * <p>
	 * findAllWithDeleted.
	 * </p>
	 *
	 * @param deviceOs
	 *            a {@link java.lang.String} object.
	 * @param pushId
	 *            a {@link java.lang.Long} object.
	 * @param request
	 *            a {@link javax.servlet.http.HttpServletRequest} object.
	 * @return a {@link java.util.List} object.
	 */
	@ApiOperation(value = "Find All including deleted Device entities from application store.", notes = "<br/><strong>NOTES:</strong>"
			+ "<br/>  #  If the search result has no issues, then a list will be returned"
			+ "<br/>  #  #  Search can be done using Parameter such as : deviceOs, pushId." + "<br/>  #  Default Sort : ", hidden = true)
	@GetMapping("/devices/all")
	public List<DeviceBean> findAllWithDeleted(@RequestParam(required = false) final String deviceOs,
			@RequestParam(required = false) final Long pushId, final HttpServletRequest request) {
		final List<DeviceEntity> results = deviceService.findAllWithDeleted(getSearchParams(request));
		return convertEntityListToBeanList(results, DeviceBean.class);
	}

	/**
	 * <p>
	 * registerDevice.
	 * </p>
	 *
	 * @param deviceBean
	 *            a {@link com.milkbasket.rest.services.push.common.bean.DeviceBean}
	 *            object.
	 * @return a {@link java.lang.Long} object.
	 */
	@ApiOperation(value = "Register Device [B2B]", nickname = "registerDevice")
	@PostMapping("/devices/register")
	public Long registerDevice(@Valid @RequestBody final DeviceBean deviceBean) {
		final DeviceEntity device = deviceService.registerDevice(deviceBean);
		// _LOGGER.debug("Entity saved .. returnd id =" + device.getId());
		return device.getId();
	}

	/**
	 * <p>
	 * unregisterDevice.
	 * </p>
	 *
	 * @param device
	 *            a
	 *            {@link com.milkbasket.rest.services.push.common.bean.UnregisterDevice}
	 *            object.
	 * @return a {@link java.lang.Long} object.
	 */
	@ApiOperation(value = "Unregister Device [B2B]", nickname = "unregisterDevice")
	@PostMapping("/devices/unregister")
	public Long unregisterDevice(@Valid @RequestBody final UnregisterDevice device) {
		final Map<String, Object> filter = new HashMap<>();
		filter.put(DeviceEntity_.customerId.getName(), device.getCustomerId());
		filter.put(DeviceEntity_.udid.getName(), device.getUdid());

		final List<DeviceEntity> deviceEntryList = deviceService.findAll(filter);
		if (CollectionUtils.isNotEmpty(deviceEntryList)) {
			deviceEntryList.get(0).setDeviceLogout(BooleanFlag.YES);
			deviceService.save(deviceEntryList.get(0));
			return deviceEntryList.get(0).getId();
		}
		return null;
	}
}
