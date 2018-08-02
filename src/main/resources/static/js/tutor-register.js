'use strict';

$(document).ready(function () {
    $("#tutor-register-form").submit(function ( event ) {
        event.preventDefault();
        let email = $("#email").val();
        let password = $("#password").val();
        let phoneNumber = $("#phone-number").val();
        let firstName = $("#user-first-name").val();
        let lastName = $("#user-last-name").val();
        let creditCardNumber = $("#user-credit-card").val();
        let creditCardHolder = $("#user-credit-card-holder").val();
        let expireDate = $("#expire-date").val();
        let securityCode = $("#security-code").val();
        securityCode = parseInt(securityCode, 10);

        if(!validateForm()){
            return;
        }

        var tutorData = {
            "email": email,
            "password": password,
            "phoneNumber": phoneNumber,
            "firstName": firstName,
            "lastName": lastName,
            "creditCardNum": creditCardNumber,
            "creditCardHolder": creditCardHolder,
            "creditCardExpiryDate": expireDate,
            "securityCode": securityCode
        };

        $.ajax({
            url: location.origin + "/tutor",
            data: JSON.stringify(tutorData),
            type: "POST",
            contentType: "application/json",
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
