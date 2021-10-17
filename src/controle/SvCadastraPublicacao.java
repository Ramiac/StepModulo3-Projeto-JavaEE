package controle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Hotel;
import model.entity.Publicacao;
import model.entity.Restaurante;
import model.entity.Usuario;

@WebServlet("/SvCadastraPublicacao")
public class SvCadastraPublicacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			Restaurante r = new Restaurante();
			Hotel h = new Hotel();
			
			String tipoPublicacao = request.getParameter("tipoPublicacao"); // publicacao Pessoal, hotel ou Restaurante.
			if (!tipoPublicacao.equals("pessoal")) {
				Long idEstabelecimento = Long.parseLong(tipoPublicacao);
				
				r.setId(idEstabelecimento);
				
				h.setId(idEstabelecimento);
				
				if (r.localizaPeloId() != null) {
					r = r.localizaPeloId();
					h = null;
				} else if (h.localizaPeloId() != null) {
					h = h.localizaPeloId();
					r = null;
				}
			} else {
				h = null;
				r = null;
			}
			
			String descricao = request.getParameter("descricao");
			
			Date data = new Date();
			Date hora = new Date();
			
			Publicacao p = new Publicacao(u, r, h, null, descricao, data, hora, null);
			
			if (p.salvar()) {
				session.setAttribute("usuarioLogado", u);
				request.setAttribute("msg", "Publicacao cadastrada com sucesso.");
				
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
				
			} else {
				request.setAttribute("msg", p.getMsg());
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
