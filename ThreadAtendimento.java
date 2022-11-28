package atividadetcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tatiana
 */
public class ThreadAtendimento implements Runnable{
    private Socket conexao;
    private DataInputStream entrada;
    private JTable tabela;
    
    public ThreadAtendimento(Socket conexao, JTable tabela) {
        this.conexao = conexao;
        this.tabela = tabela;
    }
    
    @Override
    public void run(){
        String status = "Conectado";
        int dados = 0;    
        int clienteContador = tabela.getRowCount();
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        
        try {
            entrada = new DataInputStream(conexao.getInputStream());
            model.addRow(new Object[]{"Cliente " + (clienteContador +1), status, dados});
            
            while(!conexao.isClosed()){
                entrada.readUTF();
                dados++;
                model.setValueAt("Enviando comando " + dados, clienteContador, 2);
            }
            conexao.close();
        } catch (IOException ex) {
            System.out.println(ex);

        } finally {
            status = "Desconectado";
            model.setValueAt("Fim", clienteContador, 2);
            model.setValueAt(status, clienteContador, 1);
        }
    }
}