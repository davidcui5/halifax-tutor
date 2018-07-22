'use strict';

$(document).ready(function () {
    var token = localStorage.getItem("token");
    var objToken = {'token': token};

    $.ajax({
        url: location.origin + "/student/setting/access",
        data: JSON.stringify(objToken),
        contentType: "application/json",
        type: "POST",
        dataType: "text"
    }).done(function (data) {
        if (data === "UNAUTHORIZED") {
            alert("You do not have access to this page.");
            window.location.replace("../index.html");
        }
    }).fail(function (xhr, status, errorThrown) {
        //window.location.replace("../index.html");
    });

    $("#logout").click(function (event) {
        event.preventDefault();
        localStorage.removeItem("token");
    });

    $("#Cpassword").submit(function (event) {
        event.preventDefault();

        if ($("#pwd").val() != $("#Rpwd").val()) {
            alert("The two password fields didn't match.")
            return;
        }

        let password = $("#pwd").val();
        var data = {
            'token': token,
            "password": password
        };

        $.ajax({
            url: location.origin + "/student/setting/password",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#Cemail").submit(function (event) {
        event.preventDefault();

        let email = $("#email").val();
        var data = {
            'token': token,
            "email": email
        };

        $.ajax({
            url: location.origin + "/student/setting/email",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

    $("#Cphone").submit(function (event) {
        event.preventDefault();

        let email = $("#phone").val();
        var data = {
            'token': token,
            "email": email
        };

        $.ajax({
            url: location.origin + "/student/setting/phone",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            alert(data);
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });
});