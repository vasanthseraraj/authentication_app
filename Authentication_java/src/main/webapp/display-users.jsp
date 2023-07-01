<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Role Based Auth App</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2 align="center">MFCWL</h2>

		</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="INSERT USER"
				onclick="window.location.href='add-user.jsp'; return false;" />
			<table border="1">
				<tr>


					<th>USER NAME</th>
					<th>ROLE ID</th>
					<th>ROLE NAME</th>
					<th>UPDATED BY</th>
					<th>ACTION</th>
				</tr>
				<c:forEach var="tempUser" items="${USER_LIST}">
					<!-- 				set up link for each user -->
					<c:url var="tempLink" value="UserAuthenticationServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="userId" value="${tempUser.userId}" />
					</c:url>
					<c:url var="deleteLink" value="UserAuthenticationServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="userId" value="${tempUser.userId}" />
					</c:url>

					<tr>
						<td>${tempUser.userName}</td>
						<td>${tempUser.roleId}</td>
						<td>${tempUser.roleName}</td>
						<td>${tempUser.updatedBy}</td>
						<td><a href="${tempLink}">EDIT</a> | <a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this Student?'))) return false">DELETE</a>
						</td>
					</tr>
				</c:forEach>



			</table>
		</div>
	</div>


</body>



</html>