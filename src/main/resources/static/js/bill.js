$(document).ready(function () {
	  var contributors = [];
	  $("#add-contributor").on("click", function (event) {
		   event.preventDefault();
		   var name = $("#bill-contributor").val();
		   addContributor(name);
		   $("#bill-contributor").val("");
		   $("#bill-contributor").focus();
		   
		   function addContributor(name) {
			    $("#contributors-table tbody").append(
			    		'<tr>'+
			    			'<td id="contributor-name" style="text-align:center">' +
			    			name +
			    			'</td>' +
			    			'<td style="text-align:center">' +
			    				'<span><button type="button" class="btn btn-danger"><i class="far fa-trash-alt"></i></button></span>' +
			    			'</td>' +
			            '</tr>'      
			    );
			    
			    // Item Delete Button (table) - removes an item from the table
			    $("#contributors-table").on("click", "span", function (event) {
			      $(this)
			        .parent()
			        .parent()
			        .fadeOut(200, function () {
			          $(this).remove();
			        });
			      
			      event.stopPropagation();
			    });
			  }
		});	       
	  
     	$("#save-contributors").on("click", function (event) {
     	   event.preventDefault();
     	    $("#contributors-table table > tbody  > tr").each(function () {
     	      var name = $(this).find("#contributor-name").text();
     	      contributors.push(name);
     	    });
     	    contributors.forEach(addContributorPill);

     	 function addContributorPill(name) {
     		    $("#contributors-list").append(
     	        '<span class="ml-2 badge badge-pill badge-name">' +
     	       	name + 
     	      '</span>'
     	        
     		    );
     		  }
     	});
	  
  // Select Dropdown Box - populates with receipts from database
  $.ajax({
    url: "http://localhost:8080/api/v1/receipt/",
    success: function (response) {
      response.forEach((element) => {
        $("select").append(
          '<option value="' + element.id + '">' + element.name + "</option>"
        );
      });
    },
    error: function (response) {
      console.log(response);
    },
  });

  // Select Dropdown Box (on change) - fills the page with selected receipt data
  $("#receipt-select").on("change", function () {
    $("#receipt-table tbody").empty();
    $("#receipt-name").val("");
    $("#total-cost").text("0.00");

    if ($("#receipt-select").val() != 0) {
      $.ajax({
        url: "http://localhost:8080/api/v1/receipt/" + $(this).val(),
      }).then(function (data) {
    	var id  = 1;
        $("#receipt-name").val(data.name);
        data.items.forEach((element) => {
          addItem(element.name, element.cost);
        });
      });
    }
  });

  // Add Item (generic) - function to add an item to the table
  function addItem(item, cost) {
    if (typeof cost !== "number" || isNaN(cost)) {
      cost = 0.0;
    }
    
    $("#receipt-table tbody").append(
      '<tr>' +
        '<th scope="row"><i class="fas fa-receipt"></i></th>' +
        '<td id="name">' +
        item +
        '</td>' +
        '<td id="cost">' +
        cost.toFixed(2) +
        '</td>' +
        '<td id="contributors">' +
        '</td>' +
        '</tr>'
    );
    
    var testID = item + "-contributors";
    //alert("TEST: " + testID)
    var testID2;
    document.getElementById("contributors").id = testID;
    for(var i = 0; i < contributors.length; i++)
    	{
    	testID2 = item + "-" + contributors[i];
        document.getElementById(testID).innerHTML +=
        	'<div class="form-check">' +
      		'<input class="form-check-input" type="checkbox" id=' + testID2 + ' value="option1">' +
         	'<label class="form-check-label" for=' + testID2 + '>' + 
         	contributors[i] + 
         	'</label>' +
         	'</div>'
    	}

 
    var newTotal = parseFloat($("#total-cost").text()) + cost;
    $("#total-cost").text(newTotal.toFixed(2));
    
  }
  $("#calculate-bill").on("click", function (event) {
	   event.preventDefault();
	   var billName = $("#bill-name").val();
	   var billDate = $("#bill-date").val();
	   addBillNameDate(billName, billDate);
	   $("#bill-name").val("");
	   $("#bill-name").focus();
	   $("#bill-date").val("");
	   $("#bill-date").focus();
	   getTableData();
	   
	   
	   function getTableData(){
		   var itemContributors  =[];
		   var contribBillId;
		   var contribBillTotal;
		   
		   //initialize a table for each contributor
		   for(var i = 0; i < contributors.length; i++)
			   {
			   		contribBillId = contributors[i] + "-billTable";
			   		contribBillTotal = contributors[i] + "-billTotal";
			   		contribBillBodyId = contributors[i] + "-tableBody";
			   		//alert("Table Created For: " + contributors[i] + "ID: " + contribBillID);
			   		
			   		document.getElementById("bill").innerHTML +=
			   			'<h5>' + contributors[i] +'</h5>' +
			   			'<div class="d-flex justify-content-center p-2">' +
			   				'<ul class="items list-group"></ul>' +
			   					'<table id=' + contribBillId + ' class="table table-bordered">' +
			   						'<thead>' +
			   							'<tr>' +       
					                        '<th scope="col" style="text-align:center">Item</th>' +
					                        '<th scope="col" style="text-align:center">Cost</th>' +
					                    '</tr>' +
					                '</thead>'
					                '<tbody>' +
					                '</tbody>' +
					            '</table>' +
					    '</div>' 
					document.getElementById("bill").innerHTML +=					            
					    '<div class="d-flex justify-content-start p-2">' +
					    	'<h5>Total = $<span id=' + contribBillTotal +'>0.00</span></h5>' +
			    		'</div>' +
			    		'<hr>'
				}
		   
		   $("#receipt-table tbody tr").each(function(){
			      var item = {
			    	        name: $(this).find("#name").text(),
			    	        cost: $(this).find("#cost").text(),
			    	      };
			      
			      var checkboxName;
			      for(var i = 0; i < contributors.length; i++)
			    	  {			    	  
			    	  checkboxName = item.name + "-" + contributors[i];
			    	  var checkbox = document.getElementById(checkboxName).checked;
			    	  
			    	  if(checkbox) //validate checkbox
			    		  {
			    		  //alert("Contributor: '" + contributors[i] + "' added to contributor list for item: '" + item.name + "'");
			    		  itemContributors.push(contributors[i]);
			    		  }
			    	  }
			      
			      item.cost = item.cost / itemContributors.length;
			      
			      //add item and cost to contributor table
			      for(var i = 0; i < itemContributors.length; i++)
			    	  {
			    	  	contribBillId = itemContributors[i] + "-billTable";
			    	  	contribBillTotal = "#" + itemContributors[i] + "-billTotal";
			    	  	document.getElementById(contribBillId).innerHTML += 
			    	  		'<tr>' +
			    	  			'<td id="name">' +
			    	  			item.name +
			    	  			'</td>' +
			    	  			'<td id="cost">$' +
			    	  			item.cost.toFixed(2) +
			    	  			'</td>' +
			    	  		'</tr>'	 
			    	  			
			    	    var newBillTotal = parseFloat($(contribBillTotal).text()) + item.cost;
			    	    $(contribBillTotal).text(newBillTotal.toFixed(2));
			    	  }
			      
			   itemContributors = []; //reset item contributors
		   })
	   }
	   
	   function addBillNameDate(billName, billDate){
	  	   $("#bill").append(
	  	  		'<h5>Bill Name: ' +
	  	  		billName +
	  	  		'</h5>'  +
	  	  		'<h5>Date: ' +
	  	  		billDate +
	  	  		'</h5>' +
	  	  		'<hr>'
	  	   );  	  
	   }
	});
  
  $("#save-bill").on("click", function () {
	  var bill = {};
	  
	  billjson = JSON.stringify(bill);
	  
      $.ajax({
          type: "PUT",
          url: "http://localhost:8080/api/v1/bill/",
          contentType: "application/json",
          dataType: "json",
          data: billjson,
          success: function (response) {
            console.log(response);
          },
          error: function (response) {
            console.log(response);
          },
        });
      alert("Bill Saved")
      window.location.replace("/billManagement");
  
  });
  
});
