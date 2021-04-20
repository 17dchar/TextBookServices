package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Stvterm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StvtermRepositoryTests {

    final String code = "999999";
    final String code2 = "198930";
    final String code3 = "198920";
    final String desc = "The End of Time";
    final String desc2 = "Summer 1989";
    final String desc3 = "Spring 1989";

    @Autowired
    private StvtermRepository stvtermRepository;

    @Test
    public void testFindAll(){
        List<Stvterm> models = stvtermRepository.findAll();
        assertEquals(desc, models.get(0).getDesc());
    }

    @Test
    public void testFindByCodeOrCodeOrCode(){
        List<Stvterm> models = stvtermRepository.findByCodeOrCodeOrCode(code, code2, code3);
        assertEquals(desc, models.get(0).getDesc());
        assertEquals(desc2, models.get(1).getDesc());
        assertEquals(desc3, models.get(2).getDesc());
    }

}
