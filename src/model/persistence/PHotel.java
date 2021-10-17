package model.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import model.entity.Endereco;
import model.entity.Hotel;
import model.entity.Usuario;

public class PHotel {
private Session session = HibernateUtil.abrirSession();
	
	public boolean salvar(Hotel h) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.save(h);
				session.getTransaction().commit();

				h.setMsg("Hotel: " + h.getNome() + ", cadastrado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				h.setMsg("Erro ao salvar o Hotel no BD: \n" + e1.toString());
			} catch (Exception e2) {
				h.setMsg("Erro ao salvar o Hotel no BD: \n" + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			return false;
	}
	
	public Hotel localizarPeloId(Hotel h) {
		if (this.session != null) {

			try {

				Hotel hAux = session.find(Hotel.class, h.getId());

				if (hAux != null) {
					
					hAux.setMsg("Hotel localisado com sucesso.");
					return hAux;
				} else {
					h.setMsg("Hotel não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				h.setMsg("Erro ao localizar o Hotel: " + e1.toString());
			} catch (Exception e2) {
				h.setMsg("Erro ao localizar o Hotel: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		} else
			h.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public boolean editar(Hotel h) {

		if (this.session != null) {
			try {
				session.beginTransaction();
				session.update(h);
				session.getTransaction().commit();

				h.setMsg("Hotel editado com sucesso");
				return true;

			} catch (HibernateException e1) {
				h.setMsg("Erro ao editar o Hotel. Erro: " + e1.toString());
			} catch (Exception e2) {
				h.setMsg("Erro ao editar o Hotel. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}

			return false;
		} else
			h.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public boolean deletar(Hotel h) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.delete(h);
				session.getTransaction().commit();

				h.setMsg("Hotel deletado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				h.setMsg("Erro ao deletar o Hotel. Erro: " + e1.toString());
			} catch (Exception e2) {
				h.setMsg("Erro ao deletar o Hotel. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			h.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public List<Hotel> localizarPeloNomeList(Hotel h){
		if (this.session != null) {

			try {
				
				Criteria criteria = session.createCriteria(Hotel.class);
				criteria.add(Restrictions.like("nome", h.getNome(), MatchMode.ANYWHERE));
				
				List<Hotel> hAux = criteria.list();
				
				if (hAux.size() != 0) {
					h.setMsg("Hotel localisado com sucesso.");
					return hAux;
				} else {
					h.setMsg("Hotel não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				h.setMsg("Erro Hibernate ao localizar o Hotel: " + e1.toString());
			} catch (Exception e2) {
				h.setMsg("Erro ao localizar o Hotel: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;

		} else
			h.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public List<Hotel> localizarPeloCnpjList(Hotel h){
		if (this.session != null) {

			try {
				
				Criteria criteria = session.createCriteria(Hotel.class);
				criteria.add(Restrictions.eq("cnpj", h.getCnpj()));
				
				List<Hotel> hAux = criteria.list();
				
				if (hAux.size() != 0) {
					h.setMsg("Hotel localisado com sucesso.");
					return hAux;
				} else {
					h.setMsg("Hotel não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				h.setMsg("Erro Hibernate ao localizar o Hotel: " + e1.toString());
			} catch (Exception e2) {
				h.setMsg("Erro ao localizar o Hotel: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;

		} else
			h.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public List<Hotel> localizarPeloCepList(Hotel h, Endereco e){
		if (this.session != null) {

			try {
				
				Criteria criteria = session.createCriteria(Hotel.class);
				criteria.createAlias("listaEnderecosPro", "enderecoAlias");
				
				criteria.add(Restrictions.eq("enderecoAlias.cep", e.getCep()));
				
				List<Hotel> hAux = criteria.list();
			
				
				if (hAux.size() != 0) {
					h.setMsg("Hotel localisado com sucesso.");
					return hAux;
				} else {
					h.setMsg("Hotel não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				e1.printStackTrace();
				h.setMsg("Erro Hibernate ao localizar o Hotel: " + e1.toString());
			} catch (Exception e2) {
				h.setMsg("Erro ao localizar o Hotel: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;

		} else
			h.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public List<Hotel> localizarPeloUsuarioList(Hotel h, Usuario u){
		if (this.session != null) {

			try {
				
				Criteria criteria = session.createCriteria(Hotel.class);
				criteria.createAlias("listaUsuarios", "usuarioAlias");
				
				criteria.add(Restrictions.eq("usuarioAlias.id", u.getId()));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				
				List<Hotel> hAux = criteria.list();
			
				
				if (hAux.size() != 0) {
					h.setMsg("Hotel localisado com sucesso.");
					return hAux;
				} else {
					h.setMsg("Hotel não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				e1.printStackTrace();
				h.setMsg("Erro Hibernate ao localizar o Hotel: " + e1.toString());
			} catch (Exception e2) {
				h.setMsg("Erro ao localizar o Hotel: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;

		} else
			h.setMsg(HibernateUtil.erro);
			return null;
	}

}
