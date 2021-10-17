package controle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Pessoa;

@WebServlet("/SvCadastraUsuario")
public class SvCadastraUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nickName = request.getParameter("nickName");
			String senha1 = request.getParameter("senha1");
			String nome = request.getParameter("nome");
			char sexo = request.getParameter("sexo").charAt(0);
			Date nascimento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("nascimento"));
			String cpf = request.getParameter("cpf");
			String email = request.getParameter("email");
			String telefone = request.getParameter("telefone");
			

			Pessoa p = new Pessoa(nickName, senha1, null, null, null, null, nome, nascimento, sexo, cpf, email, telefone,
					null);

			if (p.salvar()) {
				request.setAttribute("erro", "Usuario cadastrado com sucesso.");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.setAttribute("erro", p.getMsg());
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

			

		} catch (ParseException e) {
			request.setAttribute("msg", "Erro ao cadastrar data de nascimento: " + e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
			request.setAttribute("msg", "Deu Erro geral: " + e1.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}

	}

}
