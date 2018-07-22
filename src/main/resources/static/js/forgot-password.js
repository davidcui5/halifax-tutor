'use strict';

$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();
        let email = $("#email").val();

        var studentData = {
            "email": email
        };

        if(!validateForm()){
            return;
        }


        if (document.getElementById('student').checked){
            $.ajax({

                url: location.origin + "/studentforgotpassword",
                data: JSON.stringify(studentData),
                contentType: "application/json",
                type: "POST",
                dataType: "json"
            }).done(function (data) {
                if (data.result == "Success") {
                    alert("Verification email has been sent!");
                } else {
                    alert("Something went wrong: " + text);
                }
            }).fail(function (xhr, status, errorThrown) {

            });
        }
        else{
            $.ajax({
                url: location.origin + "http://localhost:8080/tutorforgotpassword",
                data: JSON.stringify(studentData),
                contentType: "application/json",
                type: "POST",
                dataType: "json"
            }).done(function (data) {
                if (data.result == "Success") {
                    alert("Verification email has been sent!");
                } else {
                    alert("Something went wrong: " + text);
                }
            }).fail(function (xhr, status, errorThrown) {

            });
        }


    });

});
