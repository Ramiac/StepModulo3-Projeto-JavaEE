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
import model.entity.Restaurante;
import model.entity.Usuario;

@WebServlet("/SvCadastraRestaurante")
public class SvCadastraRestaurante extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			boolean reservar = false;
			boolean retirada = false;
			boolean rappi = false;
			boolean iFood = false;
			boolean uberEats = false;
			List<String> opcaoCompra = Arrays.asList(request.getParameterValues("opcaoCompra"));
			for (String oc : opcaoCompra) {
				switch (oc) {
				case "reservar":
					reservar = true;
					break;
				case "retirada":
					retirada = true;
					break;
				case "rappi":
					rappi = true;
					break;
				case "ifood":
					iFood = true;
					break;
				case "ubereats":
					uberEats = true;
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

			Restaurante r = new Restaurante(listaUsuarios, nome, cnpj, email, telefone, listaEnderecos, descricao, reservar,
					retirada, rappi, iFood, uberEats);

			if (r.salvar()) {
				session.setAttribute("usuarioLogado", u);
				request.setAttribute("msg", "Restaurante Cadastrado com sucesso.");
				
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", r.getMsg());
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
