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
	<acme:form-textbox code="consumer.offer.label.title" path="title"/>
	<acme:form-moment code="consumer.offer.label.deadline" path="deadline"/>
	<acme:form-textarea code="consumer.offer.label.text" path="text"/>
	<acme:form-money code="consumer.offer.label.minReward" path="minReward"/>
	<acme:form-money code="consumer.offer.label.maxReward" path="maxReward"/>
	<acme:form-textbox code="consumer.offer.label.ticker" path="ticker" placeholder="OXXXX-99999"/>
	 	
	 <acme:form-checkbox code="consumer.offer.label.accept" path="accept"/>
	 	
	<acme:form-submit code="consumer.offer.button.create" action="/consumer/offer/create"/>
  	<acme:form-return code="consumer.offer.button.return"/>
</acme:form>
