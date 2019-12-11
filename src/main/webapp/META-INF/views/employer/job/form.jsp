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

	<jstl:if test="${finalMode == false || finalMode == true && command == 'update'}">
	
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EEEE-JJJJ"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="employer.job.form.label.description" path="description"/>
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode"/>		
	
	<acme:form-submit test="${command == 'show'}" 
		code="employer.job.form.button.update" 
		action="/employer/job/update"/>
	
	</jstl:if>	
	
	<jstl:if test="${finalMode == true && command != 'update'}">
	
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EEEE-JJJJ" readonly="true"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title" readonly="true"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" readonly="true"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary" readonly="true"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" readonly="true"/>
	<acme:form-textarea code="employer.job.form.label.description" path="description" readonly="true"/>
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode" readonly="true"/>	
	
	</jstl:if>	
	
	<acme:form-submit test="${command == 'show'}"
	 	code="employer.job.form.button.delete" 
	 	action="/employer/job/delete"/>	
	 	
	<jstl:if test="${command == 'show' && finalMode == false}">
		<acme:form-submit code="employer.job.form.button.add-duty" action="/employer/duty/create?idJob=${id}" method="get" />
	</jstl:if>	 	
		
	<jstl:if test="${command == 'show'}">
		<acme:form-submit code="employer.job.form.button.list-duties" action="/employer/duty/list?idJob=${id}" method="get" />
		<acme:form-submit code="employer.job.form.button.list-audit-records" action="/authenticated/audit-record/list?idJob=${id}" method="get" />	
	</jstl:if>
		
	<acme:form-submit test="${command == 'create'}"
		code="employer.job.form.button.create"
		action="/employer/job/create"/>	
	
	<acme:form-submit test="${command == 'update'}"
		code="employer.job.form.button.update"
		action="/employer/job/update"/>
		
	<acme:form-submit test="${command == 'delete'}"
		code="employer.job.form.button.delete"
		action="/employer/job/delete"/>		
		
	<acme:form-return code="employer.job.form.button.return"/>	
	
</acme:form>