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

// Função para fazer a requisição para o endpoint findAllBooks
async function fetchBooks() {
    const token = getToken();
    if (!token) {
        console.error('Token não encontrado no localStorage');
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/api/books', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (response.ok) {
            const data = await response.json();
            if (data && data.length > 0) {
                // Limpar a tabela
                const tableBody = document.getElementById('bookTableBody');
                tableBody.innerHTML = '';

                // Preencher a tabela com os dados dos livros
                data.forEach(book => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.description}</td>
                        <td>${book.price}</td>
                    `;
                    tableBody.appendChild(row);

                    showMessageBook('Livro(s) encontrado(s)', true);
                });
            } else {
                console.log('Nenhum livro encontrado');
            }
        } else if (response.status === 404) {
            console.log('Livro não encontrado');
            showMessageBook('Livro(s) não encontrad(o)', false); 
        } else {
            console.error('Erro ao buscar livros:', response.status);
        }
    } catch (error) {
        console.error('Erro ao buscar livros:', error);
    }
}

// Chamar a função fetchBooks para buscar os livros ao carregar a página
fetchBooks();
