function signUp() {
    const username = document.getElementById("signup-username").value;
    const email = document.getElementById("signup-email").value;
    const password = document.getElementById("signup-password").value;

    if (username && email && password) {
        const user = { username, email, password };
        localStorage.setItem("user_" + username, JSON.stringify(user));
        alert("Account created! Please log in.");
        window.location.href = "index.html";
    } else {
        alert("Please fill all fields.");
    }
}

function signIn() {
    const username = document.getElementById("signin-username").value;
    const password = document.getElementById("signin-password").value;

    const userData = localStorage.getItem("user_" + username);
    if (userData) {
        const user = JSON.parse(userData);
        if (user.password === password) {
            localStorage.setItem("loggedInUser", username);
            window.location.href = "dashboard.html";
        } else {
            alert("Incorrect password.");
        }
    } else {
        alert("User not found. Please sign up.");
    }
}

function logout() {
    localStorage.removeItem("loggedInUser");
    window.location.href = "index.html";
}
