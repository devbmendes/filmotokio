 setTimeout(function() {
  const alertElement = document.getElementById("successAlert");
  alertElement.classList.remove("d-none");

  // Oculta após 5 segundos
  setTimeout(() => {
    alertElement.classList.add("d-none");
  }, 3000);