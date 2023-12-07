document.addEventListener("DOMContentLoaded", function () {
  const url = "/odontologo";
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
        noDataDiv.textContent = "No hay odontólogos en la lista.";
        document.body.appendChild(noDataDiv);
      } else {
        for (let i = 0; i < data.length; i++) {
          const odontologo = data[i];
          //console.log(data);

          const newRow = document.createElement("tr");

          const idCell = document.createElement("td");
          const buttonId = document.createElement("button");
          buttonId.textContent = odontologo.id;
          buttonId.value = odontologo.id;
          buttonId.id = "odontologo_id";
          buttonId.className = "btn btn-primary rounded-pill";
          buttonId.addEventListener("click", function () {
            const update = document.querySelector("#update_odontologo");
            update.style = "block";
            const data = {
              id: odontologo.id,
            };
            actualizarOdontologo(data);
          });
          idCell.appendChild(buttonId);

          const nombreCell = document.createElement("td");
          nombreCell.textContent = odontologo.nombre;

          const apellidoCell = document.createElement("td");
          apellidoCell.textContent = odontologo.apellido;

          const matriculaCell = document.createElement("td");
          matriculaCell.textContent = odontologo.matricula;

          const deletCell = document.createElement("button");
          deletCell.id = "delete_id";
          deletCell.textContent = "DELETE";
          deletCell.className = "btn btn-danger rounded-pill";

          deletCell.addEventListener("click", (e) => {
            deleteCell(odontologo.id, "/odontologo/eliminar");
            window.location.reload();
          });

          newRow.appendChild(idCell);
          newRow.appendChild(nombreCell);
          newRow.appendChild(apellidoCell);
          newRow.appendChild(matriculaCell);
          newRow.appendChild(deletCell);

          tbody.appendChild(newRow);
        }
      }
    })
    .catch((error) => {
      console.log("Error al traer lista de odontologo" + error);
    });
});
