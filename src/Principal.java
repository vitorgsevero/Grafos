import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

	public static void main(String[] args) {
		
		String arquivo = "src/dados.txt";

		
		//criando o grafo
		Grafo grafo;
		try {
			grafo = new Grafo(arquivo);
			System.out.println(grafo);
		} catch (Exception exception) {
			Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, exception);
		}

	}

}
