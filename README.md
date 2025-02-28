## API E-Commerce | Mercado ON
### USUÁRIO

* **Listar**
```http
  GET /api/usuario
```

| Descrição               |
:------------------------|
| Lista todos os usuários |

* **Buscar**

```http
  GET /api/usuario/{id}
```

| Parâmetro | Tipo     | Descrição              |
| :-------- | :------- |:-----------------------|
| `id`      | `string` | Busca usuário por `id` |

* **Adicionar**

```http
  POST /api/usuario/{id}
```


| Parâmetro 1 | Tipo     | Parâmetro 2      | Tipo  | Descrição                                                   | 
|:------------|:---------|:-----------------| :------- |:-----------|
| `usuarioId` | `string` | `Body`      | `JSON` | Cadastra usuário |


_Exemplo **Body**_
```json
{
  "email": "general@email.com",
  "senha": "b@123"
}
```

* **Atualizar**

```http
  PUT /api/usuario/{id}
```

| Parâmetro 1 | Tipo | Parâmetro 2 |Tipo     | Descrição                 |
| :-------- | :------ | :------- | :-------------------------------- |:--------------------------|
| `id`      | `string` | `Body`|`JSON` | Atualiza usuário por `id` |

_Exemplo **Body**_
```json
{
  "email": "maicom@email.com"
}
```

* **Deletar**

```http
  DELETE /api/usuario/{id}
```

| Parâmetro | Tipo     | Descrição               |
| :-------- | :------- |:------------------------|
| `id`      | `string` | Deleta usuário por `id` |

* **Deletar Todos**
```http
  DELETE /api/usuario
```

| Descrição                |
:-------------------------|
| Deleta todos os usuários |

### CLIENTE

* **Listar**
```http
  GET /api/cliente
```

| Descrição                |
:------------------------- |
| Lista todos os clientes|

* **Buscar**

```http
  GET /api/cliente/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Busca cliente por `id` |

* **Adicionar**

```http
  POST /api/cliente/{usuarioId}
```


| Parâmetro 1 | Tipo     | Parâmetro 2      | Tipo  | Descrição                                                 | 
|:------------|:---------|:-----------------| :------- |:----------------------------------------------------------|
| `usuarioId` | `string` | `Body`      | `JSON` | Cadastra cliente com o usuário especificado por `usuarioId` |


_Exemplo **Body**_
```json
{
    "nome": "Laura",
    "sobrenome": "Ferreira",
    "endereco": "Brasília/DF"
}
```

* **Atualizar**

```http
  PUT /api/cliente/{id}
```

| Parâmetro 1 | Tipo | Parâmetro 2 |Tipo     | Descrição                       |
| :-------- | :------ | :------- | :-------------------------------- | :------ |
| `id`      | `string` | `Body`|`JSON` | Atualiza cliente por `id` |

_Exemplo **Body**_
```json
{
    "nome": "Laura",
    "sobrenome": "da Silva",
    "endereco": "Santos/SP"
}
```

* **Deletar**

```http
  DELETE /api/cliente/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Deleta cliente por `id` |

* **Deletar Todos**
```http
  DELETE /api/cliente
```

| Descrição                |
:------------------------- |
| Deleta todos os clientes|


### CARTÃO

* **Listar**
```http
  GET /api/cartao
```

| Descrição                |
:------------------------- |
| Lista todos os cartões|

* **Buscar**

```http
  GET /api/cartao/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Busca cartão por `id` |

* **Adicionar**

```http
  POST /api/cartao/{cliente_id}
```

| Parâmetro 1 | Tipo | Parâmetro 2 |Tipo     | Descrição                       |
| :-------- | :------ | :------- | :-------------------------------- | :------ |
| `cliente_id`      | `string` | `Body`|`JSON` | Cadastra cartão para o cliente de id: `cliente_id` |

_Exemplo **Body**_
```json
{
    "nomeUsuario": "JULIO PEREIRA",
    "numero": "9977 4677 9686 3234",
    "cvv":"299",
    "validade":"05/27",
    "bandeira": "1",
    "funcao": "0"
}
```

* **Atualizar**

```http
  PUT /api/cartão/{id}
```

| Parâmetro 1 | Tipo | Parâmetro 2 |Tipo     | Descrição                       |
| :-------- | :------ | :------- | :-------------------------------- | :------ |
| `id`      | `string` | `Body`|`JSON` | Atualiza cartão por `id` |

_Exemplo **Body**_
```json
{
    "nomeUsuario": "ALEXANDRE WILLIAM",
    "numero": "9977 4677 9686 3234",
    "cvv":"299",
    "validade":"05/27",
    "bandeira": "1",
    "funcao": "1"
}
```

* **Deletar**

```http
  DELETE /api/cartao/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Deleta cartão por `id` |

* **Deletar Todos**
```http
  DELETE /api/cartao
```

| Descrição                |
:------------------------- |
| Deleta todos os cartões |


### PRODUTO

* **Listar**
```http
  GET /api/produto
```

| Descrição                |
:------------------------- |
| Lista todos os produtos |

* **Buscar**

```http
  GET /api/produto/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Busca produto por `id` |

* **Adicionar**

```http
  POST /api/produto/{cliente_id}
```


| Parâmetro 1 | Tipo | Parâmetro 2 |Tipo     | Descrição                       |
| :-------- | :------ | :------- | :-------------------------------- | :------ |
| `cliente_id` | `string` | `Body`|`JSON` | Adiciona produto para `cliente_id` |


_Exemplo **Body**_
```json
{
    "nome": "Computador",
    "marca": "ASUS",
    "categoria": "0",
    "modelo": "ASUS VivoBook",
    "cor": "Cinza",
    "descricao": "Computador ASUS VivoBook em excelentes condições",
    "descricaoTecnica": "SSD 1 TB, 8 GB RAM, PLACA DE VÍDEO NVDIA ...",
    "preco": 3000.00
}
```

* **Atualizar**

```http
  PUT /api/produto/{id}
```

| Parâmetro 1 | Tipo | Parâmetro 2 |Tipo     | Descrição                       |
| :-------- | :------ | :------- | :-------------------------------- | :------ |
| `id`      | `string` | `Body`|`JSON` | Atualiza produto por `id` |

_Exemplo **Body**_
```json
{
    "nome": "Computador",
    "marca": "DELL",
    "categoria": "0",
    "modelo": "DELL 323X33",
    "cor": "Cinza",
    "descricao": "Computador DELL em excelentes condições",
    "descricaoTecnica": "SSD 1 TB, 8 GB RAM, PLACA DE VÍDEO NVDIA ...",
    "preco": 3000.00
}
```

* **Deletar**

```http
  DELETE /api/produto/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Deleta produto por Id |

* **Deletar Todos**
```http
  DELETE /api/produto
```

| Descrição                |
:------------------------- |
| Deleta todos os produtos |


### COMPRA

* **Listar**
```http
  GET /api/compra
```

| Descrição                |
:------------------------- |
| Lista todas as compras |

* **Buscar**

```http
  GET /api/compra/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Busca compra por `id` |

* **Adicionar**

```http
  POST /api/compra
```


| Parâmetro | Tipo     | Descrição                       | 
| :-------- | :------- | :-------------------------------- |
| `Body`      | `JSON` | Cadastra compra |


_Exemplo **Body**_
```json
{
    "clienteId": "36",
    "cartaoId": "18",
    "produtosId" : ["12", "11"]
}
```

* **Atualizar**

```http
  PUT /api/compra/{id}
```

| Parâmetro 1 | Tipo | Parâmetro 2 |Tipo     | Descrição                       |
| :-------- | :------ | :------- | :-------------------------------- | :------ |
| `id`      | `string` | `Body`|`JSON` | Atualiza compra por `id` |

_Exemplo **Body**_
```json
{
    "clienteId": "36",
    "cartaoId": "18",
    "produtosId" : ["10"]
}
```

* **Deletar**

```http
  DELETE /api/compra/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Deleta compra por `id` |

* **Deletar Todas**
```http
  DELETE /api/compra
```

| Descrição                |
:------------------------- |
| Deleta todas as compras |


### ARQUIVO

* **Upload**

```http
  GET /api/arquivo/upload
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `Body`      | `multipart/form-data` | Upload de arquivo multipart |

* **Download**

```http
  GET /api/arquivo/download/{id}
```

| Parâmetro | Tipo     | Descrição                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | Download de arquivo multipart de `id` |

## Versões
* Java 17, Spring Boot 3.2.4
