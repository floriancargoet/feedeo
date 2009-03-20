<%@page import="org.stringtree.json.JSONReader"%>
<%@page import="org.stringtree.json.JSONWriter"%>
<%@page import="java.util.*"%>
<%@page import="serveur.*" %>
<%
HashMap jsonResponse = new HashMap();

JSONReader jsonReader = new JSONReader();
JSONWriter jsonWriter = new JSONWriter();

String userName = (String) session.getAttribute("userName");

FeedeoHandler handler = new FeedeoHandler(userName);

String jsonRequest = request.getParameter("request");
String requestType = request.getParameter("type");

if(userName == null)
{
	//pas logg�
	//traite une requete de login ou de creation
	try
	{
	        HashMap loginOrCreateRequest = (HashMap)jsonReader.read(jsonRequest);
	        String action = (String) loginOrCreateRequest.get("action");
	        if(action !=null && action.equals("login"))
	        {
				userName = handler.login(loginOrCreateRequest);
				if(userName != null)
				{
					session.setAttribute("userName",userName);
			        jsonResponse.put("success",true);
			        jsonResponse.put("msg", "login successful");
			        //ajouter qq donn�es sur l'user
				}
				else
				{
			        jsonResponse.put("success",false);
			        jsonResponse.put("error", "login failed");
				}
	        }
	        else if(action !=null && action.equals("create"))
	        {
	        	userName = handler.createAccount(loginOrCreateRequest);
	        	if(userName != null)
	        	{
	        		//log him
	        		session.setAttribute("userName",userName);
			        jsonResponse.put("success",true);
			        jsonResponse.put("msg", "Account creation successful");
			        //ajouter qq donn�es sur l'user
	        	}
	        	else
	        	{
	        		jsonResponse.put("success",false);
			        jsonResponse.put("error", "Account creation failed");
	        	}
	        }
	}
	catch(Exception e)
	{
	        jsonResponse.put("success",false);
	        jsonResponse.put("error", "Request failed. You must login.");
	}	
}
else if(requestType != null && requestType.equals("multiple"))
{
	try
	{
	        ArrayList<HashMap> requests = (ArrayList)jsonReader.read(jsonRequest);
	        //handle each request
	        for(HashMap r:requests)
	        {
	                //out.println("Req : "+r);
	                handler.handle(r,jsonResponse);
	        }
	
	
	//debug
	//jsonResponse = jsonWriter.write(requests);
	
	
	}
	catch(Exception e)
	{
	        jsonResponse.put("success",false);
	        jsonResponse.put("error", "bad multiple request");
	}
}
else if(requestType != null && requestType.equals("simple"))
{
	try
	{
	        HashMap simpleRequest = (HashMap)jsonReader.read(jsonRequest);
			handler.handle(simpleRequest, jsonResponse);

	}
	catch(Exception e)
	{
	        jsonResponse.put("success",false);
	        jsonResponse.put("error", "bad simple request");
	}
}
else 
{
	jsonResponse.put("success",false);
    jsonResponse.put("error", "bad request type [multiple, simple]");

}

out.println(jsonWriter.write(jsonResponse));

%>