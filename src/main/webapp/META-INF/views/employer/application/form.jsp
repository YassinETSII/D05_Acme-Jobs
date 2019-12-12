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

<acme:form>
	<jstl:if test="${(status == 'pending' && command == 'show') || (status == 'pending' || status == 'accepted' || status == 'rejected') && command == 'update'}">

	<acme:form-textbox code="employer.application.form.label.reference" path="reference" placeholder="EEEE-JJJJ:WWWW"/>
	<acme:form-moment code="employer.application.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.qualifications" path="qualifications" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.justification" path="justification"/>
	<acme:form-textbox code="employer.application.form.label.job.reference" path="job.reference" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.applierWorker" path="worker.identity.fullName" readonly="true"/>	
	
	<acme:form-select code="employer.application.form.label.status" path="status">
			<acme:form-option code="ACCEPT" value="accepted" selected="true"/>
			<acme:form-option code="REJECT" value="rejected"/>
		</acme:form-select>		
		<acme:form-submit code="employer.application.form.button.update" action="/employer/application/update"/>
	</jstl:if>
	
	<jstl:if test="${status != 'pending' && command == 'show'}">

	<acme:form-textbox code="employer.application.form.label.reference" path="reference" placeholder="EEEE-JJJJ:WWWW" readonly="true"/>
	<acme:form-moment code="employer.application.form.label.moment" path="moment" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.skills" path="skills" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.qualifications" path="qualifications" readonly="true"/>
	<acme:form-textarea code="employer.application.form.label.justification" path="justification" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.job.reference" path="job.reference" readonly="true"/>
	<acme:form-textbox code="employer.application.form.label.applierWorker" path="worker.identity.fullName" readonly="true"/>	
	
	</jstl:if>		
	
	<acme:form-submit code="employer.application.form.label.job" action="/employer/job/show?id=${idJob}" method="get" />
	
	<acme:form-return code="employer.application.form.button.return"/>
</acme:form>