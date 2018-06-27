'use strict';

$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();
        let email = $("#email").val();
        let password = $("#password").val();
        let phoneNumber = $("#phone-number").val();
        let firstName = $("#user-first-name").val();
        let lastName = $("#user-last-name").val();
        let school = $("#school option:selected").text();

        var studentData = {
            "email": email,
            "password": password,
            "phoneNumber": phoneNumber,
            "firstName": firstName,
            "lastName": lastName,
            "school": school
        };

        $.ajax({
            url: "https://csci5308group12devint.azurewebsites.net/student",
            data: JSON.stringify(studentData),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (text) {
            if (text === "registration success") {
                alert("Registration succeed!");
                window.location.replace("../index.html");
            } else {
                alert("Something went wrong: " + text);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

    $("form").submit(function validateForm(){
        var email = document.forms["user_register"]["user_email"].value;
        var password = document.forms["user_register"]["user_password"].value;
        var confirmPassword = document.forms["user_register"]["user_repeat_password"].value;
        var phoneNumber = document.forms["user_register"]["user_phone_number"].value;
        var firstName = document.forms["user_register"]["user_first_name"].value;
        var lastName = document.forms["user_register"]["user_last_name"].value;

        // https://stackoverflow.com/questions/46155/how-to-validate-an-email-address-in-javascript
        var validateEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        // https://www.w3resource.com/javascript/form/phone-no-validation.php
        var validatePhone = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
        var validateName = /^[A-Za-z]+$/;

        // validate email
        if(!validateEmail.test(String(email).toLowerCase())){
            alert("Invalid Email Address format");
            email.focus();
            return false;
        }

        // validate passwords
        if (validateName.test(password)){
            alert("Passwords must contain atleast one number");
            password.focus();
            return false;
        }

        if (password.length < 8){
            alert("Passwords must contain atleast 8 characters");
            password.focus();
            return false;
        }

        if (password.toLowerCase() == password){
            alert("Passwords must contain atleast one Capital letter");
            password.focus();
            return false;
        }

        if (password !==  confirmPassword){
            alert("Passwords do not match.");
            password.focus();
            return false;
        }

        // validate phone number
        if(!validatePhone.test(phoneNumber) || phoneNumber.length != 10){
            alert("Invalid Phone Number format");
            phoneNumber.focus();
            return false;
        }

        // validate first name
        if( !validateName.test(firstName)){
            alert("First Name can't contain a number");
            firstName.focus();
            return false;
        }

        // validate last name
        if( !validateName.test(lastName)){
            console.log(lastName)
            alert("Last Name can't contain a number");
            lastName.focus();
            return false;
        }

    });


});
