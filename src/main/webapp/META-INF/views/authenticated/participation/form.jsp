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
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="authenticated.participation.form.label.thread" path="thread.title" readonly="true"/>
		<acme:form-select code="authenticated.participation.form.label.userName" path="participant">
			<jstl:forEach items="${users}" var="user">
				<acme:form-option code="${user.identity.fullName}" value="${user.id}"/>
			</jstl:forEach>
		</acme:form-select>
		<acme:form-submit code="authenticated.participation.form.button.addToThread" action="/authenticated/participation/create?messageThreadId=${messageThreadId}"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
		<acme:form-textbox code="authenticated.participation.form.label.thread" path="thread.title" readonly="true"/>
		<acme:form-textbox code="authenticated.participation.form.label.userName" path="participant.identity.fullName" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${command == 'show' && self == false}">
		<acme:form-submit code="authenticated.participation.form.button.removeFromThread" action="/authenticated/participation/delete"/>
	</jstl:if>
	
	<jstl:if test="${command == 'delete'}">
		<acme:form-textbox code="authenticated.participation.form.label.thread" path="thread.title" readonly="true"/>
		<acme:form-textbox code="authenticated.participation.form.label.userName" path="participant.identity.fullName" readonly="true"/>
		<acme:form-submit code="authenticated.participation.form.button.removeFromThread" action="/authenticated/participation/delete"/>
	</jstl:if>
	 
	<acme:form-return code="authenticated.participation.form.button.return"/>
</acme:form>