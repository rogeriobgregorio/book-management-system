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

// Função para buscar livro por ID e deletar
async function fetchDeleteBook() {
    const bookId = document.getElementById('bookId').value;
    const token = getToken();

    if (!token) {
        console.error('Token não encontrado no localStorage');
        showMessageBook('Token não encontrado no localStorage', false);
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/books/${bookId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (response.ok) {
            console.log('Livro deletado com sucesso');
            showMessageBook('Livro deletado com sucesso', true);
        } else if (response.status === 404) {
            console.log('Livro não encontrado');
            showMessageBook('Livro não encontrado', false);
        } else if (response.status === 403) {
            console.log('Não autorizado');
            showMessageBook('Não autorizado', false);
        } else {
            console.error('Erro ao deletar livro:', response.status);
            showMessageBook('Erro ao deletar livro', false);
        }
    } catch (error) {
        console.error('Erro ao deletar livro:', error);
        showMessageBook('Erro ao deletar livro', false);
    }
}