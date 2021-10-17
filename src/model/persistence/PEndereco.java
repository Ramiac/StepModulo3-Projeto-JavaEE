package model.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.entity.Endereco;
import model.entity.Pessoa;
import model.entity.Restaurante;

public class PEndereco {
private Session session = HibernateUtil.abrirSession();
	
	public boolean salvar(Endereco e) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.save(e);
				session.getTransaction().commit();

				e.setMsg("Endereco na rua: " + e.getRua() + ", cadastrado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				e.setMsg("Erro ao salvar o Endereco no BD: \n" + e1.toString());
			} catch (Exception e2) {
				e.setMsg("Erro ao salvar o Endereco no BD: \n" + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			return false;
	}
	
	public Endereco localizarPeloId(Endereco e) {
		if (this.session != null) {

			try {

				Endereco eAux = session.find(Endereco.class, e.getId());

				if (eAux != null) {
					
					eAux.setMsg("Endereco localisado com sucesso.");
					return eAux;
				} else {
					e.setMsg("Endereco não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				e.setMsg("Erro ao localizar o Endereco: " + e1.toString());
			} catch (Exception e2) {
				e.setMsg("Erro ao localizar o Endereco: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		} else
			e.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public boolean editar(Endereco e) {

		if (this.session != null) {
			try {
				session.beginTransaction();
				session.update(e);
				session.getTransaction().commit();

				e.setMsg("Endereco editado com sucesso");
				return true;

			} catch (HibernateException e1) {
				e.setMsg("Erro ao editar o Endereco. Erro: " + e1.toString());
			} catch (Exception e2) {
				e.setMsg("Erro ao editar o Endereco. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}

			return false;
		} else
			e.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public boolean deletar(Endereco e) {
		if (this.session != null) {
			try {
				String hql = "delete from enderecos where id = :id";
				
				session.beginTransaction();
				session.createQuery(hql).setParameter("id", e.getId()).executeUpdate();
				session.getTransaction().commit();

				e.setMsg("Endereco deletado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				e1.printStackTrace();
				e.setMsg("Erro1 ao deletar o Endereco. Erro: " + e1.toString());
			} catch (Exception e2) {
				e2.printStackTrace();
				e.setMsg("Erro2 ao deletar o Endereco. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			e.setMsg(HibernateUtil.erro);
			return false;
	}
	
}
