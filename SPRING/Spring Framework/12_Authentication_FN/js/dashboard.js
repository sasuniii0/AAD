const api = "http://localhost:8080/auth";
const auth_base = "http://localhost:8080/hello";


function logout() {
    localStorage.removeItem('jwtToken');
    alert('You have been logged out successfully.');
    window.location.href = 'index.html';
}