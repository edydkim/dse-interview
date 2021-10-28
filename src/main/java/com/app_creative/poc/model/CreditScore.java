package com.app_creative.poc.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Eddie Kim
 * @project dse-interview
 * @package com.app_creative.poc.model
 */
@Table(schema = "public")
@Entity(name = "credit_score")
@Data
@Getter
@Setter
@NoArgsConstructor
public class CreditScore {

    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "serious_dlqin2yrs")
    private boolean seriousDlqin2yrs;

    @Column(name = "revolving_utilization_of_unsecured_lines")
    private BigDecimal revolvingUtilizationOfUnsecuredLines;

    @Column(name = "age")
    private Integer age;

    @Column(name = "number_of_time30_59days_past_due_not_worse")
    private Integer numberOfTime30_59DaysPastDueNotWorse;

    @Column(name = "debt_ratio")
    private BigDecimal debtRatio;

    @Column(name = "monthly_income")
    private BigDecimal monthlyIncome;

    @Column(name = "number_of_open_credit_lines_and_loans")
    private Integer numberOfOpenCreditLinesAndLoans;

    @Column(name = "number_of_times90days_late")
    private Integer numberOfTimes90DaysLate;

    @Column(name = "number_real_estate_loans_or_lines")
    private Integer numberRealEstateLoansOrLines;

    @Column(name = "number_of_time60_89days_past_due_not_worse")
    private Integer numberOfTime60_89DaysPastDueNotWorse;

    @Column(name = "number_of_dependents")
    private Integer numberOfDependents;
}
