package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TXTLogWriterTest {

  public FileReader fileReader = new TXTFileReader();
  String fileName = "123456789_INFO.txt";
  public TaxpayerManager taxPayerManager = new TaxpayerManager();  

  @Before
  public void before() throws NumberFormatException, IOException, WrongTaxpayerStatusException, WrongFileFormatException, WrongReceiptKindException, WrongReceiptDateException, WrongFileEndingException {
    
    
    fileReader.readFile(fileName);
    taxPayerManager.saveLogFile(123456789, "txt");
  }
  
  @Test
  public void generateFileTest() throws IOException {
    
    String array[];
    int taxRegistrationNumber;
    float income ;
    float basicTax;
    float taxIncrease;
    float totalTax;
    float totalReceiptsGathered;
    float entertainment;
    float basic;
    float travel;
    float health;
    float other;
    
    //right formation
    BufferedReader inputStream1 = new BufferedReader(new java.io.FileReader("123456789_LOG.txt"));
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Name:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("AFM:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Income:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Basic", array[0]);
    assertEquals("Tax:", array[1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Tax", array[0]);
    assertEquals("Increase:", array[1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Total", array[0]);
    assertEquals("Tax:", array[1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("TotalReceiptsGathered:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Entertainment:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Basic:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Travel:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Health:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Other:", array[0]);
    
    
    inputStream1.close();
    
    
    //check contents
    BufferedReader inputStream = new BufferedReader(new java.io.FileReader("123456789_LOG.txt"));
    
    array =inputStream.readLine().split(" ");
    assertEquals("Apostolos", array[1]);
    assertEquals("Zarras", array[2]);
    
    array = inputStream.readLine().split(" ");
    taxRegistrationNumber = Integer.parseInt(array[1]);
    assertEquals(123456789, taxRegistrationNumber);
    
    //float income = Float.parseFloat(inputStream.readLine());
    array = inputStream.readLine().split(" ");
    income = Float.parseFloat(array[1]);
    assertEquals(22570.0, income, 0.000001);
        
    array = inputStream.readLine().split(" ");
    basicTax = Float.parseFloat(array[2]);
    assertEquals(1207.495, basicTax, 0.01);
    
    array = inputStream.readLine().split(" ");
    taxIncrease = Float.parseFloat(array[2]);
    assertEquals(96.5996, taxIncrease, 0.01);

    array = inputStream.readLine().split(" ");
    totalTax = Float.parseFloat(array[2]);
    assertEquals(1304.0946, totalTax, 0.01);

    array = inputStream.readLine().split(" ");
    totalReceiptsGathered = Float.parseFloat(array[1]);
    assertEquals(2, totalReceiptsGathered, 0.01);
    
    array = inputStream.readLine().split(" ");
    entertainment = Float.parseFloat(array[1]);
    assertEquals(0.0, entertainment, 0.01);
    
    array = inputStream.readLine().split(" ");
    basic = Float.parseFloat(array[1]);
    assertEquals(266.0, basic, 0.01);
    
    array = inputStream.readLine().split(" ");
    travel = Float.parseFloat(array[1]);
    assertEquals(0.0, travel, 0.01);
    
    array = inputStream.readLine().split(" ");
    health = Float.parseFloat(array[1]);
    assertEquals(0.0, health, 0.01);
    
    array = inputStream.readLine().split(" ");
    other = Float.parseFloat(array[1]);
    assertEquals(0.0, other, 0.01);
   
    inputStream.close();  
  }

}
