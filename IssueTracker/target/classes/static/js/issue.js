var selectedText = "";
$(document).ready(function () {
        $("select.selector").change(function () {
            selectedText = $(".selector option:selected").val();
            if (selectedText.localeCompare("LOCALE") === 0) {
                //document.getElementById("selector").disabled = true;
                var div = $("#projects");
                var select = $("<select class='form-control' name='project'>");
                $.get("/allProjects", function (data, status) {
                    var a = data.length;
                    console.log(a);
                    while (a > 0) {
                        var currentText = data[--a];
                        var option = $("<option>").text(currentText).val(currentText);
                        select.append(option);
                    }
                    div.html(select);
                });
            } else {
                $("#projects").html(null);
            }
        });
    }
);