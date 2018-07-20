var serverUrl = 'http://localhost:8080';

function postJson(path, jsonData, callback, unauthorizedCallback, badRequestCallback) {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        url: serverUrl + path,
        data: jsonData,
        dataType: 'json',
        statusCode: {
            200: callback,
            401: unauthorizedCallback,
            400: badRequestCallback
        }
    });
}

function getJson(path, callback, unauthorizedCallback) {
    var accessToken = getAccessToken();
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + accessToken
        },
        'type': 'GET',
        'url': serverUrl + path,
        'dataType': 'json',
        statusCode: {
            200: callback,
            401: unauthorizedCallback
        }
    });
}