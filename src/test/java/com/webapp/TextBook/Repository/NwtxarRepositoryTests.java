package com.webapp.TextBook.Repository;

import com.webapp.TextBook.Model.Nwtxar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NwtxarRepositoryTests{

    final String pidm = "439221";
    final String seqNo = "10";
    final String detailCode = "570";

    @Autowired
    private NwtxarRepository nwtxarRepository;

    @Test
    public void testFindAll(){
        final List<Nwtxar> model = nwtxarRepository.findAll();
        assertEquals(detailCode, model.get(0).getDetailCode());
    }

}
