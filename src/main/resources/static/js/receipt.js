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

  // Add Item Button - adds an item to the table when the button is pressed
  $("#item-form").submit(function (event) {
    // this cancels the default action of submitting a form
    event.preventDefault();
    var item = $("#item-name").val();
    var money = parseFloat($("#item-cost").val());
    addItem(item, money);
    // reset the form
    $("#item-name").val("");
    $("#item-cost").val("");
    $("#item-name").focus();
  });

  // Add Item (generic) - function to add an item to the table
  function addItem(item, cost) {
    if (typeof cost !== "number" || isNaN(cost)) {
      cost = 0.0;
    }

    $("#receipt-table tbody").append(
      "<tr>" +
        '<th scope="row"><i class="fas fa-receipt"></i></th>' +
        '<td id="name">' +
        item +
        "</td>" +
        '<td id="cost">' +
        cost.toFixed(2) +
        "</td>" +
        "<td>" +
        '<span><button type="button" class="btn btn-danger"><i class="far fa-trash-alt"></i></button></span>' +
        "</td>" +
        "</tr>"
    );
  }

  // Item Delete Button (table) - removes an item from the table
  $("#receipt-table").on("click", "span", function (event) {
    $(this)
      .parent()
      .parent()
      .fadeOut(200, function () {
        $(this).remove();
      });
    event.stopPropagation();
  });

  // Save Button - saves a receipt to the database
  $("#save-receipt-button").on("click", function () {
    var receipt = {};
    receipt.name = $("#receipt-name").val();
    // creating a receipt does not take an id
    receipt.items = [];
    $("#receipt-table table > tbody  > tr").each(function () {
      var item = {
        name: $(this).find("#name").text(),
        cost: $(this).find("#cost").text(),
      };
      receipt.items.push(item);
    });

    receiptjson = JSON.stringify(receipt);

    if ($("#receipt-select").val() != 0) {
      receipt.id = $("#receipt-select").val();
      $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/v1/receipt/" + receipt.id,
        contentType: "application/json",
        dataType: "json",
        data: receiptjson,
        success: function (response) {
          console.log(response);
        },
        error: function (response) {
          console.log(response);
        },
      });
    } else {
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/receipt/",
        contentType: "application/json",
        dataType: "json",
        data: receiptjson,
        success: function (response) {
          console.log(response);
        },
        error: function (response) {
          console.log(response);
        },
      });
    }
    location.reload();
  });

  // Delete Receipt Button - removes selected receipt from the database
  $("#delete-receipt-button").on("click", function () {
    if ($("#receipt-select").val() != 0) {
      $.ajax({
        type: "DELETE",
        url:
          "http://localhost:8080/api/v1/receipt/" + $("#receipt-select").val(),
        success: function (response) {
          console.log(response);
        },
        error: function (response) {
          console.log(response);
        },
      });
    } else {
      alert("Receipt is not saved in the database");
    }
    location.reload();
  });
});

// Receipt Database Card - populates with first receipt for testing purposes
$(document).ready(function () {
  $.ajax({
    url: "http://localhost:8080/api/v1/receipt/",
  }).then(function (data) {
    $(".receipt-id").append(data[0].id);
    $(".receipt-name").append(data[0].name);
    data[0].items.forEach((element) => {
      $(".receipt-items").append(element.name + " ");
    });
  });

  // OCR API
  $("#upload-button").click(function () {
    var files = $("#file")[0].files;
    var text = "";

    // Check file selected or not
    if (files.length > 0) {
      //Prepare form data
      var formData = new FormData();
      formData.append("file", files[0]);
      // formData.append("url", "URL-of-Image-or-PDF-file");
      formData.append("language", "eng");
      formData.append("apikey", "75e82a628388957");
      formData.append("OCREngine", 2);
      // formData.append("isOverlayRequired", True);
      //Send OCR Parsing request asynchronously
      jQuery.ajax({
        url: "https://api.ocr.space/parse/image",
        data: formData,
        dataType: "json",
        cache: false,
        contentType: false,
        processData: false,
        type: "POST",
        success: function (ocrParsedResult) {
          //Get the parsed results, exit code and error message and details
          var parsedResults = ocrParsedResult["ParsedResults"];
          var ocrExitCode = ocrParsedResult["OCRExitCode"];
          var isErroredOnProcessing = ocrParsedResult["IsErroredOnProcessing"];
          var errorMessage = ocrParsedResult["ErrorMessage"];
          var errorDetails = ocrParsedResult["ErrorDetails"];
          var processingTimeInMilliseconds =
            ocrParsedResult["ProcessingTimeInMilliseconds"];
          //If we have got parsed results, then loop over the results to do something
          if (parsedResults != null) {
            //Loop through the parsed results
            $.each(parsedResults, function (index, value) {
              var exitCode = value["FileParseExitCode"];
              var parsedText = value["ParsedText"];
              var errorMessage = value["ParsedTextFileName"];
              var errorDetails = value["ErrorDetails"];

              var textOverlay = value["TextOverlay"];
              var pageText = "";
              switch (+exitCode) {
                case 1:
                  pageText = parsedText;
                  break;
                case 0:
                case -10:
                case -20:
                case -30:
                case -99:
                default:
                  pageText += "Error: " + errorMessage;
                  break;
              }

              $("#ocrbox").val(pageText);
              $("#ocrbox").removeAttr("disabled");

              $.each(textOverlay["Lines"], function (index, value) {
                // LOOP THROUGH THE LINES AND GET WORDS TO DISPLAY ON TOP OF THE IMAGE AS OVERLAY
              });
              //YOUR CODE HERE
            });
          }
        },
      });
    } else {
      alert("Please select a file.");
    }
  });
});
