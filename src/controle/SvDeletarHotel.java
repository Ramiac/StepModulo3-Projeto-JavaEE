package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Hotel;

@WebServlet("/SvDeletarHotel")
public class SvDeletarHotel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Hotel h = new Hotel();
		h.setId((long) session.getAttribute("id"));

		if (h.deletar()) {
			request.setAttribute("msg", "Hotel " + h.getNome() + ", deletado com sucesso.");
			session.removeAttribute("id");
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", h.getMsg());
			session.removeAttribute("id");
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
	}
}
