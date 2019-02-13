package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class XMLFileReaderTest {

  TaxpayerManager taxPayerManager = new TaxpayerManager();
  public FileReader fileReader = new XMLFileReader();
  Taxpayer taxPayer;
  HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>();
  
  
  @Before
  public void before() throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException, WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
     String fileName = "123456789_INFO.xml";
     
     fileReader.readFile(fileName);
  }
  
  @Test
  public void readFileTest(){
    assertEquals(true, taxPayerManager.containsTaxpayer());
    assertEquals(true, taxPayerManager.containsTaxpayer(123456789));
    
    taxPayer = taxPayerManager.getTaxpayer(123456789);
    
    String fullName = taxPayer.getFullname();
    assertEquals("Apostolos Zarras",fullName);
    
    String status = taxPayer.getStatus();
    assertEquals("Married Filing Jointly",status);  
    
    float income = taxPayer.getIncome();
    assertEquals(22570.0,income,0.0000001);
    
    //Receipts number
    receiptHashMap = taxPayer.getReceiptHashMap();
    assertEquals(2,receiptHashMap.size());
    
    //Receipt id
    assertEquals(true, receiptHashMap.containsKey(1));
    assertEquals(true, receiptHashMap.containsKey(2));
    assertEquals(false, receiptHashMap.containsKey(4));
    assertEquals(false, receiptHashMap.containsKey(10));
    assertEquals(false, receiptHashMap.containsKey(100));
    
    String kind = receiptHashMap.get(1).getKind();
    assertEquals("Basic", kind);
    
    kind = receiptHashMap.get(2).getKind();
    assertEquals("Basic", kind);
    
    String date = receiptHashMap.get(1).getIssueDate();
    assertEquals("10/5/1996", date);
    
    date = receiptHashMap.get(2).getIssueDate();
    assertEquals("12/6/2015", date);
    
  }

}
