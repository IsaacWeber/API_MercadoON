const form = document.getElementById("cadastro_form");
const imagensTD = document.getElementById('imagens_td');
const divImagensTD = document.getElementById('div_imagens_enviadas');

const divNomesImagens = [];
let contadorImagens = 0;

const LIMITE_IMAGENS = 3;
const botoesImagens = [];
let inputEscolher = null;
let divNomeImagem = null;

for(let i = 0; i < LIMITE_IMAGENS; ++i){
  inputEscolher = document.createElement('input');
  inputEscolher.setAttribute('type', 'file');
  inputEscolher.setAttribute('name', 'arquivos');
  inputEscolher.setAttribute('accept', '.gif, .jpg, .jpeg, .png');
  inputEscolher.setAttribute('multiple', 'yes');
  botoesImagens.push(inputEscolher);

  divNomeImagem = document.createElement('div');
  divNomeImagem.setAttribute('class', 'div_nomes_imagens');
  //divNomeImagem.innerText = "TEST";
  divNomesImagens.push(divNomeImagem);
  divImagensTD.appendChild(divNomeImagem);
}



for(let i = 0; i < LIMITE_IMAGENS; ++i) { // Colocando Handlers
  // Handler para botoes imagem
  botoesImagens[i].onchange = () => {
    if(!ehUltimoBotaoImagem()) {
      // Muda seletor de imagens do lugar
      colocarForm(botoesImagens[i]);
      colocarTD(pegarBotaoImagensDisponivel());
    }

    ++contadorImagens;
    atualizarImagensEnviadas(botoesImagens[i].files[0].name);

    if(contadorImagens == LIMITE_IMAGENS) {
      desabilitarBotaoImagem();
    }
  }

  // Handler para div botoes imagem
  divNomesImagens[i].onclick = () => {
    for(let j = 0; j < botoesImagens.length; ++j) {
      if(botoesImagens[j].files[0] != null && botoesImagens[j].files[0].name === divNomesImagens[i].innerText) {
        divNomesImagens[i].innerText = '';
        botoesImagens[j].value = '';

        colocarForm(document.querySelector('#imagens_td input'));
        colocarTD(botoesImagens[j]);

        --contadorImagens;
        break;
      }
    }
  }

}

imagensTD.appendChild(botoesImagens[0]);

function desabilitarBotaoImagem() {
  document.querySelector('#imagens_td input').disabled = true;
}

function colocarTD(e) {
  e.disabled = false;
  e.style.opacity = '1';
  imagensTD.appendChild(e);
}

function colocarForm(e) {
  e.disabled = true;
  e.style.opacity = '0';
  form.appendChild(e);
}

function ehUltimoBotaoImagem() {
  for(let i = 0; i < botoesImagens.length; ++i) {
    if(botoesImagens[i].files[0] == null) {
      return false;
    }
  }

  return true;
}

function atualizarImagensEnviadas(nome) {
  for(let i = 0; i < divNomesImagens.length; ++i) {
    if(divNomesImagens[i].innerText === '') {
      divNomesImagens[i].innerText = nome;
      break;
    }
  }
}

function pegarBotaoImagensDisponivel() {
  for(let i = 0; i < botoesImagens.length; ++i) {
    if(botoesImagens[i].files[0] == null) {
      return botoesImagens[i];
    }
  }
}

function prepararImagens(produtoJson) {
  for(let i = 0; i < botoesImagens.length; ++i) {
    botoesImagens[i].disabled = false;
  }
  
  form.action = '/api/arquivo/upload/' + produtoJson.id;
  form.onsubmit = () => {
    window.location.replace("/produto/cadastrado");
  }
  
  form.submit();
}

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
        response.json().then(produtoJson => {
          if(produtoJson != null && contadorImagens > 0) {
            prepararImagens(produtoJson);
          }
        });

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