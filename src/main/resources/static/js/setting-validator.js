'use strict';
function validatePwd(){
    var inputs = document.forms["Cpassword"].getElementsByTagName("input");
    var flag = true;
    for (var i=0; i<inputs.length; i++) {
        var element = document.getElementById(inputs[i].id);
        var elementValue = document.getElementById(inputs[i].id).value;
        alert("Passwords must contain atleast one number");

        // validate password
        if(inputs[i].id == "pwd"){

            if (validateName.test(elementValue)){
                alert("Passwords must contain atleast one number");
                element.focus();
                flag = false;
            }

            if (elementValue.length < 8){
                alert("Passwords must contain atleast 8 characters");
                element.focus();
                flag = false;
            }

            if (elementValue.toLowerCase() == elementValue){
                alert("Passwords must contain atleast one Capital letter");
                element.focus();
                flag = false;
            }
        }

        // validate confirm password
        if(inputs[i].id == "Rpwd") {
            var passwordValue = document.getElementById("password").value;
            if (elementValue !==  passwordValue){
                alert("Passwords do not match.");
                element.focus();
                return false;
            }
        }
    }
}
function validateEmail(){
     var inputs = document.forms["Cemail"].getElementsByTagName("input");
    var validateEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var flag = true;
    for (var i=0; i<inputs.length; i++) {
        var element = document.getElementById(inputs[i].id);
        var elementValue = document.getElementById(inputs[i].id).value;

        // validate email
        if (inputs[i].id == "email") {
            if (!validateEmail.test(String(elementValue).toLowerCase())) {
                alert("Invalid Email Address format");
                element.focus();
                flag = false;
            }

        }
    }
}
