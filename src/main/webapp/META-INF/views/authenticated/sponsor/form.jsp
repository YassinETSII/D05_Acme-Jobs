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
	<acme:form-textarea code="authenticated.sponsor.form.label.organisation" path="organisation"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.form.button.create" action="/authenticated/sponsor/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.sponsor.form.button.update" action="/authenticated/sponsor/update"/>
	
	<jstl:if test="${command == 'update' && noCreditCard==true}">
		<acme:form-submit code="authenticated.sponsor.form.button.add-creditCard" action="/sponsor/credit-card/create?idSponsor=${idSponsor}" method="get" />
	</jstl:if>
	
	<jstl:if test="${command == 'update' && noCreditCard==false}">
		<acme:form-submit code="authenticated.sponsor.form.button.creditCard" action="/sponsor/credit-card/show?id=${idCreditCard}" method="get" />
	</jstl:if>		
	
	<acme:form-return code="authenticated.sponsor.form.button.return"/>
</acme:form>