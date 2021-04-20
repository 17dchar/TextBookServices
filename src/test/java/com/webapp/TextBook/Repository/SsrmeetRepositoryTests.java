package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Ssbsect;
import com.webapp.TextBook.Model.Ssrmeet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsrmeetRepositoryTests {

    final String termCode = "202110";
    final String crn = "10023";

    @Autowired
    private SsrmeetRepository ssrmeetRepository;

    @Test
    public void testFindAll(){
        List<Ssrmeet> models = ssrmeetRepository.findAll();
        assertEquals(crn, models.get(0).getCrn());
    }

    @Test
    public void testFindByTermCodeAndCrn(){
        List<Ssrmeet> models = ssrmeetRepository.findByTermCodeAndCrn(termCode, crn);
        assertEquals(crn, models.get(0).getCrn());
    }

}
