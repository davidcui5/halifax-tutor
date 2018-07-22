'use strict';

function processResults(success, numOfResults, results) {
    if (!success) {
        alert("Oops! Something went wrong in our server. Please try again");
    } else {
        let text = "The number of results found is: " + numOfResults;
        console.log(text);
        document.getElementById("numOfResultsText").innerHTML = text;
        $("#search-results-filters").show();
    }
}

$(document).ready(function () {
    let token = localStorage.getItem("token");
    if (token === null) {
        console.log("You are not logged in. Please log in to search for tutors.");
//        window.location.assign("../index.html");
    }
    $("form").submit(function (event) {
        event.preventDefault();
        let school = $("#university-select").val();
        let courseName = $("#course-id").val();
        let searchData = {
            "school": school,
            "courseName": courseName
        };

        $.ajax({
            url: location.origin + "/search",
            data: JSON.stringify(searchData),
            type: "POST",
            contentType: "application/json",
            dataType: "json"
        }).done(function (data) {
            let success = data["success"];
            let numOfResults = data["numOfResults"];
            let results = data["results"];
            processResults(success, numOfResults, results);
        }).fail(function (xhr, status, errorThrown) {
            alert( "Sorry, there was a problem!" );
            console.log( "Error: " + errorThrown );
            console.log( "Status: " + status );
            console.dir( xhr );
        });
    });
});