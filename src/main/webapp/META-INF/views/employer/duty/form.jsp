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

	<jstl:if test="${command == 'create' || command == 'update' || job.finalMode == false}">
		<acme:form-textbox code="employer.duty.form.label.title" path="title" />
		<acme:form-textarea code="employer.duty.form.label.description" path="description" />
		<acme:form-double code="employer.duty.form.label.timePercentage" path="timePercentage" />
		<acme:form-hidden code="employer.duty.form.label.idJob" path="idJob" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show' && job.finalMode == false">
		<acme:form-submit code="employer.duty.form.button.update" 
		action="/employer/duty/update"/>
		<acme:form-submit code="employer.duty.form.button.delete" 
	 	action="/employer/duty/delete"/>
	</jstl:if>	
		
	<jstl:if test="${command == 'show' && job.finalMode == true || command == 'delete'}">
		<acme:form-textbox code="employer.duty.form.label.title" path="title" readonly="true"/>
		<acme:form-textarea code="employer.duty.form.label.description" path="description" readonly="true"/>
		<acme:form-double code="employer.duty.form.label.timePercentage" path="timePercentage" readonly="true"/>
		<acme:form-hidden code="employer.duty.form.label.idJob" path="idJob" readonly="true"/>
	</jstl:if>		
	
	<acme:form-submit test="${command == 'create'}"
		code="employer.duty.form.button.create"
		action="/employer/duty/create?idJob=${idJob}" method="post"/>	
		
	<acme:form-submit test="${command == 'update'}" 
		code="employer.duty.form.button.update"
		action="/employer/duty/update"/>
		
	<acme:form-submit test="${command == 'delete'}" 
		code="employer.duty.form.button.delete"
		action="/employer/duty/delete"/>
							
	<acme:form-return code="employer.duty.form.button.return"/>
	
</acme:form>