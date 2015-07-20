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
/*bbs*/
$(".huifu2").each(function(){
	$(this).find(".hui:last").addClass("border0")
 })
 
/* $(".htan").each(function(){
	 $(this).click(function(){
   $(".pingnum2").show();
   
  })

  $(".btijiao").click(function(){
	   $(".pingnum2").hide(); 
	 })
 })*/

})



/*$(function(){
 $(".huiff").each(function(){
	$(this).click(function(){
    $(this).parents(".huifu2").append("<div style=\"padding:10px 0px 10px 10px; margin-top:10px;\"><input type='text' value=\"说两句\" style=\"width:200px; height:30px; border:solid 1px #CCC; float:left; margin-right:10px; color:#999; padding-left:5px\" /><input type=\"button\" value=\"提交\" style=\"border:0;float:left; color:#FFF; border:solid 1px #ccc; border-radius:5px; height:35px; width:90px; text-align:center; line-height:35px; background:#426ab3;\"/></div>");
	
  }) 
 })	
})*/
$(function(){
	/*$html="<div style=\"padding:10px 0px 10px 10px; margin-top:10px;\"><input type='text' value=\"说两句\" style=\"width:200px; height:30px; border:solid 1px #CCC; float:left; margin-right:10px; color:#999; padding-left:5px\" /><input type=\"button\" value=\"提交\" style=\"border:0;float:left; color:#FFF; border:solid 1px #ccc; border-radius:5px; height:35px; width:90px; text-align:center; line-height:35px; background:#426ab3;\" id='btijiao' /></div>"

 $(".huiff").bind('click', function() {
       $(this).unbind('click');
       $(this).parents(".huifu2").append($html);
 	$("#btijiao").click(function(){
	   $html.remove();
	 })
		
 });
 $(".htan").each(function(){
	$(this).bind('click', function() {
       $(this).unbind('click');
       $(this).parent(".hui").append($html);
	   $(this).parent(".hui").siblings(".hui").remove($html)
	   
  })
})
 */
 
 
  $(".pingnum2").hide()
 $(".huiff").each(function(){
	 $(this).click(function(){
		 $(this).parent(".bbsd").siblings(".pingnum2").show().parent(".huifu2").find(".hui").children(".pingnum2").hide();
	})
	
	})
 $(".btijiao").click(function(){
	   $(".pingnum2").hide(); 
	 })	
	 
 $(".htan").each(function(){
   $(this).click(function(){
   $(this).siblings(".pingnum2").show().parent(".hui").siblings(".hui").find(".pingnum2").hide();
   $(this).parents(".huifu2").children(".pingnum2").hide();
    $(this).parents(".huifu2").siblings(".huifu2").find(".pingnum2").hide();
	 })
  })
  $(".btijiao2").click(function(){
	   $(".pingnum2").hide(); 
	 })
})