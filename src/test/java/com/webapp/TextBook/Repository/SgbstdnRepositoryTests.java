package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Sgbstdn;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SgbstdnRepositoryTests {

    final Integer pidm = 545099;

    @Autowired
    private SgbstdnRepository sgbstdnRepository;

    @Test
    public void testFindAll(){
        List<Sgbstdn> models = sgbstdnRepository.findAll();
        assertEquals(pidm, models.get(0).getPidm());
    }
}
