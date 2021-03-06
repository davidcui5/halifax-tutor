$(document).ready(function () {
    let token = localStorage.getItem("token");

    if (token === null) {
        alert("Please login. Redirecting to login page.");
        location.href = "../index.html";
    }

    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();

    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);

        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });

});
$(document).ready(function () {
    document.getElementById("upload_widget_opener").addEventListener("click", function () {
        cloudinary.openUploadWidget({cloud_name: 'yrzzzzzz', upload_preset: 'ktpg8ic7', folder: 'user_photos'},
            function (error, result) {
                if (error !== null) {
                    alert(error);
                } else {
                    alert("Upload successfully. Please press confirm to save it.");

                    let photoURL = result[0]["secure_url"];
                    console.log(photoURL);
                    let token = localStorage.getItem("token");
                    let data = {
                        "token": token,
                        "photoURL": photoURL
                    };
                    $("#photoS").on("click", function () {
                        $.ajax({
                            url: location.origin + "/tutor/setting/photo",
                            data: JSON.stringify(data),
                            contentType: "application/json",
                            type: "POST",
                            dataType: "json"
                        }).done(function (json) {
                            let success = json['success'];
                            if (success) {
                                alert("Your profile picture is updated!");
                            } else {
                                alert("Something went wrong on our database..");
                            }
                        }).fail(function () {
                            alert("Something wrong with our server");
                        });
                    });
                }
            });
    }, false);
});

$(document).on('cloudinarywidgetfileuploadsuccess', function (e, data) {
    console.log("Single file success", e, data);
});

function Addshow(id) {
    if (document.getElementById(id).style.display == 'block')
        document.getElementById(id).style.display = 'none';
    else
        document.getElementById(id).style.display = 'block'
}

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}

function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}


$(document).ready(function () {
    let token = localStorage.getItem("token");

    $.ajax({
        url: location.origin + "/tutor/setting/courses",
        data: {
            "token": token
        },
        contentType: "application/json",
        type: "GET",
        dataType: "json"
    }).done(function (json) {
        let success = json['success'];
        let courses = json['courses'];
        if (success && courses !== null && courses.length !== 0) {
            courses.forEach(function (course) {
                let school = course['school'];
                let name = course['name'];
                let price = course['price'];
                let courseDiv = document.createElement("div");
                let courseP = document.createElement("p");
                courseP.innerHTML = school + " " + name + " " + price;
                courseDiv.appendChild(courseP);
                let button = document.createElement("button");
                button.innerHTML = "Remove";
                button.addEventListener("click", function (event) {
                    let data = {
                        "token": token,
                        "school": school,
                        "courseCode": name
                    };
                    $.ajax({
                        url: location.origin + "/tutor/setting/course",
                        data: JSON.stringify(data),
                        contentType: "application/json",
                        type: "DELETE",
                        dataType: "json"
                    }).done(function (json) {
                        let success = json['success'];
                        if (success) {
                            alert("Course removed!");
                        } else {
                            alert("Course is not removed.");
                        }
                    }).fail(function (xhr, status, errorThrown) {
                        alert("Something went wrong when removing this course.");
                    });
                });
                courseDiv.appendChild(button);
                document.getElementById("currentCourses").appendChild(courseDiv);
            });
        } else {
            document.getElementById("currentCourses").innerHTML = "<p>You don't have any course right now.</p>";
        }
    }).fail(function (xhr, status, errorThrown) {
        console.log("Cannot get current courses");
    });

    $("#logout").click(function () {
        localStorage.removeItem("token");
        alert("Signing out...");
        window.location = "../index.html";
    });

    $("#Cpassword-form").submit(function (event) {
        event.preventDefault();

        let password = $("#pwd").val();
        let data = {
            'token': token,
            'password': password
        };

        $.ajax({
            url: location.origin + "/tutor/setting/password",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Password change succeed!");
            } else {
                alert("Something went wrong with database...");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong on our back end...");
        });
    });

    $("#addCourseForm").submit(function (event) {
        event.preventDefault();

        let school = $("select#school").val();
        let courseName = $("#courseCode").val();
        let price = $("#price").val();

        let data = {
            "token": token,
            "school": school,
            "courseCode": courseName,
            "coursePrice": price
        };

        $.ajax({
            url: location.origin + "/tutor/setting/course",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Course added successfully!");
            } else {
                alert("Something went wrong on our database.");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong on our back end...");
        });
    });

    $("#Cemail-form").submit(function (event) {
        event.preventDefault();
        let email = $("#email").val();

        let data = {
            'token': token,
            'email': email
        };

        $.ajax({
            url: location.origin + "/tutor/setting/email",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Email change succeed!");
                localStorage.removeItem("token");
                alert("Signing out... Please log in with your new email");
                window.location = "../index.html";
            } else {
                alert("Something went wrong with our database...");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong on our backend");
        });

    });

    $("#Cphone-form").submit(function (event) {
        event.preventDefault();
        let phone = $("#phone").val();

        let data = {
            'token': token,
            'phone': phone
        };

        $.ajax({
            url: location.origin + "/tutor/setting/phone",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Phone Number change succeed!");
            } else {
                alert("Something went wrong on our database...");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong with our server...");
        });

    });

    $("#Ccard-form").submit(function (event) {
        event.preventDefault();
        let creditCardNumber = $("#cardnum").val();
        let expireDate = $("#expire_date").val();
        let securityCode = $("#code").val();
        let holderName = $("#cardname").val();
        let data = {
            'token': token,
            'holderName': holderName,
            'creditCardNumber': creditCardNumber,
            'expireDate': expireDate,
            'securityCode': securityCode
        };

        $.ajax({
            url: location.origin + "/tutor/setting/card",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Card information change succeed!");
            } else {
                alert("Something went wrong on our database");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong with our server");
        });

    });

    $("#Cedu").submit(function (event) {
        event.preventDefault();
        let education = $("#education").val();

        let data = {
            'token': token,
            'education': education
        };

        $.ajax({
            url: location.origin + "/tutor/setting/education",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Education change succeed!");
            } else {
                alert("Something went wrong with our database...");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong on our server....");
        });

    });

    $("#Cexperience").submit(function (event) {
        event.preventDefault();
        let experience = $("#experience").val();

        let data = {
            'token': token,
            'experience': experience
        };

        $.ajax({
            url: location.origin + "/tutor/setting/experience",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Experience change succeed!");
            } else {
                alert("Something wrong with our database...");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something wrong with our server...");
        });

    });

    $("#Cavailability").submit(function (event) {

        event.preventDefault();
        let su1 = $("#Su1").is(":checked");
        let mo1 = $("#Mo1").is(":checked");
        let tu1 = $("#Tu1").is(":checked");
        let we1 = $("#We1").is(":checked");
        let th1 = $("#Th1").is(":checked");
        let fr1 = $("#Fr1").is(":checked");
        let sa1 = $("#Sa1").is(":checked");

        let su2 = $("#Su2").is(":checked");
        let mo2 = $("#Mo2").is(":checked");
        let tu2 = $("#Tu2").is(":checked");
        let we2 = $("#We2").is(":checked");
        let th2 = $("#Th2").is(":checked");
        let fr2 = $("#Fr2").is(":checked");
        let sa2 = $("#Sa2").is(":checked");

        let su3 = $("#Su3").is(":checked");
        let mo3 = $("#Mo3").is(":checked");
        let tu3 = $("#Tu3").is(":checked");
        let we3 = $("#We3").is(":checked");
        let th3 = $("#Th3").is(":checked");
        let fr3 = $("#Fr3").is(":checked");
        let sa3 = $("#Sa3").is(":checked");

        let weeklySchedule = [
            [su1, su2, su3],
            [mo1, mo2, mo3],
            [tu1, tu2, tu3],
            [we1, we2, we3],
            [th1, th2, th3],
            [fr1, fr2, fr3],
            [sa1, sa2, sa3]
        ];

        let data = {
            'token': token,
            'weeklySchedule': weeklySchedule
        };

        $.ajax({
            url: location.origin + "/tutor/setting/weeklySchedule",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (data) {
            let success = data['success'];
            if (success) {
                alert("Availability change succeed!");
            } else {
                alert("Something went wrong in the backend...");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong...");
        });
    });

    $("#plan1").click(function (event) {
        event.preventDefault();

        let data = {
            'token': token,
            'planNo': 1
        };

        $.ajax({
            url: location.origin + "/tutor/setting/plan",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Subscription Plan change succeed!");
            } else {
                alert("Something wrong on our database.");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something wrong on our server.");
        });
    });

    $("#plan2").click(function (event) {
        event.preventDefault();

        let data = {
            'token': token,
            'planNo': 2
        };

        $.ajax({
            url: location.origin + "/tutor/setting/plan",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Subscription Plan change succeed!");
            } else {
                alert("Something wrong on our database.");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something wrong on our server.");
        });
    });

    $("#plan3").click(function (event) {
        event.preventDefault();

        let data = {
            'token': token,
            'planNo': 3
        };

        $.ajax({
            url: location.origin + "/tutor/setting/plan",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Subscription Plan change succeed!");
            } else {
                alert("Something wrong on our database.");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something wrong on our server.");
        });
    });

    $("#plan4").click(function (event) {
        event.preventDefault();

        let data = {
            'token': token,
            'planNo': 4
        };

        $.ajax({
            url: location.origin + "/tutor/setting/plan",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Subscription Plan change succeed!");
            } else {
                alert("Something wrong on our database.");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something wrong on our server.");
        });
    });

    $("#resend").click(function (event) {
        event.preventDefault();

        let data = {
            'token': token
        };

        $.ajax({
            url: location.origin + "/tutor/setting/resend",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Confirmation Email resend succeed!");
            } else {
                alert("Something wrong with our back end.");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something wrong with our server.");
        });
    });

    $("#cancel").click(function (event) {
        event.preventDefault();

        let data = {
            'token': token
        };

        $.ajax({
            url: location.origin + "/tutor/setting/plan",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "DELETE",
            dataType: "json"
        }).done(function (data) {
            let success = data['success'];
            if (success) {
                alert("Subscription cancel succeed!");
            } else {
                alert("Something wrong on our database.");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something wrong on our server.");
        });
    });
});

