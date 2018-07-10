
<!DOCTYPE html>

<%@ page import="static information.leakage.psi.Constants.*" %>
<%@page import="java.util.List"%>
<%@page import="information.leakage.psi.backend.rest.clients.SongClient"%>
<%@ page import="information.leakage.psi.model.Song" %>
<%@ page import="javax.ws.rs.core.MediaType" %>


<%
    // extract possible error
    String errorMsg = (String) request.getAttribute(Parameter.ERROR_MESSAGE);

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

    <% if (errorMsg != null) { %>
	<div class="alert alert-danger">
	    <%= errorMsg %>
	</div>
    <% } %>

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

    <div class="width100">
	<span type="button" class="btn btn-primary addSong">Add Song</span>
    </div>

    <div id="addSongModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
	    <div class="modal-content">
		<div class="modal-header">
		    <h4 class="modal-title">Add Song</h4>
		    <button type="button" class="close" data-dismiss="modal">&times;</button>
		</div>
		<div class="modal-body addSongModalBody">
		    <form method="post" action="/songbook/songs">
			<div class="row" style="margin-bottom:10px">
			    <div class="col-sm-4">
				<label class="">Title:</label>
			    </div>
			    <div class="col-sm-8">
				<input class="form-control" name="<%= Parameter.INPUT_TITLE %>" type="text">
			    </div>
			</div>
			<div class="row" style="margin-bottom:10px">
			    <div class="col-sm-4">
				<label class="">Artist:</label>
			    </div>
			    <div class="col-sm-8">
				<input class="form-control" name="<%= Parameter.INPUT_ARTIST %>" type="text">
			    </div>
			</div>
			<div class="row"style="margin-bottom:10px">
			    <div class="col-sm-4">
				<label class="">Duration in seconds:</label>
			    </div>
			    <div class="col-sm-8">
				<input class="form-control" name="<%= Parameter.INPUT_DURATION %>" type="number">
			    </div>
			</div>
			    <%
				// Retrieve session id from url
				sessionId = request.getParameter(Parameter.SESSION_ID);
			    %>
			    <input class="form-control hidden" name="<%= Parameter.SESSION_ID %>" value="<%= sessionId %>" readonly>
		    </div>
		    <div class="modal-footer">
			<button type="submit" class="btn btn-primary pull-left" style="width: 150px;">Create</button>
			<button type="button" class="btn btn-default" data-dismiss="modal" style="width: 150px;">Cancel</button>
		    </div>
		</form>
	    </div>
	</div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	    integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
	    integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

    <script>
	$(function () {
	    $('.addSong').click(function () {
		$('#addSongModal').modal();
	    });
	});
    </script>
</body>
</html>