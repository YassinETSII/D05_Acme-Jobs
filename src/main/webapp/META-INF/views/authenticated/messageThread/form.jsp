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
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment 
		code="authenticated.messageThread.form.label.moment" 
		path="moment"
		readonly="true"/>
		<acme:form-moment 
		code="authenticated.messageThread.form.label.creator" 
		path="creator.identity.fullName"
		readonly="true"/>
		<acme:form-submit test="${postedMessage == true}" code="authenticated.messageThread.form.button.list-messages" action="/authenticated/message/list?messageThreadId=${id}" method="get"/>
	</jstl:if>
	
	<jstl:if test="${command !='create' && isCreator == true}">
		<acme:form-submit code="authenticated.messageThread.form.button.list-participants" action="/authenticated/participation/list?messageThreadId=${id}" method="get"/>
		<acme:form-submit code="authenticated.messageThread.form.button.add-participant" action="/authenticated/participation/create?messageThreadId=${id}" method="get"/>
	</jstl:if>
		
	<acme:form-submit test="${command == 'create'}"
		code="authenticated.messageThread.form.button.create"
		action="/authenticated/message-thread/create"/>	
		
	<acme:form-submit test="${command == 'show' && isCreator == true}"
		code="authenticated.messageThread.form.button.update"
		action="/authenticated/message-thread/update"/>	
		
		<acme:form-submit test="${command == 'update'}"
		code="authenticated.messageThread.form.button.update"
		action="/authenticated/message-thread/update"/>
		
		
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
</acme:form>