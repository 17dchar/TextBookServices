package com.webapp.TextBook.Repository;


import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Nwtxin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NwtxinRepositoryTests {

    final String bookCode = "ECON3000";
    final String editionYear = "1998";
    final String title = "ECONOMIC APPROACH TO ENV RES";

    @Autowired
    private NwtxinRepository nwtxinRepository;

    @Test
    public void testFindAll(){
        List<Nwtxin> models = nwtxinRepository.findAll();
        assertEquals(title, models.get(0).getTitle());
    }

    @Test
    public void testFindByBookCodeAndEditionYear(){
        List<Nwtxin> models = nwtxinRepository.findByBookCodeAndEditionYear(bookCode, editionYear);
        assertEquals(title, models.get(0).getTitle());
    }

    @Test
    public void testFindByBookCode(){
        List<Nwtxin> models = nwtxinRepository.findByBookCode(bookCode);
        assertEquals(title, models.get(0).getTitle());
    }
}
