package com.filmotokio.config;

import com.filmotokio.model.Film;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;


@Configuration
@EnableBatchProcessing
public class FilmExportBatchConfig {

    @Bean
    public Job filmExportJob(JobRepository jobRepository,
                             Step exportStep,
                             JobExecutionListener jobExecutionListener) {
        return new JobBuilder("filmExportJob", jobRepository)
                .start(exportStep)
                .listener(jobExecutionListener)
                .build();
    }

    @Bean
    public Step exportStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           ItemReader<Film> reader,
                           ItemProcessor<Film, Film> processor,
                           ItemWriter<Film> writer) {
        return new StepBuilder("exportStep", jobRepository)
                .<Film, Film>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public JpaPagingItemReader<Film> reader(EntityManagerFactory emf) {
        JpaPagingItemReader<Film> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emf);
        reader.setQueryString("SELECT f FROM Film f WHERE f.migration = null");
        reader.setPageSize(10);
        reader.setSaveState(false); // Evita usar execução anterior
        return reader;
    }

    @Bean
    public ItemProcessor<Film, Film> processor() {
        return film -> {
            film.setMigration(LocalDate.now());
            return film;
        };
    }

    @Bean
    public FlatFileItemWriter<Film> writer() {
        FlatFileItemWriter<Film> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("films_export.csv"));
        writer.setHeaderCallback(writer1 -> writer1.write("ID,Título,Data de Lançamento"));
        writer.setLineAggregator(film -> String.join(",",
                film.getId().toString(),
                film.getTitle(),
                film.getYear().toString()
        ));
        writer.setAppendAllowed(true);
        return writer;
    }

    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                System.out.println("🎬 Iniciando exportação de filmes...");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                System.out.println("✅ Exportação concluída com sucesso!");
            }
        };
    }
}


