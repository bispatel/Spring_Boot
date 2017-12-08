$(document).ready(function () {

    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();
    });
    
    $("#btnSubmit").click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        uploadFile("/api/upload/multi");
    });
    $("#btnSingleSubmit").click(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        uploadFile("/api/upload");
    });
    
});

function uploadFile(urlName) {

    // Get form
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    data.append("CustomField", "This is some extra data, testing");
    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: urlName,
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	  var json = "<h1>Ajax Response</h1><pre>"
                  + data+ "</pre>";            
            $("#result").html(json);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}

function fire_ajax_submit() {
    var search = {}
    search["username"] = $("#username").val();
    $("#btn-search").prop("disabled", true);  
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}