'use strict';
function validatePwd(){
    var inputs = document.forms["Cpassword"].getElementsByTagName("input");
    var flag = true;
    for (var i=0; i<inputs.length; i++) {
        var element = document.getElementById(inputs[i].id);
        var elementValue = document.getElementById(inputs[i].id).value;
        // alert("Passwords must contain atleast one number");

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
function validatePhone(){
    var inputs = document.forms["Cphone"].getElementsByTagName("input");
    var validatePhone = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    var flag = true;
    for (var i=0; i<inputs.length; i++) {
        var element = document.getElementById(inputs[i].id);
        var elementValue = document.getElementById(inputs[i].id).value;

        // validate phone number
        if(inputs[i].id == "phone") {
            if(!validatePhone.test(elementValue) || elementValue.length != 10){
                alert("Invalid Phone Number format");
                element.focus();
                return false;
            }
        }
    }
}

function validateCard(){
    var inputs = document.forms["Ccard"].getElementsByTagName("input");
    var flag = true;
    for (var i=0; i<inputs.length; i++) {
        var element = document.getElementById(inputs[i].id);
        var elementValue = document.getElementById(inputs[i].id).value;


        // validate credit card no
        if(inputs[i].id == "cardnum") {
            if (elementValue.length != 16){
                alert("Invalid Credit Card Number format");
                element.focus();
                return false;
            }
        }

        // validate credit card date
        if(inputs[i].id == "expire_date") {
            //https://stackoverflow.com/questions/1531093/how-do-i-get-the-current-date-in-javascript
            var today = new Date();
            var month = today.getMonth()+1;
            var year = today.getFullYear();

            var cardDate = new Date(elementValue);
            var cardMonth = cardDate.getMonth()+1;
            var cardYear = cardDate.getFullYear();

            if (cardYear < year){
                alert("This credit card is already expired");
                element.focus();
                return false;
            }
            else if (cardYear.equals(year)){
                if(cardMonth < month){
                    alert("This credit card is already expired");
                    element.focus();
                    return false;
                }
            }
        }

        // validate credit card security code
        if(inputs[i].id == "code") {
            if (!elementValue.length.equals(3)){
                alert("Invalid Security Code Format");
                element.focus();
                return false;
            }
        }
    }
}