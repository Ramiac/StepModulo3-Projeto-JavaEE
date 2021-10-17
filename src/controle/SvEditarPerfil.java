package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Endereco;
import model.entity.Pessoa;
import model.entity.Usuario;

@WebServlet("/SvEditarPerfil")
public class SvEditarPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
			Pessoa p = new Pessoa();
			p.setId(u.getId());
			p = p.localizaPeloId();

			String nickName = request.getParameter("nickName");
			p.setNickName(nickName);

			String senha = request.getParameter("senha1");
			if (senha != null)
				p.setSenha(senha);

			String email = request.getParameter("email");
			p.setEmail(email);

			String telefone = request.getParameter("telefone");
			p.setTelefone(telefone);

			String enderecoNome = request.getParameter("enderecoNome");
			
			Endereco e = new Endereco();
			
			if (!enderecoNome.equals("")) {
				String rua = request.getParameter("rua");
				int numero = Integer.parseInt(request.getParameter("numero"));
				String complemento = request.getParameter("complemento");
				String cep = request.getParameter("cep");
				String estado = request.getParameter("estado");
				String pais = request.getParameter("pais");

				e = new Endereco(enderecoNome, rua, numero, complemento, cep, estado, pais, null, null);
				List<Endereco> listaEnderecos = p.getListaEnderecos();
				listaEnderecos.add(e);
				p.setListaEnderecos(listaEnderecos);
			};

			if (p.editar()) {
				session.setAttribute("usuarioLogado", u);
				request.setAttribute("msg", "Usuario editado com sucesso.");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", p.getMsg());
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
