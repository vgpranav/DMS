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
                   
                   <div class="col-md-7 col-sm-7 col-xs-12">
                   		<div id="imgContainer"></div>
                   </div>
                   <div class="col-md-5 col-sm-5 col-xs-12">
                    <form id="fileForm">
                    	<select name="societyid" id="societyid" class="form-control" onchange="getDocTypes()">
							<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
								<option value="${myItem.societyid}">${myItem.societyname}</option>
							</c:forEach>
						</select>
					    <input type="file" name="file" />
					    <button id="btnUpload" type="button">Upload file</button>
					</form>
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
        
        $.ajax({
            url: 'uploadSocietyPhoto.do',
            type: "POST",
            data: new FormData(document.getElementById("fileForm")),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false
          }).done(function(data) {
              //imgContainer.html('');
              var img = '<img height="100" width="50" src="data:' + data.contenttype + ';base64,'
                  + data.base64 + '"/>';
              imgContainer.append(img);
          }).fail(function(jqXHR, textStatus) {
              alert('File upload failed ...');
          });
    });
    
    $('#btnClear').on('click', function() {
        imgContainer.html('');
        file.val('');
    });
});
    
    
    function getSocietyPhotos(){
    	
    	var societyid = $('#societyid').val();
    	
    	$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getSocietyPhotos.do",
	        data :"societyid="+societyid,
	        success: function(response){
	        	if(response!=null) {
	        		$.each(response, function(i, item) { 
	        			var img = '<img height="100" width="50" src="data:' + item.contenttype + ';base64,'
	                    + item.file + '"/>';
	        			imgContainer.append(img);
	        		});
	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
    	
    	/* $.ajax({
            url: 'getSocietyPhotos.do',
            type: "POST",
            data: "societyid="+societyid,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false
          }).done(function(data) {
              //imgContainer.html('');
              var img = '<img height="100" width="50" src="data:' + data.contenttype + ';base64,'
                  + data.base64 + '"/>';
              imgContainer.append(img);
          }).fail(function(jqXHR, textStatus) {
              alert('File upload failed ...');
          }); */
    }
</script>