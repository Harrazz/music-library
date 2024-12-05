<%-- 
    Document   : result_music
    Created on : Dec 2, 2024, 2:42:12 PM
    Author     : Harraz
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Search Results</h2>
<c:set var="title" value="${param.title}" />
<c:set var="artist" value="${param.artist}" />

<c:choose>

    <c:when test="${title eq 'Cardigan' && artist eq 'Taylor Swift'}">
        <p>Found: Cardigan by Taylor Swift (Indie Folk, 2020)</p>
    </c:when>

    <c:otherwise>
        <c:redirect url='list_music.jsp?forwarded=true' />
    </c:otherwise>

</c:choose>