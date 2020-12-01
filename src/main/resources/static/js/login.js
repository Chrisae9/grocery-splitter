$(document).ready(function () {

    //Login Button Pressed
    $("#login-button").click(function (event) {
        // this cancels the default action of submitting a form
        event.preventDefault();

        // collect information from the input fields 
        var userEmail = $("#inputEmail").val();
        var password = $("#inputPassword").val();
        var firstname;
        var user;
        //VERIFY THE userEmail and password
        $.ajax({
          type:"GET",
          url: "http://localhost:8080/api/v1/member/" + userEmail,
          contentType:"application/json",
          success: function(response){
            console.log(response);
            user = response["userEmail"];
            firstname = response["firstname"];
            sessionStorage.setItem('name', firstname);

            window.location.replace("/memberHomePage");
            
          },
      });

});

});

