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
	
	<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment 
		code="auditor.auditRecord.form.label.moment" 
		path="moment"
		readonly="true"/>
	</jstl:if>
	<acme:form-textarea code="auditor.auditRecord.form.label.body" path="body"/>
	<jstl:if test="${finalMode == true}">
		<acme:form-checkbox code="auditor.auditRecord.form.label.finalMode" path="finalMode" readonly="true"/>
	</jstl:if>
	<jstl:if test="${finalMode == false}">
		<acme:form-checkbox code="auditor.auditRecord.form.label.finalMode" path="finalMode"/>
	</jstl:if>
	<acme:form-textbox code="auditor.auditRecord.form.label.job" path="job.reference" readonly="true"/>
	
	<acme:form-submit test="${command == 'create'}"
		code="auditor.auditRecord.form.button.create" 
		action="/auditor/audit-record/create?idJob=${idJob}" method="post"/>	
	
	<acme:form-submit test="${command == 'update'}"
		code="auditor.auditRecord.form.button.update" 
		action="/auditor/audit-record/update"/>
		
	<acme:form-submit test="${command == 'show' && finalMode == false}" 
		code="auditor.auditRecord.form.button.update" 
		action="/auditor/audit-record/update"/>
	<acme:form-submit test="${command == 'show' && finalMode == false}"
	code="auditor.auditRecord.form.button.delete" 
		action="/auditor/audit-record/delete"/>
		
	<acme:form-submit test="${command == 'delete'}"
		code="auditor.auditRecord.form.button.delete" 
		action="/auditor/audit-record/delete"/>
		
	<acme:form-return code="auditor.auditRecord.form.button.return"/>
</acme:form>