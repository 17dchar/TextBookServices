package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxbn;
import com.webapp.TextBook.Model.Nwtxcm;
import com.webapp.TextBook.Model.Nwtxdt;
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
public class NwtxcmRepositoryTest {

    final String course = "THEA408**";
    final String message = "GIVE THEA 407 ";

    @Autowired
    private NwtxcmRepository nwtxcmRepository;

    @Test
    public void testFindAll(){
        List<Nwtxcm> models = nwtxcmRepository.findAll();
        assertEquals(message, models.get(0).getMessage());
    }

    @Test
    public void testFindByCourse(){
        List<Nwtxcm> models = nwtxcmRepository.findByCourse(course);
        assertEquals(message, models.get(0).getMessage());
    }
}
