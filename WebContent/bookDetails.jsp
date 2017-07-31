	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
	<!-- Just some stuff you need -->
	<header>
	  <div class="container">
		
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Book Details - ${book.isbn13}</small></h1>
		<hr class="book-primary">
		
		<form action="UpdateBook" method="post" class="form-horizontal">
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13}" />
		  
		  <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Title</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="title" name="title" placeholder="Title" required="required" value="${book.title}" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="author" class="col-sm-4 control-label">Author</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="author" name="author" placeholder="Author" required="required" value="${book.author}" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="price" class="col-sm-4 control-label">Price</label>
		    <div class="col-sm-5">
		      <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required="required" value="${book.price}" />
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Update</button>
		    </div>
		  </div>
		</form>

	  </div>
	</header>
  
	<section>
	  <div class="container">
		
		<h1><small>Tags</small></h1>
		<table
			class="table table-striped table-hover table-responsive pubhub-datatable">
			<thead>
				<tr>
					<th>Tag</th>
					<td></td>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="tag" items="${tags}">
					<tr>
						<td><c:out value="${tag.tag}" /></td>
						<td> 
						  <form method="post" action="RemoveBookTag">
							  <input type="hidden" value="${tag.isbn13}" name="isbn13"/>
							  <input type="hidden" value="${tag.tag}" name="tag"/>
							  <button class="btn btn-danger popover-bookpub-removetag" type="submit">Delete</button>
						  </form>						  					
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	  </div>
	</section>

	<section class="success">
	  <div class="container">

		<h1><small>Add Tag</small></h1>
		<form action="AddBookTag" method="post" class="form-horizontal">
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" placeholder="ISBN 13" required="required" value="${book.isbn13}" />
		  
		  <div class="form-group">
		    <label for="tag" class="col-sm-4 control-label">Tag</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="tag" name="tag" placeholder="Tag" required="required" />
		    </div>
		  </div>
		  <div class="form-group">
		    <div id="popover-bookpub-addtag" class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-info">Add Tag</button>
		    </div>
		  </div>
		</form>
		
		
	  </div>
	</section>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
