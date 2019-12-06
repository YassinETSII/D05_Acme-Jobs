<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-integer code="administrator.dashboard.form.label.numberAnnouncements" path="numberAnnouncements"/>
	<acme:form-integer code="administrator.dashboard.form.label.numberCompanyRecords" path="numberCompanyRecords"/>
	<acme:form-integer code="administrator.dashboard.form.label.numberInvestorRecords" path="numberInvestorRecords"/>
	
	<acme:form-double code="administrator.dashboard.form.label.minimumNumberOfRewardsOfActiveRequests" path="minimumNumberOfRewardsOfActiveRequests"/>
	<acme:form-double code="administrator.dashboard.form.label.maximumNumberOfRewardsOfActiveRequests" path="maximumNumberOfRewardsOfActiveRequests"/>
	<acme:form-double code="administrator.dashboard.form.label.averageNumberOfRewardsOfActiveRequests" path="averageNumberOfRewardsOfActiveRequests"/>
	<acme:form-double code="administrator.dashboard.form.label.standardDesviationNumberOfRewardsOfActiveRequests" path="standardDesviationNumberOfRewardsOfActiveRequests"/>
	
	<acme:form-double code="administrator.dashboard.form.label.minimumNumberOfRewardsOfActiveOffers" path="minimumNumberOfRewardsOfActiveOffers"/>	
	<acme:form-double code="administrator.dashboard.form.label.maximumNumberOfRewardsOfActiveOffers" path="maximumNumberOfRewardsOfActiveOffers"/>
	<acme:form-double code="administrator.dashboard.form.label.averageNumberOfRewardsOfActiveOffers" path="averageNumberOfRewardsOfActiveOffers"/>
	<acme:form-double code="administrator.dashboard.form.label.standardDesviationNumberOfRewardsOfActiveOffers" path="standardDesviationNumberOfRewardsOfActiveOffers"/>
	
	<acme:form-double code="administrator.dashboard.form.label.averageNumberOfJobsPerEmployer" path="averageNumberOfJobsPerEmployer"/>
	<acme:form-double code="administrator.dashboard.form.label.averageNumberOfApplicationsPerEmployer" path="averageNumberOfApplicationsPerEmployer"/>
	<acme:form-double code="administrator.dashboard.form.label.averageNumberOfApplicationsPerWorker" path="averageNumberOfApplicationsPerWorker"/>	
	
	<acme:form-return code="administrator.dashboard.form.button.return"/>
</acme:form>