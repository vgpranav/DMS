<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Add Vendor Card</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                  <div class="col-md-4 col-sm-4 col-xs-12">
		                  <div class="row">
		                   <div class="col-md-12 col-sm-12 col-xs-12">
		                    <form id="fileForm" class="form-horizontal form-label-left">
		                     
		                    <div class="form-group">
		                        <label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="first-name">Society Name <span class="required">*</span>
								</label>
		                    	<div class="col-md-8 col-sm-8 col-xs-12">
			                    	<select name="societyid" id="societyid" class="form-control " onchange="getVendorsBySocId()">
			                    	<option value=""> -- select -- </option>
										<c:forEach items="${societyList}" var="myItem" varStatus="loopStatus">
											<option value="${myItem.societyid}">${myItem.societyname}</option>
										</c:forEach>
									</select>
								</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12"
										for="first-name">Select Vendor
									</label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										<select name="vendorid" id="vendorid" class="form-control" onchange="getVendorPhotos()">
											<option value=""> -- select -- </option>
										</select>
									</div>
								</div>
								
								
								<div class="form-group">
									<label class="control-label col-md-4 col-sm-4 col-xs-12"
										for="first-name">Select File <span class="required">*</span>
									</label>
									<div class="col-md-8 col-sm-8 col-xs-12">
										 	<input type="file" name="file"   />
									</div> 
								</div>
								
								 
							    <div class="form-group" align="center">
							     <button id="btnUpload" type="button" class="btn btn-primary">
							    	 Upload
							     </button>
							    </div>
							    
							</form>
		                   </div>
		                   </div>
                   </div>
                   
                   
                   <div class="col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-12" >
	                   <div class="row">
	                   		<div id="imgContainer" align="right"></div>
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
	//getSocietyPhotos();
	
    var file = $('[name="file"]');
    var imgContainer = $('#imgContainer');
    
    $('#btnUpload').on('click', function() {
        var filename = $.trim(file.val());
        
        if (!(isJpg(filename) || isPng(filename))) {
            alert('Please browse a JPG/PNG file to upload ...');
            return;
        }
        
        $.ajax({
            url: 'uploadVendorPhoto.do',
            type: "POST",
            data: new FormData(document.getElementById("fileForm")),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false
          }).done(function(data) {
              //imgContainer.html('');
              //var img = '<div class="col-md-4 col-sm-4 col-xs-12"><img height="100" width="50" src="data:' + data.contenttype + ';base64,'
                  //+ data.base64 + '"/></div>';
              //imgContainer.append(img);
              getVendorPhotos();
          }).fail(function(jqXHR, textStatus) {
              alert('File upload failed ...');
          });
    });
    
    $('#btnClear').on('click', function() {
        $('#imgContainer').html('');
        file.val('');
    });
    
    $("#username").click(function(){
		//$('#userid').val("");
			if($('#societyid').val().length<1)
				{ alert('Select Society');
					$('#societyid').focus();
				}
		});
	
	$( "#username").autocomplete({
		 source: function (request, response) {
	            $.getJSON("${pageContext. request. contextPath}/userAutosuggestForSociety.do", {
	            	searchText: request.term,
	            	societyid: $('#societyid').val(),
	            }, response);
	        },
		focus: function () {
	       // prevent value inserted on focus
	       return false;
	   },
	   select: function (event, ui) {
	       var thisValue = ui.item.value;
	       var str = thisValue.split("--");
	       for (var i = 0; i < str.length; i++) {		       	
	    	   $('#userid').val(str[0]); 
	       	   ui.item.value=str[1]; 
	       }
	       getSocietyPhotos();
		}
	});
	
});
    
    
    
    function getVendorPhotos(){
    	
    	var vendorid = $('#vendorid').val();
    	
    	$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getVendorPhotos.do",
	        data :"vendorid="+vendorid,
	        enctype: 'multipart/form-data',
	        processData: false,
            contentType: false 
			}).done(function(data) {
			var img='';
			var cnt =1;
               $.each(data, function(i, item) {
               		img += '<div class="col-md-4 col-sm-4 col-xs-12"><img height="350"  src="data:' + item.contenttype + ';base64,' +  item.file + '"/></div>';
               		if(cnt%3==0){
               			img+='<div class="clearfix"></div><br/>';
               		}
               		cnt++;
               });
               $('#imgContainer').html(img);
          }).fail(function(jqXHR, textStatus) {
              alert('File Fetch failed ...');
          });;
    }
    
    
    function getVendorsBySocId(){
		var societyid = $('#societyid').val();
		var table = $('#thetable').DataTable();
			
		table .clear() .draw();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getVendorsBySocId.do",
	        data :"societyid="+societyid,
	        success: function(response){ 
        		var $select = $('#vendorid');                        
        	    $select.find('option').remove(); 
        	    $select.append($("<option />").val('').text(' -- select --'));
        	    $.each(response, function() {
        	    	$select.append($("<option />").val(this.vendorid).text(this.companyname));
        	    });
       		 },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
</script>

