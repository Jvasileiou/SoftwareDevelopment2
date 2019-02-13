package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public abstract class InfoWriter extends FileWriter {

  private String infoFlag = "" ;
  
  private String name = "Name" ;
  private String afm = "AFM" ; 
  private String status = "Status" ;  
  private String income = "Income" ;
  private String receipts = "Receipts" ; 
  
  private String receiptString = "Receipt" ;
  private String id = "ID";
  private String date = "Date" ;
  private String kind = "Kind" ;
  private String amount = "Amount" ;
  private String company = "Company" ;
  private String country = "Country" ;
  private String city = "City" ;
  private String street = "Street" ;
  private String number = "Number" ;
  
  private static TaxpayerManager manager = new TaxpayerManager();

  public InfoWriter() {  }
  
  public void generateFile(int taxRegistrationNumber) throws IOException 
  {
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + infoFlag));
    
    outputStream.println( getFirstFlag(name)
        + manager.getTaxpayerName(taxRegistrationNumber) + getEndFlagOfLine(name) );
       
    outputStream.println( getFirstFlag(afm)
          + taxRegistrationNumber + getEndFlagOfLine(afm) );
    
    outputStream.println( getFirstFlag(status)
        + manager.getTaxpayerStatus(taxRegistrationNumber) + getEndFlagOfLine(status) );
    
    outputStream.println( getFirstFlag(income)
        +  manager.getTaxpayerIncome(taxRegistrationNumber) + getEndFlagOfLine(income) );
    
    outputStream.println();// den mas emfanize to \n se aplo notepad
    outputStream.println( getFirstFlag(receipts) );
    outputStream.println();
    
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    
    outputStream.close(); 
  }
  
  public void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) throws IOException 
  {
    HashMap<Integer, Receipt> receiptsHashMap = manager.getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    
    while (iterator.hasNext()) 
    {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      
      outputStream.println( getFirstFlag( receiptString , id ) 
                + getReceiptId(receipt) + getEndFlagOfLine( receiptString + id ) );
      
      outputStream.println( getFirstFlag( date )
                + getReceiptIssueDate(receipt) + getEndFlagOfLine( date ) );
      
      outputStream.println( getFirstFlag( kind )
                + getReceiptKind(receipt) + getEndFlagOfLine( kind ) );
      
      outputStream.println( getFirstFlag( amount )
                + getReceiptAmount(receipt) + getEndFlagOfLine( amount ) );
      
      outputStream.println( getFirstFlag( company )
                + getCompanyName(receipt) + getEndFlagOfLine( company ) );
      
      outputStream.println( getFirstFlag( country ) 
                + getCompanyCountry(receipt) + getEndFlagOfLine( country ) );
      
      outputStream.println( getFirstFlag( city ) 
                + getCompanyCity(receipt) + getEndFlagOfLine( city ) );
      
      outputStream.println( getFirstFlag( street )
                + getCompanyStreet(receipt) + getEndFlagOfLine( street ) );
      
      outputStream.println( getFirstFlag( number )
                + getCompanyNumber(receipt) + getEndFlagOfLine( number ) );
      
      outputStream.println();
    }
  }
  
  public int getReceiptId(Receipt receipt) {
    return receipt.getId();
  }

  public String getReceiptIssueDate(Receipt receipt) {
    return receipt.getIssueDate();
  }

  public String getReceiptKind(Receipt receipt) {
    return receipt.getKind();
  }

  public float getReceiptAmount(Receipt receipt) {
    return receipt.getAmount();
  }

  public String getCompanyName(Receipt receipt) {
    return receipt.getCompany().getName();
  }

  public String getCompanyCountry(Receipt receipt) {
    return receipt.getCompany().getCountry();
  }

  public String getCompanyCity(Receipt receipt) {
    return receipt.getCompany().getCity();
  }

  public String getCompanyStreet(Receipt receipt) {
    return receipt.getCompany().getStreet();
  }

  public int getCompanyNumber(Receipt receipt) {
    return receipt.getCompany().getNumber();
  }

  public String getInfoFlag() {
    return infoFlag;
  }

  public void setInfoFlag(String infoFlag) {
    this.infoFlag = infoFlag;
  }
  

}
