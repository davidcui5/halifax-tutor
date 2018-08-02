'use strict';

$(document).ready(function () {
    $("#student-register-form").submit(function (event) {
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

        if(!validateForm()){
            return;
        }

        $.ajax({
            url: location.origin + "/student",
            data: JSON.stringify(studentData),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (data) {
            if (data.result === "SUCCESS") {
                alert("Registration Success! Check mailbox for Activation Email");
                window.location.replace(data.detail);
            } else {
                alert(data.detail);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

});
