package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class XMLLogWriter extends LogWriter {
  
  public void generateFile(int taxRegistrationNumber) throws IOException, WrongTaxpayerStatusException
  {
    setLogFlag("_LOG.xml") ;
    super.generateFile(taxRegistrationNumber);
  }

  @Override
  public void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream)
      throws IOException {   }

  @Override
  public String getFirstBeginFlag() {    return "<";  }

  @Override
  public String getFirstEndFlag() {    return "> ";  }

  @Override
  public String getEndFlagOfLine(String endFlagOfLine) {
    return " </" + endFlagOfLine  + ">";  }

  @Override
  public String getSpace() {  return "";  }

  @Override
  public String getTotal() {  return "";  }

  @Override
  public String getGathered() { return "";  }

}
