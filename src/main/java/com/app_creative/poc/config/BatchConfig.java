package com.app_creative.poc.config;

import com.app_creative.poc.dao.SampleData;
import com.app_creative.poc.model.CreditScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.MessageFormat;

/**
 * @author Eddie Kim
 * @project dse-interview
 * @package com.app_creative.poc.config
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    @Value("${input.file}")
    private Resource resource;

    private final DataSourceProperties dataSourceProperties;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private DataSource dataSource;

    @Autowired
    public BatchConfig(DataSourceProperties dataSourceProperties,
                       JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory) {
        this.dataSourceProperties = dataSourceProperties;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = DataSourceBuilder.create()
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build();
    }

    @Bean
    public FlatFileItemReader<SampleData> reader() {
        FlatFileItemReader<SampleData> reader = new FlatFileItemReader<>();
        reader.setResource(resource);
        reader.setLineMapper(lineMapper());
        reader.setLinesToSkip(1);

        return reader;
    }

    @Bean
    public LineMapper<SampleData> lineMapper() {
        DefaultLineMapper<SampleData> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[] {"SeriousDlqin2yrs", "RevolvingUtilizationOfUnsecuredLines", "age", "NumberOfTime30_59DaysPastDueNotWorse", "DebtRatio", "MonthlyIncome", "NumberOfOpenCreditLinesAndLoans", "NumberOfTimes90DaysLate", "NumberRealEstateLoansOrLines", "NumberOfTime60_89DaysPastDueNotWorse", "NumberOfDependents"});
        tokenizer.setIncludedFields(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<SampleData>() {{
            setTargetType(SampleData.class);
        }});
        return lineMapper;
    }

    @Bean
    public ItemProcessor<SampleData, CreditScore> processor() {
        logger.info("Data Cleaning started...");
        return sampleData -> {
            // Data Cleaning
            CreditScore creditScore = new CreditScore();
            try {
                creditScore.setSeriousDlqin2yrs(sampleData.getSeriousDlqin2yrs().equals("1"));
                creditScore.setRevolvingUtilizationOfUnsecuredLines(new BigDecimal(sampleData.getRevolvingUtilizationOfUnsecuredLines()));
                creditScore.setAge(Integer.parseInt(sampleData.getAge()));
                creditScore.setNumberOfTime30_59DaysPastDueNotWorse(Integer.parseInt(sampleData.getNumberOfTime30_59DaysPastDueNotWorse()));
                creditScore.setDebtRatio(new BigDecimal(sampleData.getDebtRatio()));
                creditScore.setMonthlyIncome(new BigDecimal(sampleData.getMonthlyIncome()));
                creditScore.setNumberOfOpenCreditLinesAndLoans(Integer.parseInt(sampleData.getNumberOfOpenCreditLinesAndLoans()));
                creditScore.setNumberOfTimes90DaysLate(Integer.parseInt(sampleData.getNumberOfTimes90DaysLate()));
                creditScore.setNumberRealEstateLoansOrLines(Integer.parseInt(sampleData.getNumberRealEstateLoansOrLines()));
                creditScore.setNumberOfTime60_89DaysPastDueNotWorse(Integer.parseInt(sampleData.getNumberOfTime60_89DaysPastDueNotWorse()));
                creditScore.setNumberOfDependents(Double.valueOf(sampleData.getNumberOfDependents()).intValue());
            } catch (Exception e) {
                logger.error(MessageFormat.format("Error while processing data: {0}.", sampleData.toString()));
            }

            return creditScore;
        };
    }

    @Bean
    public JdbcBatchItemWriter<CreditScore> writer() {
        JdbcBatchItemWriter<CreditScore> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("INSERT INTO "
                + dataSourceProperties.getName() + ".credit_score " +
                "(serious_dlqin2yrs, revolving_utilization_of_unsecured_lines, age, number_of_time30_59days_past_due_not_worse, debt_ratio, monthly_income, number_of_open_credit_lines_and_loans, number_of_times90days_late, number_real_estate_loans_or_lines, number_of_time60_89days_past_due_not_worse, number_of_dependents) " +
                "VALUES " +
                "(:seriousDlqin2yrs, :revolvingUtilizationOfUnsecuredLines, :age, :numberOfTime30_59DaysPastDueNotWorse, :debtRatio, :monthlyIncome, :numberOfOpenCreditLinesAndLoans, :numberOfTimes90DaysLate, :numberRealEstateLoansOrLines, :numberOfTime60_89DaysPastDueNotWorse, :numberOfDependents)");
        try {
            writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        } catch (Exception e) {
            logger.error(MessageFormat.format("Error while writing data to database: {0}.", e.getMessage()));
        }
        return writer;
    }

    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get("step1")
                .<SampleData, CreditScore>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory
                .get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws IOException {
                        Files.deleteIfExists(resource.getFile().toPath());
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
