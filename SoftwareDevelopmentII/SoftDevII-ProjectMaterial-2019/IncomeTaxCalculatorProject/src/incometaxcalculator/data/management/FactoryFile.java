package incometaxcalculator.data.management;

public class FactoryFile {

  public FactoryFile() {  }

  public boolean isTxtFile(String fileType) { return fileType.equals("txt") ? true : false; }
  
  public boolean isXmlFile(String fileType) { return fileType.equals("xml") ? true : false; }
  
}
