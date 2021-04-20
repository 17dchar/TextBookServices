package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Ssbsect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsbsectRepositoryTests {

    final String termCode = "202030";
    final String crn = "33774";
    final String crseNumb = "54201";

    @Autowired
    private SsbsectRepository ssbsectRepository;

    @Test
    public void testFindAll(){
        List<Ssbsect> models = ssbsectRepository.findAll();
        assertEquals(crseNumb, models.get(0).getCrseNumb());
    }

    @Test
    public void testFindByCrnAndTermCode(){
        List<Ssbsect> models = ssbsectRepository.findByCrnAndTermCode(crn, termCode);
        assertEquals(crseNumb, models.get(0).getCrseNumb());
    }

}
