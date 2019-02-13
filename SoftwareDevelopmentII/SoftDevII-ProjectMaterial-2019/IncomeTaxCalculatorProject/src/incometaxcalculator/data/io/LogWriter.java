package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public abstract class LogWriter extends FileWriter{
  
  private String logFlag = "" ;
  
  private String name = "Name" ;
  private String afm = "AFM" ; 
  private String income = "Income" ;
  private String basic = "Basic" ;
  private String tax = "Tax" ;
  private String increase = "Increase" ;
  private String decrease = "Decrease" ;
  private String totalString = "Total" ;
  private String receipts = "Receipts" ;
  private String entertainment = "Entertainment" ;
  private String travel = "Travel" ;
  private String health = "Health" ;
  private String other = "Other" ;
    
  private static TaxpayerManager manager = new TaxpayerManager();
  
  protected static final short ENTERTAINMENT = 0;
  protected static final short BASIC = 1;
  protected static final short TRAVEL = 2;
  protected static final short HEALTH = 3;
  protected static final short OTHER = 4;
  
  public abstract String getTotal() ; 
  public abstract String getGathered() ;

  public LogWriter() {  }
  
  public void generateFile(int taxRegistrationNumber) throws IOException, WrongTaxpayerStatusException {
      
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + logFlag));
    
    outputStream.println( getFirstFlag( name )
                 + manager.getTaxpayerName(taxRegistrationNumber) 
                 + getEndFlagOfLine(name) );
    
    outputStream.println( getFirstFlag( afm )
                 + taxRegistrationNumber + getEndFlagOfLine(afm) );
    
    outputStream.println( getFirstFlag( income )
                 + manager.getTaxpayerIncome(taxRegistrationNumber) 
                 +  getEndFlagOfLine(income) );
   
    outputStream.println( getFirstFlag( basic, tax ) 
                 + manager.getTaxpayerBasicTax(taxRegistrationNumber)
                 +  getEndFlagOfLine(basic + tax) );
    
    if (manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) 
    {
        outputStream.println( getFirstFlag( tax, increase )
                 + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber)
                 +  getEndFlagOfLine(tax + increase) );
    } 
    else
    {
        outputStream.println( getFirstFlag( tax, decrease )
                 + manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber)
                 +  getEndFlagOfLine(tax + decrease) );
    }
       
    outputStream.println( getFirstFlag( totalString, tax ) 
                 + manager.getTaxpayerTotalTax(taxRegistrationNumber)
                 + getEndFlagOfLine(totalString + tax) );
    
    outputStream.println( getFirstFlag( getTotal(), receipts, getGathered() ) 
                + manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber)
                + getEndFlagOfLine(receipts) );
    
    outputStream.println( getFirstFlag( entertainment )
                + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)
                + getEndFlagOfLine(entertainment) );
    
    outputStream.println( getFirstFlag( basic )
                + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC)
                + getEndFlagOfLine(basic) );
    
    outputStream.println( getFirstFlag( travel )
                + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL)
                + getEndFlagOfLine(travel) );
    
    outputStream.println( getFirstFlag( health )
                + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH)
                + getEndFlagOfLine(health) );
    
    outputStream.println( getFirstFlag( other )
                + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER)
                + getEndFlagOfLine(other) );
    
    outputStream.close();
  }
  /*
  public String getFirstFlag(String word)
  {
    return getFirstBeginFlag() + word + getFirstEndFlag() ;
  }
  
  public String getFirstFlag(String word1, String word2)
  {
    return getFirstBeginFlag() + word1 + getSpace() + word2 + getFirstEndFlag() ;
  }  */
  
  public String getFirstFlag(String word1, String word2, String word3)
  {
    return getFirstBeginFlag() + word1 + word2 + word3 + getFirstEndFlag() ;
  }  
  
  public String getLogFlag() {
    return logFlag;
  }

  public void setLogFlag(String logFlag) {
    this.logFlag = logFlag;
  }
  
  
}
