package com.filmotokio.batch.job;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilmMigrationJobListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(FilmMigrationJobListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Iniciando processo de migração de filmes...");
        // Poderia adicionar lógica para contar quantos filmes serão migrados
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Migração concluída com sucesso!");
        } else {
            logger.error("Migração falhou com status: " + jobExecution.getStatus());
        }
    }
}
