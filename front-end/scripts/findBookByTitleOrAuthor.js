// Função para pegar o token do localStorage
function getToken() {
    return localStorage.getItem('token');
}

// Função para buscar livros por título ou autor
async function fetchBooks() {
    const searchTerm = document.getElementById('searchTerm').value;
    const token = getToken();

    if (!token) {
        console.error('Token não encontrado no localStorage');
        return;
    }

    try {
        const response = await fetch(`http://localhost:8080/api/books/search?titleOrAuthor=${searchTerm}`, {
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

                // Preencher a tabela com os dados dos livros encontrados
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
                });
            } else {
                console.log('Nenhum livro encontrado');
            }
        } else {
            console.error('Erro ao buscar livros:', response.status);
        }
    } catch (error) {
        console.error('Erro ao buscar livros:', error);
    }
}
