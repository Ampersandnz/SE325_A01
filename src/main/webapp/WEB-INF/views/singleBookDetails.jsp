<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
    
<html>
	<head>
		<title>Viewing ${book.title}</title>
	</head>
	
	<body>
		<table>
		<tr>
			<td>ISBN: </td>
			<td>${book.isbn}</td>
		</tr>
		<tr>
			<td>Title: </td>
			<td>${book.title}</td>
		</tr>
		<tr>
			<td>Author: </td>
			<td>${book.author}</td>
		</tr>
		<tr>
			<td>Current owner: </td>
			<td><a href="/customer/${book.owner.id}"><c:out value="${book.owner.name}"/></a></td>
		</tr>
		</table>
		
		<form action="/library/book/edit/${book.id}">
    		<input type="submit" value="Edit details">
		</form>
		
		<form action="/library/book/deleted/${book.id}">
    		<input type="submit" value="Delete"
    		onclick="return confirm('Are you sure?')">
		</form>
	</body>
</html>
