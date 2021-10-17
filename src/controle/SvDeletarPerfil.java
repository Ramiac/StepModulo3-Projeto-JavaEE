package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Pessoa;
import model.entity.Usuario;

@WebServlet("/SvDeletarPerfil")
public class SvDeletarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario u = (Usuario) session.getAttribute("usuarioLogado");
		Pessoa p = new Pessoa();
		p.setId(u.getId());
		p = p.localizaPeloId();
		
		if (p.deletar()) {
			request.setAttribute("msg", "Usuario: " + u.getNickName() + ", deletado com sucesso.");
			session.invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
				request.setAttribute("msg", p.getMsg());
				session.setAttribute("usuarioLogado", u);
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
		
	}

}
