package incometaxcalculator.data.management;

import java.util.Calendar;
import java.util.GregorianCalendar;

import incometaxcalculator.exceptions.WrongReceiptDateException;

public class Receipt {

  private final int id;
  private final GregorianCalendar issueDate;
  private final float amount;
  private final String kind;
  private final Company company;

  public Receipt(int id, String issueDate, float amount, String kind, Company company)
      throws WrongReceiptDateException {
    this.id = id;
    this.issueDate = createCalendar(issueDate);
    this.amount = amount;
    this.kind = kind;
    this.company = company;
  }

  private GregorianCalendar createCalendar(String issueDate) throws WrongReceiptDateException {
    String token[] = issueDate.split("/");
    if (token.length != 3) 
      throw new WrongReceiptDateException();
 
    int day = Integer.parseInt(token[0]);
    int month = Integer.parseInt(token[1]);
    int year = Integer.parseInt(token[2]);
    
    return new GregorianCalendar( year, month, day );
  }
  
  public int getDay() {
    return issueDate.get(Calendar.DAY_OF_MONTH);
  }

  public int getMonth() {
    return issueDate.get(Calendar.MONTH) ;
  }

  public int getYear() {
    return issueDate.get(Calendar.YEAR);
  }
  
  public int getId() {
    return id;
  }

  public String getIssueDate() {
    return  issueDate.get(Calendar.DAY_OF_MONTH) + "/"
        + issueDate.get(Calendar.MONTH) + "/" 
        + issueDate.get(Calendar.YEAR);
  }

  public float getAmount() {
    return amount;
  }

  public String getKind() {
    return kind;
  }

  public Company getCompany() {
    return company;
  }
}