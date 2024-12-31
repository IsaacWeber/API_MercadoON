function cadastrar() {
    let nomeUsuario = document.getElementById("nome_usuario").value;
    let numero = document.getElementById("numero").value;
    let cvv = document.getElementById("cvv").value;
    let validade = document.getElementById("validade").value;
    let bandeira = document.getElementById("bandeira").value;
    let funcao = document.getElementById("funcao").value;;

    let erroDiv = document.getElementById("erro");

    let clienteId = 51; // ID DE CLIENTE FIXO (ALTERAR POR CLIENTE LOGADO NA SESSÃO)

    fetch("/api/cartao/" + clienteId, {
        method: "POST",
        body: JSON.stringify({
            "nomeUsuario": nomeUsuario,
            "numero": numero,
            "cvv":cvv,
            "validade": validade,
            "bandeira": bandeira,
            "funcao": funcao
        }),
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
      })
      .then((response) => {
        if(response.status == 201) {
          window.location.replace("/cartao/cadastrado")
        } else { // tem erro
          response.json().then(json => {
            erroDiv.innerText = (json != null && json.campos != null) 
                    ? "* " + json.campos[0].mensagem
                    : "* Erro de API: { mensagem: " + json.mensagem + ", status: " + json.status + " }"
            });
        } 
      }); 

}