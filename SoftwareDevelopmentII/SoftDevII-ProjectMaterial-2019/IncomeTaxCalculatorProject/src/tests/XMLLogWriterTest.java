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

public class XMLLogWriterTest {

  public FileReader fileReader = new TXTFileReader();
  String fileName = "123456789_INFO.txt";
  public TaxpayerManager taxPayerManager = new TaxpayerManager();  

  @Before
  public void before() throws NumberFormatException, IOException, WrongTaxpayerStatusException, WrongFileFormatException, WrongReceiptKindException, WrongReceiptDateException, WrongFileEndingException {
    fileReader.readFile(fileName);
    taxPayerManager.saveLogFile(123456789, "xml");
  }
  
  @Test
  public void generateFileTest() throws IOException {
    
    String array[];
    int taxRegistrationNumber;
    float income ;
    float basicTax;
    float taxIncrease;
    float totalTax;
    float receipts;
    float entertainment;
    float basic;
    float travel;
    float health;
    float other;
    int i;
    
    //right formation
    BufferedReader inputStream1 = new BufferedReader(new java.io.FileReader("123456789_LOG.xml"));
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<Name>", array[0]);
    i = array.length;
    assertEquals("</Name>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<AFM>", array[0]);
    i = array.length;
    assertEquals("</AFM>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<Income>", array[0]);
    i = array.length;
    assertEquals("</Income>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<BasicTax>", array[0]);
    i = array.length;
    assertEquals("</BasicTax>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<TaxIncrease>", array[0]);
    i = array.length;
    assertEquals("</TaxIncrease>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<TotalTax>", array[0]);
    i = array.length;
    assertEquals("</TotalTax>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<Receipts>", array[0]);
    i = array.length;
    assertEquals("</Receipts>", array[i-1]);

    array =inputStream1.readLine().split(" ");
    assertEquals("<Entertainment>", array[0]);
    i = array.length;
    assertEquals("</Entertainment>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<Basic>", array[0]);
    i = array.length;
    assertEquals("</Basic>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<Travel>", array[0]);
    i = array.length;
    assertEquals("</Travel>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<Health>", array[0]);
    i = array.length;
    assertEquals("</Health>", array[i-1]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("<Other>", array[0]);
    i = array.length;
    assertEquals("</Other>", array[i-1]);
    
    
    inputStream1.close();
    
    
    //check contents
    BufferedReader inputStream = new BufferedReader(new java.io.FileReader("123456789_LOG.xml"));
    
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
    basicTax = Float.parseFloat(array[1]);
    assertEquals(1207.495, basicTax, 0.01);
    
    array = inputStream.readLine().split(" ");
    taxIncrease = Float.parseFloat(array[1]);
    assertEquals(96.5996, taxIncrease, 0.01);

    array = inputStream.readLine().split(" ");
    totalTax = Float.parseFloat(array[1]);
    assertEquals(1304.0946, totalTax, 0.01);

    array = inputStream.readLine().split(" ");
    receipts = Float.parseFloat(array[1]);
    assertEquals(2, receipts, 0.01);
    
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
