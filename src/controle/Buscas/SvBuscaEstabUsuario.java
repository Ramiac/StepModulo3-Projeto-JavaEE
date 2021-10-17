package controle.Buscas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Hotel;
import model.entity.Restaurante;
import model.entity.Usuario;

@WebServlet("/SvBuscaEstabUsuario")
public class SvBuscaEstabUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			Restaurante r = new Restaurante();
			Hotel h = new Hotel();
			
			List<Restaurante> listaRestaurantes = r.localizarPeloUsuarioList(u);
			List<Hotel> listaHoteis = h.localizarPeloUsuarioList(u);
			
			if ( listaRestaurantes != null || listaHoteis != null) {
				
				request.setAttribute("listaRestaurantes", listaRestaurantes);
				request.setAttribute("listaHoteis", listaHoteis);
				request.getRequestDispatcher("EditarEstabelecimento.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "Não tem estabelecimento cadastrado ao seu nome.");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
	}
}
