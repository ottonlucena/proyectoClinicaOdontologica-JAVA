document.addEventListener("DOMContentLoaded", function () {
  //creamos uns constante document d
  const d = document;
  //seleccionamos mediante el id el formulario
  const formulario = d.querySelector("#agregar_turno");

  function obtenerLista(url) {
    return fetch(url)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error al obtener la lista de odontologos");
        }
        return response.json();
      })
      .then((data) => {
        const element = data.map((odontologo) => odontologo.id);
        return element;
      })
      .catch((error) => {
        console.log("Error al obtener la lista odontologo", error);
        throw error;
      });
  }

  formulario.addEventListener("submit", async (e) => {
    e.preventDefault();
    //capturamos los value de los input
    const pacienteId = d.querySelector("#paciente_id").value;
    const odontologoId = d.querySelector("#odontologo_id").value;
    const turnoFecha = d.querySelector("#turno_fecha").value;

    //Validamos que nombre y apellido sean de tipo String
    if (!/^[0-9]+$/.test(pacienteId) || !/^[0-9]+$/.test(odontologoId)) {
      respuesta(
        "Los campos ID paciente y ID odontologo deben contener solo el número de ID",
        "warning"
      );
      return;
    }

    if (turnoFecha === "") {
      respuesta("Debe elegir una fecha", "warning");
      return;
    }

    try {
      const listaOdontologos = await obtenerLista("/odontologo");
      const listaPacientes = await obtenerLista("/paciente");

      if (!listaOdontologos.includes(parseInt(odontologoId))) {
        respuesta(
          `El ID del odontologo: ${odontologoId} no está en la base de datos`,
          "warning"
        );
        return;
      }

      if (!listaPacientes.includes(parseInt(pacienteId))) {
        respuesta(
          `El ID del paciente: ${pacienteId} no está en la base de datos`,
          "warning"
        );
        return;
      }
      const formData = {
        paciente: {
          id: pacienteId,
        },
        odontologo: {
          id: odontologoId,
        },
        fechaTurno: turnoFecha,
      };

      const url = "/turnos";
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
          //console.log(data);
          resetInput();
        });
    } catch (error) {
      console.log("Error", error);
      respuesta("Error, intenta nuevamente", "danger");
      resetInput();
    }

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
      document.querySelector("#paciente_id").value = "";
      document.querySelector("#odontologo_id").value = "";
      document.querySelector("#turno_fecha").value = "";
    }
  });
});
