function actualizarPaciente(data) {
  const form = document.querySelector("#update-paciente");
  const d = document;

  form.addEventListener("submit", function (e) {
    //Prevenimos su comportamiento por default
    e.preventDefault();

    //Creamos el formData
    const formData = {
      id: data.id,
      nombre: d.querySelector("#nombre").value,
      apellido: d.querySelector("#apellido").value,
      cedula: d.querySelector("#cedula").value,
      fechaIngreso: d.querySelector("#fecha").value,
      email: d.querySelector("#email").value,
      domicilio: {
        calle: d.querySelector("#calle").value,
        numero: d.querySelector("#numero").value,
        localidad: d.querySelector("#localidad").value,
        provincia: d.querySelector("#provincia").value,
      },
    };

    //console.log(formData);

    const url = "/paciente/actualizar";
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
        //console.log(data);
        window.location.href = "http://localhost:8080/pacientes.html";
      })
      .catch((error) => {
        console.log("Error al actualizar", error);
      });
  });
}
