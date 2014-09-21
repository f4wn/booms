<%-- 
    Document   : header
    Created on : Sep 20, 2014, 6:28:54 PM
    Author     : Fawn
--%>
<nav class="top-bar" data-topbar role="navigation"> 
    <ul class="title-area"> 
        <li class="name"> 
            <h1><a href="/">BOOMS</a></h1> 
        </li> <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone --> 
        <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a>
        </li> 
    </ul> 
    <section class="top-bar-section">
        <ul class="left"> 
            <li class="${requestScope.categoryTopNav}"><a href="/category">Category</a></li> 
            <li class="${requestScope.publisherTopNav}"><a href="/publisher">Publisher</a></li> 
            <li class="${requestScope.bookTopNav}"><a href="/book">Book</a></li>
        </ul> 
    </section> 
</nav>
