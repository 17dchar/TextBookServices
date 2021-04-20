package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Sfrstcr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SfrstcrRepositoryTests {

    final String termCode = "200510";
    final Integer pidm = 74720;
    final String crn = "10905";

    @Autowired
    private SfrstcrRepository sfrstcrRepository;

    @Test
    public void testFindAll(){
        List<Sfrstcr> models = sfrstcrRepository.findAll();
        assertEquals(crn, models.get(0).getCrn());
    }

    @Test
    public void testFindAllByTermCodeAndPidm(){
        List<Sfrstcr> models = sfrstcrRepository.findAllByTermCodeAndPidm(termCode, pidm);
        assertEquals(crn, models.get(0).getCrn());
    }
    
}
