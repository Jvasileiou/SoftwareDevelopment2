package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import incometaxcalculator.data.management.FactoryTaxpayer;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class MarriedFilingSeparatelyTaxpayerTest {

  private HashMap<Integer, Taxpayer> taxPayerHashMap = new HashMap<Integer, Taxpayer>(0);  
  private int AFM1 = 1;
  private int AFM2 = 2;   
  private int AFM3 = 3;
  private int AFM4 = 4;
  private int AFM5 = 5;
  
  @Before 
  public void before() {
    String name = "Periklis Ioannou";
    String marriedFilingSeparately = "Married Filing Separately" ;
    
    FactoryTaxpayer factoryTaxpayer = new FactoryTaxpayer( marriedFilingSeparately );
    
    
    taxPayerHashMap.put(AFM1, new Taxpayer(name, AFM1, 1000,  marriedFilingSeparately ,
        factoryTaxpayer.getDataStructureArray( marriedFilingSeparately ) ) ) ;
    
    taxPayerHashMap.put(AFM2, new Taxpayer(name, AFM2, 60000,  marriedFilingSeparately ,
        factoryTaxpayer.getDataStructureArray( marriedFilingSeparately ) ) ) ;
    
    taxPayerHashMap.put(AFM3, new Taxpayer(name, AFM3, 80000,  marriedFilingSeparately ,
        factoryTaxpayer.getDataStructureArray( marriedFilingSeparately ) ) ) ;
    
    taxPayerHashMap.put(AFM4, new Taxpayer(name, AFM4, 100000,  marriedFilingSeparately ,
        factoryTaxpayer.getDataStructureArray( marriedFilingSeparately ) ) ) ;
    
    taxPayerHashMap.put(AFM5, new Taxpayer(name, AFM5, 200000,  marriedFilingSeparately ,
        factoryTaxpayer.getDataStructureArray( marriedFilingSeparately ) ) ) ;
  }
  
  @Test
  public void calculateBasicTaxTest() throws WrongTaxpayerStatusException {

    double rigth1 = 0.0535 * 1000;
    double rigth2 = 965.14 + 0.0705 * (60000 - 18040);
    double rigth3 = 4746.76 + 0.0785 * (80000 - 71680);
    double rigth4 = 6184.88 + 0.0785 * (100000 - 90000);
    double rigth5 = 9098.80 + 0.0985 * (200000 - 127120);

    // Because of the doubles we need the 3rd parameter
    assertEquals(rigth1, taxPayerHashMap.get(AFM1).calculateBasicTax(), 0.01);
    assertEquals(rigth2, taxPayerHashMap.get(AFM2).calculateBasicTax(), 0.01);
    assertEquals(rigth3, taxPayerHashMap.get(AFM3).calculateBasicTax(), 0.01);
    assertEquals(rigth4, taxPayerHashMap.get(AFM4).calculateBasicTax(), 0.01);
    assertEquals(rigth5, taxPayerHashMap.get(AFM5).calculateBasicTax(), 0.01);
   
  }
}
