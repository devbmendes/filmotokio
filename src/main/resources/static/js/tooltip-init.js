document.addEventListener('DOMContentLoaded', function () {
  // Ativar tooltips
  const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  tooltipTriggerList.forEach(function (tooltipTriggerEl) {
    new bootstrap.Tooltip(tooltipTriggerEl);
  });

  // Toggle botão de favoritos (coração)
  const likeButtons = document.querySelectorAll('.like-btn');
  likeButtons.forEach(button => {
    button.addEventListener('click', function () {
      const icon = this.querySelector('i');
      const tooltip = bootstrap.Tooltip.getInstance(this);

      if (icon.classList.contains('bi-heart')) {
        icon.classList.remove('bi-heart');
        icon.classList.add('bi-heart-fill', 'text-danger');
        this.setAttribute('data-bs-title', 'Remover dos favoritos');
      } else {
        icon.classList.remove('bi-heart-fill', 'text-danger');
        icon.classList.add('bi-heart');
        this.setAttribute('data-bs-title', 'Marcar como favorito');
      }

      tooltip.dispose();
      new bootstrap.Tooltip(this);
    });
  });

  // Toggle botão de lista (checklist)
  const listButtons = document.querySelectorAll('.add-list-btn');
  listButtons.forEach(button => {
    button.addEventListener('click', function () {
      const icon = this.querySelector('i');
      const tooltip = bootstrap.Tooltip.getInstance(this);

      if (icon.classList.contains('bi-card-checklist')) {
        icon.classList.remove('bi-card-checklist');
        icon.classList.add('bi-check2-square', 'text-success');
        this.setAttribute('data-bs-title', 'Remover da sua lista');
      } else {
        icon.classList.remove('bi-check2-square', 'text-success');
        icon.classList.add('bi-card-checklist');
        this.setAttribute('data-bs-title', 'Adicionar à sua lista');
      }

      tooltip.dispose();
      new bootstrap.Tooltip(this);
    });
  });
});
