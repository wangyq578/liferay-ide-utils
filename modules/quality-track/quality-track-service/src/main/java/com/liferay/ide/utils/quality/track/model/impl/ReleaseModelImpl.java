/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.ide.utils.quality.track.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.ide.utils.quality.track.model.Release;
import com.liferay.ide.utils.quality.track.model.ReleaseModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Release service. Represents a row in the &quot;qualitytrack_Release&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ReleaseModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ReleaseImpl}.
 * </p>
 *
 * @author Terry Jia
 * @see ReleaseImpl
 * @see Release
 * @see ReleaseModel
 * @generated
 */
@ProviderType
public class ReleaseModelImpl extends BaseModelImpl<Release>
	implements ReleaseModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a release model instance should use the {@link Release} interface instead.
	 */
	public static final String TABLE_NAME = "qualitytrack_Release";
	public static final Object[][] TABLE_COLUMNS = {
			{ "releaseId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "releaseName", Types.VARCHAR },
			{ "releaseDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("releaseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("releaseName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("releaseDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table qualitytrack_Release (releaseId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,releaseName VARCHAR(75) null,releaseDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table qualitytrack_Release";
	public static final String ORDER_BY_JPQL = " ORDER BY release.releaseId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY qualitytrack_Release.releaseId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(quality.track.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.ide.utils.quality.track.model.Release"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(quality.track.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.ide.utils.quality.track.model.Release"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final String MAPPING_TABLE_QUALITYTRACK_TESTCASES_RELEASES_NAME =
		"qualitytrack_TestCases_Releases";
	public static final Object[][] MAPPING_TABLE_QUALITYTRACK_TESTCASES_RELEASES_COLUMNS =
		{
			{ "companyId", Types.BIGINT },
			{ "releaseId", Types.BIGINT },
			{ "testCaseId", Types.BIGINT }
		};
	public static final String MAPPING_TABLE_QUALITYTRACK_TESTCASES_RELEASES_SQL_CREATE =
		"create table qualitytrack_TestCases_Releases (companyId LONG not null,releaseId LONG not null,testCaseId LONG not null,primary key (releaseId, testCaseId))";
	public static final boolean FINDER_CACHE_ENABLED_QUALITYTRACK_TESTCASES_RELEASES =
		GetterUtil.getBoolean(quality.track.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.qualitytrack_TestCases_Releases"),
			true);
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(quality.track.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.ide.utils.quality.track.model.Release"));

	public ReleaseModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _releaseId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setReleaseId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _releaseId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Release.class;
	}

	@Override
	public String getModelClassName() {
		return Release.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("releaseId", getReleaseId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("releaseName", getReleaseName());
		attributes.put("releaseDate", getReleaseDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long releaseId = (Long)attributes.get("releaseId");

		if (releaseId != null) {
			setReleaseId(releaseId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String releaseName = (String)attributes.get("releaseName");

		if (releaseName != null) {
			setReleaseName(releaseName);
		}

		Date releaseDate = (Date)attributes.get("releaseDate");

		if (releaseDate != null) {
			setReleaseDate(releaseDate);
		}
	}

	@Override
	public long getReleaseId() {
		return _releaseId;
	}

	@Override
	public void setReleaseId(long releaseId) {
		_releaseId = releaseId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getReleaseName() {
		if (_releaseName == null) {
			return StringPool.BLANK;
		}
		else {
			return _releaseName;
		}
	}

	@Override
	public void setReleaseName(String releaseName) {
		_releaseName = releaseName;
	}

	@Override
	public Date getReleaseDate() {
		return _releaseDate;
	}

	@Override
	public void setReleaseDate(Date releaseDate) {
		_releaseDate = releaseDate;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Release.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Release toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Release)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ReleaseImpl releaseImpl = new ReleaseImpl();

		releaseImpl.setReleaseId(getReleaseId());
		releaseImpl.setGroupId(getGroupId());
		releaseImpl.setCompanyId(getCompanyId());
		releaseImpl.setUserId(getUserId());
		releaseImpl.setUserName(getUserName());
		releaseImpl.setCreateDate(getCreateDate());
		releaseImpl.setModifiedDate(getModifiedDate());
		releaseImpl.setReleaseName(getReleaseName());
		releaseImpl.setReleaseDate(getReleaseDate());

		releaseImpl.resetOriginalValues();

		return releaseImpl;
	}

	@Override
	public int compareTo(Release release) {
		long primaryKey = release.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Release)) {
			return false;
		}

		Release release = (Release)obj;

		long primaryKey = release.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ReleaseModelImpl releaseModelImpl = this;

		releaseModelImpl._setModifiedDate = false;
	}

	@Override
	public CacheModel<Release> toCacheModel() {
		ReleaseCacheModel releaseCacheModel = new ReleaseCacheModel();

		releaseCacheModel.releaseId = getReleaseId();

		releaseCacheModel.groupId = getGroupId();

		releaseCacheModel.companyId = getCompanyId();

		releaseCacheModel.userId = getUserId();

		releaseCacheModel.userName = getUserName();

		String userName = releaseCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			releaseCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			releaseCacheModel.createDate = createDate.getTime();
		}
		else {
			releaseCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			releaseCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			releaseCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		releaseCacheModel.releaseName = getReleaseName();

		String releaseName = releaseCacheModel.releaseName;

		if ((releaseName != null) && (releaseName.length() == 0)) {
			releaseCacheModel.releaseName = null;
		}

		Date releaseDate = getReleaseDate();

		if (releaseDate != null) {
			releaseCacheModel.releaseDate = releaseDate.getTime();
		}
		else {
			releaseCacheModel.releaseDate = Long.MIN_VALUE;
		}

		return releaseCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{releaseId=");
		sb.append(getReleaseId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", releaseName=");
		sb.append(getReleaseName());
		sb.append(", releaseDate=");
		sb.append(getReleaseDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.ide.utils.quality.track.model.Release");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>releaseId</column-name><column-value><![CDATA[");
		sb.append(getReleaseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>releaseName</column-name><column-value><![CDATA[");
		sb.append(getReleaseName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>releaseDate</column-name><column-value><![CDATA[");
		sb.append(getReleaseDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Release.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Release.class
		};
	private long _releaseId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _releaseName;
	private Date _releaseDate;
	private Release _escapedModel;
}