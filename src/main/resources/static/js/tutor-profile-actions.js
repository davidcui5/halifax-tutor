'use strict';


function sendMessage(){
    let id = localStorage.getItem("tutor-public-id");
    let message = $("#message-text").val();
    let email = $("#same-email").text();

    //https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage
    let emailToken = localStorage.getItem("token");

    var messageData = {
        "id": id,
        "message": message,
        "emailToken": emailToken,
        "email":email
    };
    $.ajax({
        url: location.origin + "/sendtutormessage",
        data: JSON.stringify(messageData),
        contentType: "application/json",
        type: "POST",
        dataType: "json"
    }).done(function (data) {
        if (data.result === "Success") {
            alert("Message Sent!");
            window.location.replace("../tutor-profile.html");
        } else {
            alert(data.details);
        }
    }).fail(function (xhr, status, errorThrown) {

    });
}

function submitFeedback(){
    let id = localStorage.getItem("tutor-public-id");
    let rating = $("#rating").val();
    let feedback = $("#feedback-text").val();
    let email = $("#same-email").text();

    var feedbackData = {
        "id": id,
        "rating": rating,
        "feedback": feedback,
        "email":email
    };
    $.ajax({
        url: location.origin + "/sendtutorfeedback",
        data: JSON.stringify(feedbackData),
        contentType: "application/json",
        type: "POST",
        dataType: "json"
    }).done(function (data) {
        if (data.result === "Success") {
            alert("Feedback Sent !");
            window.location.replace("../tutor-profile.html");
        } else {
            alert(data.details);
        }
    }).fail(function (xhr, status, errorThrown) {

    });
}


