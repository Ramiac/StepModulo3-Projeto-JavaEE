package controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Usuario;

@WebServlet("/SvLogin")
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName = request.getParameter("nickName");
		String senha = request.getParameter("senha");
		HttpSession session = request.getSession();
		
		Usuario u = new Usuario();
		
		if (u.login(nickName, senha)) {
			session.setAttribute("usuarioLogado", u);
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}else {
			request.setAttribute("erro", u.getMsg());
			session.invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
