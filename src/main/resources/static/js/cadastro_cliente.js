function cadastrar() {
    
    let nome = document.getElementById("nome").value;
    let sobrenome = document.getElementById("sobrenome").value;
    let email = document.getElementById("email").value;
    let cpf = document.getElementById("cpf").value;
    let endereco = document.getElementById("endereco").value;
    let senha = document.getElementById("senha").value;
    let confSenha = document.getElementById("confirma_senha").value;

    let erroDiv = document.getElementById("erro");
    
    if(senha === confSenha) {
      fetch("/api/cliente", {
        method: "POST",
        body: JSON.stringify({
            "nome": nome,
            "sobrenome": sobrenome,
            "cpf": cpf,
            "email": email,
            "endereco": endereco,
            "senha": senha
        }),
        headers: {
          "Content-type": "application/json; charset=UTF-8"
        }
      })
      .then((response) => {
        if(response.status == 201) {
          window.location.replace("/cliente/cadastrado")
        } else { // tem erro
          response.json().then(json => {
            erroDiv.innerText = (json != null && json.campos != null) 
                    ? "* " + json.campos[0].mensagem
                    : "* Erro de API: { mensagem: " + json.mensagem + ", status: " + json.status + " }"
            });
        }
      }); 
    
    } else {
      erroDiv.innerText = "* As senhas não correspondem";
    }
}