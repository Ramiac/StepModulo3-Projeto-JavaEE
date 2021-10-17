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
import model.entity.Restaurante;
import model.entity.Usuario;

@WebServlet("/SvEditarRestaurante")
public class SvEditarRestaurante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			
			Restaurante r = new Restaurante();
			long id = (long) session.getAttribute("id");
			r.setId(id);
			r = r.localizaPeloId();

			String email = request.getParameter("email");
			r.setEmail(email);

			String telefone = request.getParameter("telefone");
			r.setTelefone(telefone);
			
			String descricao = request.getParameter("descricao");
			r.setDescricao(descricao);
			
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
			
			r.setReservar(reservar);
			r.setRetirada(retirada);
			r.setRappi(rappi);
			r.setIFood(iFood);
			r.setUberEats(uberEats);	
			
			String enderecoNome = request.getParameter("enderecoNome");
			if (!enderecoNome.equals("")) {
				String rua = request.getParameter("rua");
				int numero = Integer.parseInt(request.getParameter("numero"));
				String complemento = request.getParameter("complemento");
				String cep = request.getParameter("cep");
				String estado = request.getParameter("estado");
				String pais = request.getParameter("pais");

				Endereco e = new Endereco(enderecoNome, rua, numero, complemento, cep, estado, pais, null, null);
				List<Endereco> listaEnderecos = r.getListaEnderecosPro();
				listaEnderecos.add(e);
				r.setListaEnderecosPro(listaEnderecos);
			};

			if (r.editar()) {
				session.setAttribute("usuarioLogado", u);
				request.setAttribute("msg", "Restaurante " + r.getNome() + ", editado com sucesso.");
				session.removeAttribute("id");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", r.getMsg());
				session.setAttribute("usuarioLogado", u);
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
