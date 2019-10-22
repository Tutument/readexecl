function exportFile(){
    var jkh = $("#jkhh").val();
    var jkm = $("#jkmm").val();
    var form=$("<form>");
    form.attr("style","display:none");
    form.attr("target","");
    form.attr("method","post");//提交方式为post
    form.attr("action","/downloadFile?jkh="+jkh+"&jkm="+jkm);//定义action

    $("body").append(form);
    form.submit();
}
function upload() {
    //mydata = document.getElementById("mydata");
    formData = new FormData();
   // formData.append("mydata", mydata);
    alert("haha");
    $.ajax({
        contentType:"multipart/form-data",
        url:"/uploadFile",
        type:"POST",
        data:$('#myfile1').val(),
        dataType:"text",
        processData: false, // 告诉jQuery不要去处理发送的数据
        contentType: false, // 告诉jQuery不要去设置Content-Type请求头
        success: function(result){
            alert(result);
        }
    });
}