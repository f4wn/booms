<%-- 
    Document   : book
    Created on : Sep 18, 2014, 2:44:11 AM
    Author     : Fawn
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <c:set var="bookTopNav" value="active" scope="request"/>
        <jsp:include page="topbar.jsp"/>
 
        <div class="panel callout radius"> 
        <div class="off-canvas-wrap" data-offcanvas> 
            <!-- If the contents of the .inner-wrap isn't the main content of the page, use another approripate sectioning element instead --> 
            <div class="inner-wrap large-4">
                <!-- all content goes here -->
                <form:form method="post" action="addbook" commandName="book">
                    <table>
                    <tr>
                        <td><form:label path="isbn">ISBN</form:label></td>
                        <td><form:input path="isbn" /></td> 
                    </tr>
                    <tr>
                        <td><form:label path="title">Book Title</form:label></td>
                        <td><form:input path="title" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="author">Author</form:label></td>
                        <td><form:input path="author" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="yearPublished">Year</form:label></td>
                        <td><form:input path="yearPublished" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="publisher">Publisher</form:label></td>
                        <td><form:select path="publisher" items="${publisherList}" itemLabel="name" itemValue="id">
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="categoryList">Category</form:label></td>
                        <td><form:checkboxes path="categoryList" items="${categoryList}" itemLabel="name" itemValue="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Add Book" class="button [tiny small large]"/>
                        </td>
                    </tr>
                </table>  
                </form:form>
            </div> 
        </div>
        </div>
        
        <!--a href="#" data-reveal-id="firstModal" class="radius button">test</a>
        <div id="firstModal" class="reveal-modal" data-reveal>
            <h2>This is a modal.</h2>
            <p>Reveal makes these very easy to summon and dismiss. The close button is simply an anchor with a unicode character icon and a class of <code>close-reveal-modal</code>. Clicking anywhere outside the modal will also dismiss it.</p>
            <p>Finally, if your modal summons another Reveal modal, the plugin will handle that for you gracefully.</p>
            <p><a href="#" data-reveal-id="secondModal" class="secondary button">Second Modal...</a></p>
            <a class="close-reveal-modal">&#215;</a>
        </div-->
        <div>
            <h3>Books</h3>
        </div>
        <c:if  test="${!empty bookList}">
        <table class="data">
        <tr>
            <th>ISBN</th>
            <th>Title</th>
            <th>Author</th>
            <th>Year</th>
            <th>Publisher</th>
            <th>Category</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td>${book.isbn}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.yearPublished}</td>
                <td>${book.publisher.name}</td>
                <td><c:forEach items="${book.categoryList}" var="category" varStatus="loop">
                        ${category.name} &nbsp;
                    </c:forEach></td>
                <td><a href="editbook/id=${book.isbn}">edit</a></td>
                <td><a href="deletebook/id=${book.isbn}">delete</a></td>
            </tr>
        </c:forEach>
        </table>
        </c:if>
    </body>
</html>

