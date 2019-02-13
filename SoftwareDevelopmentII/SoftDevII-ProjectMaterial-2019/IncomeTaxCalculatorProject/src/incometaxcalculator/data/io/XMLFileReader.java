package incometaxcalculator.data.io;

import java.io.IOException;

import incometaxcalculator.exceptions.WrongFileFormatException;

public class XMLFileReader extends FileReader {

  protected int checkForReceipt(String value[])
      throws NumberFormatException, IOException {

     if (value[0].equals("<ReceiptID>")) {
       int receiptId = Integer.parseInt(value[1].trim());
       return receiptId;
     }
     return -2;
  }

  protected String getValueOfField(String value) throws WrongFileFormatException 
  {
      String valueReversed[] = new StringBuilder(value).reverse().toString().trim().split(" ", 2);
      return new StringBuilder(valueReversed[1]).reverse().toString();
  }

}
