function validar (){
var usuario = document.querySelector("#usuario");
var email = document.querySelector("#email");
var senha = document.querySelector("#senha");

if(usuario.value === "" || email.value === "" || senha.value === ""){
    alert("Preencha todos os campos");
}else{
    alert("Usuário cadastrado com sucesso");
}

}
