package model.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import model.entity.Comentario;

public class PComentario {
	
	private Session session = HibernateUtil.abrirSession();
	
	public boolean salvar(Comentario c) {
		if (this.session != null) {
			try {
//				session.beginTransaction();
				session.save(c);
//				session.getTransaction().commit();

				c.setMsg("Comentario: " + c.getComentario() + ", cadastrado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				c.setMsg("Erro ao salvar o comentario no BD: \n" + e1.toString());
			} catch (Exception e2) {
				c.setMsg("Erro ao salvar o comentario no BD: \n" + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			return false;
	}
	
	public Comentario localizarPeloId(Comentario c) {
		if (this.session != null) {

			try {

				Comentario cAux = session.find(Comentario.class, c.getId());

				if (cAux != null) {
					
					cAux.setMsg("Comentario localisado com sucesso.");
					return cAux;
				} else {
					c.setMsg("Comentario não localizado no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				c.setMsg("Erro ao localizar o Comentario: " + e1.toString());
			} catch (Exception e2) {
				c.setMsg("Erro ao localizar o Comentario: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		} else
			c.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public boolean editar(Comentario c) {

		if (this.session != null) {
			try {
				session.beginTransaction();
				session.update(c);
				session.getTransaction().commit();

				c.setMsg("Comentario editado com sucesso");
				return true;

			} catch (HibernateException e1) {
				c.setMsg("Erro ao editar o Comentario. Erro: " + e1.toString());
			} catch (Exception e2) {
				c.setMsg("Erro ao editar o Comentario. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}

			return false;
		} else
			c.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public boolean deletar(Comentario c) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.delete(c);
				session.getTransaction().commit();

				c.setMsg("Comentario deletado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				c.setMsg("Erro ao deletar o Comentario. Erro: " + e1.toString());
			} catch (Exception e2) {
				c.setMsg("Erro ao deletar o Comentario. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			c.setMsg(HibernateUtil.erro);
			return false;
	}
}
