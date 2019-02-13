package incometaxcalculator.data.management;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.exceptions.WrongFileEndingException;

public class FactoryFileReader extends FactoryFile{

  public FactoryFileReader() {  }

   public FileReader getTheRightFilerReaderType(String fileType) throws WrongFileEndingException {

    if ( isTxtFile(fileType) ) 
        return new TXTFileReader() ;
    
    if ( isXmlFile(fileType) )
      return new XMLFileReader() ;
      
    throw new WrongFileEndingException();  
  }
 
}
