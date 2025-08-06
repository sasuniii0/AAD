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

                const token= result.data;

                if (token){
                    document.cookie = `jwtToken=${token}; path=/; max-age=3600`; // Set cookie for 1 hour
                    console.log('Token set in cookie:', token);
                    /*localStorage.setItem('jwtToken',token)*/
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


})



