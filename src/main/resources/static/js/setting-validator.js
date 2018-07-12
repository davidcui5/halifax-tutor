'use strict';
function validateEmail(){
    var validateEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var flag = true;
    for (var i=0; i<inputs.length; i++) {
        var element = document.getElementById(inputs[i].id)
        var elementValue = document.getElementById(inputs[i].id).value;

        // validate email
        if (inputs[i].id == "email") {
            if (!validateEmail.test(String(elementValue).toLowerCase())) {
                alert("Invalid Email Address format");
                element.focus();
                flag = false;
            }

        }
    }
}
