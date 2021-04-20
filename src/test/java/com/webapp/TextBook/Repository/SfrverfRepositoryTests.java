package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Sfrverf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SfrverfRepositoryTests {

    final int pidm = 492534;
    final String termCode = "202030";

    @Autowired
    private SfrverfRepository sfrverfRepository;

    @Test
    public void testFindAll(){
        List<Sfrverf> models = sfrverfRepository.findAll();
        assertEquals(termCode, models.get(0).getTermCode());
    }

    @Test
    public void testFindByPidmAndTermCode(){
        List<Sfrverf> models = sfrverfRepository.findByPidmAndTermCode(pidm, termCode);
        assertEquals(termCode, models.get(0).getTermCode());
    }

}
