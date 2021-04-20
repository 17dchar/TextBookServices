package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NwtxbnRepositoryTests {

    final String pidm = "544584";
    final String bagNum = "4153";

    @Autowired
    private NwtxbnRepository nwtxbnRepository;

    @Test
    public void testFindAll(){
        List<Nwtxbn> models = nwtxbnRepository.findAll();
        assertEquals(bagNum, models.get(0).getBagNum());
    }

}
