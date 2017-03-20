/*first bar topnavbar*/
/*$('#topnavbar').affix({
 offset: {
 top: $('#firstbar').height()
 }   
 });*/

/* Currency input */
//$(document).ready(
/*function() {
 $("#currency_input").inputmask("numeric", {
 radixPoint : ",",
 groupSeparator : ".",
 digits : 2,
 autoGroup : true,
 prefix : '$ ',
 rightAlign : false,
 'placeholder' : '',
 oncleared : function() {
 self.Value('');
 }
 });*/

/*
 * function reload_dashboard() { var url = '/itacon/dashboard';
 * 
 * $("#listupdate").load(url); }
 */

/*
 * function updateList() { var url = '/itacon/customer/updatebalace/';
 * 
 * if ($('#').val() != '') { url = url + '/' + $('#').val(); }
 * 
 * $("#listupdate").load(url); }
 */
//});
$(document).ready(function() {
	$("#register_form").click(function() {
		var url = '/itacon/customers';
		$("#listupdate").load(url);
	});

	$("#update_balance_form").click(function() {
		var url = '/itacon/customers';
		$("#listupdate").load(url);
	});

	$("#teste").click(function() {
		alert("Not implemented yet!");
	});

	$(function() {
		$('#currency_input').inputmask("decimal", {
			radixPoint : ",",
			groupSeparator : ".",
			digits : 2,
			autoGroup : true,
			digitsOptional : false,
			placeholder : '0',
			rightAlign : false
		});
	});
});

function updateCustomersList() {
	var url = '/itacon/customers';
	$("#listupdate").load(url);
}

function findCustomer() {
	
    var url = '/itacon/searchcustomer';
    if ($('#queriedcustomer').val() != '') {
        url = url + '/' + $('#queriedcustomer').val();
    }
    
    $("#listupdate").load(url);
}