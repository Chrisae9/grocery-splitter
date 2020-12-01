$(document).ready(function () {
  
    // collect information from the input fields 
    var firstname;
    firstname = sessionStorage.getItem('name');
    var welcomeMsg = document.getElementById("welcome-message");
    welcomeMsg.append("Welcome "+ firstname + "!");
    // alert("name: " + firstname);
    // console.log(firstname);
});

