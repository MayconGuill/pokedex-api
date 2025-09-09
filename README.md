# Pokedex API

**Projeto pessoal**: API de consulta e importação de Pokémons, construída em Java com Spring Boot.  

A Pokedex API consome dados da [PokeAPI](https://pokeapi.co/), armazena localmente no banco e fornece informações detalhadas sobre os Pokémons, incluindo **estatísticas, tipos, altura, peso e imagem**.

---

## Funcionalidades

- Consultar Pokémon pelo **nome**.
- Importar Pokémons da **PokeAPI** para o banco local.
- Retornar **estatísticas (stats), tipos, altura, peso e imagens**.
- Paginação de Pokémons (ex: listar 10 por vez e botão “Mostrar mais”).
- Tratamento de exceções para Pokémon não encontrado.
- Mantém consistência com padrão **REST**.

---

## Tecnologias

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database** (PostgreSQL em produção)
- **WebClient** para consumir a PokeAPI
- **Flyway** para versionamento de banco de dados
- **Lombok** para reduzir boilerplate
- **Maven** como gerenciador de dependências

---

## Branches

Atualmente o projeto conta com as seguintes branches:

- `main`: branch principal, estável.
- `feature/consume-pokeapi`: branch de desenvolvimento atual, onde estão sendo implementadas as integrações com a PokeAPI antes do merge para a `main`.

---

## Estrutura do Projeto

📂 src/main/java/com/pokedex/api  
 ┣ 📂 config        → Configuração do WebClient  
 ┣ 📂 controller    → Endpoints REST  
 ┣ 📂 dto           → Data Transfer Objects  
 ┣ 📂 exception     → Exceções personalizadas  
 ┣ 📂 infra         → Handlers globais de erro  
 ┣ 📂 model/domain  → Entidades do banco  
 ┣ 📂 repository    → Interfaces de persistência  
 ┗ 📂 service       → Regras de negócio  

---

## Endpoints principais

| Método | URL | Descrição |
|--------|-----|-----------|
| GET    | `/api/pokemon/{name}` | Consulta Pokémon no banco de dados pelo nome. |
| POST   | `/api/pokemon/{name}` | Importa Pokémon da PokeAPI e salva no banco. |
| GET    | `/api/pokemon?page=X&size=Y` | Lista Pokémons com paginação. |
| GET    | `/api/pokemon/greatest` | Consulta Pokémon no banco de dados do maior Id para o menor. |
| GET    | `/api/pokemon/smallest` | Consulta Pokémon no banco de dados do menor Id para o maior. |

---

## Observações

- Atualmente o projeto é back-end, mas está estruturado para futura integração com front-end.
- As imagens são fornecidas via URLs da PokeAPI.
- Implementa padrão REST e tratamento de erros consistente.
- Back-end ainda não finalizado.
