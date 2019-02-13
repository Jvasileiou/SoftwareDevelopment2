package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public abstract class FileWriter {
  
  public String getFirstFlag(String word)
  {
    return getFirstBeginFlag() + word + getFirstEndFlag() ;
  }
  
  public String getFirstFlag(String word1, String word2)
  {
    return getFirstBeginFlag() + word1 + getSpace() + word2 + getFirstEndFlag() ;
  }
  
  public void generateFile(int taxRegistrationNumber) 
      throws IOException, WrongTaxpayerStatusException {
  }
  
  public void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) throws IOException {
  }
  
  public String getFirstBeginFlag() {
    return null;
  }

  public String getFirstEndFlag() {
    return null;
  }
 
  public String getEndFlagOfLine(String endFlagOfLine) {
    return null;
  }
  
  public String getSpace() {
    return null;
  }
  
}