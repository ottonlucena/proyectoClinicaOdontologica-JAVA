document.addEventListener("DOMContentLoaded", function () {
  // Tu código aquí se ejecutará cuando el DOM esté completamente cargado
  console.log("El DOM ha sido completamente cargado.");
  //creamos uns constante document d
  const d = document;
  //seleccionamos mediante el id el formulario
  const formulario = d.querySelector("#form_pacientes");

  formulario.addEventListener("submit", (e) => {
    e.preventDefault();
    //capturamos los value de los input
    const nombre = d.querySelector("#nombre").value;
    const apellido = d.querySelector("#apellido").value;
    const cedula = d.querySelector("#cedula").value;
    const fechaIngreso = d.querySelector("#fecha").value;
    const email = d.querySelector("#email").value;
    const calle = d.querySelector("#calle").value;
    const numero = d.querySelector("#numero").value;
    const localidad = d.querySelector("#localidad").value;
    const provincia = d.querySelector("#provincia").value;

    //convertimos el tipo de dato de calle a numero
    let convertirNumero = parseInt(numero);

    //Validamos que nombre y apellido sean de tipo String
    if (
      !/^[a-zA-Z]+$/.test(nombre) ||
      !/^[a-zA-Z]+$/.test(apellido) ||
      !/^[a-zA-Z]+$/.test(localidad) ||
      !/^[a-zA-Z]+$/.test(provincia)
    ) {
      respuesta(
        "Los campos nombre, apellido, localidad y provincia deben contener solo letras",
        "warning"
      );
      return;
    }

    if (!/^[0-9]+$/.test(convertirNumero)) {
      respuesta("El campo calle debe ser de tipo número");
      return;
    }

    const formData = {
      nombre: nombre,
      apellido: apellido,
      cedula: cedula,
      fechaIngreso: fechaIngreso,
      email: email,
      domicilio: {
        calle: calle,
        numero: convertirNumero,
        localidad: localidad,
        provincia: provincia,
      },
    };

    const url = "/paciente";
    const settings = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error en la solicitud");
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
        respuesta(
          `Paciente nombre: ${data.nombre}, email: ${data.email} agregado correctamente.`,
          "success"
        );
        resetInput();
      })
      .catch((error) => {
        console.log("Error", error);
        respuesta("Error, intenta nuevamente", "danger");
        resetInput();
      });
  });

  function resetInput() {
    document.querySelector("#nombre").value = "";
    document.querySelector("#apellido").value = "";
    document.querySelector("#cedula").value = "";
    document.querySelector("#fecha").value = "";
    document.querySelector("#email").value = "";
    document.querySelector("#calle").value = "";
    document.querySelector("#numero").value = "";
    document.querySelector("#localidad").value = "";
    document.querySelector("#provincia").value = "";
  }

  function respuesta(mensaje, tipo) {
    const alertHTML = `<div class="alert alert-${tipo} alert-dismissible">
                         <button type="button" class="close" data-dismiss="alert">&times;</button>
                          <strong>${mensaje}</strong> </div>`;

    const respuestaElemento = d.querySelector("#respuesta");
    respuestaElemento.innerHTML = alertHTML;
    respuestaElemento.style.display = "block";
  }
});
