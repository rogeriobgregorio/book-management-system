document.addEventListener("DOMContentLoaded", () => {
    const bookForm = document.getElementById("bookForm");
    const messagesContainerBook = document.getElementById("messagesBook");

    // Função para exibir mensagens de livro
    const showMessageBook = (message, isSuccess = true) => {
        const messageElement = document.createElement("p");
        messageElement.textContent = message;
        messageElement.style.color = isSuccess ? "green" : "red";
        messagesContainerBook.innerHTML = "";
        messagesContainerBook.appendChild(messageElement);
    };

    /// Função assíncrona para enviar dados do livro
    const registerBook = async (bookData) => {
        try {
            const token = getToken(); // Get token from localStorage
            if (!token) {
                console.error('Token não encontrado no localStorage');
                return;
            }

            const response = await fetch("http://localhost:8080/api/books", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(bookData),
            });

            if (response.status === 200) {
                console.log("Cadastro bem-sucedido");
                showMessageBook("Cadastro bem-sucedido", true);
            } else if (response.status === 403) {
                console.log('Não autorizado');
                showMessageBook('Não autorizado', false);
            } else {
                console.error("Erro no cadastro");
                showMessageBook("Erro no cadastro", false);
            }
        } catch (error) {
            console.error("Erro no cadastro: " + error);
            showMessageBook("Erro no cadastro: " + error, false);
        }
    };

    // Adicionar um ouvinte de evento para o envio do formulário
    bookForm.addEventListener("submit", async (event) => {
        event.preventDefault(); // Impede o comportamento padrão do formulário

        const title = document.getElementById("title").value;
        const author = document.getElementById("author").value;
        const description = document.getElementById("description").value;
        const price = document.getElementById("price").value;

        const bookData = {
            title,
            author,
            description,
            price,
        };

        // Chama a função para enviar os dados do livro
        await registerBook(bookData);
    });

    // Função para pegar o token do localStorage
    function getToken() {
        return localStorage.getItem('token');
    }
});
