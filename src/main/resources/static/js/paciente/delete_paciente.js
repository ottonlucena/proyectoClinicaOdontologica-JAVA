function deleteCell(id, url) {
  //console.log(url);
  if (url === "/paciente/eliminar") {
    url = "/paciente/eliminar/" + id;
  } else {
    url = "/odontologo/eliminar/" + id;
  }
  const settings = {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  };

  fetch(url, settings)
    .then((response) => {
      return response.text();
    })
    .then((data) => {
      //console.log(data);
    })
    .catch((error) => {
      console.log("Error al eliminar paciente", error);
    });
}
