package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import incometaxcalculator.data.management.FactoryTaxpayer;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class HeadOfHouseholdTaxPayerTest {

  private HashMap<Integer, Taxpayer> taxPayerHashMap = new HashMap<Integer, Taxpayer>(0);  
  private int AFM1 = 1;
  private int AFM2 = 2;
  private int AFM3 = 3;
  private int AFM4 = 4; 
  private int AFM5 = 5;
  
  @Before 
  public void before() {
    String name = "Periklis Ioannou";
    String headOfHousehold = "Head of Household" ;
    
    FactoryTaxpayer factoryTaxpayer = new FactoryTaxpayer( headOfHousehold );
    
      
    taxPayerHashMap.put(AFM1, new Taxpayer(name, AFM1, 1000, headOfHousehold,
        factoryTaxpayer.getDataStructureArray( headOfHousehold ) ) ) ;
    
    taxPayerHashMap.put(AFM2, new Taxpayer(name, AFM2, 85000, headOfHousehold,
        factoryTaxpayer.getDataStructureArray( headOfHousehold ) ) ) ;
    
    taxPayerHashMap.put(AFM3, new Taxpayer(name, AFM3, 100000, headOfHousehold,
        factoryTaxpayer.getDataStructureArray( headOfHousehold ) ) ) ;
    
    taxPayerHashMap.put(AFM4, new Taxpayer(name, AFM4, 200000, headOfHousehold,
        factoryTaxpayer.getDataStructureArray( headOfHousehold ) ) ) ;
    
    taxPayerHashMap.put(AFM5, new Taxpayer(name, AFM5, 250000, headOfHousehold,
        factoryTaxpayer.getDataStructureArray( headOfHousehold ) ) ) ;

  }

  @Test
  public void calculateBasicTaxTest() throws WrongTaxpayerStatusException {

    double rigth1 = 0.0535 * 1000;
    double rigth2 = 1625.87 + 0.0705 * (85000 - 30390);
    double rigth3 = 5828.38 + 0.0705 * (100000 - 90000);
    double rigth4 = 8092.13 + 0.0785 * (200000 - 122110);
    double rigth5 = 14472.61 + 0.0985 * (250000 - 203390);

    // Because of the doubles we need the 3rd parameter
    assertEquals(rigth1, taxPayerHashMap.get(AFM1).calculateBasicTax(), 0.01);
    assertEquals(rigth2, taxPayerHashMap.get(AFM2).calculateBasicTax(), 0.01);
    assertEquals(rigth3, taxPayerHashMap.get(AFM3).calculateBasicTax(), 0.01);
    assertEquals(rigth4, taxPayerHashMap.get(AFM4).calculateBasicTax(), 0.01);
    assertEquals(rigth5, taxPayerHashMap.get(AFM5).calculateBasicTax(), 0.01);
   }
}