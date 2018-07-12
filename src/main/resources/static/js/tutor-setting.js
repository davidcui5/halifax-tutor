$(document).ready(function () {
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
$(document).ready(function(){
    document.getElementById("upload_widget_opener").addEventListener("click", function() {
        cloudinary.openUploadWidget({ cloud_name: 'yrzzzzzz', upload_preset: 'ktpg8ic7', folder: 'user_photos'},
            function(error, result) { console.log(error, result) });
    }, false);
}) ;

$(document).on('cloudinarywidgetfileuploadsuccess', function(e, data) {
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


//function logic
$(document).ready(function() {
    var token = localStorage.getItem("token");
    var objToken = {'token': token};


    //passing token
    $.ajax({
        url: location.origin + "/setting",
        data: JSON.stringify(objToken),
        contentType: "application/json",
        type: "POST",
        dataType: "json"
    }).done(function (data) {
        if (data.result === "Success") {
            // alert("Email succeed!");
            // window.location.replace("../index.html");
        } else {
            alert(data.details);
        }
    }).fail(function (xhr, status, errorThrown) {

    });

    $("#Cemail").submit(function (event) {
        event.preventDefault();
        let email = $("#email").val();

        var ChangeEmailData = {
            "email": email
        };

        $.ajax({
            url: location.origin + "/cemail",
            data: JSON.stringify(ChangeEmailData),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (data) {
            if (data.result === "Success") {
                alert("Email change succeed!");
            } else {
                alert(data.details);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

});