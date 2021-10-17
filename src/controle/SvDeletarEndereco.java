package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Endereco;
import model.entity.Pessoa;
import model.entity.Usuario;

@WebServlet("/SvDeletarEndereco")
public class SvDeletarEndereco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			Pessoa p = new Pessoa();
			p.setId(u.getId());
			p = p.localizaPeloId();
			Endereco e = new Endereco();
			e.setId(Long.parseLong(request.getParameter("id")));
			e = e.localizaPeloId();

			if (e.deletar()) {
					request.setAttribute("msg", "Endereco deletado com sucesso");
					request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			
			} else {
					
					request.setAttribute("msg", e.getMsg());
					request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
					
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
	}

}
