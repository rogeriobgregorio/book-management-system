// Função para pegar o token do localStorage
function getToken() {
    return localStorage.getItem('token');
}

// Função para exibir mensagens de livro
const showMessageBookId = (message, isSuccess = true) => {
    const messageElement = document.createElement("p");
    messageElement.textContent = message;
    messageElement.style.color = isSuccess ? "green" : "red";
    const messagesContainerBook = document.getElementById("messagesBookId");
    messagesContainerBook.innerHTML = "";
    messagesContainerBook.appendChild(messageElement);
};

function updateFormFields(bookData) {
    document.getElementById("id").value = bookData.id;
    document.getElementById("title").value = bookData.title;
    document.getElementById("author").value = bookData.author;
    document.getElementById("description").value = bookData.description;
    document.getElementById("price").value = bookData.price;
}

function clenFormFields() {
    document.getElementById("id").value = "";
    document.getElementById("title").value = "";
    document.getElementById("author").value = "";
    document.getElementById("description").value = "";
    document.getElementById("price").value = "";
}

// Função para buscar livro por ID
async function fetchBookById() {
    const bookId = document.getElementById('bookId').value;
    const token = getToken();

    if (!token) {
        console.error('Token não encontrado no localStorage');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/books/${bookId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (response.ok) {
            const data = await response.json();
            if (data) {
                // Limpar a tabela
                const tableBody = document.getElementById('bookTableBody');
                tableBody.innerHTML = '';

                // Preencher a tabela com os dados do livro encontrado
                const row = document.createElement('tr');
                row.innerHTML = `
                            <td>${data.id}</td>
                            <td>${data.title}</td>
                            <td>${data.author}</td>
                            <td>${data.description}</td>
                            <td>${data.price}</td>
                        `;
                tableBody.appendChild(row);

                // Preencher os inputs do formulário com os dados do livro encontrado
                updateFormFields(data);

                const messagesContainerBook = document.getElementById("messagesBook");
                messagesContainerBook.innerHTML = '';

                showMessageBookId('Livro encontrado', true);
            } else {
                console.log('Livro não encontrado');
            }
        } else if (response.status === 404) {
            console.log('Livro não encontrado');
            showMessageBookId('Livro não encontrado', false);
            const tableBody = document.getElementById('bookTableBody');
            tableBody.innerHTML = '';
            clenFormFields();
        } else {
            console.error('Erro ao buscar livro:', response.status);
        }
    } catch (error) {
        console.error('Erro ao buscar livro:', error);
    }
}

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
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(bookData),
            });

            if (response.status === 200) {
                console.log("Atualização bem-sucedido");
                showMessageBook("Atualização bem-sucedida", true);
            } else if (response.status === 403) {
                console.log('Não autorizado');
                showMessageBook('Não autorizado', false);
            } else {
                console.error("Erro ao atualizar");
                showMessageBook("Erro ao atualizar", false);
            }
        } catch (error) {
            console.error("Erro ao atualizar: " + error);
            showMessageBook("Erro ao atualizar: " + error, false);
        }
    };

    // Adicionar um ouvinte de evento para o envio do formulário
    bookForm.addEventListener("submit", async (event) => {
        event.preventDefault(); // Impede o comportamento padrão do formulário

        const id = document.getElementById("id").value;
        const title = document.getElementById("title").value;
        const author = document.getElementById("author").value;
        const description = document.getElementById("description").value;
        const price = document.getElementById("price").value;

        const bookData = {
            id,
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