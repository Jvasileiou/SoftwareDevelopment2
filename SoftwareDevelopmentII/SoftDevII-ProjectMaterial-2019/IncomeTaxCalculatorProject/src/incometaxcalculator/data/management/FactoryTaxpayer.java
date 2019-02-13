package incometaxcalculator.data.management;

import java.util.HashMap;

public class FactoryTaxpayer {
  
  private HashMap <String,double [][]> dataStructure = new HashMap<String, double[][]> () ;
  private static double [][] arrayHelper ;
  private static String status;
  
  public FactoryTaxpayer(String status) { 
    this.setStatus(status); 
    
    arrayHelper = new double[][] { {0.0705 , 0.0705 , 0.0785 , 0.0985} , 
                                { 36080 , 90000 , 143350 , 254240  }, 
                                { 1930.28 , 5731.64 , 9492.82 , 18197.69 },
                                };
    dataStructure.put( "Married Filing Jointly" , arrayHelper );
    
    arrayHelper = new double[][] { { 0.0705 , 0.0785 , 0.0785 , 0.0985} , 
                                    { 18040 , 71680 , 90000  , 127120  }, 
                                    { 965.14 , 4746.76 , 6184.88 , 9098.80   },
                                   };
    dataStructure.put( "Married Filing Separately" , arrayHelper );
       
    arrayHelper = new double[][] { { 0.0705 , 0.0785 , 0.0785 , 0.0985} , 
                                    { 24680 , 81080 , 90000  , 152540 }, 
                                    { 1320.38 , 5296.58 , 5996.80 , 10906.19 },
                                 };
    dataStructure.put( "Single" , arrayHelper );
    
    
    arrayHelper = new double[][] { { 0.0705 , 0.0705 , 0.0785 , 0.0985} , 
                                    { 30390 , 90000 , 122110 , 203390 }, 
                                    { 1625.87 , 5828.38 , 8092.13 , 14472.61 },
                                  };
    dataStructure.put( "Head of Household" , arrayHelper );
    
  }
  
  public double[][] getDataStructureArray(String status)
  {
    return dataStructure.get( status );
  }

  public static String getStatus() {
    return status;
  }

  public static void setStatus(String status) {
    FactoryTaxpayer.status = status;
  }
}