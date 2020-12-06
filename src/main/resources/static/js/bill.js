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
    
 
    var newTotal = parseFloat($("#total-cost").text()) + cost;
    $("#total-cost").text(newTotal.toFixed(2));
    
  }
  
});

$("#calculate-bill").on("click", function (event) {
	   event.preventDefault();
	   var billName = $("#bill-name").val();
	   var billDate = $("#bill-date").val();
	   addBillNameDate(billName, billDate);
	   $("#bill-name").val("");
	   $("#bill-name").focus();
	   $("#bill-date").val("");
	   $("#bill-date").focus();
	   
	   function addBillNameDate(billName, billDate){
	  	   $("#bill").append(
	  	  		'<h4>Bill Name: ' +
	  	  		billName +
	  	  		'</h4>'  +
	  	  		'<h4>Date: ' +
	  	  		billDate +
	  	  		'</h4>' +
	  	  		'<hr>'
	  	   );  	  	  	   
	   }
	});
