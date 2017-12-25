function addUploadImgUrl(imgUrl,submitInput){
	var exists = $("#"+submitInput).val();
	if(exists == undefined){
		exists='';
	}
	exists = exists + ',' + imgUrl;
	$("#"+submitInput).val(exists);
}

function dealUpload(imgUrl,obj,submitInput){
	addUploadImgUrl(imgUrl, submitInput);
	var html='<li class="uploadAfter">'
				+'<div class="imgDiv"><img src="'+imgUrl+'" width="78px" height="78px"><span onclick="removeUploadImg(this,\'' +submitInput +'\')"></span></div>'
			+'</li>';
	var exitsHtml='<div class="imgDiv"><img src="'+imgUrl+'" width="78px" height="78px"><span onclick="removeUploadImg(this,\'' +submitInput +'\')"></span></div>';
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
 
function removeUploadImg(obj,submitInput){
	removeUploadImgUrl(obj,submitInput);
	var liObj = $(obj).parent().parent();
	$(liObj).removeClass("uploadAfter");
	$(liObj).html('');
}
function removeUploadImgUrl(obj,submitInput){
	var imgDivObj = $(obj).parent();
	var imgObj = $("img",imgDivObj);
	var srcUrl = $(imgObj).attr("src");
	var exists = $("#"+submitInput).val();
	exists = exists.replace(","+srcUrl, "");
	$("#"+submitInput).val(exists);
}