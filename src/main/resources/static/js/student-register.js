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
            url: location.origin + "/student",
            data: JSON.stringify(studentData),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (data) {
            if (data.result === "Success") {
                alert("registration succeed!");
                window.location.replace("../index.html");
            } else {
                alert(data.details);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

});
