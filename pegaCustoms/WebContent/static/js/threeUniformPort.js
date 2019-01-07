
function fitCoulms(){
//	$("#table_dg").datagrid({
//		fitColumns:true
//	})
}

function formSubmit(){
	if($("#post").val()==''){
		alert("请选择岗位信息！！！");
		return false;
	}
	if($("input:radio[name='roleArea']:checked").val()==null){
		alert("请先选择关区信息！！！");
		return false;
	}
	if($("input:checkbox[name='role_cast']:checked").val()==null){
		alert("请先选择关区信息！！！");
		return false;
	}
	var targetUrl = $("#form").attr("action");
	var path=$("#path").val();
	var info=isInfo(targetUrl);
	if(info!=''){
		alert(info);
		return false;
	}
	var data = $("#form").serialize();
     $.ajax({
    	 type:'post',
    	 url:targetUrl,
    	 cache:false,
    	 data:data,
    	 dataType:'json',
    	 async:false,
    	 success:function(data){ 
 			if(data!=null){
 			     var contentPage='';
 				contentPage =
 					"<a href=javascript:getPage(1,'"+path+"')>首页</a>" 
 					+"<a href=javascript:getPage("+1+",'+"+path+"+')>上一页</a> "
 					+"<span>当前页:"+data.page+" </span>"
 					+"<a href=javascript:getPage("+2+",'"+path+"')>下一页</a>" 
 					+"<a href=javascript:getPage("+data.endPage+",'"+path+"')>尾页</a>"
 					+"共计: <span>"+data.endPage+" </span>页—<span>"+data.sumRows+" </span>条";	
 					 $("#pageUtil").html("");
 					 $("#pageUtil").append(contentPage);
 				if(data.t.post!=null){
					if(data.t.post=='0'){
						$("#item1").prop('checked','checked');
				}
					if(data.t.post=='1'){ 	
 						$("#item2").prop('checked','checked');
					}
 				}	 
 			}		
 			if(null!=data.list){
 		     var content='';
 			for(var i=0;i<data.list.length;i++){
 				 var imexStatusName;			    
 	    		 if(data.list[i].post=='0'){
 	    			 imexStatusName = "申报地";
 	    		 }else if(data.list[i].post=='1'){
 	    			 imexStatusName = "查验地"
 	    		 }	 
 	    		 else{
 	    			 imexStatusName="";
 	    		 } 
 	    		 var remark='';
 	    		 if(data.list[i].remark!=null){
 	    			 remark=data.list[i].remark
 	    		 }
 				 content += "<tr>"
 			         +"<td>"+i+"</td>"
 			         +"<td>"+imexStatusName +"</td>"
 			         +"<td>"+data.list[i].roleAreaName+"</td>"
 			         +"<td>"+data.list[i].roleRaeaCast +"</td>"
 			         +"<td>"+remark+"</td>"
 			         +"<td style='text-align:center;'><a href='javascript:deleteInfo("+data.list[i].id+")' style='text-decoration: none;'><font color='red'>X</font></a></td>"
 		             +"</tr>"
 				
 			}
 			 $("#tbodyB").html("");
 			 $("#tbodyB").append(content);			 
 			}else{
 			 $("#tbodyB").html("");
 			}
 		},
    	 error:function(result){
    		 alert("请求失败！！！");
    	 }
     });
     
 	$("input[type=checkbox]").attr("checked",false);
 	$("#RoleTR input[type=radio]").attr("checked",false);
 	$("#remark").val("");
}


function deleteInfo(id){
	var path=$("#path").val();
	if(id ==''|| id ==null) return ;
	if(confirm("是否删除！！！")) {		
	}else{
		return ;
	}
	var post=$("#post").val();
	$.ajax({
		url:path+"/maintenance/delete?id="+id+"&post="+post,
		//url:"http://localhost:8080/pegaCustoms/maintenance/delete",
		// data:id,
		//data:{ids:ids},
		type:'post',
		cache:false,
		dataType:'json',
		traditional:true,
		success:function(data){
			if(data!=null){
			     var contentPage='';
				contentPage =
					"<a href=javascript:getPage(1,'"+path+"')>首页</a>" 
					+"<a href=javascript:getPage("+1+",'+"+path+"+')>上一页</a> "
					+"<span>当前页:"+data.page+" </span>"
					+"<a href=javascript:getPage("+2+",'"+path+"')>下一页</a>" 
					+"<a href=javascript:getPage("+data.endPage+",'"+path+"')>尾页</a>"
					+"共计: <span>"+data.endPage+" </span>页—<span>"+data.sumRows+" </span>条";	
					 $("#pageUtil").html("");
					 $("#pageUtil").append(contentPage);
			   	if(data.t.post!=null){
				if(data.t.post=='0') {
						$("#item1").prop('checked','checked');
						}
				if(data.t.post=='1') { 	
		 				$("#item2").prop('checked','checked');
						}
		 				}	 
			}		
			if(null!=data.list){
		     var content='';

			for(var i=0;i<data.list.length;i++){
				 var imexStatusName;			    
	    		 if(data.list[i].post=='0'){
	    			 imexStatusName = "申报地";
	    		 }else if(data.list[i].post=='1'){
	    			 imexStatusName = "查验地"
	    		 }	 
	    		 else{
	    			 imexStatusName="";
	    		 } 
	    		 var remark='';
	    		 if(data.list[i].remark!=null){
	    			 remark=data.list[i].remark
	    		 }
				 content += "<tr>"
			         +"<td>"+i+"</td>"
			         +"<td>"+imexStatusName +"</td>"
			         +"<td>"+data.list[i].roleAreaName+"</td>"
			         +"<td>"+data.list[i].roleRaeaCast +"</td>"
			         +"<td>"+remark+"</td>"
			         +"<td style='text-align:center;'><a href='javascript:deleteInfo("+data.list[i].id+")' style='text-decoration: none;'><font color='red'>X</font></a></td>"
		             +"</tr>"
				
			}
			 $("#tbodyB").html("");
			 $("#tbodyB").append(content);			 
			}else{
			 $("#tbodyB").html("");
			}
		},
		error:function(error){
		  alert("删除失败！！！");
		}	
		
	}) 
	
}

function jsonData(){
	$.ajax({
		url:"http://localhost:8080/pegaCustoms/maintenance/isRepetition",
		contenType:'application/json;charset=utf-8',
		data:'{"name":"贾晓东"，"age":"18"}',
		type:'post',
		sucess:function(data){
			alert("1111");
			alert(data);
		},
		error:function(error){
			alert(111);
			alert(error);
		}	
	}) 
}

function isInfo(){
	var info='';
	var path=$("#path").val();
	var targetUrl = path+"/maintenance/isRepetition";
	var data = $("#form").serialize();
	 $.ajax({
    	 type:'post',
    	 url:targetUrl,
    	 cache:false,
    	 data:data,
    	 async:false,
    	 success:function(data){
                  info=data;
    	 },
    	 error:function(result){
    		 alert("请求失败！！！");
    	 }
     });
	 return info;
}
function reset(){
	$("input[type=checkbox]").attr("checked",false);
	$("input[type=radio]").attr("checked",false);
	$("#remark").val("");
}
function changePost(value){
//	$("#post").val(value);
//	if(value=='0'){
//		$("#item1").prop('checked','checked');
//	}
//	if(value=='1'){ 	
//		$("#item2").prop('checked','checked');
//	}
	var path=$("#path").val();
    location.href=path+"/maintenance/list?post="+value;

}

function getPage(page,path){
    location.href=path+"/maintenance/list?page="+page;
}






