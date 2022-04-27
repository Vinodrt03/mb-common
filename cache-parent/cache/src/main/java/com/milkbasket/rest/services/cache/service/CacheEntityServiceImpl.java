package com.milkbasket.rest.services.cache.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milkbasket.core.framework.caching.config.EntityConfig;
import com.milkbasket.core.framework.caching.entity.CacheEntity;
import com.milkbasket.core.framework.caching.entity.RedisCacheEntity;
import com.milkbasket.core.framework.caching.service.EntityCacheService;
import com.milkbasket.core.framework.common.bean.CompletableFutureContextBean;
import com.milkbasket.core.framework.common.constants.CommonConstants.DeploymentType;
import com.milkbasket.core.framework.common.utils.ContextUtils;
import com.milkbasket.core.framework.eligibility.EligibilityUtils;
import com.milkbasket.core.framework.logging.core.AppLogger;
import com.milkbasket.core.framework.logging.manager.LoggingManager;
import com.milkbasket.core.framework.masterdata.utils.MasterDataReader;
import com.milkbasket.core.framework.params.utils.ParamsReader;
import com.milkbasket.core.framework.property.utils.PropertiesReader;

@Service
/**
 * <p>
 * CacheEntityServiceImpl class.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public class CacheEntityServiceImpl<T> implements CacheEntityService<T> {

	private static final AppLogger _LOGGER = LoggingManager.getLogger(CacheEntityService.class);

	@Autowired
	private EntityCacheService<?> entityCacheService;

	/** {@inheritDoc} */
	@Override
	public void refreshEntityById(final String tableName, final Long id) {
		entityCacheService.refreshEntityById(getCacheEntity(), tableName, id);
	}

	/** {@inheritDoc} */
	@Override
	public Map<String, Object> getEntityById(final String tableName, final Long id) {
		return entityCacheService.getEntityById(getCacheEntity(), tableName, id);
	}

	/** {@inheritDoc} */
	@Override
	public Map<String, Object> getEntityByKey(final String tblName, final Object... keyValues) {
		return entityCacheService.getEntityByKey(getCacheEntity(), tblName, keyValues);
	}

	/** {@inheritDoc} */
	@Override
	public Collection<Object> getEntityList(final String... keys) {
		return entityCacheService.getEntityList(getCacheEntity(), keys);

	}

	/** {@inheritDoc} */
	@Override
	public Collection<Object> getEntityListByKeyPrefix(final String keyPrefix) {
		return entityCacheService.getEntityListByKeyPrefix(getCacheEntity(), keyPrefix);
	}

	/** {@inheritDoc} */
	@Override
	public Collection<Object> getEntityListByKeys(final String groupName, final Object... keyValues) {
		return entityCacheService.getEntityListByKeys(getCacheEntity(), groupName, keyValues);

	}

	/** {@inheritDoc} */
	@Override
	public void saveObject(final String groupName, final Object entity, final boolean noExpiry, final Object... keyValues) {
		entityCacheService.saveObject(getCacheEntity(), groupName, entity, noExpiry, keyValues);
	}

	/** {@inheritDoc} */
	@Override
	public void reloadAllEntities(final String tableName) {
		entityCacheService.reloadAllEntities(getCacheEntity(), tableName);
	}

	/** {@inheritDoc} */
	@Override
	public void reloadAllEntities(final EntityConfig cacheConfig) {
		entityCacheService.reloadAllEntities(getCacheEntity(), cacheConfig);
	}

	/** {@inheritDoc} */
	@Override
	public void reloadAll() {
		entityCacheService.reloadAll(getCacheEntity());
	}

	/** {@inheritDoc} */
	@Override
	public void reloadParams(final String type) {
		final CompletableFutureContextBean context = CompletableFutureContextBean.getContext();
		final boolean batchCall = ContextUtils.getDeploymentType().equalsIgnoreCase(DeploymentType.BATCH.toString());
		if (batchCall || ContextUtils.isProfileTest()) {
			reoadParamsAndMasterData(context, type);
		} else {
			CompletableFuture.runAsync(() -> reoadParamsAndMasterData(context, type));
		}
	}

	private void reoadParamsAndMasterData(final CompletableFutureContextBean context, final String type) {
		try {
			CompletableFutureContextBean.setContext(context);
			switch (type) {
			case "params":
				ContextUtils.getBean(ParamsReader.class).loadParams();
				break;
			case "property":
				ContextUtils.getBean(PropertiesReader.class).loadProperties();
				break;
			case "eligibility":
				EligibilityUtils.loadEligibility();
				break;
			case "masterdata":
				ContextUtils.getBean(MasterDataReader.class).loadMasterData();
				break;
			default:
				ContextUtils.getBean(ParamsReader.class).loadParams();
				ContextUtils.getBean(PropertiesReader.class).loadProperties();
				ContextUtils.getBean(MasterDataReader.class).loadMasterData();
				EligibilityUtils.loadEligibility();
			}
		} catch (final Exception e) {
			_LOGGER.error("Error in reloadParams " + e.getMessage());
		}
	}

	private CacheEntity getCacheEntity() {
		return ContextUtils.getBean(RedisCacheEntity.class);
	}

}
