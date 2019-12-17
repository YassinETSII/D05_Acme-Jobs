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
<script type="text/javascript" src="libraries/chart.js/2.7.2/js/chart.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/moment.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.bundle.js"></script>

<acme:form readonly="true">
	
	<div class="chart-container" style="position: relative; height:40; width:80">	
	<h2>
	<acme:message code="administrator.chart.form.title.timeSeriesApplications"/>
	</h2>
	<canvas id="canvas"></canvas>			
	</div>
	
	<script type="text/javascript">
	var timeFormat = 'DD/MM/YYYY';
	
	var apppending = new Array();
		<jstl:forEach items="${countPendingApplications}" var="countPendingApplicationsItem">
			apppending.push("${countPendingApplicationsItem}");
		</jstl:forEach>
	
	var mompending = new Array();
		<jstl:forEach items="${momentPendingApplications}" var="momentPendingApplicationsItem">
			mompending.push("${momentPendingApplicationsItem}");
		</jstl:forEach>
		
		functionPending = function(x, y) {
		  return x.map((item, index) => {
		    return {
		      x: item,
		      y: y[index]
		    }
		  })
		}
				
	var appaccepted = new Array();
		<jstl:forEach items="${countAcceptedApplications}" var="countAcceptedApplicationsItem">
			appaccepted.push("${countAcceptedApplicationsItem}");
		</jstl:forEach>
	
	var momaccepted = new Array();
		<jstl:forEach items="${momentAcceptedApplications}" var="momentAcceptedApplicationsItem">
			momaccepted.push("${momentAcceptedApplicationsItem}");
		</jstl:forEach>
		
		functionAccepted= function(x, y) {
		  return x.map((item, index) => {
		    return {
		      x: item,
		      y: y[index]
		    }
		  })
		}
		
	var apprejected = new Array();
		<jstl:forEach items="${countRejectedApplications}" var="countRejectedApplicationsItem">
			apprejected.push("${countRejectedApplicationsItem}");
		</jstl:forEach>
	
	var momrejected = new Array();
		<jstl:forEach items="${momentRejectedApplications}" var="momentRejectedApplicationsItem">
			momrejected.push("${momentRejectedApplicationsItem}");
		</jstl:forEach>
		
		functionRejected= function(x, y) {
		  return x.map((item, index) => {
		    return {
		      x: item,
		      y: y[index]
		    }
		  })
		}
	
		var config = {
		        type:    'line',
		        data:    {
		            datasets: [
		                {
		                    label: "Pending",
		                    data: functionPending(mompending,apppending),
		                    fill: false,
		                    borderColor: 'blue'
		                },
		                
		                {
		                    label: "Accepted",
		                    data: functionAccepted(momaccepted,appaccepted),
		                    fill: false,
		                    borderColor: 'green'
		                },
		                
		                {
		                    label: "Rejected",
		                    data: functionRejected(momrejected,apprejected),
		                    fill: false,
		                    borderColor: 'red'
		                }
		            ]
		        },
		        options: {
		            responsive: true,
		            title:      {
		                display: false,
		            },
		            scales:     {
		                xAxes: [{
		                    type:       "time",
		                    distribution: 'series',
		                    time:       {
		                        format: timeFormat,
		                        tooltipFormat: 'll'
		                    },
		                    scaleLabel: {
		                        display:     true,
		                        labelString: 'Date'
		                    }
		                }],
		                yAxes: [{
		                    scaleLabel: {
		                        display:     true,
		                        labelString: 'Number of applications'
		                    }
		                }]
		            }
		        }
		    };

		    window.onload = function () {
		        var ctx       = document.getElementById("canvas").getContext("2d");
		        window.myLine = new Chart(ctx, config);
		    };
	</script>

	
	

	<acme:form-return code="administrator.chart.form.button.return"/>
</acme:form>