package com.filmotokio.batch.job;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.BatchStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilmMigrationJobListener implements JobExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(FilmMigrationJobListener.class);
    private final JdbcTemplate jdbcTemplate;

    // Construtor que recebe JdbcTemplate
    public FilmMigrationJobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        int count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM film WHERE migration IS NULL", Integer.class);

        logger.info("Iniciando migração. Filmes pendentes: {}", count);
        jobExecution.getExecutionContext().put("initialCount", count);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        int initialCount = jobExecution.getExecutionContext().getInt("initialCount", 0);
        int remaining = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM film WHERE migration IS NULL", Integer.class);
        int processed = initialCount - remaining;

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Migração concluída. Filmes processados: {}/{}", processed, initialCount);
        } else {
            logger.warn("Migração interrompida. Progresso: {}/{}", processed, initialCount);
        }
    }
}