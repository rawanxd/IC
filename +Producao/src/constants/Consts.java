package constants;
/**
 * <p>Title: Consts</p>
 * <p>Description: Interface com todas as constantes utilizadas no VLattes </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: Instituto de Informatica UFG-Brasil</p>
 * @author Luciano Silva
 * @author Marcio Balian
 * @author Rogerio Fiuza
 * @version 1.0
 */
import java.awt.Color;
public interface Consts {
   /**
    * largura da tela
    */	
   public final int SCREEN_WIDTH = 2000;
   /**
    * altura da tela
    */
   public final int SCREEN_HEIGHT = 500;

	/**
    * altura de plotagem
    */
   public final int PLOT_HEIGHT = 638;   
   /**
    * largura do JPanel usado para construir uma barra
    */
   public final int CANVAS_BAR_WIDTH = 35;
   /**
    * largura de cada barra (dentro do JPanel)
    */
   public final int BAR_WIDTH = 1;
   /**
    * altura utilizada pelo label abaixo de cada barra
    */
   public final int BAR_LABEL_HEIGHT = 20;
   /**
    * cor de fundo da Area de plotagem
    */
   public final Color PLOT_AREA_BACKGROUND = Color.WHITE;
   /**
    * Valor usado para se criar uma "folga" no valor maximo da escala
    */
   public final int EXTRA_SPACE = 1;   
   /**
    * Fator de incremento das pegadas de um valor na escala de cada barra
    */
   public final int FOOTPRINT_FACTOR = 30;  
   /**
    * Largura da Area de texto de um ResearcherAxis
    */
   public final int TEXT_WIDTH = 300;     
   
}