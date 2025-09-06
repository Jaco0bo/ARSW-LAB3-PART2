package edu.eci.arsw.filters;

import edu.eci.arsw.filters.BluePrintsFilter;
import edu.eci.arsw.filters.RedundancyFilter;
import edu.eci.arsw.model.Blueprint;
import edu.eci.arsw.model.Point;
import edu.eci.arsw.persistence.BlueprintsPersistence;
import edu.eci.arsw.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.services.BluePrintServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class RedundancyFilterTest {

    @Autowired
    private BluePrintServices services;

    @TestConfiguration
    static class RedundancyFilterConfig {
        @Bean
        public BlueprintsPersistence blueprintsPersistence() {
            return new InMemoryBlueprintPersistence();
        }

        @Bean
        public BluePrintsFilter filter() {
            return new RedundancyFilter();
        }
    }

    @Test
    void testRedundancyFilter() throws Exception {
        Blueprint bp = new Blueprint("Andres", "Test",
                new Point[]{
                        new Point(0,0),
                        new Point(0,0),
                        new Point(1,1),
                        new Point(1,1),
                        new Point(2,2)
                });
        services.addNewBlueprint(bp);

        Blueprint result = services.getBlueprint("Andres", "Test");
        Assertions.assertEquals(3, result.getPoints().size()); // redundantes eliminados
    }
}