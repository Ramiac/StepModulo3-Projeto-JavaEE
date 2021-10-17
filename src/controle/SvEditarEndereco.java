package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Endereco;

@WebServlet("/SvEditarEndereco")
public class SvEditarEndereco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Endereco e = new Endereco();
			e.setId(Long.parseLong(request.getParameter("id")));
			
			if (e.localizaPeloId() != null) {
				e.setNome(request.getParameter("nome"));
				e.setRua(request.getParameter("rua"));
				e.setNumero(Integer.parseInt(request.getParameter("numero")));
				e.setComplemento(request.getParameter("complemento"));
				e.setCep(request.getParameter("cep"));
				e.setEstado(request.getParameter("estado"));
				e.setPais(request.getParameter("pais"));
			} else {
				request.setAttribute("msg", "localiza pelo id retornou Null");
				request.getRequestDispatcher("CompletaPerfil.jsp").forward(request, response);
			}
			
			if (e.editar()) {

					request.setAttribute("msg", "Endereco editado com sucesso");
					request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);


			}else {
				request.setAttribute("msg", "Editar deu erro");
				request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
	}

}
