$(function(){
		$(".nav1 li a").click(function(){
		$(this).siblings().slideToggle(500);
		$(this).addClass("show1").parent().siblings().children("a").removeClass("show1");
		$(this).parent().siblings().children(".nav2").hide(500);
		
		Hui_admin_tab(this);
	});
	$(".nav2 li a").click(function(){
		$(this).siblings().show();
		$(this).addClass("show2").parent().siblings().children("a").removeClass("show2");
		$(this).parent().siblings().children(".nav3").hide();
		
		Hui_admin_tab(this);
	});
	$(".nav3 li a").click(function(){
		$(this).addClass("show3").parent().siblings().children("a").removeClass("show3");
		
		Hui_admin_tab(this);
	});
})