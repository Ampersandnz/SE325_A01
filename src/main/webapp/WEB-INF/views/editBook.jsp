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
					<td><input type="number" name="isbn" value="${book.getIsbn()}"><br></td>
				</tr>
				<tr>
					<td><b>Title: </b></td>
					<td><input type="text" name="title" value="${book.getTitle()}"><br></td>
				</tr>
				<tr>
					<td><b>Author: </b></td>
					<td><input type="text" name="author" value="${book.getAuthor()}"></td>
				</tr>
			</table>
			<input type="submit" value="Save">
		</form>
	</body>
</html>
