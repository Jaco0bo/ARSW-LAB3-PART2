package edu.eci.arsw.filters;

import edu.eci.arsw.filters.BluePrintsFilter;
import edu.eci.arsw.filters.SubsamplingFilter;
import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.services.BluePrintServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class SubsamplingFilterTest {

    @Autowired
    private BluePrintServices services;

    @TestConfiguration
    static class Config {
        @Bean
        public BluePrintsFilter filter() {
            return new SubsamplingFilter();
        }
    }

    @Test
    void testSubsamplingFilter() throws Exception {
        Blueprint bp = new Blueprint("Andres", "Test2",
                new Point[]{ new Point(0,0), new Point(1,1), new Point(2,2), new Point(3,3) });
        services.addNewBlueprint(bp);

        Blueprint result = services.getBlueprint("Andres", "Test2");
        Assertions.assertEquals(2, result.getPoints().size());
    }
}