package com.app_creative.poc.dao;

import org.springframework.stereotype.Component;

/**
 * @author Eddie Kim
 * @project dse-interview
 * @package com.app_creative.poc.dao
 */
@Component
public class SampleData {
    private String SeriousDlqin2yrs;

    private String RevolvingUtilizationOfUnsecuredLines;

    private String age;

    private String NumberOfTime30_59DaysPastDueNotWorse;

    private String DebtRatio;

    private String MonthlyIncome;

    private String NumberOfOpenCreditLinesAndLoans;

    private String NumberOfTimes90DaysLate;

    private String NumberRealEstateLoansOrLines;

    private String NumberOfTime60_89DaysPastDueNotWorse;

    private String NumberOfDependents;

    public SampleData() {
    }

    public String getSeriousDlqin2yrs() {
        return SeriousDlqin2yrs;
    }

    public void setSeriousDlqin2yrs(String seriousDlqin2yrs) {
        SeriousDlqin2yrs = seriousDlqin2yrs;
    }

    public String getRevolvingUtilizationOfUnsecuredLines() {
        return RevolvingUtilizationOfUnsecuredLines;
    }

    public void setRevolvingUtilizationOfUnsecuredLines(String revolvingUtilizationOfUnsecuredLines) {
        RevolvingUtilizationOfUnsecuredLines = revolvingUtilizationOfUnsecuredLines;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumberOfTime30_59DaysPastDueNotWorse() {
        return NumberOfTime30_59DaysPastDueNotWorse;
    }

    public void setNumberOfTime30_59DaysPastDueNotWorse(String numberOfTime30_59DaysPastDueNotWorse) {
        NumberOfTime30_59DaysPastDueNotWorse = numberOfTime30_59DaysPastDueNotWorse;
    }

    public String getDebtRatio() {
        return DebtRatio;
    }

    public void setDebtRatio(String debtRatio) {
        DebtRatio = debtRatio;
    }

    public String getMonthlyIncome() {
        return MonthlyIncome;
    }

    public void setMoMonthlyIncome(String moMonthlyIncome) {
        MonthlyIncome = moMonthlyIncome;
    }

    public String getNumberOfOpenCreditLinesAndLoans() {
        return NumberOfOpenCreditLinesAndLoans;
    }

    public void setNumberOfOpenCreditLinesAndLoans(String numberOfOpenCreditLinesAndLoans) {
        NumberOfOpenCreditLinesAndLoans = numberOfOpenCreditLinesAndLoans;
    }

    public String getNumberOfTimes90DaysLate() {
        return NumberOfTimes90DaysLate;
    }

    public void setNumberOfTimes90DaysLate(String numberOfTimes90DaysLate) {
        NumberOfTimes90DaysLate = numberOfTimes90DaysLate;
    }

    public String getNumberRealEstateLoansOrLines() {
        return NumberRealEstateLoansOrLines;
    }

    public void setNumberRealEstateLoansOrLines(String numberRealEstateLoansOrLines) {
        NumberRealEstateLoansOrLines = numberRealEstateLoansOrLines;
    }

    public String getNumberOfTime60_89DaysPastDueNotWorse() {
        return NumberOfTime60_89DaysPastDueNotWorse;
    }

    public void setNumberOfTime60_89DaysPastDueNotWorse(String numberOfTime60_89DaysPastDueNotWorse) {
        NumberOfTime60_89DaysPastDueNotWorse = numberOfTime60_89DaysPastDueNotWorse;
    }

    public String getNumberOfDependents() {
        return NumberOfDependents;
    }

    public void setNumberOfDependents(String numberOfDependents) {
        NumberOfDependents = numberOfDependents;
    }

    @Override
    public String toString() {
        return "SampleData{" +
                "SeriousDlqin2yrs='" + SeriousDlqin2yrs + '\'' +
                ", RevolvingUtilizationOfUnsecuredLines='" + RevolvingUtilizationOfUnsecuredLines + '\'' +
                ", age='" + age + '\'' +
                ", NumberOfTime30_59DaysPastDueNotWorse='" + NumberOfTime30_59DaysPastDueNotWorse + '\'' +
                ", DebtRatio='" + DebtRatio + '\'' +
                ", MonthlyIncome='" + MonthlyIncome + '\'' +
                ", NumberOfOpenCreditLinesAndLoans='" + NumberOfOpenCreditLinesAndLoans + '\'' +
                ", NumberOfTimes90DaysLate='" + NumberOfTimes90DaysLate + '\'' +
                ", NumberRealEstateLoansOrLines='" + NumberRealEstateLoansOrLines + '\'' +
                ", NumberOfTime60_89DaysPastDueNotWorse='" + NumberOfTime60_89DaysPastDueNotWorse + '\'' +
                ", NumberOfDependents='" + NumberOfDependents + '\'' +
                '}';
    }
}
