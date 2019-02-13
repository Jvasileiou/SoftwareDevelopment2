package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class TXTInfoWriter extends InfoWriter {
     
  public void generateFile(int taxRegistrationNumber) throws IOException 
  {
    setInfoFlag("_INFO.txt") ;
    super.generateFile(taxRegistrationNumber);
  }
  public void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) throws IOException 
  {
      super.generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
  }

  @Override
  public String getFirstBeginFlag() {  return "" ;  }

  @Override
  public String getFirstEndFlag() {  return ": " ;  }

  @Override
  public String getEndFlagOfLine(String endFlagOfLine) {
    return "" ; }
  @Override
  public String getSpace() {    return " " ;  }

}