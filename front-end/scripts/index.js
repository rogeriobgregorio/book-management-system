function getToken() {
    return localStorage.getItem('token');
}

console.log(getToken)

function logout() {
    localStorage.removeItem('token');
    window.location.href = "http://127.0.0.1:5500/front-end/pages/login.html";
}