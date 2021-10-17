package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Restaurante;

@WebServlet("/SvDeletarRestaurante")
public class SvDeletarRestaurante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Restaurante r = new Restaurante();
		r.setId((long) session.getAttribute("id"));
		
		if (r.deletar()) {
			request.setAttribute("msg", "Restaurante " + r.getNome() + ", deletado com sucesso.");
			session.removeAttribute("id");
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		} else {
				request.setAttribute("msg", r.getMsg());
				session.removeAttribute("id");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
	}

}
