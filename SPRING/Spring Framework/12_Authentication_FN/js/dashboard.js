function logout() {
    alert('You have been logged out successfully.');
    sessionStorage.clear();
    window.location.href = 'index.html';
}

document.addEventListener('DOMContentLoaded', () => {
    const role = sessionStorage.getItem('userRole');
    const token = sessionStorage.getItem('jwtToken');

    if (!role || !token) {
        alert('You are not authorized to view this page. Please log in.');
        logout();
        return;
    }

    document.getElementById('user-name').innerText = role;

    let endpoint = '';
    if (role === 'ADMIN') {
        endpoint = 'http://localhost:8080/hello/admin';
    } else if (role === 'USER') {
        endpoint = 'http://localhost:8080/hello/user';
    } else {
        alert('Invalid role. Logging out.');
        logout();
        return;
    }

    fetch(endpoint, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(res => {
            if (!res.ok) throw new Error('Access denied');
            return res.text();
        })
        .catch(err => {
            console.error(err);
            alert('Error fetching dashboard info. Logging out.');
            logout();
        });
});
