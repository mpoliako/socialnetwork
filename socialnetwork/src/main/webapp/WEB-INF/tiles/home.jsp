<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel" id="home">
	<h1><c:out value="${user.firstName} ${user.lastName}" /> </h1>
	<div class="image_wrapper image_fl">
		<img
			src="${pageContext.request.contextPath}/static/images${user.photoUrl}"
			alt="image" />
	</div>
	<p>
		<em>Personal Information</em>
	</p>
	<p>
		${user.information}
	</p>
	<div class="cleaner_h40"></div>
	
</div>