package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Estabelecimento;
import model.entity.Hotel;
import model.entity.Restaurante;
import model.entity.Usuario;


@WebServlet("/SvNovaPublicacao")
public class SvNovaPublicacao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			if(u.localizaPeloId() != null) {
				u = u.localizaPeloId();
			List <Estabelecimento> restaurantes = new ArrayList<>();
			List <Estabelecimento> hoteis = new ArrayList<>();
			if (u.getListaRestaurantes() != null) {
				for (Restaurante r : u.getListaRestaurantes()) {
					Estabelecimento e = new Estabelecimento();
					e.setId(r.getId());
					e.setNome(r.getNome());
					restaurantes.add(e);
				}
				session.setAttribute("restaurantes", restaurantes);
			}
			if (u.getListaHoteis() != null) {
				for (Hotel h : u.getListaHoteis()) {
					Estabelecimento e = new Estabelecimento();
					e.setId(h.getId());
					e.setNome(h.getNome());
					hoteis.add(e);
				}
				session.setAttribute("hoteis", hoteis);
			}
			session.setAttribute("usuarioLogado", u);
			request.getRequestDispatcher("NovaPublicacao.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", u.getMsg());
				session.setAttribute("usuarioLogado", u);
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Deu Erro geral: " + e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
	}

}
