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
            <form:form method="post" action="/editcategory" commandName="category">

                <table>
                <tr>
                    <td><form:label path="categoryName">Name</form:label></td>
                    <td><form:input path="categoryName" readonly="true"/></td> 
                </tr>
                <tr>
                    <td><form:label path="description">Description</form:label></td>
                    <td><form:input path="description" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Update Category" class="button [tiny small large]"/>
                    </td>
                </tr>
            </table>  
            </form:form>
            </div>
        </div>
        </div>
    </body>
</html>

