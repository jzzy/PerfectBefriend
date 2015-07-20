// JavaScript Document
$(function(){
	$(".shuru").focus(function(){
		if($(this).val()==this.defaultValue){
			$(this).val("");
			}
		}).blur(function(){
			if($(this).val()==""){
				$(this).val(this.defaultValue)
				
				}
			
		})

/*exchange*/
$(".userlist li:not(':last')").find(".taoluncont").hide()
$(".userlist li:last").addClass("current2");
$(".userlist li").each(function(){
	$(this).find(".tname").click(function(){
	  if($(this).siblings(".taoluncont").is(':visible')){
		 
		$(this).siblings(".taoluncont").hide();
		 $(this).parent("li").removeClass("current2");
			}
		else{
			$(this).siblings(".taoluncont").show();
			 $(this).parent("li").addClass("current2");
			 
			}
			return false;
	
	
	})
})
$(".city2:not(':first')").siblings(".taolun").hide()

$(".city2:first").addClass("current3");
$(".city").each(function(){
	$(this).find(".city2").click(function(){
	  if($(this).siblings(".taolun").is(':visible')){
		 
		$(this).siblings(".taolun").hide();
		 $(this).removeClass("current3");
			}
		else{
			$(this).siblings(".taolun").show();
			 $(this).addClass("current3");
			 
			}
			return false;
	
	
	})
})
/*window*/
$(".close2").click(function(){
$("#window").fadeOut("slow");	
 
})
 $(".after").click(function(){
$("#window").fadeOut("slow");	
 
})  
})


