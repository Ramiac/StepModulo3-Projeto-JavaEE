package controle.Buscas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Restaurante;


@WebServlet("/SvBucaRestauranteNome")
public class SvBucaRestauranteNome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("busca");
		
		Restaurante r = new Restaurante();
		r.setNome(nome);
		
		List<Restaurante> listaRestaurante = new ArrayList<Restaurante>();

		if (r.localizarPeloNomeList() != null) {
			listaRestaurante = r.localizarPeloNomeList();
			request.setAttribute("status", "ok");
			request.setAttribute("listaRestaurante", listaRestaurante);

			request.getRequestDispatcher("BuscaRestaurante2.jsp").forward(request, response);

		} else {
			request.setAttribute("msg", r.getMsg());
			request.getRequestDispatcher("BuscaRestaurante.jsp").forward(request, response);
		}
	}

}
