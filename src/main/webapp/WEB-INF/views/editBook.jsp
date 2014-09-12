<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Editing ${book.title}</title>
	</head>
	
	<body>
		<form action="/library/book/${book.id}" method=post>
			<table>
				<tr>
					<td><b>ISBN: </b></td>
					<td><input type="number" name="isbn" value="${book.isbn}"><br></td>
				</tr>
				<tr>
					<td><b>Title: </b></td>
					<td><input type="text" name="title" value="${book.title}"><br></td>
				</tr>
				<tr>
					<td><b>Author: </b></td>
					<td><input type="text" name="author" value="${book.author}"></td>
				</tr>
				<tr>
					<td><b>Current owner: </b></td>
					<td>
						<select name="owner">
							<c:forEach items="${allCustomers}" var="customer">
				    			<option value="${customer.name}">${customer.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<a href="/library/book/${book.id}">
				<input type="button" value="Cancel" />
			</a>
			<input type="submit" value="Save">
		</form>
	</body>
</html>
