package model.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import model.entity.Endereco;
import model.entity.Restaurante;
import model.entity.Usuario;

public class PRestaurante {
	
private Session session = HibernateUtil.abrirSession();
	
	public boolean salvar(Restaurante r) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.save(r);
				session.getTransaction().commit();

				r.setMsg("Restaurante: " + r.getNome() + ", cadastrado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				r.setMsg("Erro ao salvar o Restaurante no BD: \n" + e1.toString());
			} catch (Exception e2) {
				r.setMsg("Erro ao salvar o Restaurante no BD: \n" + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			return false;
	}
	
	public Restaurante localizarPeloId(Restaurante r) {
		if (this.session != null) {

			try {

				Restaurante rAux = session.find(Restaurante.class, r.getId());

				if (rAux != null) {
					
					rAux.setMsg("Restaurante localisada com sucesso.");
					return rAux;
				} else {
					r.setMsg("Restaurante não localizada no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				r.setMsg("Erro ao localizar o Restaurante: " + e1.toString());
			} catch (Exception e2) {
				r.setMsg("Erro ao localizar o Restaurante: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		} else
			r.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public boolean editar(Restaurante r) {

		if (this.session != null) {
			try {
				session.beginTransaction();
				session.update(r);
				session.getTransaction().commit();

				r.setMsg("Restaurante editado com sucesso");
				return true;

			} catch (HibernateException e1) {
				r.setMsg("Erro ao editar o Restaurante. Erro: " + e1.toString());
			} catch (Exception e2) {
				r.setMsg("Erro ao editar o Restaurante. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}

			return false;
		} else
			r.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public boolean deletar(Restaurante r) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.delete(r);
				session.getTransaction().commit();

				r.setMsg("Restaurante deletado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				r.setMsg("Erro ao deletar o Restaurante. Erro: " + e1.toString());
			} catch (Exception e2) {
				r.setMsg("Erro ao deletar o Restaurante. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			r.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public List<Restaurante> localizarPeloNomeList(Restaurante r){
		if (this.session != null) {

			try {
				
				Criteria criteria = session.createCriteria(Restaurante.class);
				criteria.add(Restrictions.like("nome", r.getNome(), MatchMode.ANYWHERE));
				
				List<Restaurante> rAux = criteria.list();
				
				if (rAux.size() != 0) {
					r.setMsg("Restaurante localisado com sucesso.");
					return rAux;
				} else {
					r.setMsg("Restaurante não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				r.setMsg("Erro Hibernate ao localizar o Restaurante: " + e1.toString());
			} catch (Exception e2) {
				r.setMsg("Erro ao localizar o Restaurante: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;

		} else
			r.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public List<Restaurante> localizarPeloCnpjList(Restaurante r){
		if (this.session != null) {

			try {
				
				Criteria criteria = session.createCriteria(Restaurante.class);
				criteria.add(Restrictions.eq("cnpj", r.getCnpj()));
				
				List<Restaurante> rAux = criteria.list();
				
				if (rAux.size() != 0) {
					r.setMsg("Restaurante localisado com sucesso.");
					return rAux;
				} else {
					r.setMsg("Restaurante não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				r.setMsg("Erro Hibernate ao localizar o Restaurante: " + e1.toString());
			} catch (Exception e2) {
				r.setMsg("Erro ao localizar o Restaurante: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;

		} else
			r.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public List<Restaurante> localizarPeloCepList(Restaurante r, Endereco e){
		if (this.session != null) {

			try {
				
				Criteria criteria = session.createCriteria(Restaurante.class);
				criteria.createAlias("listaEnderecosPro", "enderecoAlias");
				criteria.add(Restrictions.eq("enderecoAlias.cep", e.getCep()));
				
				List<Restaurante> rAux = criteria.list();
				
				if (rAux.size() != 0) {
					r.setMsg("Restaurante localisado com sucesso.");
					return rAux;
				} else {
					r.setMsg("Restaurante não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				e1.printStackTrace();
				r.setMsg("Erro Hibernate ao localizar o Restaurante: " + e1.toString());
			} catch (Exception e2) {
				r.setMsg("Erro ao localizar o Restaurante: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;

		} else
			r.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public List<Restaurante> localizarPeloUsuarioList(Restaurante r, Usuario u){
		if (this.session != null) {

			try {
				
				Criteria criteria = session.createCriteria(Restaurante.class);
				criteria.createAlias("listaUsuarios", "usuarioAlias");
				
				criteria.add(Restrictions.eq("usuarioAlias.id", u.getId()));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				
				List<Restaurante> rAux = criteria.list();
			 
				
				if (rAux.size() != 0) {
					r.setMsg("Restaurante localisado com sucesso.");
					return rAux;
				} else {
					r.setMsg("Restaurante não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				e1.printStackTrace();
				r.setMsg("Erro Hibernate ao localizar o Restaurante: " + e1.toString());
			} catch (Exception e2) {
				r.setMsg("Erro ao localizar o Restaurante: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;

		} else
			r.setMsg(HibernateUtil.erro);
			return null;
	}

}
