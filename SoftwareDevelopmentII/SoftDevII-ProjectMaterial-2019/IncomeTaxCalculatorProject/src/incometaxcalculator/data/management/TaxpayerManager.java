package incometaxcalculator.data.management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.FileWriter;
import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerManager {

  private static HashMap<Integer, Taxpayer> taxpayerHashMap = new HashMap<Integer, Taxpayer>(0);
  private static HashMap<Integer, Integer> receiptOwnerTRN = new HashMap<Integer, Integer>(0);
  private static FactoryFile factoryFile  = new FactoryFile();
  
  public void createTaxpayer(String fullname, int taxRegistrationNumber, String status,
      float income) throws WrongTaxpayerStatusException {
    
    FactoryTaxpayer factoryTaxpayer = new FactoryTaxpayer(status);
    
    taxpayerHashMap.put(taxRegistrationNumber, 
                        new Taxpayer(fullname, taxRegistrationNumber,
                        income, status ,factoryTaxpayer.getDataStructureArray(status) ) );
  }

  public void createReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws WrongReceiptKindException, WrongReceiptDateException {

    Receipt receipt = new Receipt(receiptId, issueDate, amount, kind,
        new Company(companyName, country, city, street, number));
    taxpayerHashMap.get(taxRegistrationNumber).addReceipt(receipt);
    receiptOwnerTRN.put(receiptId, taxRegistrationNumber);
  }

  public void removeTaxpayer(int taxRegistrationNumber) {
    Taxpayer taxpayer = taxpayerHashMap.get(taxRegistrationNumber);
    taxpayerHashMap.remove(taxRegistrationNumber);
    HashMap<Integer, Receipt> receiptsHashMap = taxpayer.getReceiptHashMap();
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      receiptOwnerTRN.remove(receipt.getId());
    }
  }

  public void addReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws IOException, WrongReceiptKindException,
      WrongReceiptDateException, ReceiptAlreadyExistsException, WrongTaxpayerStatusException {

    if (containsReceipt(receiptId)) {
      throw new ReceiptAlreadyExistsException();
    }
    createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street, number,
        taxRegistrationNumber);
    updateFiles(taxRegistrationNumber);
  }

  public void removeReceipt(int receiptId) throws IOException, WrongReceiptKindException, WrongTaxpayerStatusException {
    taxpayerHashMap.get(receiptOwnerTRN.get(receiptId)).removeReceipt(receiptId);
    updateFiles(receiptOwnerTRN.get(receiptId));
    receiptOwnerTRN.remove(receiptId);
  }

  private void updateFiles(int taxRegistrationNumber) throws IOException, WrongTaxpayerStatusException 
  {
    FileWriter infoFileWriter = null ;
    factoryFile = getFactoryFile("Writer") ;
        
    infoFileWriter = ((FactoryFileWriter) factoryFile).getTheRightInfoFileWriter(taxRegistrationNumber);
    
    infoFileWriter.generateFile(taxRegistrationNumber);   
    
    new TXTInfoWriter().generateFile(taxRegistrationNumber);
  }

  public void saveLogFile(int taxRegistrationNumber, String fileFormat)
      throws IOException, WrongFileFormatException, WrongTaxpayerStatusException, WrongFileEndingException 
  {     
    factoryFile = getFactoryFile("Writer") ;
    
    FileWriter writer = ((FactoryFileWriter) factoryFile).getTheRightFilerWriterType(fileFormat);
    
    writer.generateFile(taxRegistrationNumber); 
  } 
  
  public void loadTaxpayer(String fileName)
      throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
      WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException
  {
    String fileType = fileName.split("\\.") [1] ;
    
    factoryFile = getFactoryFile("Reader") ;
    
    FileReader reader = ((FactoryFileReader) factoryFile).getTheRightFilerReaderType(fileType);
    
    reader.readFile(fileName);
  }
  
  
  private FactoryFile getFactoryFile(String type) {    
    if(type.equals("Writer")) return new FactoryFileWriter(); 
    
    return new FactoryFileReader();
  }

  public boolean containsTaxpayer(int taxRegistrationNumber) {
    if (taxpayerHashMap.containsKey(taxRegistrationNumber)) 
      return true;
    
    return false;
  }

  public boolean containsTaxpayer() {
    if (taxpayerHashMap.isEmpty()) 
      return false;
    
    return true;
  }

  public boolean containsReceipt(int id) {
    if (receiptOwnerTRN.containsKey(id)) 
      return true;
    
    return false;
  }

  public Taxpayer getTaxpayer(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber);
  }
  
  public String getTaxpayerName(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getFullname();
  }

  public String getTaxpayerStatus(int taxRegistrationNumber) {
      return taxpayerHashMap.get(taxRegistrationNumber).getStatus();
  }

  public String getTaxpayerIncome(int taxRegistrationNumber) {
    return "" + taxpayerHashMap.get(taxRegistrationNumber).getIncome();
  }

  public double getTaxpayerVariationTaxOnReceipts(int taxRegistrationNumber) throws WrongTaxpayerStatusException {
    return taxpayerHashMap.get(taxRegistrationNumber).getVariationTaxOnReceipts();
  }

  public int getTaxpayerTotalReceiptsGathered(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalReceiptsGathered();
  }

  public float getTaxpayerAmountOfReceiptKind(int taxRegistrationNumber, short kind) {
    return taxpayerHashMap.get(taxRegistrationNumber).getAmountOfReceiptKind(kind);
  }

  public double getTaxpayerTotalTax(int taxRegistrationNumber) throws WrongTaxpayerStatusException {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalTax();
  }

  public double getTaxpayerBasicTax(int taxRegistrationNumber) throws WrongTaxpayerStatusException {
    return taxpayerHashMap.get(taxRegistrationNumber).getBasicTax();
  }

  public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getReceiptHashMap();
  }

}