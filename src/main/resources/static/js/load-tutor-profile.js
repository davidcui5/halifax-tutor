'use strict';
function loadProfile() {
    let id = localStorage.getItem("tutor-public-id");

    id = 437; // remove

    var tutorData = {
        "id": id
    };

    $.ajax({
        url: location.origin + "/gettutorprofiledata",
        data: JSON.stringify(tutorData),
        contentType: "application/json",
        type: "POST",
        dataType: "json"
    }).done(function (data) {
        if (data.result == "Success") {
            alert("Profile information successfullly loaded !");
            document.getElementById("tutor-name").innerHTML = data.firstName + " " + data.lastName;
            document.getElementById("phone").innerHTML = data.phoneNumber;
            document.getElementById("email").innerHTML = data.email;
            document.getElementById("education").innerHTML = data.education;
            document.getElementById("tutor-rating").innerHTML = data.rating;
            var courseInfo =  data.courseList;
            var courseList = document.getElementById("tutor-courses");
            for (var i=0;i<courseInfo.length;i++){
                var row = courseList.insertRow(i+1)
                row.insertCell(0).innerHTML = courseInfo[i][0];
                row.insertCell(1).innerHTML = courseInfo[i][1];
                row.insertCell(2).innerHTML = courseInfo[i][2];
            }


            var scheduleArray = data.tutorSchedule;
            var schedule = document.getElementById("tutor-schedule");
            var morningRow = schedule.insertRow(1);
            morningRow.insertCell(0).innerHTML = "Morning";
            morningRow.insertCell(1).innerHTML = scheduleArray[0];
            morningRow.insertCell(2).innerHTML = scheduleArray[3];
            morningRow.insertCell(3).innerHTML = scheduleArray[6];
            morningRow.insertCell(4).innerHTML = scheduleArray[9];
            morningRow.insertCell(5).innerHTML = scheduleArray[12];
            morningRow.insertCell(6).innerHTML = scheduleArray[15];
            morningRow.insertCell(7).innerHTML = scheduleArray[18];

            var afternoonRow = schedule.insertRow(2);
            afternoonRow.insertCell(0).innerHTML = "Afternoon";
            afternoonRow.insertCell(1).innerHTML = scheduleArray[1];
            afternoonRow.insertCell(2).innerHTML = scheduleArray[4];
            afternoonRow.insertCell(3).innerHTML = scheduleArray[7];
            afternoonRow.insertCell(4).innerHTML = scheduleArray[10];
            afternoonRow.insertCell(5).innerHTML = scheduleArray[13];
            afternoonRow.insertCell(6).innerHTML = scheduleArray[16];
            afternoonRow.insertCell(7).innerHTML = scheduleArray[19];


            var eveningRow = schedule.insertRow(3);
            eveningRow.insertCell(0).innerHTML = "Evening";
            eveningRow.insertCell(1).innerHTML = scheduleArray[2];
            eveningRow.insertCell(2).innerHTML = scheduleArray[5];
            eveningRow.insertCell(3).innerHTML = scheduleArray[8];
            eveningRow.insertCell(4).innerHTML = scheduleArray[11];
            eveningRow.insertCell(5).innerHTML = scheduleArray[14];
            eveningRow.insertCell(6).innerHTML = scheduleArray[17];
            eveningRow.insertCell(7).innerHTML = scheduleArray[20];

            document.getElementById('profile-pic').src = data.photoURL;

        } else {
            alert("Something went wrong: " + text);
        }
    }).fail(function (xhr, status, errorThrown) {

    });

}