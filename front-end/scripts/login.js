document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.getElementById("registerForm");
    const loginForm = document.getElementById("loginForm");
    const formTitle = document.getElementById("formTitle");
    const toggleFormButton = document.getElementById("toggleFormButton");
    const messagesContainerRegister = document.getElementById("messagesRegister");
    const messagesContainerLogin = document.getElementById("messagesLogin");

    // Função para exibir mensagens de registro
    const showMessageRegister = (message, isSuccess = true) => {
        const messageElement = document.createElement("p");
        messageElement.textContent = message;
        messageElement.style.color = isSuccess ? "green" : "red";
        messagesContainerRegister.innerHTML = "";
        messagesContainerRegister.appendChild(messageElement);
    };

    // Função assíncrona para enviar dados de registro
    const register = async (registerData) => {
        try {
            const response = await fetch("http://localhost:8080/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(registerData),
            });

            if (response.status === 200) {
                console.log("Registro bem-sucedido");
                showMessageRegister("Cadastro bem-sucedido", true);
            } else {
                console.error("Erro no registro");
                showMessageRegister("Erro no cadastro", false);
            }
        } catch (error) {
            console.error("Erro no registro: " + error);
            showMessageRegister("Erro no cadastro: " + error, false);
        }
    };

    // Função para exibir mensagens de login
    const showMessageLogin = (message, isSuccess = true) => {
        const messageElement = document.createElement("p");
        messageElement.textContent = message;
        messageElement.style.color = isSuccess ? "green" : "red";
        messagesContainerLogin.innerHTML = "";
        messagesContainerLogin.appendChild(messageElement);
    };

    // Função assíncrona para enviar dados de login
    const login = async (loginData) => {
        try {
            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(loginData),
            });

            if (response.status === 200) {
                const data = await response.json();

                // Armazene o token no localStorage
                if (data.token) {
                    localStorage.setItem("token", data.token);
                    console.log("Token armazenado no localStorage");

                    // Redirecionar para a página index.html após o login bem-sucedido
                    window.location.href = "http://127.0.0.1:5500/bookmanagementsystem/pages/index.html";
                }
            } else {
                console.error("Erro no login");
                showMessageLogin("Erro no login", false);
            }
        } catch (error) {
            console.error("Erro no login: " + error);
            showMessageLogin("Erro no login: " + error, false);
        }
    };

    // Manipulador de evento para alternar entre os formulários
    toggleFormButton.addEventListener("click", () => {
        if (registerForm.style.display === "none") {
            registerForm.style.display = "block";
            loginForm.style.display = "none";
            formTitle.textContent = "Cadastro";
            toggleFormButton.textContent = "Ir para Login";
            switchTitle.textContent = "Já tem conta? Faça login";
        } else {
            loginForm.style.display = "block";
            registerForm.style.display = "none";
            formTitle.textContent = "Login";
            toggleFormButton.textContent = "Ir para cadastro";
            switchTitle.textContent = "Não tem conta? Cadastre-se";
        }
    });

    // Manipuladores de eventos para os formulários
    registerForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const registerData = {
            login: document.getElementById("registerLogin").value,
            password: document.getElementById("registerPassword").value,
        };

        register(registerData);
    });

    loginForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const loginData = {
            login: document.getElementById("login").value,
            password: document.getElementById("password").value,
        };

        login(loginData);
    });
});