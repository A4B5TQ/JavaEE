$(document).ready(function () {
    $(".registerForm").validate(
        {
            rules: {
                username: {
                    required: true,
                    minlength: 3,
                },
                email: {
                    required: true,
                    email: true,

                },
                fullname: {
                    required: true,
                    minlength: 3,

                },
                password: {
                    required: true,
                    minlength: 5,

                },
                confirmPassword: {
                    required: true,
                    minlength: 5,
                    equalTo: "#inputPassword",

                },
            }
        }
    );
});
$('#username').on('keyup', function () {
    var username = $('#username').val();
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });
    $.post("/usernameCheck", {
        username: username,
    }, function (data) {
        var result = JSON.parse(data);
        if (result === true) {
            console.log(true);
            $("#nameValidator").removeClass('has-success').addClass('has-error');
            $("#innerText").html($("<div class='has-error'><span class='help-block'>Потребителското име е заето</span></div>"));
        } else {
            console.log(false);
            $("#nameValidator").removeClass('has-error').addClass('has-success');
            $("#innerText").html($("<div class='has-success'><span class='help-block'>Потребителското име е свободно</span></div>"));
        }
    });

});