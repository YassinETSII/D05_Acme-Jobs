<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous.panel1" access="isAnonymous()">
      		<acme:menu-suboption code="master.menu.anonymous.favourite-link1" action="https://www.youtube.com/watch?v=YbnIEcTPKRY/"/>         
			<acme:menu-suboption code="master.menu.anonymous.favourite-link2" action="https://myanimelist.net/"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.shout.list" action="/anonymous/shout/list"/>
			<acme:menu-suboption code="master.menu.anonymous.shout.create" action="/anonymous/shout/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.laljbulletin.list" action="/anonymous/lalj-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.laljbulletin.create" action="/anonymous/lalj-bulletin/create"/>
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.anonymous.rodriguezbulletin.list" action="/anonymous/rodriguez-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.rodriguezbulletin.create" action="/anonymous/rodriguez-bulletin/create"/>	
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous.panel2" access="isAnonymous()">			
			<acme:menu-suboption code="master.menu.anonymous.announcement.list" action="/anonymous/announcement/list"/>	
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.anonymous.companyRecord.list" action="/anonymous/company-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.companyRecord.listTop" action="/anonymous/company-record/list-top"/>
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.anonymous.investorRecord.list" action="/anonymous/investor-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.investorRecord.listTop" action="/anonymous/investor-record/list-top"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list"/>	
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.authenticated.companyRecord.list" action="/authenticated/company-record/list"/>
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.authenticated.investorRecord.list" action="/authenticated/investor-record/list"/>
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.authenticated.request.list" action="/authenticated/request/list"/>
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.authenticated.offer.list" action="/authenticated/offer/list"/>
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.authenticated.challenge.list" action="/authenticated/challenge/list"/>	
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.authenticated.job.list" action="/authenticated/job/list"/>
			<acme:menu-separator/>		
			<acme:menu-suboption code="master.menu.authenticated.messageThread.create" action="/authenticated/message-thread/create"/>		
			<acme:menu-suboption code="master.menu.authenticated.messageThread.list" action="/authenticated/message-thread/list"/>	
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator.panel1" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.announcement.list" action="/administrator/announcement/list"/>	
			<acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create"/>	
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.companyRecord.list" action="/administrator/company-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.companyRecord.create" action="/administrator/company-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.investorRecord.list" action="/administrator/investor-record/list"/>	
			<acme:menu-suboption code="master.menu.administrator.investorRecord.create" action="/administrator/investor-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.challenge.list" action="/administrator/challenge/list"/>	
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.commercialBanner.list" action="/administrator/commercial-banner/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.nonCommercialBanner.list" action="/administrator/non-commercial-banner/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.auditorRequest.list" action="/administrator/auditor-request/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrator.panel2" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>				
			<acme:menu-suboption code="master.menu.administrator.customisation.form" action="/administrator/customisation/show"/>				
			<acme:menu-separator/>				
			<acme:menu-suboption code="master.menu.administrator.dashboard.form" action="/administrator/dashboard/show"/>
			<acme:menu-separator/>				
			<acme:menu-suboption code="master.menu.administrator.chart.form" action="/administrator/chart/show"/>	
			<acme:menu-separator/>	
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
			<acme:menu-separator/>	
			<acme:menu-suboption code="master.menu.provider.request.create" action="/provider/request/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
			<acme:menu-separator/>	
			<acme:menu-suboption code="master.menu.consumer.offer.create" action="/consumer/offer/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.job.listMine" action="/employer/job/list-mine"/>
			<acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create"/>
			<acme:menu-separator/>				
			<acme:menu-suboption code="master.menu.employer.application.listMine" action="/employer/application/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.job.list-not-applied" action="/worker/job/list-not-applied"/>
			<acme:menu-suboption code="master.menu.worker.application.list-mine" action="/worker/application/list-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.job.list-mine" action="/auditor/job/list-mine"/>
			<acme:menu-suboption code="master.menu.auditor.job.list-not-mine" action="/auditor/job/list-not-mine"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.commercialBanner.list-mine" action="/sponsor/commercial-banner/list-mine"/>
			<acme:menu-suboption code="master.menu.sponsor.nonCommercialBanner.list-mine" action="/sponsor/non-commercial-banner/list-mine"/>
		</acme:menu-option>
	</acme:menu-left>
	
	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create" access="!hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update" access="hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create" access="!hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update" access="hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/auditor-request/create" access="!hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create" access="!hasRole('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update" access="hasRole('Sponsor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

