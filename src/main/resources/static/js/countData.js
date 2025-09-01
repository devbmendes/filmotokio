document.addEventListener("DOMContentLoaded", () => {
        const counters = document.querySelectorAll(".counter");

        counters.forEach(counter => {
            counter.innerText = "0";
            const updateCounter = () => {
                const target = +counter.getAttribute("data-target");
                const current = +counter.innerText;

                // velocidade da animação
                const increment = target / 100;

                if (current < target) {
                    counter.innerText = `${Math.ceil(current + increment)}`;
                    setTimeout(updateCounter, 20); // tempo de atualização
                } else {
                    counter.innerText = target; // garante o valor final
                }
            };
            updateCounter();
        });
    });