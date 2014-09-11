<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
    
<html>
	<head>
		<title>Viewing ${customer.name}</title>
	</head>
	
	<body>
		<table>
			<tr>
				<td>Name: </td>
				<td>${customer.name}</td>
			</tr>
			<tr>
				<td>Email address: </td>
				<td>${customer.email}</td>
			</tr>
			<tr>
				<td>Address: </td>
				<td>${customer.address}</td>
			</tr>
			<tr>
				<td>Borrowed books: </td>
				<c:forEach items="${borrowedBooks}" var="book">
			    	<td><a href="/library/book/${book.id}"><c:out value="${book.title}"/></a></td> 
				</c:forEach>
			</tr>
		</table>
		
		<form action="/library/customer/edit/${customer.id}">
    		<input type="submit" value="Edit details">
		</form>
		
		<form action="/library/customer/deleted/${customer.id}">
    		<input type="submit" value="Delete"
    		onclick="return confirm('Are you sure?')">
		</form>
		
		<a href="/library">
			<input type="button" value="Return" />
		</a>
	</body>
</html>
