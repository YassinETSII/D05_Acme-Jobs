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
		<acme:form-textarea code="authenticated.messageThread.form.label.userNameList" path="userNameList"/>
		<acme:form-select code="authenticated.messageThread.form.label.addUser" path="${userAdded}">
			<form:options items="${usersToInvolve}"/>
		</acme:form-select>
		<acme:form-select code="authenticated.messageThread.form.label.removeUser" path="${userRemoved}">
			<form:options items="userNameList"/>
		</acme:form-select>
		<acme:form-submit code="authenticated.messageThread.form.button.list-messages" action="/authenticated/message/list?idMessageThread=${id}" method="get"/>
	</jstl:if>
		
	<acme:form-submit test="${command == 'create'}"
		code="authenticated.messageThread.form.button.create"
		action="/authenticated/message-thread/create"/>	
		
	<acme:form-submit test="${command == 'show'}"
		code="authenticated.messageThread.form.button.update"
		action="/authenticated/message-thread/update"/>	
		
		
	<acme:form-return code="authenticated.messageThread.form.button.return"/>
</acme:form>