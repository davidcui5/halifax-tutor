'use strict';

function processResults(success, numOfResults, results) {
    if (!success) {
        alert("Oops! Something went wrong in our server. Please try again");
    } else {
        $("#search-results").html("");
        let text = "The number of results found is: " + numOfResults;
        $("#numOfResultsText").html(text);
        results.forEach(function (tutorProfile) {
            let tutorProfileDiv = generateTutorProfile(tutorProfile);
            $("#search-results").append(tutorProfileDiv);
        });
    }
}

function generateTutorProfile(tutorProfile) {
    let photoURL = tutorProfile["photoURL"];
    let firstName = tutorProfile["firstName"];
    let lastName = tutorProfile["lastName"];
    let education = tutorProfile["education"];
    let rating = tutorProfile["rating"];
    let pricePerHour = tutorProfile["pricePerHour"];

    let element = document.createElement("div");
    let photoURLElement = document.createElement("img");
    if (photoURL === null) {
        photoURL = "../images/profile-placeholder.png"
    }
    photoURLElement.setAttribute('src', photoURL);
    photoURLElement.setAttribute('height', 30);
    photoURLElement.setAttribute('width', 30);
    element.appendChild(photoURLElement);

    let nameElement = document.createElement("p");
    let name = firstName + " " + lastName;
    nameElement.innerHTML = "Name: " + name;
    element.appendChild(nameElement);

    let educationElement = document.createElement("p");
    educationElement.innerHTML = "Education: " + education;
    element.appendChild(educationElement);

    let ratingElement = document.createElement("p");
    ratingElement.innerHTML = "Rating: " + rating;
    element.appendChild(ratingElement);

    let pricePerHourElement = document.createElement("p");
    pricePerHourElement.innerHTML = "Price per hour: " + pricePerHour;
    element.appendChild(pricePerHourElement);

    return element;
}

$(document).ready(function () {
    var success;
    var numOfResults;
    var results;
    let token = localStorage.getItem("token");
    if (token === null) {
        console.log("You are not logged in. Please log in to search for tutors.");
        //window.location.assign("../index.html");
    }
    $("form").submit(function (event) {
        event.preventDefault();
        $("#search-results-filters").show();
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
            success = data["success"];
            numOfResults = data["numOfResults"];
            results = data["results"];
            processResults(success, numOfResults, results);
        }).fail(function (xhr, status, errorThrown) {
            alert("Sorry, there was a problem!");
            console.log("Error: " + errorThrown);
            console.log("Status: " + status);
            console.dir(xhr);
        });
    });

    $("#sort-button").click(function () {
        let sortBy = $("#filter-select").val();
        let order = $("#filter-order").val();
        console.log(sortBy);
        console.log(order);
        if (order === "asc") {
            if (sortBy === "rating") {
                results.sort(function (a, b) {
                    return a.rating - b.rating;
                });
            } else {
                results.sort(function (a, b) {
                    return a.pricePerHour - b.pricePerHour;
                });
            }
        } else {
            if (sortBy === "rating") {
                results.sort(function (a, b) {
                    return b.rating - a.rating;
                });
            } else {
                results.sort(function (a, b) {
                    return b.pricePerHour - a.pricePerHour;
                });
            }
        }
        console.log(results);
        processResults(success, numOfResults, results);
    });
});