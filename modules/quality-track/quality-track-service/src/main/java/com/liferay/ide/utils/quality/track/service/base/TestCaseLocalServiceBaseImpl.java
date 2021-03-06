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

package com.liferay.ide.utils.quality.track.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.service.persistence.AssetEntryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetTagPersistence;

import com.liferay.ide.utils.quality.track.model.TestCase;
import com.liferay.ide.utils.quality.track.service.TestCaseLocalService;
import com.liferay.ide.utils.quality.track.service.persistence.ReleasePersistence;
import com.liferay.ide.utils.quality.track.service.persistence.TestCasePersistence;
import com.liferay.ide.utils.quality.track.service.persistence.TestPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the test case local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.ide.utils.quality.track.service.impl.TestCaseLocalServiceImpl}.
 * </p>
 *
 * @author Terry Jia
 * @see com.liferay.ide.utils.quality.track.service.impl.TestCaseLocalServiceImpl
 * @see com.liferay.ide.utils.quality.track.service.TestCaseLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class TestCaseLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements TestCaseLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.ide.utils.quality.track.service.TestCaseLocalServiceUtil} to access the test case local service.
	 */

	/**
	 * Adds the test case to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testCase the test case
	 * @return the test case that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TestCase addTestCase(TestCase testCase) {
		testCase.setNew(true);

		return testCasePersistence.update(testCase);
	}

	/**
	 * Creates a new test case with the primary key. Does not add the test case to the database.
	 *
	 * @param testCaseId the primary key for the new test case
	 * @return the new test case
	 */
	@Override
	public TestCase createTestCase(long testCaseId) {
		return testCasePersistence.create(testCaseId);
	}

	/**
	 * Deletes the test case with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testCaseId the primary key of the test case
	 * @return the test case that was removed
	 * @throws PortalException if a test case with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TestCase deleteTestCase(long testCaseId) throws PortalException {
		return testCasePersistence.remove(testCaseId);
	}

	/**
	 * Deletes the test case from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testCase the test case
	 * @return the test case that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public TestCase deleteTestCase(TestCase testCase) {
		return testCasePersistence.remove(testCase);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(TestCase.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return testCasePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.ide.utils.quality.track.model.impl.TestCaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return testCasePersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.ide.utils.quality.track.model.impl.TestCaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return testCasePersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return testCasePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return testCasePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public TestCase fetchTestCase(long testCaseId) {
		return testCasePersistence.fetchByPrimaryKey(testCaseId);
	}

	/**
	 * Returns the test case with the primary key.
	 *
	 * @param testCaseId the primary key of the test case
	 * @return the test case
	 * @throws PortalException if a test case with the primary key could not be found
	 */
	@Override
	public TestCase getTestCase(long testCaseId) throws PortalException {
		return testCasePersistence.findByPrimaryKey(testCaseId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(testCaseLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(TestCase.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("testCaseId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(testCaseLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(TestCase.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("testCaseId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(testCaseLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(TestCase.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("testCaseId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return testCaseLocalService.deleteTestCase((TestCase)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return testCasePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the test cases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.ide.utils.quality.track.model.impl.TestCaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of test cases
	 * @param end the upper bound of the range of test cases (not inclusive)
	 * @return the range of test cases
	 */
	@Override
	public List<TestCase> getTestCases(int start, int end) {
		return testCasePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of test cases.
	 *
	 * @return the number of test cases
	 */
	@Override
	public int getTestCasesCount() {
		return testCasePersistence.countAll();
	}

	/**
	 * Updates the test case in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testCase the test case
	 * @return the test case that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public TestCase updateTestCase(TestCase testCase) {
		return testCasePersistence.update(testCase);
	}

	/**
	 */
	@Override
	public void addReleaseTestCase(long releaseId, long testCaseId) {
		releasePersistence.addTestCase(releaseId, testCaseId);
	}

	/**
	 */
	@Override
	public void addReleaseTestCase(long releaseId, TestCase testCase) {
		releasePersistence.addTestCase(releaseId, testCase);
	}

	/**
	 */
	@Override
	public void addReleaseTestCases(long releaseId, long[] testCaseIds) {
		releasePersistence.addTestCases(releaseId, testCaseIds);
	}

	/**
	 */
	@Override
	public void addReleaseTestCases(long releaseId, List<TestCase> testCases) {
		releasePersistence.addTestCases(releaseId, testCases);
	}

	/**
	 */
	@Override
	public void clearReleaseTestCases(long releaseId) {
		releasePersistence.clearTestCases(releaseId);
	}

	/**
	 */
	@Override
	public void deleteReleaseTestCase(long releaseId, long testCaseId) {
		releasePersistence.removeTestCase(releaseId, testCaseId);
	}

	/**
	 */
	@Override
	public void deleteReleaseTestCase(long releaseId, TestCase testCase) {
		releasePersistence.removeTestCase(releaseId, testCase);
	}

	/**
	 */
	@Override
	public void deleteReleaseTestCases(long releaseId, long[] testCaseIds) {
		releasePersistence.removeTestCases(releaseId, testCaseIds);
	}

	/**
	 */
	@Override
	public void deleteReleaseTestCases(long releaseId, List<TestCase> testCases) {
		releasePersistence.removeTestCases(releaseId, testCases);
	}

	/**
	 * Returns the releaseIds of the releases associated with the test case.
	 *
	 * @param testCaseId the testCaseId of the test case
	 * @return long[] the releaseIds of releases associated with the test case
	 */
	@Override
	public long[] getReleasePrimaryKeys(long testCaseId) {
		return testCasePersistence.getReleasePrimaryKeys(testCaseId);
	}

	/**
	 */
	@Override
	public List<TestCase> getReleaseTestCases(long releaseId) {
		return releasePersistence.getTestCases(releaseId);
	}

	/**
	 */
	@Override
	public List<TestCase> getReleaseTestCases(long releaseId, int start, int end) {
		return releasePersistence.getTestCases(releaseId, start, end);
	}

	/**
	 */
	@Override
	public List<TestCase> getReleaseTestCases(long releaseId, int start,
		int end, OrderByComparator<TestCase> orderByComparator) {
		return releasePersistence.getTestCases(releaseId, start, end,
			orderByComparator);
	}

	/**
	 */
	@Override
	public int getReleaseTestCasesCount(long releaseId) {
		return releasePersistence.getTestCasesSize(releaseId);
	}

	/**
	 */
	@Override
	public boolean hasReleaseTestCase(long releaseId, long testCaseId) {
		return releasePersistence.containsTestCase(releaseId, testCaseId);
	}

	/**
	 */
	@Override
	public boolean hasReleaseTestCases(long releaseId) {
		return releasePersistence.containsTestCases(releaseId);
	}

	/**
	 */
	@Override
	public void setReleaseTestCases(long releaseId, long[] testCaseIds) {
		releasePersistence.setTestCases(releaseId, testCaseIds);
	}

	/**
	 * Returns the release local service.
	 *
	 * @return the release local service
	 */
	public com.liferay.ide.utils.quality.track.service.ReleaseLocalService getReleaseLocalService() {
		return releaseLocalService;
	}

	/**
	 * Sets the release local service.
	 *
	 * @param releaseLocalService the release local service
	 */
	public void setReleaseLocalService(
		com.liferay.ide.utils.quality.track.service.ReleaseLocalService releaseLocalService) {
		this.releaseLocalService = releaseLocalService;
	}

	/**
	 * Returns the release persistence.
	 *
	 * @return the release persistence
	 */
	public ReleasePersistence getReleasePersistence() {
		return releasePersistence;
	}

	/**
	 * Sets the release persistence.
	 *
	 * @param releasePersistence the release persistence
	 */
	public void setReleasePersistence(ReleasePersistence releasePersistence) {
		this.releasePersistence = releasePersistence;
	}

	/**
	 * Returns the test local service.
	 *
	 * @return the test local service
	 */
	public com.liferay.ide.utils.quality.track.service.TestLocalService getTestLocalService() {
		return testLocalService;
	}

	/**
	 * Sets the test local service.
	 *
	 * @param testLocalService the test local service
	 */
	public void setTestLocalService(
		com.liferay.ide.utils.quality.track.service.TestLocalService testLocalService) {
		this.testLocalService = testLocalService;
	}

	/**
	 * Returns the test persistence.
	 *
	 * @return the test persistence
	 */
	public TestPersistence getTestPersistence() {
		return testPersistence;
	}

	/**
	 * Sets the test persistence.
	 *
	 * @param testPersistence the test persistence
	 */
	public void setTestPersistence(TestPersistence testPersistence) {
		this.testPersistence = testPersistence;
	}

	/**
	 * Returns the test case local service.
	 *
	 * @return the test case local service
	 */
	public TestCaseLocalService getTestCaseLocalService() {
		return testCaseLocalService;
	}

	/**
	 * Sets the test case local service.
	 *
	 * @param testCaseLocalService the test case local service
	 */
	public void setTestCaseLocalService(
		TestCaseLocalService testCaseLocalService) {
		this.testCaseLocalService = testCaseLocalService;
	}

	/**
	 * Returns the test case persistence.
	 *
	 * @return the test case persistence
	 */
	public TestCasePersistence getTestCasePersistence() {
		return testCasePersistence;
	}

	/**
	 * Sets the test case persistence.
	 *
	 * @param testCasePersistence the test case persistence
	 */
	public void setTestCasePersistence(TestCasePersistence testCasePersistence) {
		this.testCasePersistence = testCasePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.asset.kernel.service.AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.asset.kernel.service.AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {
		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset tag local service.
	 *
	 * @return the asset tag local service
	 */
	public com.liferay.asset.kernel.service.AssetTagLocalService getAssetTagLocalService() {
		return assetTagLocalService;
	}

	/**
	 * Sets the asset tag local service.
	 *
	 * @param assetTagLocalService the asset tag local service
	 */
	public void setAssetTagLocalService(
		com.liferay.asset.kernel.service.AssetTagLocalService assetTagLocalService) {
		this.assetTagLocalService = assetTagLocalService;
	}

	/**
	 * Returns the asset tag persistence.
	 *
	 * @return the asset tag persistence
	 */
	public AssetTagPersistence getAssetTagPersistence() {
		return assetTagPersistence;
	}

	/**
	 * Sets the asset tag persistence.
	 *
	 * @param assetTagPersistence the asset tag persistence
	 */
	public void setAssetTagPersistence(AssetTagPersistence assetTagPersistence) {
		this.assetTagPersistence = assetTagPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.ide.utils.quality.track.model.TestCase",
			testCaseLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.ide.utils.quality.track.model.TestCase");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return TestCaseLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return TestCase.class;
	}

	protected String getModelClassName() {
		return TestCase.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = testCasePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.ide.utils.quality.track.service.ReleaseLocalService.class)
	protected com.liferay.ide.utils.quality.track.service.ReleaseLocalService releaseLocalService;
	@BeanReference(type = ReleasePersistence.class)
	protected ReleasePersistence releasePersistence;
	@BeanReference(type = com.liferay.ide.utils.quality.track.service.TestLocalService.class)
	protected com.liferay.ide.utils.quality.track.service.TestLocalService testLocalService;
	@BeanReference(type = TestPersistence.class)
	protected TestPersistence testPersistence;
	@BeanReference(type = TestCaseLocalService.class)
	protected TestCaseLocalService testCaseLocalService;
	@BeanReference(type = TestCasePersistence.class)
	protected TestCasePersistence testCasePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = com.liferay.asset.kernel.service.AssetEntryLocalService.class)
	protected com.liferay.asset.kernel.service.AssetEntryLocalService assetEntryLocalService;
	@ServiceReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@ServiceReference(type = com.liferay.asset.kernel.service.AssetTagLocalService.class)
	protected com.liferay.asset.kernel.service.AssetTagLocalService assetTagLocalService;
	@ServiceReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}