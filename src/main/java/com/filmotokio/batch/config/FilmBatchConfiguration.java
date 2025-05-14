package com.filmotokio.batch.config;

import com.filmotokio.batch.job.FilmMigrationJobListener;
import com.filmotokio.batch.mapper.FilmRowMapper;
import com.filmotokio.batch.writer.FilmWriteListener;
import com.filmotokio.model.Film;
import com.filmotokio.repository.FilmRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDate;

@Configuration
@EnableBatchProcessing
public class FilmBatchConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public JdbcCursorItemReader<Film> reader() {
        return new JdbcCursorItemReaderBuilder<Film>()
                .name("filmReader")
                .dataSource(dataSource)
                .sql("SELECT id, title, year, duration, synopsis, poster, createdAt, migration FROM film WHERE migration IS NULL")
                .rowMapper(new FilmRowMapper())
                .build();
    }

    @Bean
    public FlatFileItemWriter<Film> writer() {
        return new FlatFileItemWriterBuilder<Film>()
                .name("filmWriter")
                .resource(new FileSystemResource("films_migrated_" + LocalDate.now() + ".csv"))
                .delimited()
                .delimiter(",")
                .names(new String[]{"id", "title", "year", "duration", "synopsis", "poster", "createdAt"})
                .build();
    }

    @Bean
    public Step migrateFilmsStep() {
        return new StepBuilder("migrateFilmsStep", jobRepository)
                .<Film, Film>chunk(10, transactionManager)
                .reader(reader())
                .writer(writer())
                .listener(new FilmWriteListener(filmRepository))
                .build();
    }

    @Bean
    public Job migrateFilmsJob(JobRepository jobRepository,
                               JdbcTemplate jdbcTemplate) {
        return new JobBuilder("migrateFilmsJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(new FilmMigrationJobListener(jdbcTemplate)) // Passa o JdbcTemplate
                .start(migrateFilmsStep())
                .build();
    }
}
