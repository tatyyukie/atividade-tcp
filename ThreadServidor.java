package atividadetcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Tatiana
 */
public class ThreadServidor implements Runnable {

    private JTable tabela;
    private ServerSocket servidor;
    private int porta;

    public ThreadServidor(JTable tabela, int porta) {
        this.tabela = tabela;
        this.porta = porta;
    }

    @Override
    public void run() {
        try {
            //Criando servidor em uma porta
            servidor = new ServerSocket(this.porta);
            while (true) {
               new Thread(new ThreadAtendimento(servidor.accept(), this.tabela)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}