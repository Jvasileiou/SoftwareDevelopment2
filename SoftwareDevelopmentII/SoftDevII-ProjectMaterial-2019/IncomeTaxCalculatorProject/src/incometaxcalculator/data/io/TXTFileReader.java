package incometaxcalculator.data.io;

import java.io.IOException;
import incometaxcalculator.exceptions.WrongFileFormatException;

public class TXTFileReader extends FileReader {

  protected int checkForReceipt(String value[])
      throws NumberFormatException, IOException {

    if (value[0].equals("Receipt")) {
      if (value[1].equals("ID:")) {
        int receiptId = Integer.parseInt(value[2].trim());
        return receiptId;
      }
    }
    return -2;
  }

  protected String getValueOfField(String value) throws WrongFileFormatException {
      return value.trim();
  }

}