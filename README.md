# Sistema de Gerenciamento de Produtos com Spring Boot

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.03-green)

## Sobre o Projeto

Sistema CRUD completo para gerenciamento de produtos, desenvolvido com **Java Spring Boot** no backend e **HTML/CSS/JavaScript** puro no frontend. É apenas uma base que vou continuar evoluindo, o foco foi totalmente no aprendizado do back-end com Java e Spring Boot, para o front-end utilizei de auxílio de IA. A ideia é construir um projeto sólido enquanto aprendo na prática!

###  Funcionalidades

- Criar, ler, atualizar e deletar produtos
- Busca de produtos por nome
- Validações de campos obrigatórios
- Interface web integrada
- Arquitetura em camadas (Controller, Service, Repository)

##  Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 4.03**
- **Spring Data JPA**
- **H2**
- **MySQL**
- **Maven**

### Frontend
- **HTML5**
- **CSS3**
- **JavaScript (Vanilla)**
- **Fetch API**

### Banco de Dados
- **MySQL** (desenvolvimento local)
- **H2 Database** (testes e demonstração)


## Como Executar o Projeto

### Pré-requisitos

- **Java 17** ou superior
- **Maven**

### Passo a Passo



1. Clone o repositório
   ```
   git clone https://github.com/luzyaga/learningSpringboot.git
   cd nobsv2
   
2. Execute o projeto
   ```
   ./mvnw spring-boot:run

4. Acesse a aplicação
  
   Front-End: Recomendo usar um servidor local na porta 3000:

     
     Com Python:
     ```
     python -m http.server 3000
     ```
     Com VS Code (Live Server):
        
     O frontend (index.html) está localizado em:
     src/main/resources/static/index.html
     - Instale a extensão Live Server
     - Clique com botão direito no index.html
     - "Open with Live Server" (abre na porta 5500 por padrão)
     - Configure o Live Server para porta 3000:
       No VS Code: Ctrl+Shift+P → Preferences: Open Settings (JSON)
       Adicione: "liveServer.settings.port": 3000
   
   - Acesse: http://localhost:3000
   
   API: http://localhost:8080/api/products

5. Console do banco H2 (opcional)
   - URL: http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:nobsv2
   - Usuário: sa
   - Senha: (deixe em branco)

## Endpoints da API

- **GET** `/api/products` - Lista todos os produtos
- **GET** `/api/product/{id}` - Busca produto por ID
- **GET** `/api/product/search?name={nome}` - Busca produtos por nome
- **POST** `/api/product` - Cria um novo produto
- **PUT** `/api/product/{id}` - Atualiza um produto
- **DELETE** `/api/product/{id}` - Remove um produto

## Exemplo de requisição POST
{
  "name": "iPhone 14",
  
  "description": "Smartphone Apple com tela de 6.1 polegadas, câmera dupla e processador A15",
  
  "price": 4999.99
}

## Validações
- Nome: obrigatório
- Descrição: mínimo de 20 caracteres
- Preço: deve ser positivo ou zero
