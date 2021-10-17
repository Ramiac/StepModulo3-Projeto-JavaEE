package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Pessoa;
import model.entity.Usuario;

@WebServlet("/SvCadastraCompletaPerfil")
public class SvCadastraCompletaPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			Usuario u = (Usuario) session.getAttribute("usuarioLogado");
//			if (session.getLastAccessedTime() < (1000*60*15) || u != null) // ver a materia para tempo, data de hoje + 15 minute {
				Pessoa p = new Pessoa();
				p.setId(u.getId());
				p = p.localizaPeloId();

				request.setAttribute("nickName", p.getNickName());
				request.setAttribute("nomeCompleto", p.getNome());

				Date dataNacimento = p.getNascimento();
				Locale local = new Locale("pt", "BR");
				DateFormat df = new SimpleDateFormat("dd 'de' MMMM ', ' yyyy", local);
				request.setAttribute("dataNacimento", df.format(dataNacimento));

				switch (p.getSexo()) {
				case 'M':
					request.setAttribute("Sexo", "Masculino");
					break;
				case 'F':
					request.setAttribute("Sexo", "Feminino");
					break;
				case 'O':
					request.setAttribute("Sexo", "Outro");
					break;
				}

				request.setAttribute("cpf", p.getCpf());

				request.setAttribute("email", p.getEmail());
				request.setAttribute("telefone", p.getTelefone());

				request.setAttribute("listaEnderecos", p.getListaEnderecos());

				request.getRequestDispatcher("CompletaPerfil.jsp").forward(request, response);

//			}else {
//				request.setAttribute("msg", "Sessão incerrada por tempo ou por usuario nulo." + e.toString());
//				request.getRequestDispatcher("index.jsp").forward(request, response);
//			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Deu Erro geral: " + e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}

	}

}
