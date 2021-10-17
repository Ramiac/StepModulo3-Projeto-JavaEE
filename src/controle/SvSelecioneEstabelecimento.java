package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Hotel;
import model.entity.Restaurante;

@WebServlet("/SvSelecioneEstabelecimento")
public class SvSelecioneEstabelecimento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			String tipoEstabelecimento = request.getParameter("tipoEstabelecimento");

			switch (tipoEstabelecimento) {
			case "restaurante":
				Restaurante r = new Restaurante();
				r.setId(Long.parseLong(request.getParameter("estabelecimento")));
				if (r.localizaPeloId() != null) {
					r = r.localizaPeloId();
					session.setAttribute("id", r.getId());
					request.setAttribute("nome", r.getNome());
					request.setAttribute("cnpj", r.getCnpj());
					request.setAttribute("email", r.getEmail());
					request.setAttribute("telefone", r.getTelefone());
					request.setAttribute("listaEnderecos", r.getListaEnderecosPro());
					request.setAttribute("descricao", r.getDescricao());
					request.setAttribute("reservar", r.isReservar());
					request.setAttribute("retirada", r.isRetirada());
					request.setAttribute("rappi", r.isRappi());
					request.setAttribute("iFood", r.isIFood());
					request.setAttribute("uberEats", r.isUberEats());
					
					request.getRequestDispatcher("EditarRestaurante.jsp").forward(request, response);
					
				}else {
					request.setAttribute("msg", "Restaurante nao localizado no sistema.");
					request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
				}
				break;
			case "hotel":
				Hotel h = new Hotel();
				h.setId(Long.parseLong(request.getParameter("estabelecimento")));
				if (h.localizaPeloId() != null) {
					h = h.localizaPeloId();
					session.setAttribute("id", h.getId());
					request.setAttribute("nome", h.getNome());
					request.setAttribute("cnpj", h.getCnpj());
					request.setAttribute("email", h.getEmail());
					request.setAttribute("telefone", h.getTelefone());
					request.setAttribute("listaEnderecos", h.getListaEnderecosPro());
					request.setAttribute("descricao", h.getDescricao());
					request.setAttribute("direito", h.isDireito());
					request.setAttribute("booking", h.isBooking());
					request.setAttribute("tripAdvisor", h.isTripAdvisor());
					request.setAttribute("agencia", h.isAgencia());
					
					request.getRequestDispatcher("EditarHotel.jsp").forward(request, response);
					
				}else {
					request.setAttribute("msg", "Hotel nao localizado no sistema.");
					request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
				}
				break;
			default:
				request.setAttribute("msg", "Estabelecimento nao localizado no sistema.");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
		

	}

}
