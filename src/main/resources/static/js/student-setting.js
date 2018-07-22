'use strict';

$(document).ready(function () {
    var token = localStorage.getItem("token");
    var objToken = {'token': token};

    $.ajax({
        url: location.origin + "/admin/setting/access",
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

        if($("#pwd").val() != $("#Rpwd").val()){
            alert("The two password fields didn't match.")
            return;
        }

        let password = $("#pwd").val();
        var data = {
            'token': token,
            "password": password
        };

        $.ajax({
            url: location.origin + "/admin/setting/password",
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