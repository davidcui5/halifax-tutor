'use strict';

$(document).ready(function () {
    let token = localStorage.getItem("token");
    if (token === null) {
        alert("You are not logged in. Please log in to search for tutors.");
//        window.location.assign("../index.html");
    }
    $("form").submit(function (event) {
        event.preventDefault();
        let school = $("#university-select").val();
        let courseName = $("#course-id").val();
        let data = {
            "school": school,
            "courseName": courseId
        };
        $.ajax({
            url: location.origin + "/search",
            data: JSON.stringify(data),
            type: "POST",
            dataType: "json",
            contentType: "application/json"
        })
        .done(function (json) {
            alert(json);
        })
        .fail(function( xhr, status, errorThrown ) {
            alert( "Sorry, there was a problem!" );
            console.log( "Error: " + errorThrown );
            console.log( "Status: " + status );
            console.dir( xhr );
        });
    });


});