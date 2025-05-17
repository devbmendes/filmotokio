package com.filmotokio.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class TestScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TestScheduler.class);

    @Scheduled(cron = "*/15 * * * * *") // Executa a cada 15 segundos
    public void testarAgendamento() {
        logger.info("🔁 Agendador funcionando! {}", java.time.LocalTime.now());
    }
}
