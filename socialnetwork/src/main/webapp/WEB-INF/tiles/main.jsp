<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Mini Social</title>
<link rel="shortcut icon" href="static/images/icon.ico" type="image/x-icon"/>
<link rel="icon" href="static/images/icon.ico" type="image/x-icon"/>
<meta name="keywords" content="mini social, free download, website templates, CSS, HTML" />
<meta name="description" content="Mini Social is a free website template from templatemo.com" />
<link href="static/css/templatemo_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="static/css/coda-slider.css" type="text/css" media="screen" charset="utf-8" />

<script src="static/js/jquery-1.2.6.js" type="text/javascript"><jsp:text/></script>
<script src="static/js/jquery.scrollTo-1.3.3.js" type="text/javascript"><jsp:text/></script>
<script src="static/js/jquery.localscroll-1.2.5.js" type="text/javascript" charset="utf-8"><jsp:text/></script>
<script src="static/js/jquery.serialScroll-1.2.1.js" type="text/javascript" charset="utf-8"><jsp:text/></script>
<script src="static/js/coda-slider.js" type="text/javascript" charset="utf-8"><jsp:text/></script>
<script src="static/js/jquery.easing.1.3.js" type="text/javascript" charset="utf-8"><jsp:text/></script>

</head>
<body>

<div id="slider">
	
    <div id="templatemo_sidebar">
    	<div id="templatemo_header">
        	<a href="/socialnetwork/Controller" target="_parent"><img src="${pageContext.request.contextPath}/static/images/templatemo_logo.png" alt="Mini Social" /></a>
        </div> <!-- end of header -->
        
        <ul class="navigation">
            <li>                      
            <form id="homeForm" action="/socialnetwork/Controller" method="post">				
				<input type="hidden" name="command" value ="home"/>
				<a href="#home" onclick="$(this).parent().submit()">Home<span class="ui_icon home"></span></a>
			</form>            
            </li>
            <li>
            <form id="friendsForm" action="/socialnetwork/Controller" method="post">				
				<input type="hidden" name="command" value ="friends"/>
				<a href="#friends" onclick="$(this).parent().submit()">Friends<span class="ui_icon friends"></span></a>
			</form>
			</li>
			<li>
			 <form id="friendsForm" action="/socialnetwork/Controller" method="post">				
				<input type="hidden" name="command" value ="messages"/>
				<a href="#messages" onclick="$(this).parent().submit()">Messages<span class="ui_icon messages"></span></a>
			</form>
			</li>
            <li>
             <form id="friendsForm" action="/socialnetwork/Controller" method="post">				
				<input type="hidden" name="command" value ="groups"/>
				<a href="#groups" onclick="$(this).parent().submit()">Groups<span class="ui_icon groups"></span></a>
			</form>
			</li> 
            <li><form id="friendsForm" action="/socialnetwork/Controller" method="post">				
				<input type="hidden" name="command" value ="support"/>
				<a href="#support" onclick="$(this).parent().submit()">Support<span class="ui_icon support"></span></a>
			</form>
			</li>           
        </ul>
    </div> <!-- end of sidebar -->

	<div id="templatemo_main">
    	<ul id="social_box">
            <li><a href="/socialnetwork/Controller"><img src="${pageContext.request.contextPath}/static/images/facebook.png" alt="facebook" /></a></li>
            <li><a href="/socialnetwork/Controller"><img src="${pageContext.request.contextPath}/static/images/twitter.png" alt="twitter" /></a></li>
            <li><a href="/socialnetwork/Controller"><img src="${pageContext.request.contextPath}/static/images/linkedin.png" alt="linkin" /></a></li>
            <li><a href="/socialnetwork/Controller"><img src="${pageContext.request.contextPath}/static/images/technorati.png" alt="technorati" /></a></li>
            <li><a href="/socialnetwork/Controller"><img src="${pageContext.request.contextPath}/static/images/myspace.png" alt="myspace" /></a></li>                
        </ul>
        
        <div id="content">
        
        <!-- scroll -->
        <div class="scroll">
                <div class="scrollContainer" id = "scrollContainer">
        			<tiles:insertAttribute name="body"/>
        		</div>
        </div>
          
        <div id="templatemo_footer">

           
        </div>
    
    </div> 
</div>
</div>
</body>
</html>