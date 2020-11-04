$("h1").click(function () {
  console.log("listener");
});

$("input").keypress(function (event) {
  if (event.which == 13) {
    var item = $("input").val();
    $("#receipt-list ul").append("<li><span> ~ </span>" + item + "</li>");
    $("input").val("");
  }
});

$("#receipt-list ul").on("click", "span", function (event) {
  $(this)
    .parent()
    .fadeOut(200, function () {
      $(this).remove();
    });
  event.stopPropagation();
});

$.get("navbar.html", function (data) {
  $("#default-navbar").replaceWith(data);
});

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
});

$;
