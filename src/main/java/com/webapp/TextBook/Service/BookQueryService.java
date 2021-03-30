package com.webapp.TextBook.Service;

import com.webapp.TextBook.Model.Nwtxdt;
import com.webapp.TextBook.Repository.NwtxdtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookQueryService {


  @Autowired private NwtxdtRepository nwtxdtRepository;

  public Nwtxdt saveNwtxdt(Nwtxdt nwtxdt) {
    return nwtxdtRepository.save(nwtxdt);
  }

  public Nwtxdt getNwtxdt(String bookCode, String editionYear, String barcode) {
    if(nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode).size() > 0){
      return nwtxdtRepository.findByBookCodeAndEditionYearAndBarcode(bookCode, editionYear, barcode).get(0);
    } else{
      return null;
    }
  }

  // Depricated testing code... for now
  /*
  public void testNwtxdt(){
      System.out.println("Testing Database connection");
      System.out.println("Your Book Code, Fine Sir: " + nwtxdtRepository.findAll().get(0).getBookCode());
  }

  public void logQuery(String bookCode, String editionYear, String barcode){
      System.out.println("Book Code: " + bookCode);
      System.out.println("Edition Year: " + editionYear);
      System.out.println("Sequence Number: " + barcode);
  }
   */
}
