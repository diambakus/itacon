function content_change() {
	document.getElementById('content_area').innerHTML = document
			.getElementById(location.hash.substring(1, location.hash.length)).innerHTML;
}



$(document).ready(function() {
	
	window.onhashchange=content_change;
	
	if (window.location.hash=='') {
		window.location.replace('#welcome');
	}
	
	setTimeout("content_change()", 0);
});
