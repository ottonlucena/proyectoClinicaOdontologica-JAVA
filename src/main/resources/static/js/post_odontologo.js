document.addEventListener("DOMContentLoaded", function () {
  // Tu código aquí se ejecutará cuando el DOM esté completamente cargado
  console.log("El DOM ha sido completamente cargado.");

  const d = document;
  const formulario = d.querySelector("#agregar_odontologo");

  formulario.addEventListener("submit", (e) => {
    e.preventDefault();
    const formData = {
      nombre: d.querySelector("#nombre").value,
      apellido: d.querySelector("#apellido").value,
      matricula: d.querySelector("#matricula").value,
    };

    const url = "/odontologo";
    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);

      })
      .catch((error) => {
        console.log(error);
      });
  });
});
