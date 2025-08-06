const api = "http://localhost:8080/auth";

document.addEventListener('DOMContentLoaded', () => {
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
})