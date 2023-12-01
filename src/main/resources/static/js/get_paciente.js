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
        const tbody = document.querySelector('table tbody');
        console.log(data)

        // Verificar si la lista de odontólogos está vacía
        if (data.length === 0) {
          const noDataDiv = document.createElement('div');
          noDataDiv.className = "col-auto bg-danger p-5 text-center fs-3 container";
          noDataDiv.textContent = 'No hay odontólogos en la lista.';
          document.body.appendChild(noDataDiv);
        } else {
          for (let i = 0; i < data.length; i++) {
            const paciente = data[i];

            const newRow = document.createElement('tr');

            const idCell = document.createElement('td');
            idCell.textContent = paciente.id;
            idCell.id='buttonId';
            console.log(idCell)

            const nombreCell = document.createElement('td');
            nombreCell.textContent = paciente.nombre;

            const apellidoCell = document.createElement('td');
            apellidoCell.textContent = paciente.apellido;

            const cedulaCell = document.createElement('td');
            cedulaCell.textContent = paciente.cedula;

            const fechaIngresoCell = document.createElement('td');
             fechaIngresoCell.textContent = paciente.fechaIngreso;

            const emailCell = document.createElement('td');
            emailCell.textContent = paciente.cedula;

            const calleCell = document.createElement('td');
            calleCell.textContent = paciente.domicilio.calle;

            const numeroCell = document.createElement('td');
            numeroCell.textContent = paciente.domicilio.numero;

            const localidadCell = document.createElement('td');
            localidadCell.textContent = paciente.domicilio.localidad;

            const provinciaCell = document.createElement('td');
            provinciaCell.textContent = paciente.domicilio.provincia;


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

            tbody.appendChild(newRow);
          }
        }
      })
      .catch((error) => {
        console.log(error);
      });

  });
