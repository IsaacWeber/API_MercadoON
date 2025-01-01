const imagens = [];
const elementoImagens = document.getElementById("imagens");

elementoImagens.onchange = () => {
  imagens.push(elementoImagens.files[0]);
};

function cadastrar() {
  
  let nome = document.getElementById("nome").value;
  let marca = document.getElementById("marca").value;
  let categoria = document.getElementById("categoria").value;
  let modelo = document.getElementById("modelo").value;
  let cor = document.getElementById("cor").value;
  let descricao = document.getElementById("descricao").value;
  let descricaoTecnica = document.getElementById("descricao_tecnica").value;
  let preco = document.getElementById("preco").value;

  let erroDiv = document.getElementById("erro");
  
  // Cadastra Produto
  fetch("/api/produto", {
      method: "POST",
      body: JSON.stringify({
          "nome": nome,
          "marca": marca,
          "categoria": categoria,
          "modelo": modelo,
          "cor": cor,
          "descricao": descricao,
          "descricaoTecnica": descricaoTecnica,
          "preco": preco
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8"
      }
    })
    .then((response) => {
      if(response.status == 201) {
        window.location.replace("/produto/cadastrado")
      } else { // tem erro
        response.json().then(json => {
          erroDiv.innerText = (json != null && json.campos != null) 
                  ? "* " + json.campos[0].mensagem
                  : "* Erro de API: { mensagem: " + json.mensagem + ", status: " + json.status + " }"
          });
      } 
  }); 
  /* cadastro de imagem (não funciona)
  let formData = new FormData();
  formData.append("arquivos", imagens);

  const produtoId = 11; // MODIFICAR PRODUTO ID
  fetch("/api/arquivo/upload/" + produtoId, {
      method: "POST",
      body: formData
    },
  ).then(response => {
    alert(response.status);
    response.json().then(json => console.log(json));
  });
  */

}