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
//		console.log("headerName: "+obj.csrfHeaderName)
//		console.log("token: "+obj.csrfTokenValue);
//		console.log("test headerName:"+obj.csrf.headerName);
//		console.log("test tokenValue:"+obj.csrf.token);
		$.ajax({
			type:"POST",//method
			url:"/replies/"+obj.bno,//서버 URL
			data:JSON.stringify(obj),//서버에 보내는 데이터
			contentType:"application/json",//서버에 데이터를 보낼 때 데이터 형식 지정
			//dataType:"json",//서버에서 반환되는 데이터 형식을 지정
			beforeSend:function(xhr){
				xhr.setRequestHeader(obj.csrfHeaderName, obj.csrfTokenValue);
			},
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
			beforeSend:function(xhr){
				xhr.setRequestHeader(obj.csrfHeaderName, obj.csrfTokenValue);
			},
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
			beforeSend:function(xhr){
				xhr.setRequestHeader(obj.csrfHeaderName, obj.csrfTokenValue);
			},
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