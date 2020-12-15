var replyManager = (function(){
	
	var getAll = function(obj, callback, error){
		console.log("get All....");
		$.ajax({
			type:"GET",
			url:"/replies/"+obj.bno+"/"+obj.page,
			//data:JSON.stringify(obj),
			contentType:"application/json; charset=utf-8",
			success:function(result, status, xhr){
				if(callback){
					callback(result.cnt, result.list);
				}
			},
			error:function(xhr, status, er){
				if(error){
					error(er)
				}
			}
		
		});
	}
	
	var add = function(obj, callback){
		console.log("add....");
		//var reply={replyText:obj.replyText, replyer:obj.replyer};
		$.ajax({
			type:"POST",
			url:"/replies/"+obj.bno,
			data:JSON.stringify(obj),
			contentType:"application/json; charset=utf-8",
			success:function(result, status, xhr){
				if(callback){
					callback(result);
				}
			}
		
		});
	}
	
	var update = function(obj, callback){
		console.log("update....");
		$.ajax({
			type:"put",
			url:"/replies/"+obj.bno,
			data:JSON.stringify(obj),
			//dataType:"json",
			contentType:"application/json",
			success:function(result){
				console.log("reslut: " + result);
				if(callback){
					callback(result);
				}
			}
		});
	}
	
	var remove = function(obj, callback){
		console.log("remove....");
		$.ajax({
			type:"delete",
			url:"/replies/"+obj.rno,
			//dataType:"json",
			contentType:"application/json",
			success:function(result){
				//console.log("result: " + result); 
				if(callback){
					callback(result);
				}
			}
		});
		
	}
	
	return{
		getAll:getAll,
		add:add,
		update:update,
		remove:remove
	}
	
})();