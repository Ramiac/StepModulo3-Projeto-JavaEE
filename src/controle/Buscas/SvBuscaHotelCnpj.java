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


@WebServlet("/SvBuscaHotelCnpj")
public class SvBuscaHotelCnpj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String cnpj = request.getParameter("busca");
		
		Hotel h = new Hotel();
		h.setCnpj(cnpj);
		
		List<Hotel> listaHoteis = new ArrayList<Hotel>();

		if (h.localizarPeloCnpjList() != null) {
			listaHoteis = h.localizarPeloCnpjList();
			request.setAttribute("status", "ok");
			request.setAttribute("listaHoteis", listaHoteis);

			request.getRequestDispatcher("BuscaHotel2.jsp").forward(request, response);

		} else {
			request.setAttribute("msg", h.getMsg());
			request.getRequestDispatcher("BuscaHotel.jsp").forward(request, response);
		}
	}

}
