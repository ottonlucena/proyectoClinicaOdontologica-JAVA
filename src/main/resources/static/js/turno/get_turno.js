document.addEventListener("DOMContentLoaded", function () {
  const url = "/turnos";
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

      // Verificar si la lista de odontólogos está vacía
      if (data.length === 0) {
        const noDataDiv = document.createElement("div");
        noDataDiv.className =
          "col-auto bg-danger p-5 text-center fs-3 container";
        noDataDiv.textContent = "No hay turnos en la lista.";
        document.body.appendChild(noDataDiv);
      } else {
        for (let i = 0; i < data.length; i++) {
          const turno = data[i];

          const newRow = document.createElement("tr");

          const idCell = document.createElement("td");
          const buttonId = document.createElement("button");
          buttonId.textContent = turno.id;
          buttonId.value = turno.id;
          buttonId.id = "odontologo_id";
          buttonId.className = "btn btn-primary rounded-pill";
          buttonId.addEventListener("click", function () {
            const update = document.querySelector("#update_turno");
            update.style = "block";
            const data = turno.id;
            actualizarTurno(data);
          });
          idCell.appendChild(buttonId);

          const id_paciente = document.createElement("td");
          id_paciente.textContent = turno.pacienteId;

          const id_odontologo = document.createElement("td");
          id_odontologo.textContent = turno.odontologoId;

          const fechaTurno = document.createElement("td");
          fechaTurno.textContent = turno.fechaTurno;

          const deletCell = document.createElement("button");
          deletCell.id = "delete_id";
          deletCell.textContent = "DELETE";
          deletCell.className = "btn btn-danger rounded-pill";
          deletCell.addEventListener("click", (e) => {
            //console.log("click");
            eliminarTurno(turno.id);
          });

          newRow.appendChild(idCell);
          newRow.appendChild(id_paciente);
          newRow.appendChild(id_odontologo);
          newRow.appendChild(fechaTurno);
          newRow.appendChild(deletCell);

          tbody.appendChild(newRow);
        }
      }
    })
    .catch((error) => {
      console.log(error);
    });
});
