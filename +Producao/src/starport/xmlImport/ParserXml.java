package starport.xmlImport;
import java.util.Vector;

import dataCon.*;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import test.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
 
public class ParserXml{
    public static int progress;
    FileXml xml;
    public Vector arquivoXml;
    CurriculumXmlReader reader = new CurriculumXmlReader();
    
    
  public ParserXml(String dir) throws Exception{
    Vector <Curriculum> curriculos = new Vector();
    xml = new FileXml(dir);
    arquivoXml = xml.listaArquivo();

    for (int i=0;i < arquivoXml.size();i++){
        Curriculum researcher = new Curriculum();
        try {
          System.out.println((String)arquivoXml.get(i));
          researcher = reader.getXmlLocal((String)arquivoXml.get(i));
	} catch (Exception e) {
			e.printStackTrace();	
	}
        if (researcher != null) 
            curriculos.add(researcher); 
            //StoreCurriculum.storeALL(researcher);
        
        progress = ( ( (i+1)*100 ) / arquivoXml.size() );
    }
    StoreCurriculum.storeALL(curriculos);
    WriteL(arquivoXml.size());
  }
  //C:\Users\Victor\Desktop\IC\Curr
  public ParserXml() throws Exception{
        Vector <Curriculum> curriculos = new Vector();
        Vector <String> files = new Vector();
        Blob xmlData= null;
      
        ResultSet rs = null;
        String sql ="";              // BD
        DataBase dbmain = DataBase.getDbR("remote.txt");
        rs  = dbmain.selecting("SELECT CPF FROM TB_CURRICULO_LATTES;");
        
        while(rs.next()){
         sql =  rs.getString("CPF");               // GET MAX number of Researchers
         files.add(sql);
        }
    
    for (int i=0;i < 10;i++){
        Curriculum researcher = new Curriculum();
        rs  = dbmain.selecting("SELECT CURRICULO_XML FROM TB_CURRICULO_LATTES where cpf = "+files.get(i)+";");
        
        if(rs.next()){
         xmlData =  rs.getBlob("CURRICULO_XML");               // GET MAX number of Researchers
        }
        sql = XmlFromBlob(xmlData);
          
        try {
 //         System.out.println((String)arquivoXml.get(i));
          researcher=reader.getXmlRemote(sql);
	} catch (Exception e) {
			e.printStackTrace();	
	}
        if (researcher != null)
            curriculos.add(researcher);
         //   StoreCurriculum.storeALL(researcher);
        
        progress = ( ( (i+1)*100 ) / files.size() );
    }
    StoreCurriculum.storeALL(curriculos);
    WriteL(1);
  }
  
  private String XmlFromBlob(Blob value) throws SQLException {
    long start = 1;
    String xmls = new String(value.getBytes(start,(int)value.length()));
    return xmls;
  }
  private void WriteL(int num) throws IOException{
      String str = String.valueOf(num);  ;  
      FileWriter fstream = null;
      fstream = new FileWriter("prodNumber.txt"); //WRITE
      BufferedWriter out = new BufferedWriter(fstream); 

      out.write(str);
      out.newLine();
      out.close();

  }
 

  public Vector sort(Vector sort) {
        Vector v = new Vector();
        for(int count = 0; count < v.size(); count++) {
            String s = sort.elementAt(count).toString();
            int i = 0;
            for (i = 0; i < v.size(); i++) {
                int c = s.compareTo((String) v.elementAt(i));
                if (c < 0) {
                    v.insertElementAt(s, i);
                    break;
                } else if (c == 0) {
                    break;
                }
            }
            if (i >= v.size()) {
                v.addElement(s);
            }
        }
        return v;
  }
  
  /*public static int getParseProgress(){
      return progress;
  }
  
  public static void setParseProgress(int x){
      progress = x;
  }*/
}
