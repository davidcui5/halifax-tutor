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
            data: studentData,
            type: "POST",
            dataType: "json",
            ContentType: "application/json; charset=UTF-8"
        }).done(function (json) {

        }).fail(function (xhr, status, errorThrown) {

        });

    });
});