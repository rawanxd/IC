
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Victor
 */
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class Languages {

  public void translate(int select) throws IOException{
         Field[] fields = CurrLattes.class.getDeclaredFields();
         CurrLattes lattes = new CurrLattes();
         lattes.setName(null);
         Locale currentLocale;
         ResourceBundle messages;
         fields[3].getName();
      if(select != 0){
        currentLocale = new Locale("en","US");
        messages = ResourceBundle.getBundle("locales.MessagesBundle",currentLocale);

      }
  }
        
  static public void main(String[] args) {

        String language;
        String country;

        if (args.length != 2) {
            language = new String("en");
            country = new String("US");
        } else {
            language = new String(args[0]);
            country = new String(args[1]);
        }

        Locale currentLocale;
        ResourceBundle messages;

        currentLocale = new Locale(language, country);

        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));
    }
}