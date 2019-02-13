package incometaxcalculator.data.management;

import java.io.File;

import incometaxcalculator.data.io.FileWriter;
import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLInfoWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.WrongFileEndingException;

public class FactoryFileWriter extends FactoryFile{

  public FactoryFileWriter() {  }

  public FileWriter getTheRightInfoFileWriter(int taxRegistrationNumber) 
  {
    if ( existXMLFile(taxRegistrationNumber) )   return   new XMLInfoWriter(); 
    
    return  new TXTInfoWriter() ;  
  }

  private boolean existXMLFile(int taxRegistrationNumber) 
  {
    return (new File(taxRegistrationNumber + "_INFO.xml")).exists();
  }

  public FileWriter getTheRightFilerWriterType(String fileFormat) throws WrongFileEndingException 
  {
    if ( isTxtFile(fileFormat) ) 
      return new TXTLogWriter() ;
  
    if ( isXmlFile(fileFormat) )
      return new XMLLogWriter() ;
    
    throw new WrongFileEndingException();  
  }
  
}
