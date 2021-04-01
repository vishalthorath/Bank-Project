<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Succcess</title>
</head>
<body>
 <%
     
     session = request.getSession();
    out.println(("Dear, ") + session.getAttribute("name")); 
    out.println("<br>");
    out.println("<br>");
    out.println(" Yenu Car Loan bekka ninge baa kodtini..Adu nu nam bank inda naa nam hatra ne yenu illa ninge yellinda koodona, Nam hatra illa kodake...hahahha");
    		out.println("<br>");
    		out.println("<br>");
    		
    out.println("Our executive will contact you soon on your email address mentioned below:  ");
    out.println("<br>");
    out.println("<br>");
    out.println(session .getAttribute("email"));
 
 
 
 
 %>

</body>
</html>