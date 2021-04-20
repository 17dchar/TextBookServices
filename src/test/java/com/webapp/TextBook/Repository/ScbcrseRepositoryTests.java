package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Scbcrse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScbcrseRepositoryTests {

    final String subjCode = "ENGL";
    final String crseNum = "10430";
    final String title = "DIGITAL LITERACY";

    @Autowired
    private ScbcrseRepository scbcrseRepository;

    @Test
    public void testFindAll(){
        List<Scbcrse> models = scbcrseRepository.findAll();
        assertEquals(title, models.get(0).getTitle());
    }

    @Test
    public void testFindBySubjCodeAndCrseNumb(){
        List<Scbcrse> models = scbcrseRepository.findBySubjCodeAndCrseNumb(subjCode, crseNum);
        assertEquals(title, models.get(0).getTitle());
    }

    @Test
    public void testFindBySubjCode(){
        List<Scbcrse> models = scbcrseRepository.findBySubjCode(subjCode);
        assertEquals(title, models.get(0).getTitle());
    }

}
