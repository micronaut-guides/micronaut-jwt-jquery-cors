function clearLocalStorageJwt() {
    localStorage.removeItem("username");
    localStorage.removeItem("roles");
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    localStorage.removeItem("expiresIn");
    localStorage.removeItem("tokenType");
}

function getUsername() {
    return localStorage.getItem("username");
}

function getRoles() {
    return localStorage.getItem("roles");
}

function getAccessToken() {
    return localStorage.getItem("accessToken");
}

function getExpiresIn() {
    return localStorage.getItem("expiresIn");
}

function getRefreshToken() {
    return localStorage.getItem("refreshToken");
}

function getTokenType() {
    return localStorage.getItem("tokenType");
}

function populateLocalStorageWithAccessToken(data) {
    localStorage.setItem("accessToken", data.accessToken);
    localStorage.setItem("refreshToken", data.refreshToken);
}

function populateLocalStorageWithJwt(data) {
    localStorage.setItem("expiresIn", data["expires_in"]);
    localStorage.setItem("tokenType", data["token_type"]);
    populateLocalStorageWithAccessToken(data);
    localStorage.setItem("username", data.username);
    if ( data.roles !== undefined) {
        localStorage.setItem("roles", data.roles);
    }
}

function refreshAccessToken(callback) {
    var path = '/oauth/access_token';
    var jsonData = JSON.stringify({grant_type: 'refresh_token', refresh_token: getRefreshToken()});
    postJson(path, jsonData, function(data) {
        populateLocalStorageWithAccessToken(data);
        callback();
    }, function() {
        logout()
    }, function() {
        logout()
    });
}