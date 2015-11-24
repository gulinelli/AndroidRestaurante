package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.ListaItens;
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
		
		JSONObject json;
		
		String opcao = request.getParameter("opcao");
		
		if(opcao.equals("NovoPedido")){
			HashMap<String, ListaItens> itensHm = new HashMap<String, ListaItens>();
			
			ItemDAO itemDAO = new ItemDAO();
			ListaItens listaItens = new ListaItens();
			listaItens.setItens(itemDAO.selecionaItems());
			itensHm.put("itens", listaItens);
			
			json = JSONObject.fromObject(itensHm);
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
		} else{
			
		}
	}
}
