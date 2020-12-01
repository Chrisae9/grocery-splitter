$(document).ready(function () {

    //Login Button Pressed
    $("#login-button").click(function (event) {
        // this cancels the default action of submitting a form
        event.preventDefault();

        // collect information from the input fields 
        var userEmail = $("#inputEmail").val();
        var password = $("#inputPassword").val();
       // var data = "Email is : " + userEmail + " Password is : " + password;
        
      });

});

