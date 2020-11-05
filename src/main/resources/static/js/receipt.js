// Tester function to display the first receipt from the database at the top of the page
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/api/v1/receipt/",
    }).then(function(data) {
        $(".receipt-id").append(data[0].id);
        $(".receipt-name").append(data[0].name);
        data[0].items.forEach((element) => {
            $(".receipt-items").append(element.name + " ");
        });
    });
});

// Adds all receipts to the option dropdown menu
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/api/v1/receipt/",
    }).then(function(data) {
        data.forEach((element) => {
            $("select").append(
                '<option value="' + element.id + '">' + element.name + "</option>"
            );
        });
    });
});

$(document).ready(function() {
    // Action of browser item submit form
    $("#item-form").submit(function(event) {
        // this cancels the default action of submitting a form
        event.preventDefault();
        var item = $("#item-name").val();
        var money = parseFloat($("#item-cost").val());
        addItem(item, money);
        $("#item-name").val("");
        $("#item-cost").val("");
    });

    // Deletes the items from the receipt list
    $("#receipt-list ul").on("click", "span", function(event) {
        $(this)
            .parent()
            .fadeOut(200, function() {
                $(this).remove();
            });
        event.stopPropagation();
    });

    // Saves a receipt to the database when the submit button is pressed
    $("#save-receipt-button").on("click", function() {
        var receipt = {};
        receipt.name = $("#receipt-name").val();
        // Creating a receipt does not take an id
        receipt.items = [];
        $("#receipt-list li").each(function() {
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
                success: function(response) {
                    console.log(response);
                },
                error: function(response) {
                    console.log(response);
                }
            });
        } else {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/api/v1/receipt/",
                contentType: "application/json",
                dataType: "json",
                data: receiptjson,
                success: function(response) {
                    console.log(response);
                },
                error: function(response) {
                    console.log(response);
                }
            });
        }
        location.reload();
    });

    // Generic function to add items to the receipt list
    function addItem(item, cost) {
        if (typeof cost !== "number" || isNaN(cost)) {
            cost = 0.0;
        }
        $("#receipt-list ul").append(
            '<li class="list-group-item"><span> <i class="fa fa-trash" aria-hidden="true"></i> </span>' +
            '<span id="name">' +
            item +
            "</span>" +
            " - $" +
            '<span id="cost">' +
            cost.toFixed(2) +
            "</span>" +
            "</li>"
        );
    }

    // Populates list with selected receipt items
    $("#receipt-select").on("change", function() {
        $("#receipt-list ul").empty();
        $.ajax({
            url: "http://localhost:8080/api/v1/receipt/" + $(this).val(),
        }).then(function(data) {
            $("#receipt-name").val(data.name);
            data.items.forEach((element) => {
                addItem(element.name, element.cost);
            });
        });
    });
});