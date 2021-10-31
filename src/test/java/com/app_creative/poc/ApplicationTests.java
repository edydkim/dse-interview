package com.app_creative.poc;

import com.app_creative.poc.repository.CreditScoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author Eddie Kim
 * @project dse-interview
 * @package com.app_creative.poc
 */
@Sql({"/schema_view.sql"})
@SpringBootTest
class ApplicationTests {

    @Autowired
    private CreditScoreRepository creditScoreRepository;

    @Test
    void contextLoads() {
    }
    
}
