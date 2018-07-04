
<!DOCTYPE html>

<%@ page import="static prechtl.psi.insekta.Constants.*" %>
<%@page import="java.util.List"%>
<%@page import="prechtl.psi.insekta.rest.clients.SongClient"%>
<%@ page import="prechtl.psi.insekta.model.Song" %>
<%@ page import="javax.ws.rs.core.MediaType" %>


<%
    // extract session id
    String sessionId = (String) request.getAttribute(Parameter.SESSION_ID);
    if (sessionId != null) { %>
    <script>
	document.location = "?<%= Parameter.SESSION_ID %>=" + "<%= sessionId %>";
    </script>
<% } %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Songbook</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	  integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <link rel="stylesheet" href="css/basic.css">
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
	    <%
		// Retrieve session id from url
		sessionId = request.getParameter(Parameter.SESSION_ID);

		// Send request to Webservice endpoint to receive all songs
		if (sessionId != null) {
		    SongClient songClient = new SongClient();
		    List<Song> songs = songClient.retrieveSongs(sessionId);

		    // show each song in a row
		    if (songs != null) {
			for (Song song : songs) {
	    %>
		<tr>
		    <td><%= song.getTitle() %></td>
		    <td><%= song.getArtist() %></td>
		    <td><%= song.getDuration() %></td>
		</tr>
	    <% } } } %>
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