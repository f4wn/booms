<%-- 
    Document   : category
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
        <c:set var="categoryTopNav" value="active" scope="request"/>
        <jsp:include page="topbar.jsp"/>

        <div class="panel callout radius"> 
        <div class="off-canvas-wrap" data-offcanvas> 
            <!-- If the contents of the .inner-wrap isn't the main content of the page, use another approripate sectioning element instead --> 
            <div class="inner-wrap large-4">
                <!-- all content goes here -->
            <form:form method="post" action="addcategory" commandName="category">

                <table>
                <tr>
                    <td><form:label path="categoryName">Name</form:label></td>
                    <td><form:input path="categoryName" />
                        <form:errors path="categoryName" cssClass="error"/>
                    </td> 
                </tr>
                <tr>
                    <td><form:label path="description">Description</form:label></td>
                    <td><form:input path="description" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Add Category" class="button [tiny small large]"/>
                    </td>
                </tr>
            </table>  
            </form:form>
            </div> 
        </div>
        </div>
        <h3>Categories</h3>
        <c:if  test="${!empty categoryList}">
        <table class="data">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${categoryList}" var="category">
            <tr>
                <td>${category.categoryName}</td>
                <td>${category.description}</td>
                <td><a href="editcategory/name=${category.categoryName}">edit</a></td>
                <td><a href="deletecategory/name=${category.categoryName}">delete</a></td>
            </tr>
        </c:forEach>
        </table>
        </c:if>
    </body>
</html>

