//Capturamos el button mediante el id
const buttoSesion = document.querySelector("#cerrar_sesion");
const urlLogout = "http://localhost:8080/logout";

//hacemos el evento para cuando hagan click sobre el button este se diriga al logout
buttoSesion.addEventListener("click", (e) => {
  window.location.href = urlLogout;
});
