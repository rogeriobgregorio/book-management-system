// Função para pegar o token do localStorage
function getToken() {
    return localStorage.getItem('token');
}

// Função para exibir mensagens de livro
const showMessageBook = (message, isSuccess = true) => {
    const messageElement = document.createElement("p");
    messageElement.textContent = message;
    messageElement.style.color = isSuccess ? "green" : "red";
    const messagesContainerBook = document.getElementById("messagesBook");
    messagesContainerBook.innerHTML = "";
    messagesContainerBook.appendChild(messageElement);
};

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

                showMessageBook('Livro encontrado', true);
            } else {
                console.log('Livro não encontrado');
            }
        } else if (response.status === 404) {
            console.log('Livro não encontrado');
            showMessageBook('Livro não encontrado', false);
            const tableBody = document.getElementById('bookTableBody');
            tableBody.innerHTML = '';
        } else {
            console.error('Erro ao buscar livro:', response.status);
        }
    } catch (error) {
        console.error('Erro ao buscar livro:', error);
    }
}