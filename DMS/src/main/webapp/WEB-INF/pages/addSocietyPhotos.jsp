<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Society Photos</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  
                   <div class="col-md-12 col-sm-12 col-xs-12">
                    <form id="fileForm">
                    	<div class="col-md-4 col-sm-4 col-xs-4">
	                    	<select name="societyid" id="societyid" class="form-control" onchange="getSocietyPhotos()">
								<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
									<option value="${myItem.societyid}">${myItem.societyname}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-12">
					    <input type="file" name="file" class="form-control btn btn-primary" />
					   
					    </div>
					    <div class="col-md-2 col-sm-2 col-xs-12">
					     <button id="btnUpload" type="button" class="form-control btn btn-success">
					    	 Upload
					     </button>
					    </div>
					</form>
                   </div>
                   
                   <div class="clearfix"></div>
                   <div class="col-md-12 col-sm-12 col-xs-12">
                   <br/>
	                   <div class="row">
	                   		<div id="imgContainer"></div>
	                   </div> 
                   </div>
                   
                   
                  </div>
                </div>
              </div>
              
 </div>
 
 
 <script type="text/javascript">
    
var isJpg = function(name) {
    return name.match(/jpg$/i)
};
    
var isPng = function(name) {
    return name.match(/png$/i)
};
    
$(document).ready(function() {
	getSocietyPhotos();
	
    var file = $('[name="file"]');
    var imgContainer = $('#imgContainer');
    
    $('#btnUpload').on('click', function() {
        var filename = $.trim(file.val());
        
        if (!(isJpg(filename) || isPng(filename))) {
            alert('Please browse a JPG/PNG file to upload ...');
            return;
        }
        blockUI();
        $.ajax({
            url: 'uploadSocietyPhoto.do',
            type: "POST",
            data: new FormData(document.getElementById("fileForm")),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false
          }).done(function(data) {
        	  unblockUI();
              //imgContainer.html('');
              //var img = '<div class="col-md-4 col-sm-4 col-xs-12"><img height="100" width="50" src="data:' + data.contenttype + ';base64,'
                  //+ data.base64 + '"/></div>';
              //imgContainer.append(img);
              getSocietyPhotos();
          }).fail(function(jqXHR, textStatus) {
              alert('File upload failed ...');
              unblockUI();
          });
    });
    
    $('#btnClear').on('click', function() {
        $('#imgContainer').html('');
        file.val('');
    });
});
    
    
    
    function getSocietyPhotos(){
    	
    	var societyid = $('#societyid').val();
    	blockUI();
    	$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getSocietyPhotos.do",
	        data :"societyid="+societyid,
	        enctype: 'multipart/form-data',
	        processData: false,
            contentType: false 
			}).done(function(data) {
			var img='';
			var cnt =1;
               $.each(data, function(i, item) {
               		img += '<div class="col-md-4 col-sm-4 col-xs-12"><img height="150" width="300" src="data:' + item.contenttype + ';base64,' +  item.file + '"/></div>';
               		if(cnt%3==0){
               			img+='<div class="clearfix"></div><br/>';
               		}
               		cnt++;
               });
               $('#imgContainer').html(img);
               unblockUI();
          }).fail(function(jqXHR, textStatus) {
              alert('File Fetch failed ...');
              unblockUI();
          });;
    }
</script>

