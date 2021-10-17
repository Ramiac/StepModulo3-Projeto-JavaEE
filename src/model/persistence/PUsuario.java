package model.persistence;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.entity.Pessoa;
import model.entity.Usuario;

public class PUsuario {
	private Session session = HibernateUtil.abrirSession();

	public boolean login(Usuario u) {
		if (this.session != null) {
			try {
				
				Criteria criteria = session.createCriteria(Usuario.class);
				criteria.add(Restrictions.eq("nickName", u.getNickName()));
				criteria.add(Restrictions.eq("senha", u.getSenha()));
				
				Usuario uAux = (Usuario) criteria.uniqueResult();
				
				if (uAux != null) {
					u.setId(uAux.getId());
					return true;
				} else {
					u.setMsg("NickName ou senha não localizado no sistema.");
					return false;
				}
			} catch (HibernateException e1) {
				u.setMsg("Erro ao login de clientes: " + e1.toString());
				return false;
			} catch (Exception e2) {
				u.setMsg("Erro ao login de clientes: " + e2.toString());
				return false;
			} finally {
				HibernateUtil.fecharSession();
			}
		} else
			u.setMsg(HibernateUtil.erro);
			return false;

	}	
	
	@Transactional
	public Usuario localizarPeloId(Usuario u) {
		if (this.session != null) {

			try {
				session.beginTransaction();
				Usuario uAux = session.find(Usuario.class, u.getId());
				session.getTransaction().commit();
				
				if (uAux != null) {
					
					uAux.setMsg("Usuario localisado com sucesso.");
					return uAux;
				} else {
					u.setMsg("Usuario não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				u.setMsg("Erro ao localizar o usuario: " + e1.toString());
			} catch (Exception e2) {
				u.setMsg("Erro ao localizar o usuario: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		} else
			u.setMsg(HibernateUtil.erro);
			return null;
	}
	
}
