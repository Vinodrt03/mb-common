package com.milkbasket.rest.services.cache.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.milkbasket.core.framework.caching.config.EntityConfig;
import com.milkbasket.core.framework.caching.repository.CacheEntityRepository;
import com.milkbasket.core.framework.caching.repository.CacheRepository;
import com.milkbasket.core.framework.common.utils.ContextUtils;

/**
 * <p>
 * CacheEntityService interface.
 * </p>
 *
 * @author nitin
 * @version $Id: $Id
 */
public interface CacheEntityService<T> {

	void refreshEntityById(String tableName, Long id);

	Map<String, Object> getEntityById(String tableName, Long id);

	void reloadAllEntities(String tableName);

	void reloadAllEntities(EntityConfig entityConfig);

	void reloadAll();

	void saveObject(String tblName, Object entity, boolean noExpiry, Object... keyValue);

	Map<String, Object> getEntityByKey(String tblName, Object... keyValues);

	Collection<Object> getEntityList(String... keys);

	Collection<Object> getEntityListByKeys(String groupName, Object... keyValues);

	Collection<Object> getEntityListByKeyPrefix(String key);

	void reloadParams(String type);

	default void removeFromCache(final String groupName, final Object... keys) {
		final CacheEntityRepository<?> repo = ContextUtils.getBean(CacheEntityRepository.class);
		final String keyPattern = CacheRepository.getKeyPattern(groupName, keys);
		repo.removePattern(keyPattern);
	}

	default void removeFromCache(final String groupName, final Collection<?> vars, final Object... keyValues) {
		final CacheEntityRepository<?> repo = ContextUtils.getBean(CacheEntityRepository.class);
		final Set<String> keys = CacheRepository.getCandidateKeys(groupName, vars, keyValues);
		repo.remove(new ArrayList<>(keys));
	}

	default void removefromCacheByKey(final String groupName, final Object... keys) {
		final CacheEntityRepository<?> repo = ContextUtils.getBean(CacheEntityRepository.class);
		final String key = CacheRepository.getKey(groupName, keys);
		repo.remove(key);
	}

}
