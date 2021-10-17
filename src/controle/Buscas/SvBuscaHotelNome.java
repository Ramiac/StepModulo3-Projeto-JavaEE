package controle.Buscas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Hotel;

@WebServlet("/SvBuscaHotelNome")
public class SvBuscaHotelNome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String nome = request.getParameter("busca");
		
		Hotel h = new Hotel();
		h.setNome(nome);
		
		List<Hotel> listaHoteis = new ArrayList<Hotel>();

		if (h.localizarPeloNomeList() != null) {
			listaHoteis = h.localizarPeloNomeList();
			request.setAttribute("status", "ok");
			request.setAttribute("listaHoteis", listaHoteis);

			request.getRequestDispatcher("BuscaHotel2.jsp").forward(request, response);

		} else {
			request.setAttribute("msg", h.getMsg());
			request.getRequestDispatcher("BuscaHotel.jsp").forward(request, response);
		}
	}

}
