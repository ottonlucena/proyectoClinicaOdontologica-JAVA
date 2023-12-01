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
        const tbody = document.querySelector('table tbody');

        // Verificar si la lista de odontólogos está vacía
        if (data.length === 0) {
          const noDataDiv = document.createElement('div');
          noDataDiv.className = "col-auto bg-danger p-5 text-center fs-3 container";
          noDataDiv.textContent = 'No hay odontólogos en la lista.';
          document.body.appendChild(noDataDiv);
        } else {
          for (let i = 0; i < data.length; i++) {
            const odontologo = data[i];

            const newRow = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = odontologo.id;

            const nombreCell = document.createElement('td');
            nombreCell.textContent = odontologo.nombre;

            const apellidoCell = document.createElement('td');
            apellidoCell.textContent = odontologo.apellido;

            const matriculaCell = document.createElement('td');
            matriculaCell.textContent = odontologo.matricula;

            newRow.appendChild(idCell);
            newRow.appendChild(nombreCell);
            newRow.appendChild(apellidoCell);
            newRow.appendChild(matriculaCell);

            tbody.appendChild(newRow);
          }
        }
      })
      .catch((error) => {
        console.log(error);
      });

  });