$(document).ready(function () {

    //Login Button Pressed
    $("#register-button").click(function (event) {
        // this cancels the default action of submitting a form
        event.preventDefault();

        var user = {};
        // collect information from the input fields 
        user.userEmail = $("#inputEmail").val();
        user.userPassword = $("#inputPassword").val();
        user.fName = $("#inputFirstName").val();
        user.lName = $("#inputLastName").val();
      // alert("VALUES ENTERED: " + user.userEmail + " " + user.password + " " + user.firstName + " " + user.lastName);
        
        //Create a user account
        // userjson = JSON.stringify(user);
        userjson = JSON.stringify(user);
        // alert("userjson: " + userjson);

        //Place the user account into DB
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/api/v1/member/",
          contentType:"application/json",
          data: userjson,
          success: function (response) {
            console.log(response);
            // alert("SUCESS!");
          },
          error: function (response) {
            console.log(response);
            // alert("FAILURE!");
          },
        });
        window.location.replace("/memberHomePage");
        // window.open("/memberHomePage");
      });

});

