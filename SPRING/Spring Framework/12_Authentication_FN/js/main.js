const api = "http://localhost:8080/auth";
const auth_base = "http://localhost:8080/hello";


document.addEventListener('DOMContentLoaded',()=>{
    const path = window.location.pathname;

    //signup
    if (path.includes('signUp.html')){
        const signUpForm = document.querySelector('form');
        signUpForm.addEventListener('submit',async (e)=>{
            e.preventDefault()
            console.log('✅ Form submission event fired');


            const userDTO = {
                name: document.getElementById('signup-name').value.trim(),
                username:document.getElementById('signup-username').value.trim(),
                email:document.getElementById('signup-email').value.trim(),
                password:document.getElementById('signup-password').value.trim()
            }
            console.log(userDTO)

            try{
                const response = await fetch(`${api}/signup`,{
                    method:'POST',
                    headers:{
                        'Content-Type' : 'application/json'
                    },
                    body:JSON.stringify(userDTO)
                });
                const result = await response.json();
                console.log("Response",result)

                if (response.ok){
                    alert('Sign up Successful! now you can signing')
                    window.location.href='index.html';
                }else{
                    alert(`Signup Failed: ${result.message || 'Unknown Error'}`)
                }
            }catch (error) {
                console.error(error);
                alert("Error during signup");
            }

        })
    }
    if (path.includes('index.html') || path.endsWith('/')){
        const signingFrom = document.querySelector('form');
        signingFrom.addEventListener('submit',async (e)=>{
            e.preventDefault()

            const authDTO = {
                username: document.getElementById('signIn-username').value.trim(),
                password: document.getElementById('signIn-password').value.trim()
            }

            try{
                const response = await fetch(`${api}/login`,{
                    method:'POST',
                    headers : {
                        'Content-Type' : 'application/json'
                    },
                    body:JSON.stringify(authDTO)
                });

                const result = await response.json();
                console.log('login response ',result)

                const token= result.data;

                if (token){
                    localStorage.setItem('jwtToken',token)
                    alert("SignIn Successfully!")
                    window.location.href = "dashboard.html"
                }else{
                    alert('Authentication failed : Token not found');
                }
            }catch(err){
                console.error(err)
                alert('Error during sign in.')
            }
        })
    }

    /*if (path.includes("dashboard.html")) {
        const token = localStorage.getItem("token");

        if (!token) {
            window.location.href = "index.html";
        } else {
            // Try calling /hello/admin first
            fetch("http://localhost:8080/hello/admin", {
                method: "GET",
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
                .then(response => {
                    if (response.ok) {
                        document.getElementById("user-name").textContent = `Welcome, Admin`;
                        console.log("Admin logged in");
                    } else {
                        // If not admin, try /hello/user
                        return fetch("http://localhost:8080/hello/user", {
                            method: "GET",
                            headers: {
                                Authorization: `Bearer ${token}`
                            }
                        })
                            .then(response => {
                                if (response.ok) {
                                    document.getElementById("user-name").textContent = `Welcome, User`;
                                    console.log("User logged in");
                                } else {
                                    throw new Error("Unauthorized or unknown role");
                                }
                            });
                    }
                })
                .catch(error => {
                    console.error("Authentication error:", error);
                    localStorage.removeItem("token");
                    window.location.href = "index.html";
                });
        }
    }*/

})

function logout() {
    localStorage.removeItem('jwtToken');
    alert('You have been logged out successfully.');
    window.location.href = 'index.html';
}

