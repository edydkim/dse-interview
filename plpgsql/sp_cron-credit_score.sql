-- PL/PGSQL

CREATE EXTENSION IF NOT EXISTS pg_cron;

SET debug_pretty_print FROM CURRENT;

-- Stored Procedure
CREATE OR REPLACE PROCEDURE sp_credit_score_data(in csv_path text)
    LANGUAGE plpgsql
AS
$$
DECLARE
BEGIN

    --  Drop table if exists temp_credit_score;
    CREATE TABLE IF NOT EXISTS temp_credit_score
    (
        id                                   INT generated BY DEFAULT AS IDENTITY PRIMARY KEY,
        rownum                               INT,
        SeriousDlqin2yrs                     SMALLINT,
        RevolvingUtilizationOfUnsecuredLines NUMERIC(16, 2),
        age                                  INT,
        NumberOfTime30_59DaysPastDueNotWorse INT,
        DebtRatio                            DECIMAL,
        MonthlyIncome                        NUMERIC(16, 2),
        NumberOfOpenCreditLinesAndLoans      INT,
        NumberOfTimes90DaysLate              INT,
        NumberRealEstateLoansOrLines         INT,
        NumberOfTime60_89DaysPastDueNotWorse INT,
        NumberOfDependents                   INT
    );

    EXECUTE '
        copy temp_credit_score (
                                SeriousDlqin2yrs,
                                RevolvingUtilizationOfUnsecuredLines,
                                age,
                                NumberOfTime30_59DaysPastDueNotWorse,
                                DebtRatio,
                                MonthlyIncome,
                                NumberOfOpenCreditLinesAndLoans,
                                NumberOfTimes90DaysLate,
                                NumberRealEstateLoansOrLines,
                                NumberOfTime60_89DaysPastDueNotWorse,
                                NumberOfDependents
            )
        from ''' || csv_path || ''' delimiter '','' CSV HEADER';

EXCEPTION
    WHEN OTHERS THEN
        RAISE NOTICE E'Got exception:
        SQLSTATE: %
        SQLERRM: %', SQLSTATE, SQLERRM;
END
$$;

CALL sp_credit_score_data('/Users/eddie/Documents/dse-interview/data/cleanup_sample_data.csv');