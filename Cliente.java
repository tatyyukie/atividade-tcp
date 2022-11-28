package atividadetcp;

/**
 *
 * @author Tatiana
 */
public class Cliente {
    
    public static void main(String[] args) {
          new Thread (new ThreadCliente()).start();
    }
}