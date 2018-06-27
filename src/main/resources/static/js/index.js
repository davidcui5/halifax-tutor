'use strict';

$(document).ready(function () {
    $("form").submit(
        function validateForm(){
            var email = document.forms["user_login"]["user_email"].value;
            var password = document.forms["user_login"]["user_password"].value;
            // https://stackoverflow.com/questions/46155/how-to-validate-an-email-address-in-javascript
            var validateEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            var validateName = /^[A-Za-z]+$/;

            // validate email
            if(!validateEmail.test(String(email).toLowerCase())){
                alert("Invalid Email Address format");
                $('#user_email').after('<p>Invalid Email Address format</p>')
                email.focus();
                return false;
            }

            // validate passwords
            if (validateName.test(password)){
                alert("Passwords must contain atleast one number");
                $('#user_password').after('<p>Passwords must contain atleast one number</p>')
                password.focus();
                return false;
            }

            if (password.length < 8){
                alert("Passwords must contain atleast 8 characters");
                $('#user_password').after('<p>Passwords must contain atleast 8 characters</p>')
                password.focus();
                return false;
            }

            if (password.toLowerCase() == password){
                alert("Passwords must contain atleast one Capital letter");
                password.focus();
                return false;
            }
        }
    );
});