
const api = "http://localhost:8080/auth";


document.addEventListener('DOMContentLoaded',()=>{
    const path = window.location.pathname;

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

                const data = result.data;
                const token = result.data.accessToken;
                const role = result.data.role;

                sessionStorage.setItem('jwtToken', token);
                sessionStorage.setItem('userRole', role);

                if (data && data.username && data.role) {
                    document.cookie = `jwtToken=${data.accessToken}; path=/; max-age=3600`;
                    sessionStorage.setItem('userRole', data.role);

                    alert("SignIn Successfully!");
                    window.location.href = "dashboard.html";
                } else {
                    alert('Authentication failed : Token or role not found');
                }

            }catch(err){
                console.error(err)
                alert('Error during sign in.')
            }
        })
    }


})


