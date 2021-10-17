package controle;

import java.io.IOException;
import java.util.ArrayList;
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
import model.entity.Usuario;

@WebServlet("/SvCadastraHotel")
public class SvCadastraHotel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			listaUsuarios.add(u);

			String nome = request.getParameter("nome");
			String cnpj = request.getParameter("cnpj");
			String email = request.getParameter("email");
			String telefone = request.getParameter("telefone");
			
			String descricao = request.getParameter("descricao");
			boolean direito = false;
			boolean agencia = false;
			boolean booking = false;
			boolean tripAdvisor = false;
			List<String> opcaoReserva = Arrays.asList(request.getParameterValues("opcaoReserva"));
			for (String or : opcaoReserva) {
				switch (or) {
				case "direito":
					direito = true;
					break;
				case "agencia":
					agencia = true;
					break;
				case "booking":
					booking = true;
					break;
				case "tripadvisor":
					tripAdvisor = true;
					break;
				}
			}

			String enderecoNome = request.getParameter("enderecoNome");
			String rua = request.getParameter("rua");
			int numero = Integer.parseInt(request.getParameter("numero"));
			String complemento = request.getParameter("complemento");
			String cep = request.getParameter("cep");
			String estado = request.getParameter("estado");
			String pais = request.getParameter("pais");

			Endereco e = new Endereco(enderecoNome, rua, numero, complemento, cep, estado, pais, null, null);
			List<Endereco> listaEnderecos = new ArrayList<>();
			listaEnderecos.add(e);

			Hotel h = new Hotel(listaUsuarios, nome, cnpj, email, telefone, listaEnderecos, descricao, direito, booking, tripAdvisor, agencia);
			
			if (h.salvar()) {
				session.setAttribute("usuarioLogado", u);
				request.setAttribute("msg", "Hotel Cadastrado com sucesso.");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", h.getMsg());
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
