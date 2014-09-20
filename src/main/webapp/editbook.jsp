<%-- 
    Document   : editbook
    Created on : Sep 20, 2014, 1:53:57 AM
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
            <form:form method="post" action="commit" commandName="book">

                <table>
                <tr>
                    <td><form:label path="isbn">ISBN</form:label></td>
                    <td><form:input path="isbn" readonly="true" value="${book.isbn}"/></td> 
                </tr>
                <tr>
                    <td><form:label path="title">Book Title</form:label></td>
                    <td><form:input path="title" value="${book.title}"/></td>
                </tr>
                <tr>
                    <td><form:label path="author">Author</form:label></td>
                    <td><form:input path="author" value="${book.author}"/></td>
                </tr>
                <tr>
                    <td><form:label path="yearPublished">Year</form:label></td>
                    <td><form:input path="yearPublished" value="${book.yearPublished}"/></td>
                </tr>
                <tr>
                    <td><form:label path="publisher">Publisher</form:label></td>
                    <td><form:select path="publisher" items="${publisherList}" itemLabel="name" itemValue="id">
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="categoryList">Category</form:label></td>
                    <td>
                        <form:checkboxes path="categoryList" items="${categoryList}" itemLabel="name" itemValue="name" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Update Book" class="button [tiny small large]"/>
                    </td>
                </tr>
            </table>  
            </form:form>
            </div>
        </div>
        </div>
    </body>
</html>


