<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Adding book</title>
	</head>
	
	<body>
		<form action="/library/book/added" method=post>
			<table>
				<tr>
					<td><b>ISBN: </b></td>
					<td><input type="number" name="isbn" value=""><br></td>
				</tr>
				<tr>
					<td><b>Title: </b></td>
					<td><input type="text" name="title" value=""><br></td>
				</tr>
				<tr>
					<td><b>Author: </b></td>
					<td><input type="text" name="author" value=""></td>
				</tr>
			</table>			
			<a href="/library">
				<input type="button" value="Cancel" />
			</a>
			<input type="submit" value="Save">
		</form>
	</body>
</html>
