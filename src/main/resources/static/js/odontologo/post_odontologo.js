document.addEventListener("DOMContentLoaded", function () {
  // Tu código aquí se ejecutará cuando el DOM esté completamente cargado
  console.log("El DOM ha sido completamente cargado.");
  //creamos uns constante document d
  const d = document;
  //seleccionamos mediante el id el formulario
  const formulario = d.querySelector("#agregar_odontologo");

  formulario.addEventListener("submit", (e) => {
    e.preventDefault();
    //capturamos los value de los input
    const nombre = d.querySelector("#nombre").value;
    const apellido = d.querySelector("#apellido").value;
    const matricula = d.querySelector("#matricula").value;

    //Validamos que nombre y apellido sean de tipo String
    if (!/^[a-zA-Z]+$/.test(nombre) || !/^[a-zA-Z]+$/.test(apellido)) {
      respuesta(
        "Los campos nombre y apellido deben contener solo letras",
        "warning"
      );
      return;
    }

    let matriculaNumber = parseInt(matricula);
    if (!/^[0-9]+$/.test(matriculaNumber)) {
      respuesta("El campo matricula debe ser número");
      return;
    }

    const formData = {
      nombre: nombre,
      apellido: apellido,
      matricula: matriculaNumber,
    };

    const url = "/odontologo";
    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    //Creamos un funcion respuesta con 2 parametro para reutilizarla
    function respuesta(mensaje, tipo) {
      const alertHTML = `<div class="alert alert-${tipo} alert-dismissible">
                           <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>${mensaje}</strong> </div>`;

      const respuestaElemento = d.querySelector("#respuesta");
      respuestaElemento.innerHTML = alertHTML;
      respuestaElemento.style.display = "block";
    }

    function resetInput() {
      document.querySelector("#nombre").value = "";
      document.querySelector("#apellido").value = "";
      document.querySelector("#matricula").value = "";
    }

    fetch(url, settings)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error en la solicitud");
        }
        return response.json();
      })
      .then((data) => {
        //console.log(data);
        respuesta(
          `Odontologo nombre: ${data.nombre}, matricula: ${data.matricula} agregado correctamente.`,
          "success"
        );
        resetInput();
      })
      .catch((error) => {
        console.log("Error al cargar un odontologo", error);
        respuesta("Error, intenta nuevamente", "danger");
        resetInput();
      });
  });
});
