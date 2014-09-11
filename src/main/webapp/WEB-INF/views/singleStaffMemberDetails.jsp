<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
    
<html>
	<head>
		<title>Viewing ${staffMember.name}</title>
	</head>
	
	<body>
		<table>
			<tr>
				<td>Name: </td>
				<td>${staffMember.name}</td>
			</tr>
			<tr>
				<td>Email address: </td>
				<td>${staffMember.email}</td>
			</tr>
			<tr>
				<td>Position: </td>
				<td>${staffMember.position}</td>
			</tr>
		</table>
		
		<form action="/library/staffMember/edit/${staffMember.id}">
    		<input type="submit" value="Edit details">
		</form>
		
		<form action="/library/staffMember/deleted/${staffMember.id}">
    		<input type="submit" value="Delete"
    		onclick="return confirm('Are you sure?')">
		</form>
		
		<a href="/library">
			<input type="button" value="Return" />
		</a>
	</body>
</html>
