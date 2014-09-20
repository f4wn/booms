<%-- 
    Document   : header
    Created on : Sep 20, 2014, 6:28:54 PM
    Author     : Fawn
--%>
<nav class="top-bar" data-topbar role="navigation"> 
    <ul class="title-area"> 
        <li class="name"> 
            <h1><a href="#">BOOMS</a></h1> 
        </li> <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone --> 
        <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a>
        </li> 
    </ul> 
    <section class="top-bar-section"> <!-- Right Nav Section --> 
        <!--ul class="right"> 
            <li class="active"><a href="#">Right Button Active</a></li> 
            <li class="has-dropdown"> <a href="#">Right Button Dropdown</a> 
                <ul class="dropdown"> 
                    <li><a href="#">First link in dropdown</a></li> 
                    <li class="active"><a href="#">Active link in dropdown</a></li> 
                </ul> 
            </li> 
        </ul-->
        <!-- Left Nav Section --> 

        <ul class="left"> 
            <li class="${requestScope.bookTopNav}"><a href="/book">Book</a></li>
            <li class="${requestScope.publisherTopNav}"><a href="/publisher">Publisher</a></li> 
            <li class="${requestScope.categoryTopNav}"><a href="/category">Category</a></li> 
        </ul> 
    </section> 
</nav>
