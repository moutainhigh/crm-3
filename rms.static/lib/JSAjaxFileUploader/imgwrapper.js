
function dealUpload(imgUrl,obj){
	var html='<li class="uploadAfter">'
				+'<input name="uploadImag" type="hidden" class="uploadImg" value="'+imgUrl+'"></input>'
				+'<div class="imgDiv"><img src="'+imgUrl+'" width="78px" height="78px"><span onclick="removeUploadImg(this)"></span></div>'
			+'</li>';
	var exitsHtml='<input name="uploadImag" type="hidden" class="uploadImg" value="'+imgUrl+'"></input>'
			+'<div class="imgDiv"><img src="'+imgUrl+'" width="78px" height="78px"><span onclick="removeUploadImg(this)"></span></div>';
	var ifra=$("."+obj);
	var simple=ifra.find(".simpleImg");
	if(simple.size()>0){
		var isTrue=false;
		simple.each(function(){
			var that=$(this);
			if(that.find(".imgDiv").size()==0){
					isTrue=true;
					that.addClass("uploadAfter");
					that.html(exitsHtml);	
					return false;
			}
		});
		if(!isTrue){
			ifra.append(html);
		}
	}else {
		ifra.append(html);
	}
 }
 
function removeUploadImg(obj){
	var liObj = $(obj).parent().parent();
	$(liObj).removeClass("uploadAfter");
	$(liObj).html('');
}