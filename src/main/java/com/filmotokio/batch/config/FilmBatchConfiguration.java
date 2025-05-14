package com.filmotokio.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class FilmBatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public FilmRepository filmRepository;

    @Bean
    public JdbcCursorItemReader<Film> reader() {
        JdbcCursorItemReader<Film> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT * FROM film WHERE migration IS NULL");
        reader.setRowMapper(new FilmRowMapper());
        return reader;
    }

    @Bean
    public FlatFileItemWriter<Film> writer() {
        FlatFileItemWriter<Film> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("films_migrated_" + LocalDate.now() + ".csv"));

        DelimitedLineAggregator<Film> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<Film> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"id", "title", "year", "duration", "synopsis", "poster", "createdAt"});
        lineAggregator.setFieldExtractor(fieldExtractor);

        writer.setLineAggregator(lineAggregator);
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Film, Film>chunk(10)
                .reader(reader())
                .writer(writer())
                .listener(new FilmWriteListener(filmRepository))
                .build();
    }

    @Bean
    public Job migrateFilmsJob() {
        return jobBuilderFactory.get("migrateFilmsJob")
                .incrementer(new RunIdIncrementer())
                .listener(new FilmMigrationJobListener())
                .flow(step1())
                .end()
                .build();
    }
}
