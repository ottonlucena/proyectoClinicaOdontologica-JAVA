function actualizarOdontologo(data) {
  const form = document.querySelector("#update-odontologo");
  const d = document;

  form.addEventListener("submit", function (e) {
    //Creamos el formData
    const formData = {
      id: data.id,
      nombre: d.querySelector("#nombre").value,
      apellido: d.querySelector("#apellido").value,
      matricula: d.querySelector("#matricula").value,
    };

    console.log(formData);
    const url = "/odontologo/actualizar";
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => {
        return response.text();
      })
      .then((data) => {
        console.log(data);
        //window.location.href = "http://localhost:8080/pacientes.html";
      })
      .catch((error) => {
        console.log("Error al actualizar", error);
      });
  });
}
