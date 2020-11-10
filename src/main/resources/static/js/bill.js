$(document).ready(function () {
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
        $("#receipt-name").val(data.name);
        data.items.forEach((element) => {
          addItem(element.name, element.cost);
        });
      });
    }
  });

  // Add Item (generic) - function to add an item to the table
  function addItem(item, cost, contributors) {
    if (typeof cost !== "number" || isNaN(cost)) {
      cost = 0.0;
    }

    $("#receipt-table tbody").append(
      "<tr>" +
        '<th scope="row"><i class="fas fa-receipt"></i></th>' +
        '<td id="name">' +
        item +
        '</td>' +
        '<td id="cost">' +
        cost.toFixed(2) +
        '</td>' +
        '<td id="contributors">' +
        '<span></span>' +
        '</td>' +
        '</tr>'
    );
    var newTotal = parseFloat($("#total-cost").text()) + cost;
    $("#total-cost").text(newTotal.toFixed(2));
  }
});

//
//$("#contributors").add-contributor(function (event) {
//    event.preventDefault();
//    var name = $("#bill-contributor").val();
//    addContributor(name);
//    
//    $("bill-contributor").val("");
//});
//
//  function addContributor(name) {
//	    $("#contributors .names").append(
//	    	'<p>' +	
//	    	name +
//	    	'</p'
//	    );
//	  }