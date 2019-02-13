package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TXTInfoWriterTest {

  public FileReader fileReader = new TXTFileReader();
  String fileName = "123456789_INFO.txt";

  @Before
  public void before() throws NumberFormatException, IOException, WrongTaxpayerStatusException, WrongFileFormatException, WrongReceiptKindException, WrongReceiptDateException {
    fileReader.readFile(fileName);
  }
  
  @Test
  public void generateFileTest() throws IOException {
    
    String array[];
    int taxRegistrationNumber;
    String status;
    float income ;
    String newLine;
    String receipts;
    int receiptId;
    String date;
    String kind;
    float amount;
    String company;
    String country;
    String city;
    String street;
    int number;
    
    //right formation
    BufferedReader inputStream1 = new BufferedReader(new java.io.FileReader(fileName));
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Name:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("AFM:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Status:", array[0]);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Income:", array[0]);
    
    newLine = inputStream1.readLine();
    assertEquals("", newLine);
    
    array =inputStream1.readLine().split(" ");
    assertEquals("Receipts:", array[0]);
    
    newLine = inputStream1.readLine();
    assertEquals("", newLine);
    
    while(inputStream1.ready()) {
      array =inputStream1.readLine().split(" ");
      assertEquals("Receipt", array[0]);
      assertEquals("ID:", array[1]);
      
      array =inputStream1.readLine().split(" ");
      assertEquals("Date:", array[0]);

      array =inputStream1.readLine().split(" ");
      assertEquals("Kind:", array[0]);
      
      array =inputStream1.readLine().split(" ");
      assertEquals("Amount:", array[0]);
      
      array =inputStream1.readLine().split(" ");
      assertEquals("Company:", array[0]);
      
      array =inputStream1.readLine().split(" ");
      assertEquals("Country:", array[0]);
      
      array =inputStream1.readLine().split(" ");
      assertEquals("City:", array[0]);
      
      array =inputStream1.readLine().split(" ");
      assertEquals("Street:", array[0]);
      
      array =inputStream1.readLine().split(" ");
      assertEquals("Number:", array[0]);
      
      newLine = inputStream1.readLine();
      assertEquals("", newLine);
    }
    
    inputStream1.close();
    
    //check contents
    BufferedReader inputStream = new BufferedReader(new java.io.FileReader(fileName));
    
    array =inputStream.readLine().split(" ");
    assertEquals("Apostolos", array[1]);
    assertEquals("Zarras", array[2]);
    
    array = inputStream.readLine().split(" ");
    taxRegistrationNumber = Integer.parseInt(array[1]);
    assertEquals(123456789, taxRegistrationNumber);
    
    status = inputStream.readLine();
    array = status.split(" ");
    assertEquals("Married", array[1]);
    assertEquals("Filing", array[2]);
    assertEquals("Jointly", array[3]);
    
    array = inputStream.readLine().split(" ");
    income = Float.parseFloat(array[1]);
    assertEquals(22570.0, income, 0.000001);
    
    newLine = inputStream.readLine();
    assertEquals("", newLine);
    
    receipts = inputStream.readLine();
    assertEquals("Receipts: ", receipts);
    
    newLine = inputStream.readLine();
    assertEquals("", newLine);
    
    array = inputStream.readLine().split(" ");
    receiptId = Integer.parseInt(array[2]);
    assertEquals(1, receiptId);
    
    array = inputStream.readLine().split(" ");
    date = array[1];
    assertEquals("10/5/1996", date);

    array = inputStream.readLine().split(" ");
    kind = array[1];
    assertEquals("Basic", kind);
    
    array = inputStream.readLine().split(" ");
    amount = Float.parseFloat(array[1]);
    assertEquals(251.0, amount, 0.00001);
    
    array = inputStream.readLine().split(" ");
    company = array[1];
    assertEquals("Parta", company);
    
    array = inputStream.readLine().split(" ");
    country = array[1];
    assertEquals("Jakusa", country);
    
    array = inputStream.readLine().split(" ");
    city = array[1];
    assertEquals("Drama", city);
    
    array = inputStream.readLine().split(" ");
    street = array[1];
    assertEquals("Fukosima", street);
    
    array = inputStream.readLine().split(" ");
    number = Integer.parseInt(array[1]);
    assertEquals(5, number);
    
    newLine = inputStream.readLine();
    assertEquals("", newLine);
    
    
    // Second Receipt
    array = inputStream.readLine().split(" ");
    receiptId = Integer.parseInt(array[2]);
    assertEquals(2, receiptId);
    
    array = inputStream.readLine().split(" ");
    date = array[1];
    assertEquals("12/6/2015", date);

    array = inputStream.readLine().split(" ");
    kind = array[1];
    assertEquals("Basic", kind);
    
    array = inputStream.readLine().split(" ");
    amount = Float.parseFloat(array[1]);
    assertEquals(15.0, amount, 0.00001);
    
    array = inputStream.readLine().split(" ");
    company = array[1];
    assertEquals("LOL", company);
    
    array = inputStream.readLine().split(" ");
    country = array[1];
    assertEquals("Greece", country);
    
    array = inputStream.readLine().split(" ");
    city = array[1];
    assertEquals("Ioannina", city);
    
    array = inputStream.readLine().split(" ");
    street = array[1];
    assertEquals("Napolewn", street);
    street = array[2];
    assertEquals("Zerva", street);
    
    array = inputStream.readLine().split(" ");
    number = Integer.parseInt(array[1]);
    assertEquals(12, number);
    
    inputStream.close();
  }

}
