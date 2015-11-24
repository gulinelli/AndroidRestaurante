package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO;
import dao.PedidoDAO;
import dao.PedidoItemDAO;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Pedido
 */
@WebServlet("/Pedido")
public class Pedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pedido() {
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
		String loginUsuario = request.getParameter("loginUsuario");
		String nomeItem = request.getParameter("nomeItem");
		String quantidade = request.getParameter("quantidade");
		String msg;
		boolean resultado = false;
		int idPedido = 0;
		int idItem = 0;
		
		//ADICIONA O LOGIN DO USUÁRIO NO PEDIDO
		PedidoDAO pedidoDAO = new PedidoDAO();
		try {
			pedidoDAO.setNovoPedido(loginUsuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//SELECIONA O ID DO PEDIDO
			idPedido = pedidoDAO.selecionaPedido(loginUsuario);
			if(idPedido == 0){
				System.out.println("Seleção do PedidoId falhou");
			} else{
				System.out.println("Valor Id Pedido: " + idPedido);
			}

		
		switch (nomeItem) {
		case "Lasanha":
			idItem = 1;
			break;
		case "Nhoque":
			idItem = 2;
			break;
		case "Petit Gateau":
			idItem = 3;
			break;
		case "Mousse de Maracujá":
			idItem = 4;
			break;
		case "Suco de Laranja":
			idItem = 5;
			break;
		case "Coca-Cola":
			idItem = 6;
			break;
		default:
			idItem = 0;
			break;
		}
		
		/*SELECIO ID DO ITEM
		ItemDAO itemDAO = new ItemDAO();
			itemDAO.selecionaItem(nomeItem);
			if(idItem == 0){
				System.out.println("Seleção de IdItem falhou");
			} else {
				System.out.println("Valor do IdItem: " + idItem);
			}*/

		if(idPedido != 0 && idItem != 0){
			//INSERE DADOS NO PEDIDO ITEM
			PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();
			resultado = pedidoItemDAO.setPedidoItem(idPedido, idItem, Integer.parseInt(quantidade));
		}
			
		if(resultado)
			msg = "correto";
		else
			msg = "incorreto";
		
		hm.put("message", msg);
		JSONObject json = JSONObject.fromObject(hm);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		
	}

}
