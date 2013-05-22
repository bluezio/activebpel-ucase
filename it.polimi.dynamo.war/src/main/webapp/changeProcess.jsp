<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="monitor.polimi.it.configurationmanager.ConfigurationManager" %>
<%@ page import="monitor.polimi.it.configurationmanager.ConfigurationManagerWS" %>
<%@ page import="monitor.polimi.it.configurationmanager.ProcessInfoWrapper" %>
<%@ page import="java.rmi.RemoteException" %>
<%@ page import="javax.xml.ws.WebServiceException" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Process priority change</title>
	</head>
	<body>
		<img src="figures/testa_dynamo_small.jpg"/>
<%
	final String pID = request.getParameter("pID");
	final String uID = request.getParameter("uID");
	final Integer priority = new Integer(request.getParameter("priority"));
	
	boolean result = false;
	try {
		final ConfigurationManager cm = new ConfigurationManagerWS().getConfigurationManagerPort();
		final ProcessInfoWrapper pInfo = new ProcessInfoWrapper();
		pInfo.setPriority(priority);
		pInfo.setProcessId(pID);
		pInfo.setUserId(uID);
		result = cm.setNewProcessPriority(pInfo);
	} catch (WebServiceException e) {
		out.println(e.getMessage());
	}

	if (result == true) { %>
		<p>The process priority has been changed.</p>
		<script type="text/javascript">
			setTimeout(function() { document.location = 'viewProcesses.jsp'; }, 3000);
		</script>
<%	} else { %>
		<p>We have had a problem. Please contact us.</p>
<%	} %>
		<p><a href="viewProcesses.jsp">Return to process list</a></p>
	</body>
</html>
