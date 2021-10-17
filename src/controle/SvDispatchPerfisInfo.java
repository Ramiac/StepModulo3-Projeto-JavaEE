package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Endereco;
import model.entity.Hotel;
import model.entity.Pessoa;
import model.entity.Publicacao;
import model.entity.Restaurante;
import model.entity.Usuario;

@WebServlet("/SvDispatchPerfisInfo")
public class SvDispatchPerfisInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Usuario u = new Usuario();
			long id = Long.parseLong(request.getParameter("id"));
			String tipoPublicacao = request.getParameter("tipoPublicacao");
			Publicacao pub = new Publicacao();
			List<Publicacao> listaPublicacao = new ArrayList<>();

			switch (tipoPublicacao) {
			case "Publicação pessoal":
				Pessoa p = new Pessoa();
				p.setId(id);
				
				if (p.localizaPeloId() != null) {
				
					p = p.localizaPeloId();
					
					
					u.setId(p.getId());
					u = u.localizaPeloId();
					
					if (pub.listaPublicacoesInfoUsuario(u) != null) {
						listaPublicacao = pub.listaPublicacoesInfoUsuario(u);
					}
					
					
					request.setAttribute("listaPublicacoes", listaPublicacao);
					
					request.setAttribute("nickName", p.getNickName());
					request.setAttribute("nome", p.getNome());
					request.setAttribute("nascimento", p.getNascimento());
					request.setAttribute("sexo", p.getSexo());
					request.setAttribute("email", p.getEmail());
					
					request.getRequestDispatcher("PaginaInfoPessoal.jsp").forward(request, response);
				}else {
					request.setAttribute("msg", p.getMsg());
					request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
				}
				
				break;
			case "Publicação profissional: Restaurante":
				Restaurante r = new Restaurante();
				r.setId(id);
				
				if (r.localizaPeloId() != null) {
				
					r = r.localizaPeloId();
					
					if (pub.listaPublicacoesInfoRestaurante(r) != null) {
						listaPublicacao = pub.listaPublicacoesInfoRestaurante(r);
					}
					
					request.setAttribute("listaPublicacoes", listaPublicacao);
					
					request.setAttribute("nome", r.getNome());
					request.setAttribute("descricao", r.getDescricao());
					request.setAttribute("cnpj", r.getCnpj());
					request.setAttribute("reservar", r.isReservar());
					request.setAttribute("retirada", r.isRetirada());
					request.setAttribute("rappi", r.isRappi());
					request.setAttribute("iFood", r.isIFood());
					request.setAttribute("uberEats", r.isUberEats());
					request.setAttribute("telefone", r.getTelefone());
					request.setAttribute("email", r.getEmail());
					
					List<Endereco> listaEndereco = new ArrayList<Endereco>();
					if (r.getListaEnderecosPro() != null) {
						listaEndereco = r.getListaEnderecosPro();
						request.setAttribute("listaEnderecos", listaEndereco);
					}else request.setAttribute("listaEnderecos", null);
					
					
					request.getRequestDispatcher("PaginaInfoRestaurante.jsp").forward(request, response);
				}else {
					request.setAttribute("msg", r.getMsg());
					request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
				}
				break;
			case "Publicação profissional: Hotel":
				Hotel h = new Hotel();
				h.setId(id);
				
				if (h.localizaPeloId() != null) {
				
					h = h.localizaPeloId();
					
					if (pub.listaPublicacoesInfoHotel(h) != null) {
						listaPublicacao = pub.listaPublicacoesInfoHotel(h);
					}
					
					request.setAttribute("listaPublicacoes", listaPublicacao);
					
					request.setAttribute("nome", h.getNome());
					request.setAttribute("descricao", h.getDescricao());
					request.setAttribute("cnpj", h.getCnpj());
					request.setAttribute("direito", h.isDireito());
					request.setAttribute("booking", h.isBooking());
					request.setAttribute("tripAdvisor", h.isTripAdvisor());
					request.setAttribute("agencia", h.isAgencia());
					request.setAttribute("telefone", h.getTelefone());
					request.setAttribute("email", h.getEmail());
					
					List<Endereco> listaEndereco = new ArrayList<Endereco>();
					if (h.getListaEnderecosPro() != null) {
						listaEndereco = h.getListaEnderecosPro();
						request.setAttribute("listaEnderecos", listaEndereco);
					}else request.setAttribute("listaEnderecos", null);
					
					
					request.getRequestDispatcher("PaginaInfoHotel.jsp").forward(request, response);
				}else {
					request.setAttribute("msg", h.getMsg());
					request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
				}
				
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Deu Erro geral: " + e.toString());
			request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
		}
		

	}

}
