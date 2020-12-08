var replyManager = (function(){
	var getAll = function(obj, callback, error){
		console.log("get all....")
		//$.getJSON('/replies/'+obj, callback);
		$.ajax({
			type:"GET",
			url:"/replies/"+obj.bno,
			//data:JSON.stringify(obj),
			contentType:"application/json; charset=utf-8",
			success:function(result, status, xhr){
				if(callback){
					console.log("success");
					callback(result);
				}
			},
			error:function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	};
	
	var add = function(obj, callback, error){
		console.log("add...");
		$.ajax({
			type:"POST",
			url:"/replies/"+obj.bno,
			data:JSON.stringify(obj),
			contentType:"application/json",
			//dataType:"json",
			success:function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	var update = function(obj, callback, error){
		console.log("update...");
		$.ajax({
			type:'put',
			url:'/replies/modify',
			data:JSON.stringify(obj),
			contentType:"application/json",
			//dataType:'json',
			success:function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})
	}
	var remove = function(obj, callback,error){
		console.log("remove...");
		$.ajax({
			type:"delete",
			url:"/replies/delete/"+obj.rno,
			contentType:"application/json",
			//dataType:'json',
			success:function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error:function(xhr, status, er){
				if(error){
					error(er);
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