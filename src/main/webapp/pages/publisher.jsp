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
        <c:set var="publisherTopNav" value="active" scope="request"/>
        <jsp:include page="topbar.jsp"/>
 
        <div class="panel callout radius"> 
        <div class="off-canvas-wrap" data-offcanvas> 
            <!-- If the contents of the .inner-wrap isn't the main content of the page, use another approripate sectioning element instead --> 
            <div class="inner-wrap large-4">
                <!-- all content goes here -->
            <form:form method="post" action="addpublisher" commandName="publisher">
                <table>
                <tr>
                    <td><form:label path="publisherName">Name</form:label></td>
                    <td><form:input path="publisherName" />
                        <form:errors path="publisherName" cssClass="error"/>
                    </td> 
                </tr>
                <tr>
                    <td><form:label path="location">Location</form:label></td>
                    <td><form:input path="location" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Add Publisher" class="button [tiny small large]"/>
                    </td>
                </tr>
            </table>  
            </form:form>
            </div>
        </div>
        </div>
        <div>
        <h3>Publishers</h3>
        </div>
        <c:if  test="${!empty publisherList}">
        <table class="data">
        <tr>
            <th>Name</th>
            <th>Location</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${publisherList}" var="publisher">
            <tr>
                <td>${publisher.publisherName}</td>
                <td>${publisher.location}</td>
                <td><a href="editpublisher/pubid=${publisher.id}">edit</a></td>
                <td><a href="deletepublisher/pubid=${publisher.id}">delete</a></td>
            </tr>
        </c:forEach>
        </table>
        </c:if>
    </body>
</html>

