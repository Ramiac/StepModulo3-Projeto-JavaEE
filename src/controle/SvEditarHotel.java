package controle;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Endereco;
import model.entity.Hotel;

@WebServlet("/SvEditarHotel")
public class SvEditarHotel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			
			Hotel h = new Hotel();
			long id = (long) session.getAttribute("id");
			h.setId(id);
			h = h.localizaPeloId();

			String email = request.getParameter("email");
			h.setEmail(email);

			String telefone = request.getParameter("telefone");
			h.setTelefone(telefone);
			
			String descricao = request.getParameter("descricao");
			h.setDescricao(descricao);
			
			boolean direito = false;
			boolean booking = false;
			boolean tripAdvisor = false;
			boolean agencia = false;
			List<String> opcaoCompra = Arrays.asList(request.getParameterValues("opcaoCompra"));
			for (String oc : opcaoCompra) {
				switch (oc) {
				case "direito":
					direito = true;
					break;
				case "booking":
					booking = true;
					break;
				case "tripAdvisor":
					tripAdvisor = true;
					break;
				case "agencia":
					agencia = true;
					break;
				}
			}
			
			h.setDireito(direito);
			h.setBooking(booking);
			h.setTripAdvisor(tripAdvisor);
			h.setAgencia(agencia);
			
			String enderecoNome = request.getParameter("enderecoNome");
			if (!enderecoNome.equals("")) {
				String rua = request.getParameter("rua");
				int numero = Integer.parseInt(request.getParameter("numero"));
				String complemento = request.getParameter("complemento");
				String cep = request.getParameter("cep");
				String estado = request.getParameter("estado");
				String pais = request.getParameter("pais");

				Endereco e = new Endereco(enderecoNome, rua, numero, complemento, cep, estado, pais, null, null);
				List<Endereco> listaEnderecos = h.getListaEnderecosPro();
				listaEnderecos.add(e);
				h.setListaEnderecosPro(listaEnderecos);
			};

			if (h.editar()) {
				request.setAttribute("msg", "Hotel " + h.getNome() + ", editado com sucesso.");
				session.removeAttribute("id");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", h.getMsg());
				session.removeAttribute("id");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Deu Erro geral: " + e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
	}

}
