async function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const payload = {
        username: username,
        password: password
    };

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    };

    

    try {
        const response = await fetch('http://localhost:8080/api/auth/login', requestOptions);
        if (response.status === 200) {
            const data = await response.json();
            console.log("Login successful", data);
            // Store the JWT token or navigate to another page
        } else {
            console.log("Login failed");
        }
    } catch (error) {
        console.error("There was an error logging in", error);
    }
}
