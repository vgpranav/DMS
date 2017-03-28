<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<input type="hidden" name="societyid" id="societyid" value="${societyid}">
<input type="hidden" name="doctypeid" id="doctypeid" value="${doctypeid}">
<input type="hidden" name="docsubtypeid" id="docsubtypeid" value="${docsubtypeid}">



<div class="col-md-12 col-sm-12 col-xs-12">
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Documents</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li> 
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <div class="dashboard-widget-content">
                    <div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action" id="thetable">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">Document Description</th>
                            <th class="column-title">Document Type</th>
                            <th class="column-title">Document Sub Type</th>
                            <th class="column-title" width="10%">CreatedBy </th>
                            <th class="column-title" width="10%">CreatedOn </th>
                            <th class="column-title" width="8%">Download </th>
                            <th class="column-title no-link last" width="8%">
                            	<span class="nobr">View</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
 </div>
 

<script>
	$(document).ready(function(){
		$('#thetable').DataTable();
		
		getDocumentListForView();
		
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
			}
		});
		
	});

	function showhide(id){
		$('#'+id).toggle();
	}
	
	function getDocumentListForView(){
		var societyid = $('#societyid').val();
		var doctypeid = $('#doctypeid').val();
		var docsubtypeid = $('#docsubtypeid').val();
		var userid = "";
		
		if(doctypeid.length<1)
			doctypeid="0"
			
				if(docsubtypeid.length<1)
					docsubtypeid="0"
					
						if(userid.length<1)
							userid="0"
		
		if(societyid.length<1)
			return false;
		
		var table = $('#thetable').DataTable();
			
		table .clear() .draw();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDocumentListForView.do",
	        data : "societyid="+societyid
	        	+"&doctypeid="+doctypeid
	        	+"&docsubtypeid="+docsubtypeid
	        	+"&userid="+userid,
	        success: function(response){
	        	$.each(response, function(i, item) {
	  
	        		var desc = item.description.split(','); //.join('<br>');
	        		var descTemp = item.description.split(',');
	        		descTemp[0]='';
	        		var fullText = descTemp.join('<br>');
	        		var text = desc[0]+" <a href='#!' onclick=showhide('more"+i+"')><span id='sh"+i+"'>[more]</span></a><br><div id='more"+i+"' style='display:none'>"+fullText+"</div>";
	        		
	        		var viewBtn = '<a target="_blank" class="btn btn-default btn-sm" href="displayDocumentFromSearch.do?documentid='+item.documentid+'"><i class="fa fa-search"></i></a>';
	        		var downloadBtn = '<a class="btn btn-default btn-sm" target="_blank" href="downloadAsPdf.do?documentId='+item.documentid+'"><i class="fa fa-cloud-download"></i></a>';
	        		//var ttipbtn = '<button data-toggle="tooltip" data-placement="left" title="'+text+'">Hii</button>';
	        		//item.documentid
	        		table.row.add( [
	        			text, 
	        			item.doctypename,
	        			item.docsubtypename,
	        			item.createdby, 
	        			new Date(item.createdon).toString("dd MMM yyyy"),
	        			downloadBtn,
	        			viewBtn,
	                ] ).draw( false );
	        	    
	        	  });
	        	
	        	// $('[data-toggle="tooltip"]').tooltip({ container: 'body'  });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
	
	function getDocTypes(){
		var societyid = $('#societyid').val();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDoctypeBySocId.do",
	        data :"societyid="+societyid,
	        success: function(response){ 
	        		var $select = $('#doctypeid');                        
	        	    $select.find('option').remove(); 
	        	    $select.append($("<option />").val('').text(' -- select --'));
	        	    $.each(response, function() {
	        	    	$select.append($("<option />").val(this.doctypeid).text(this.doctypename));
	        	    });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
	function getDocSubTypes(){
		var doctypeid = $('#doctypeid').val();
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/getDocSubtypeByDocId.do",
	        data :"doctypeid="+doctypeid,
	        success: function(response){ 
	        		var $select = $('#docsubtypeid');                        
	        	    $select.find('option').remove(); 
	        	    $select.append($("<option />").val('').text(' -- select --'));
	        	    $.each(response, function() {
	        	    	$select.append($("<option />").val(this.docsubtypeid).text(this.docsubtypename));
	        	    });
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
	}
	
	
	function saveFormFields(){
		
		var docsubtypeid = $('#docsubtypeid').val();
		var fieldname = $('#fieldname').val();
		var datatype = $('#datatype').val();
		var sequence = $('#sequence').val();
		var fieldtype = $('#fieldtype:checked').val();
		var active = $('#active:checked').val();
		
		$.ajax({
	        type: "GET",
	        url: "<%=request.getContextPath()%>/saveFormFields.do",
	        data :"docsubtypeid="+docsubtypeid
			        +"&fieldname="+fieldname
			        +"&datatype="+datatype
			        +"&sequence="+sequence
			        +"&fieldtype="+fieldtype
			        +"&active="+active,
	        success: function(response){
	        	if(response.fieldid>0) {
	        		getFieldsForDocSubtype();
	        		notify('success','SUCCESS','Added Successfully',2000);
	        	}  
	        },
				error : function(e) {
					notify('error','ERROR','Error occured',2000);
				}
			});
		
		return false;
	}
</script>