package model.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import model.entity.Endereco;
import model.entity.Pessoa;

public class PPessoa {
private Session session = HibernateUtil.abrirSession();
	
	public boolean salvar(Pessoa p) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.save(p);
				session.getTransaction().commit();

				p.setMsg("Comentario: " + p.getNome() + ", cadastrado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				p.setMsg("Erro ao salvar o Pessoa no BD: \n" + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro ao salvar o Pessoa no BD2: \n" + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			p.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public Pessoa localizarPeloId(Pessoa p) {
		if (this.session != null) {

			try {

				Pessoa pAux = session.find(Pessoa.class, p.getId());

				if (pAux != null) {
					
					pAux.setMsg("Pessoa localisado com sucesso.");
					return pAux;
				} else {
					p.setMsg("Pessoa não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				p.setMsg("Erro ao localizar a Pessoa: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro ao localizar a Pessoa: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		} else
			p.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public boolean editar(Pessoa p) {

		if (this.session != null) {
			try {
//				List<Endereco> listaEnderecos = p.getListaEnderecos();
				
				
				session.beginTransaction();
				session.update(p);
				session.getTransaction().commit();
				
//				e.salvar();

				p.setMsg("Pessoa editado com sucesso");
				return true;

			} catch (HibernateException e1) {
				p.setMsg("Erro ao editar o Pessoa. Erro: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro ao editar o Pessoa. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}

			return false;
		} else
			p.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public boolean deletar(Pessoa p) {
		if (this.session != null) {
			try {
				
				String hql = "delete from usuarios where id = :id";
				
				session.beginTransaction();
				session.createQuery(hql).setParameter("id", p.getId()).executeUpdate();
				session.getTransaction().commit();


				p.setMsg("Pessoa deletado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				p.setMsg("Erro ao deletar o Pessoa. Erro: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro ao deletar o Pessoa. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			p.setMsg(HibernateUtil.erro);
			return false;
	}
	


}
