'use strict';

$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();
        let new_password = $("#password").val();
        let email = $("#email").text();
        let usertype = $("#usertype").text();

        var userData = {
            "email": email,
            "password": new_password
        };

        if (usertype == "student"){
            $.ajax({

                url: "http://localhost:8080/setnewpasswordstudent",
                data: JSON.stringify(userData),
                contentType: "application/json",
                type: "POST",
                dataType: "text"
            }).done(function (text) {
                if (text === "password update success") {
                    alert("Password Updated !");
                    window.location.replace("../index.html");
                } else {
                    alert("Something went wrong: " + text);
                }
            }).fail(function (xhr, status, errorThrown) {

            });
        }
        else {
            $.ajax({
                url: "http://localhost:8080/setnewpasswordtutor",
                data: JSON.stringify(userData),
                contentType: "application/json",
                type: "POST",
                dataType: "text"
            }).done(function (text) {
                if (text === "password update success") {
                    alert("Password Updated !");
                    window.location.replace("../index.html");
                } else {
                    alert("Something went wrong: " + text);
                }
            }).fail(function (xhr, status, errorThrown) {

            });
        }



    });

});
