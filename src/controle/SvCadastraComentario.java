package controle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Comentario;
import model.entity.Publicacao;
import model.entity.Usuario;

@WebServlet("/SvCadastraComentario")
public class SvCadastraComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			
			Publicacao publicacao = new Publicacao();
			
			publicacao.setId(Long.parseLong(request.getParameter("idPublicacao")));
			
			
			if (publicacao.localizaPeloId() != null) {
				publicacao = publicacao.localizaPeloId();
			}else {
				request.setAttribute("msg", "publicao returned null.");
			}
			
			String comentario = request.getParameter("comentario");
			
			Date data = new Date();
			Date hora = new Date();
			
			Comentario c = new Comentario(u, publicacao, data, hora, comentario, null);
			
			if(c.salvar()) {
				session.setAttribute("usuarioLogado", u);
				request.setAttribute("msg", "Comentario cadastrado com sucesso.");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", c.getMsg());
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
