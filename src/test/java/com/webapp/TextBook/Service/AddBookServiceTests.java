package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Model.OutputBookInformationModel;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import com.webapp.TextBook.Repository.NwtxinRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AddBookServiceTests {

    @Mock
    private NwtxinRepository nwtxinRepository;

    @Autowired
    private AddBookService addBookService;
    private OutputBookInformationModel outputBookInformationModel;


    @Before
    public void setUp() throws Exception{
        addBookService = new AddBookService();
        outputBookInformationModel = new OutputBookInformationModel();
    }

    @Test
    public void testGetMostRecentNwtxdt(){

        addBookService.getMostRecentNwtxdt("GEOL1140");
        assertFalse(outputBookInformationModel == null);

        /*
        OutputBookInformationModel outputBookInformationModel = new OutputBookInformationModel();
        outputBookInformationModel = addBookService.getMostRecentNwtxdt("GEOL1140");
        assertFalse(outputBookInformationModel == null);

         */
    }

}
