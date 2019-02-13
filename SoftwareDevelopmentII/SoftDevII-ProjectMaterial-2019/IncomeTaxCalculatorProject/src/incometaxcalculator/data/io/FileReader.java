package incometaxcalculator.data.io;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public abstract class FileReader {

  protected abstract int checkForReceipt(String[] values)
      throws NumberFormatException, IOException;

  protected abstract String getValueOfField(String fieldsLine) throws WrongFileFormatException;

  public void readFile(String fileName)
      throws NumberFormatException, IOException, WrongTaxpayerStatusException,
      WrongFileFormatException, WrongReceiptKindException,
      WrongReceiptDateException {
    BufferedReader inputStream =
        new BufferedReader(new java.io.FileReader(fileName));
    
    String fullname = getValue(inputStream.readLine());
    int taxRegistrationNumber = Integer.parseInt(getValue(inputStream.readLine()));
    String status = getValue(inputStream.readLine());
    float income = Float.parseFloat(getValue(inputStream.readLine()));
    createTaxpayer(fullname, taxRegistrationNumber, income, status);
    while (readReceipt(inputStream, taxRegistrationNumber))
      ;
  }

  protected String getValue(String readLine) throws WrongFileFormatException {
    if ( isEmpty(readLine) )    throw new WrongFileFormatException();
   
    String values[] = readLine.split(" ", 2);
    return getValueOfField(values[1]);
  }
  
  protected int checkIfThereIsOtherReceipt( BufferedReader inputStream ) throws NumberFormatException, IOException {
    int receiptId = 0;
    String line;
    
    while ( !isEmpty( line = inputStream.readLine() )  ) {
      String values[] = line.split(" ", 3);
      receiptId = checkForReceipt(values);
      if( receiptId != -2)  return receiptId;
    }
    return -1;
  }

  protected boolean readReceipt(BufferedReader inputStream, int taxRegistrationNumber)
      throws WrongFileFormatException, IOException, WrongReceiptKindException,
      WrongReceiptDateException {

    int receiptId;
    if ( ( receiptId = checkIfThereIsOtherReceipt(inputStream) ) < 0) {
      return false;
    }
    String issueDate = getValue(inputStream.readLine());
    String kind = getValue(inputStream.readLine());
    float amount = Float.parseFloat(getValue(inputStream.readLine()));
    String companyName = getValue(inputStream.readLine());
    String country = getValue(inputStream.readLine());
    String city = getValue(inputStream.readLine());
    String street = getValue(inputStream.readLine());
    int number = Integer.parseInt(getValue(inputStream.readLine()));
    createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street, number,
        taxRegistrationNumber);
    return true;
  }

  protected void createTaxpayer(String fullname, int taxRegistrationNumber, float income,
      String status) throws WrongTaxpayerStatusException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer(fullname, taxRegistrationNumber, status, income);
  }

  protected void createReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws WrongReceiptKindException, WrongReceiptDateException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street,
        number, taxRegistrationNumber);
  }

  protected boolean isEmpty(String line) {
    if (line == null) {
      return true;
    } else {
      return false;
    }
  }

}