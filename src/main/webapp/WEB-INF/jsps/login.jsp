<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="static information.leakage.psi.Constants.*" %>


<%
    // extract possible error
    String errorMsg = null;
    if (request.getAttribute(Parameter.ERROR_MESSAGE) != null) {
	errorMsg = (String) request.getAttribute(Parameter.ERROR_MESSAGE);
    }
%>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Songbook - Login</title>

    <!-- Bootstrap Stylesheet -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <!-- Own Stylesheet -->
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/basic.css">
</head>

<body class="text-center">

    <form class="form-signin" method="post" action="/songbook/">
	<% if (errorMsg != null) { %>
	    <div class="alert alert-danger">
		<%= errorMsg %>
	    </div>
	<% } %>

	<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
	<label for="inputEmail" class="sr-only">Email address</label>
	<input type="email" id="inputEmail" name="<%= Parameter.INPUT_EMAIL %>" class="form-control" placeholder="Email address" required="" autofocus="">
	<label for="inputPassword" class="sr-only">Password</label>
	<input type="password" id="inputPassword" name="<%= Parameter.INPUT_PASSWORD %>" class="form-control" placeholder="Password" required="">
	<div class="checkbox mb-3">
	    <label>
		<input type="checkbox" value="remember-me"> Remember me
	    </label>
	</div>

	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	<p class="mt-5 mb-3 text-muted">© Songbook, 2018</p>
    </form>

    <!-- Bootstrap Scripts -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	    integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	    integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

    <!-- JQuery Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</body>
</html>