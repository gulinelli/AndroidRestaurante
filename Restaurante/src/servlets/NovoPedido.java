package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Item;
import dao.ItemDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class NovoPedido
 */
@WebServlet("/NovoPedido")
public class NovoPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovoPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<String, String> hm = new HashMap<String, String>();
		
		ItemDAO itemDAO = new ItemDAO();
		
		ArrayList<Item> listaItems = new ArrayList<>();
		listaItems = itemDAO.selecionaItems();
		
		Item item = listaItems.get(0);
		hm.put("item", item.toString());
		
		//ArrayList<String> nomes = new ArrayList<>();
		//ArrayList<String> valores = new ArrayList<>();
		//ArrayList<String> imagens = new ArrayList<>();
		
		/*for(Item i : listaItems){
			nomes.add(i.getNome());
			valores.add(String.valueOf(i.getValor()));
			imagens.add(i.getImagem());
			
			hm.put("nomes", nomes);
			
		}*/
		
		//hm.put("valores", valores);
		//hm.put("imagems", imagens);
		
		JSONObject json = JSONObject.fromObject(hm);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
	}

}
