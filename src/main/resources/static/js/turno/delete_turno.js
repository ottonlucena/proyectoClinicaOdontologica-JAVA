const eliminarTurno = (id) => {
  //console.log("eliminarTurno");

  const url = "/turnos/" + id;
  const settings = {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  };

  fetch(url, settings)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Error al actualizar odontologo");
      }
      return response.text();
    })
    .then((data) => {
      console.log(data);
      window.location.href = "http://localhost:8080/turnos.html";
    });
};
