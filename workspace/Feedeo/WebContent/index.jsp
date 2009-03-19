<%@ page import="java.util.*" %>

<%

Enumeration pNameList; // contient une liste des params transmises
String pName;          // contient un de ces noms
String pVals [];       // contient l'array des valeurs pour chaque param
String val;            // 1 valeur 

out.println("<p>Method used was: " + request.getMethod());

out.println("<p>Liste de param�tres et valeurs (pas forc�ment dans l'ordre): <ol>");

for (pNameList = request.getParameterNames();pNameList.hasMoreElements();){
	pName = (String) pNameList.nextElement();
    out.println("<li>name=" + pName + ": ");
    pVals = request.getParameterValues(pName);

    if (pVals != null) {
		for (int i=0; i<pVals.length; i++)
			out.print ("val=" + pVals[i] + " ");
    }
}

out.println("</ol>");

%>