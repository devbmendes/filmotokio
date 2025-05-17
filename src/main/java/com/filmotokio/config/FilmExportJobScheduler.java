package com.filmotokio.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class FilmExportJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job filmExportJob;

    public FilmExportJobScheduler(JobLauncher jobLauncher, Job filmExportJob) {
        this.jobLauncher = jobLauncher;
        this.filmExportJob = filmExportJob;
    }

    @Scheduled(cron = "0 0 3 * * *") // Todos os dias às 03:00 da manhã
    public void runFilmExportJob() {
        log.info("⏰ Iniciando job de exportação de filmes...");

        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis()) // evita conflitos
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(filmExportJob, jobParameters);

            log.info("✅ Job de exportação finalizado com status: {}", execution.getStatus());
            if (execution.getStatus() == BatchStatus.COMPLETED) {
                log.info("📁 Exportação concluída com sucesso.");
            } else {
                log.warn("⚠️ Job terminou com status não esperado: {}", execution.getStatus());
            }

        } catch (Exception e) {
            log.error("❌ Erro ao executar job de exportação de filmes", e);
        }
    }
}


