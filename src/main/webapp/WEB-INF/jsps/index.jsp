<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    // extract session id
    String sessionId = (String) request.getAttribute("sid");
%>

<% if (sessionId != null) { %>
    <script>
	document.location = "?sid=" + "<%= sessionId %>";
    </script>
<% } %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Songbook</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	  integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <link rel="stylesheet" href="/css/basic.css">
</head>

<body>
    <h2 class="width100 headline">Songbook</h2>

    <table class="table">
	<thead>
	    <tr>
		<th>name</th>
		<th>artist</th>
		<th>duration</th>
	    </tr>
	</thead>
	<tbody>
	    <tr>
		<td>Nichts in der Welt</td>
		<td>Die Ärzte</td>
		<td>227</td>
	    </tr>
	</tbody>
    </table>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	    integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	    integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>