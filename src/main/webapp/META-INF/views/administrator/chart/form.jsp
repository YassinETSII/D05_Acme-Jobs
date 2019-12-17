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
	<acme:message code="administrator.chart.form.title.company"/>
	</h2>
	<canvas id="myChart"></canvas>
	<br>
	<br>
	<br>
	<h2>
	<acme:message code="administrator.chart.form.title.investor"/>
	</h2>
	<canvas id="myChart2"></canvas>
	<br>
	<br>
	<br>
	<h2>
	<acme:message code="administrator.chart.form.title.ratioJobs"/>
	</h2>
	<canvas id="myChart3"></canvas>
	<br>
	<br>
	<br>
	<h2>
	<acme:message code="administrator.chart.form.title.ratioApplications"/>
	</h2>
	<canvas id="myChart4"></canvas>
	<br>
	<br>
	<br>
	<h2>
	<acme:message code="administrator.chart.form.title.ApplicationTimeSerie"/>
	</h2>
	<canvas id="myChart5"></canvas>			
	</div>
	
	<script type="text/javascript">

	var compsec = new Array();
	<jstl:forEach items="${companySector}" var="companySecItem">
		compsec.push("${companySecItem}");
	</jstl:forEach>
	
	var compnum = new Array();
	<jstl:forEach items="${companyNumber}" var="companyNumItem">
		compnum.push("${companyNumItem}");
	</jstl:forEach>
		
 	var labels = [];
	var i;
	for (i = 0; i < compsec.length; i++) {
		labels.push(compsec[i]);
	}
	
	var data = [];
	var j;
	for (j = 0; j < compnum.length; j++) {
		data.push(compnum[j]);
	}
	
	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels,
	        datasets: [{
	            data,
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(199, 103, 103, 0.2)',
	                'rgba(112, 134, 206, 0.2)',
	                'rgba(60, 235, 182, 0.2)',
	                'rgba(176, 235, 60, 0.2)',
	                'rgba(235, 206, 60, 0.2)',
	                'rgba(235, 60, 130, 0.2)',
	                'rgba(113, 62, 152, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255, 99, 132, 1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(199, 103, 103, 1)',
	                'rgba(112, 134, 206, 1)',
	                'rgba(60, 235, 182, 1)',
	                'rgba(176, 235, 60, 1)',
	                'rgba(235, 206, 60, 1)',
	                'rgba(235, 60, 130, 1)',
	                'rgba(113, 62, 152, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	legend: {display:false},
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
	
	</script>

	<script type="text/javascript">
	
	var invsec = new Array();
	<jstl:forEach items="${investorSector}" var="investorSecItem">
		invsec.push("${investorSecItem}");
	</jstl:forEach>
	
	var invnum = new Array();
	<jstl:forEach items="${investorNumber}" var="investorNumItem">
		invnum.push("${investorNumItem}");
	</jstl:forEach>
	
 	var labels = [];
	var i;
	for (i = 0; i < invsec.length; i++) {
		labels.push(invsec[i]);
	}
	
	var data = [];
	var j;
	for (j = 0; j < invnum.length; j++) {
		data.push(invnum[j]);
	}
	
	var ctx = document.getElementById('myChart2').getContext('2d');
	var myChart2 = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels,
	        datasets: [{
	            data,
	            backgroundColor: [
	            	'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(199, 103, 103, 0.2)',
	                'rgba(112, 134, 206, 0.2)',
	                'rgba(60, 235, 182, 0.2)',
	                'rgba(176, 235, 60, 0.2)',
	                'rgba(235, 206, 60, 0.2)',
	                'rgba(235, 60, 130, 0.2)',
	                'rgba(113, 62, 152, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	            	'rgba(255, 99, 132, 1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(199, 103, 103, 1)',
	                'rgba(112, 134, 206, 1)',
	                'rgba(60, 235, 182, 1)',
	                'rgba(176, 235, 60, 1)',
	                'rgba(235, 206, 60, 1)',
	                'rgba(235, 60, 130, 1)',
	                'rgba(113, 62, 152, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	legend: {display:false},
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});

	</script>

	<script type="text/javascript">

	var ratiojobs = new Array();
	<jstl:forEach items="${ratioOfJobs}" var="ratioJobsItem">
		ratiojobs.push("${ratioJobsItem}");
	</jstl:forEach>
	
	var finalmod = new Array();
	<jstl:forEach items="${finalMode}" var="finalModeItem">
		finalmod.push("${finalModeItem}");
	</jstl:forEach>
		
 	var labels = [];
	var i;
	for (i = 0; i < finalmod.length; i++) {
		labels.push(finalmod[i]);
	}
	
	var data = [];
	var j;
	for (j = 0; j < ratiojobs.length; j++) {
		data.push(ratiojobs[j]);
	}
	
	var ctx = document.getElementById('myChart3').getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels,
	        datasets: [{
	            data,
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(199, 103, 103, 0.2)',
	                'rgba(112, 134, 206, 0.2)',
	                'rgba(60, 235, 182, 0.2)',
	                'rgba(176, 235, 60, 0.2)',
	                'rgba(235, 206, 60, 0.2)',
	                'rgba(235, 60, 130, 0.2)',
	                'rgba(113, 62, 152, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	                'rgba(255, 99, 132, 1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(199, 103, 103, 1)',
	                'rgba(112, 134, 206, 1)',
	                'rgba(60, 235, 182, 1)',
	                'rgba(176, 235, 60, 1)',
	                'rgba(235, 206, 60, 1)',
	                'rgba(235, 60, 130, 1)',
	                'rgba(113, 62, 152, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	legend: {display:false},
	        scales: {
	            yAxes: [{
	                ticks: {
	                	suggestedMin: 0.0,
	                    suggestedMax: 1.0
	                }
	            }]
	        }
	    }
	});
	
	</script>	
	
	<script type="text/javascript">
	
	var appstatus = new Array();
	<jstl:forEach items="${applicationStatus}" var="applicationStatusItem">
		appstatus.push("${applicationStatusItem}");
	</jstl:forEach>
	
	var appratio = new Array();
	<jstl:forEach items="${ratioOfApplications}" var="ratioOfApplicationsItem">
		appratio.push("${ratioOfApplicationsItem}");
	</jstl:forEach>
	
 	var labels = [];
	var i;
	for (i = 0; i < appstatus.length; i++) {
		labels.push(appstatus[i]);
	}
	
	var data = [];
	var j;
	for (j = 0; j < appratio.length; j++) {
		data.push(appratio[j]);
	}
	
	var ctx = document.getElementById('myChart4').getContext('2d');
	var myChart2 = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels,
	        datasets: [{
	            data,
	            backgroundColor: [
	            	'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(199, 103, 103, 0.2)',
	                'rgba(112, 134, 206, 0.2)',
	                'rgba(60, 235, 182, 0.2)',
	                'rgba(176, 235, 60, 0.2)',
	                'rgba(235, 206, 60, 0.2)',
	                'rgba(235, 60, 130, 0.2)',
	                'rgba(113, 62, 152, 0.2)',
	                'rgba(255, 159, 64, 0.2)'
	            ],
	            borderColor: [
	            	'rgba(255, 99, 132, 1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(199, 103, 103, 1)',
	                'rgba(112, 134, 206, 1)',
	                'rgba(60, 235, 182, 1)',
	                'rgba(176, 235, 60, 1)',
	                'rgba(235, 206, 60, 1)',
	                'rgba(235, 60, 130, 1)',
	                'rgba(113, 62, 152, 1)',
	                'rgba(255, 159, 64, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	legend: {display:false},
	        scales: {
	            yAxes: [{
	                ticks: {
	                    suggestedMin: 0.0,
	                    suggestedMax: 1.0
	                }
	            }]
	        }
	    }
	});

	</script>
	
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
		        var ctx       = document.getElementById("myChart5").getContext("2d");
		        window.myLine = new Chart(ctx, config);
		    };
	</script>

	<acme:form-return code="administrator.chart.form.button.return"/>
</acme:form>