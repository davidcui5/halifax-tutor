'use strict';

$(document).ready(function () {
    let token = localStorage.getItem("token");
    if (token === null) {
        alert("You are not logged in. Please log in to search for tutors.");
//        window.location.assign("../index.html");
    }
});