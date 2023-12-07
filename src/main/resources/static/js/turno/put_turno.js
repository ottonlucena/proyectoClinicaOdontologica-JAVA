const actualizarTurno = (id) => {
  const d = document;
  const formulario = d.querySelector("#update-turno");
  formulario.addEventListener("submit", (e) => {
    e.preventDefault();
    const idPaciente = d.querySelector("#id_paciente").value;
    const idOdontologo = d.querySelector("#id_odontologo").value;
    const fecha = d.querySelector("#fecha").value;

    const formData = {
      id,
      fechaTurno: fecha,
      pacienteId: idPaciente,
      odontologoId: idOdontologo,
    };

    const url = "/turnos";
    const settings = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Error al actualizar odontologo");
        }
        return response.text();
      })
      .then((data) => {
        //console.log(data);
        window.location.href = "http://localhost:8080/turnos.html";
      });
  });
};
