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
	<acme:form-hidden path="noCreditCard"/>
	<jstl:if test="${noCreditCard == false}">

		<acme:form-textbox code="sponsor.commercialBanner.form.label.picture" path="picture"/>
		<acme:form-textbox code="sponsor.commercialBanner.form.label.slogan" path="slogan"/>
		<acme:form-url code="sponsor.commercialBanner.form.label.url" path="URL"/>
		<acme:form-textbox code="sponsor.commercialBanner.form.label.holder" path="creditCard.holder" readonly = "true"/>
		<acme:form-integer code="sponsor.commercialBanner.form.label.expirationMonth" path="creditCard.expirationMonth" readonly = "true"/>
		<acme:form-integer code="sponsor.commercialBanner.form.label.expirationYear" path="creditCard.expirationYear" readonly = "true"/>
		<acme:form-textbox code="sponsor.commercialBanner.form.label.creditCardNumber" path="creditCard.creditCardNumber" readonly = "true"/>	
		<acme:form-textbox code="sponsor.commercialBanner.form.label.brand" path="creditCard.brand" readonly = "true"/>	
		<acme:form-textbox code="sponsor.commercialBanner.form.label.CVV" path="creditCard.CVV" readonly = "true"/>
		
		<acme:form-submit test="${command == 'show'}"
			code="sponsor.commercialBanner.form.button.update"
			action="/sponsor/commercial-banner/update"/>
			
		<acme:form-submit test="${command == 'show'}"
			code="sponsor.commercialBanner.form.button.delete"
			action="/sponsor/commercial-banner/delete"/>
			
		<acme:form-submit test="${command == 'create'}"
			code="sponsor.commercialBanner.form.button.create"
			action="/sponsor/commercial-banner/create"/>	
			
		<acme:form-submit test="${command == 'update'}"
			code="sponsor.commercialBanner.form.button.update"
			action="/sponsor/commercial-banner/update"/>
			
		<acme:form-submit test="${command == 'delete'}"
			code="sponsor.commercialBanner.form.button.delete"
			action="/sponsor/commercial-banner/delete"/>

	</jstl:if>
	
	<jstl:if test="${command == 'create' && noCreditCard == true}">
		<div class="jumbotron">
			<acme:message code="sponsor.commercialBanner.creditCardText"/>
		</div>
		<acme:form-submit code="sponsor.commercialBanner.form.button.add-creditCard" action="/sponsor/credit-card/create?idSponsor=${idSponsor}" method="get" />
	</jstl:if>
		
	<acme:form-return code="sponsor.commercialBanner.form.button.return"/>
</acme:form>