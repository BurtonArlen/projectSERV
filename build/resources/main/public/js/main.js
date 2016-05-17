
$(document).ready(function(){

  $('.glyphicon-pencil').click(function(){
  	$('#main-content').toggleClass('dropdown-effect');
  	$('.dropdown-content').slideToggle(1000);
  	$('svg').fadeToggle(1000);
  });
  

});
