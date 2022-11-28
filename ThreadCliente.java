package atividadetcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tatiana
 */
public class ThreadCliente implements Runnable{
    private static ServerSocket servidor;
    private static Socket conexao;
    private static DataInputStream entrada;
    private static DataOutputStream saida;
    
    @Override
    public void run(){
        try {
            conexao = new Socket("localhost", 4000);
            
            saida = new DataOutputStream(conexao.getOutputStream());
            
            int random = (int) (Math.random()*50);
            
            for(int i = 0; i <= random; i++){
                saida.writeUTF("Mensagem: "+ i);
                Thread.sleep(200);
            }
            conexao.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
