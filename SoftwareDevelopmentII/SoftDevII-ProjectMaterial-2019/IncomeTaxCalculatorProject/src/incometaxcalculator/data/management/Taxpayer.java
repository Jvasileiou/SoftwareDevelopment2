package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class Taxpayer{

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  protected final String status;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
  private double[][] allValuesArray;
  
  public Taxpayer(String fullname, int taxRegistrationNumber, float income, String status , double [][] allValuesArray) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
    this.status = status;
    this.allValuesArray = allValuesArray ;
  }
  
  public double calculateBasicTax() throws WrongTaxpayerStatusException 
  {   
    if (income < allValuesArray[ 1 ][ 0 ] )   return 0.0535 * income;
    if (income < allValuesArray[ 1 ][ 1 ] )   return getRealBasicTax( 0 );    
    if (income < allValuesArray[ 1 ][ 2 ])    return getRealBasicTax( 1 );   
    if (income < allValuesArray[ 1 ][ 3 ])    return getRealBasicTax( 2 );
    
    return getRealBasicTax( 3 );
  }

  private double getRealBasicTax(int j) {
    return allValuesArray[ 2 ][ j ] + allValuesArray[ 0 ][ j ] * 
                            (income - allValuesArray[ 1 ][ j ]);
  }
 
  public void addReceipt(Receipt receipt) throws WrongReceiptKindException 
  {
    int kindOfReceipt = setKindOfReceipt(receipt);
    
    amountPerReceiptsKind[kindOfReceipt] += receipt.getAmount();
    
    addTotalReceipt(receipt);
  }
    
  public void addTotalReceipt(Receipt receipt) 
  {
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }
  
  public int setKindOfReceipt(Receipt receipt) throws WrongReceiptKindException 
  {
    if ( isEntertainment(receipt) ) return 0;
    if ( isBasic(receipt) )  return 1;
    if ( isTravel(receipt) ) return 2;
    if ( isHealth(receipt) ) return 3;
    if ( isOther(receipt) )  return 4;
    
    throw new WrongReceiptKindException();
  }

  public void removeReceipt(int receiptId) throws WrongReceiptKindException 
  {
    Receipt receipt = receiptHashMap.get(receiptId);
    int kindOfReceipt = setKindOfReceipt(receipt);
    amountPerReceiptsKind[kindOfReceipt] -= receipt.getAmount();
    removeTotalReceipt( receipt );
  }
   
  public void removeTotalReceipt( Receipt receipt ) 
  {
    receiptHashMap.remove( receipt.getId() );
    totalReceiptsGathered--;
  }
  
  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++)    sum += amountPerReceiptsKind[i];
    
    return sum;
  }
  
  public double getVariationTaxOnReceipts() throws WrongTaxpayerStatusException 
  {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    
    return calculateBasicTax() * findCaseOfTaxors(totalAmountOfReceipts) ; 
  }
  
  public double findCaseOfTaxors(float totalAmountOfReceipts)
  {    
    if ( hasIncrease(totalAmountOfReceipts) ) 
          return findTheTypeOfIncrease( totalAmountOfReceipts );
           
    return findTheTypeOfDecrease( totalAmountOfReceipts );
  }
  
  public double findTheTypeOfIncrease(float totalAmountOfReceipts) 
  {
    if (totalAmountOfReceipts < 0.2 * income) return 0.08 ;

    return 0.04 ;
  }
  
  public double findTheTypeOfDecrease(float totalAmountOfReceipts) 
  {
    if (totalAmountOfReceipts < 0.6 * income) return 0.15 ;

    return 0.3 ;
  }
  
  public boolean hasIncrease(float totalAmountOfReceipts) {
    return totalAmountOfReceipts < 0.4 * income ; 
  }
  
  public double getTotalTax() throws WrongTaxpayerStatusException {
    double res = calculateBasicTax() + getVariationTaxOnReceipts();
    System.out.println("TOTAL TAX = " + res);
    return res;
  }
  
  public double getBasicTax() throws WrongTaxpayerStatusException {
    return calculateBasicTax();
  }
  
  public double  increaseVariationTax(double num) throws WrongTaxpayerStatusException {
    double res = calculateBasicTax() * num;
    System.out.println("Calculatebasictax result :  " + res);
    return res;
  }

  public double  decreaseVariationTax(double num) throws WrongTaxpayerStatusException {
    return  - calculateBasicTax() * num;
  }
    
  public Boolean isEntertainment(Receipt receipt) {
    return receipt.getKind().equals("Entertainment");
  }

  public Boolean isBasic(Receipt receipt) {
    return receipt.getKind().equals("Basic");
  }
  
  public Boolean isTravel(Receipt receipt) {
    return receipt.getKind().equals("Travel");
  }
  
  public Boolean isHealth(Receipt receipt) {
    return receipt.getKind().equals("Health");
  }
  
  public Boolean isOther(Receipt receipt) {
    return receipt.getKind().equals("Other");
  }
  
  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }
    
  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }
    
  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public String getStatus() {
    return status;
  } 

}