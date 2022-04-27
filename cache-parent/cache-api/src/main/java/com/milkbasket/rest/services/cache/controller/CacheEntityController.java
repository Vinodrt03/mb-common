package com.milkbasket.rest.services.cache.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.milkbasket.core.framework.common.anotation.NewRelicIgnoreTransaction;
import com.milkbasket.core.framework.common.anotation.SnsSubscription;
import com.milkbasket.core.framework.utility.JSONUtils;
import com.milkbasket.core.framework.utility.StringUtils;
import com.milkbasket.rest.services.cache.bean.CacheEntityRefreshBean;
import com.milkbasket.rest.services.cache.caching.CustomCacheDTO;
import com.milkbasket.rest.services.cache.service.CacheEntityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
/**
 * <p>
 * CacheReportController class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
@Api(tags = { "Cache Entity Services" }, description = "Manages Entity Cache related services")
public class CacheEntityController<T> {

	@Autowired
	private CacheEntityService<T> cacheEntityService;

	/**
	 * <p>
	 * cacheUpdateEntity.
	 * </p>
	 *
	 * @param tableName
	 *            a {@link java.lang.String} object.
	 * @param id
	 *            a {@link java.lang.Long} object.
	 */
	@ApiOperation(value = "Update a record of an entity into cache.", nickname = "updateARecordInCache")
	@GetMapping("/cache/update-record")
	public void cacheUpdateEntity(@RequestParam(name = "tableName", required = true) final String tableName,
			@RequestParam(name = "id", required = true) final Long id) {
		cacheEntityService.refreshEntityById(tableName, id);
	}

	/**
	 * <p>
	 * getCachedEntity.
	 * </p>
	 *
	 * @param tableName
	 *            a {@link java.lang.String} object.
	 * @param id
	 *            a {@link java.lang.Long} object.
	 * @return a {@link java.util.Map} object.
	 */
	@ApiOperation(value = "Get a record of an entity from cache.", nickname = "findARecordFromCache")
	@GetMapping("/cache/entity")
	public Map<String, Object> getCachedEntity(@RequestParam(name = "tableName", required = true) final String tableName,
			@RequestParam(name = "id", required = false) final Long id, @RequestParam(value = "ck", required = false) final List<Object> ck) {
		if (id == null && ck == null) {
			return new HashMap<>();
		}
		if (ck != null && ck.size() > 0) {
			final Object[] keyValues = new Object[ck.size()];
			ck.toArray(keyValues);
			return cacheEntityService.getEntityByKey(tableName, keyValues);
		}
		return cacheEntityService.getEntityById(tableName, id);
	}

	/**
	 * <p>
	 * cacheUpdateAllEntities.
	 * </p>
	 *
	 * @param tableName
	 *            a {@link java.lang.String} object.
	 */
	@ApiOperation(value = "Update all records of an entity into cache.", nickname = "updateEntityInCache")
	@GetMapping("/cache/reload-entity")
	public void cacheUpdateAllEntities(@RequestParam(name = "tableName", required = false) final String tableName) {
		if (StringUtils.isBlank(tableName)) {
			cacheEntityService.reloadAll();
		} else {
			cacheEntityService.reloadAllEntities(tableName);
		}
	}

	@ApiOperation(value = "Reload all Master Data/Params/Properties etc. Pass type=params or property or eligibility or masterdata or topics or eligibility", nickname = "reloadParamsInCache")
	@GetMapping("/cache/reload")
	public void reloadParams(@RequestParam(name = "type", required = true) final String type) {
		cacheEntityService.reloadParams(type);
	}

	@ApiOperation(value = "Save/update customer object into cache.", nickname = "putInCache")
	@PostMapping("/cache/entity")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void saveObject(@Valid @RequestBody final CustomCacheDTO payload) {
		final boolean noExpiry = payload.getNoExpiry() != 0;
		cacheEntityService.saveObject(payload.getGroupName(), payload.getEntity(), noExpiry, payload.getKeyValues());
	}

	@ApiOperation(value = "Clear Profile Cache.", nickname = "clearProfileCache")
	@GetMapping("/cache/remove/profile")
	public void clearProfileCache(@RequestParam(name = "customerId", required = false) final Long customerId) {
		final String groupName = "profile";
		if (customerId == null) {
			cacheEntityService.removeFromCache(groupName);
		} else {
			cacheEntityService.removeFromCache(groupName, customerId);
		}
	}

	@ApiOperation(value = "Clear Vendor Holiday Cache.", nickname = "clearVendorHolidayCache")
	@GetMapping("/cache/remove/vendor-holiday")
	public void clearVendorHolidayCache(@RequestParam(name = "hubId", required = false) final Long hubId,
			@RequestParam(name = "vendorId", required = false) final Long vendorId,
			@RequestParam(name = "date", required = false) final String date) {

		final String groupName = "vendor_holidays";
		if (hubId == null || vendorId == null || StringUtils.isBlank(date)) {
			cacheEntityService.removeFromCache(groupName);
		} else {
			cacheEntityService.removeFromCache(groupName, Collections.singleton(date), hubId, vendorId);
		}
	}

	@ApiOperation(value = "Clear Delivery Holiday Cache.", nickname = "clearDeliveryHolidayCache")
	@GetMapping("/cache/remove/delivery-holiday")
	public void clearDeliveryHolidayCache(@RequestParam(name = "hubId", required = false) final Long hubId,
			@RequestParam(name = "date", required = false) final String date) {
		final String groupName = "delivery_holidays";
		if (hubId == null || StringUtils.isBlank(date)) {
			cacheEntityService.removeFromCache(groupName);
		} else {
			cacheEntityService.removeFromCache(groupName, Collections.singleton(date), hubId);
		}
	}

	@ApiOperation(value = "Cache Reload via sns subscription [B2B]", nickname = "processEntityCacheSubscription")
	@PostMapping("/cache/subscription")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@SnsSubscription
	@NewRelicIgnoreTransaction
	public void processEntityCache(final HttpServletRequest httpServletRequest, @RequestBody final String payload) {
		final CacheEntityRefreshBean bean = JSONUtils.jsonToObject(payload, CacheEntityRefreshBean.class);
		if (bean != null) {
			final String[] tableNames = bean.getTableNames();
			if (tableNames != null) {
				for (final String tableName : tableNames) {
					cacheEntityService.reloadAllEntities(tableName);
				}
			} else {
				cacheEntityService.reloadAll();
			}
		}
	}

}
