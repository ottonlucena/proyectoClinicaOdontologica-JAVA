document.addEventListener("DOMContentLoaded", function () {
  const url = "/paciente";
  const settings = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      const tbody = document.querySelector("table tbody");
      //console.log(data);

      // Verificar si la lista de pacientes está vacía
      if (data.length === 0) {
        const noDataDiv = document.createElement("div");
        noDataDiv.className =
          "col-auto bg-danger p-5 text-center fs-3 container";
        noDataDiv.textContent = "No hay pacientes en la lista.";
        document.body.appendChild(noDataDiv);
      } else {
        for (let i = 0; i < data.length; i++) {
          const paciente = data[i];

          const newRow = document.createElement("tr");

          const idCell = document.createElement("td");
          const button = document.createElement("button");
          button.dataset.id = paciente.id;
          button.textContent = paciente.id;
          button.value = paciente.id;
          button.id = "paciente_id";
          button.className = "btn btn-primary rounded-pill";
          button.addEventListener("click", function () {
            const form = document.querySelector("#update_paciente");
            form.style = "block";
            document.querySelector("#nombre").value = paciente.nombre;
            document.querySelector("#apellido").value = paciente.apellido;
            document.querySelector("#cedula").value = paciente.cedula;
            document.querySelector("#fecha").value = paciente.fechaIngreso;
            document.querySelector("#email").value = paciente.email;
            document.querySelector("#calle").value = paciente.domicilio.calle;
            document.querySelector("#numero").value = paciente.domicilio.numero;
            document.querySelector("#localidad").value =
              paciente.domicilio.localidad;
            document.querySelector("#provincia").value =
              paciente.domicilio.provincia;

            const data = {
              id: paciente.id,
            };

            actualizarPaciente(data);
          });
          idCell.appendChild(button);

          const nombreCell = document.createElement("td");
          nombreCell.textContent = paciente.nombre;

          const apellidoCell = document.createElement("td");
          apellidoCell.textContent = paciente.apellido;

          const cedulaCell = document.createElement("td");
          cedulaCell.textContent = paciente.cedula;

          const fechaIngresoCell = document.createElement("td");
          fechaIngresoCell.textContent = paciente.fechaIngreso;

          const emailCell = document.createElement("td");
          emailCell.textContent = paciente.email;

          const calleCell = document.createElement("td");
          calleCell.textContent = paciente.domicilio.calle;

          const numeroCell = document.createElement("td");
          numeroCell.textContent = paciente.domicilio.numero;

          const localidadCell = document.createElement("td");
          localidadCell.textContent = paciente.domicilio.localidad;

          const provinciaCell = document.createElement("td");
          provinciaCell.textContent = paciente.domicilio.provincia;

          const deletCell = document.createElement("button");
          deletCell.id = "delete_id";
          deletCell.textContent = "DELETE";
          deletCell.className = "btn btn-danger rounded-pill";

          deletCell.addEventListener("click", (e) => {
            deleteCell(paciente.id, "/paciente/eliminar");
            window.location.reload();
          });

          newRow.appendChild(idCell);
          newRow.appendChild(nombreCell);
          newRow.appendChild(apellidoCell);
          newRow.appendChild(cedulaCell);
          newRow.appendChild(fechaIngresoCell);
          newRow.appendChild(emailCell);
          newRow.appendChild(calleCell);
          newRow.appendChild(numeroCell);
          newRow.appendChild(localidadCell);
          newRow.appendChild(provinciaCell);
          newRow.appendChild(deletCell);

          tbody.appendChild(newRow);
        }
      }
    })
    .catch((error) => {
      console.log(error);
    });
});
