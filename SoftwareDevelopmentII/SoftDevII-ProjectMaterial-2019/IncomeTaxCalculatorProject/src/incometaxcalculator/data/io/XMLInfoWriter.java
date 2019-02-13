package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class XMLInfoWriter extends InfoWriter {

  public void generateFile(int taxRegistrationNumber) throws IOException 
  {
    setInfoFlag("_INFO.xml") ;
    super.generateFile(taxRegistrationNumber);
  }
  public void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) throws IOException 
  {
        super.generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
  }
  

  @Override
  public String getFirstBeginFlag() { return "<" ;  }

  @Override
  public String getFirstEndFlag() { return "> " ;  }

  @Override
  public String getEndFlagOfLine(String endFlagOfLine) {
    return " </" + endFlagOfLine  + ">";
  }
  @Override
  public String getSpace() {   return "" ;  }

}