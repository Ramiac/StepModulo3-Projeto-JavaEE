package model.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import model.entity.Hotel;
import model.entity.Pessoa;
import model.entity.Publicacao;
import model.entity.Restaurante;
import model.entity.Usuario;

public class PPublicacao {
	
private Session session = HibernateUtil.abrirSession();
	
	public boolean salvar(Publicacao p) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.save(p);
				session.getTransaction().commit();

				p.setMsg("Publicacao cadastrada com sucesso.");
				return true;

			} catch (HibernateException e1) {
				p.setMsg("Erro ao salvar o Publicacao no BD: \n" + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro ao salvar o Publicacao no BD: \n" + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			return false;
	}
	
	public Publicacao localizarPeloId(Publicacao p) {
		if (this.session != null) {

			try {

				Publicacao pAux = session.find(Publicacao.class, p.getId());

				if (pAux != null) {
					
					pAux.setMsg("Publicacao localisada com sucesso.");
					return pAux;
				} else {
					p.setMsg("Publicacao não localizada no sistema.");
					return null;
				}

			} catch (HibernateException e1) {
				p.setMsg("Erro ao localizar a Publicacao: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro ao localizar a Publicacao: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		} else
			p.setMsg(HibernateUtil.erro);
			return null;
	}
	
	public boolean editar(Publicacao p) {

		if (this.session != null) {
			try {
				session.beginTransaction();
				session.update(p);
				session.getTransaction().commit();

				p.setMsg("Publicacao editado com sucesso");
				return true;

			} catch (HibernateException e1) {
				p.setMsg("Erro ao editar o Publicacao. Erro: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro ao editar o Publicacao. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}

			return false;
		} else
			p.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public boolean deletar(Publicacao p) {
		if (this.session != null) {
			try {
				session.beginTransaction();
				session.delete(p);
				session.getTransaction().commit();

				p.setMsg("Comentario deletado com sucesso.");
				return true;

			} catch (HibernateException e1) {
				p.setMsg("Erro ao deletar o Publicacao. Erro: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro ao deletar o Publicacao. Erro: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return false;
		} else
			p.setMsg(HibernateUtil.erro);
			return false;
	}
	
	public List<Publicacao> listaDezPublicacoes(Publicacao p){
		if (this.session != null) {
			try {
				Criteria criteria = session.createCriteria(Publicacao.class);
				criteria.addOrder(Order.desc("id"));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				criteria.setMaxResults(10);
				List<Publicacao> pAux = criteria.list();
				
				if (pAux.size() != 0) {
					return pAux;
				}else {
					p.setMsg("Nenhuma publicacao no sistema.");
				}
				
			}catch (HibernateException e1) {
				p.setMsg("Erro do Hibernate: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro geral a criar lista de 10 publicacoes: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		}else p.setMsg(HibernateUtil.erro);
		return null;
	}
	
	public List<Publicacao> listaPublicacoesUsuario(Publicacao p, Usuario u){
		if (this.session != null) {
			try {
				Criteria criteria = session.createCriteria(Publicacao.class);
				criteria.add(Restrictions.eq("usuario", u));
				criteria.addOrder(Order.desc("id"));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				
				List<Publicacao> pAux = criteria.list();
				
				if (pAux.size() != 0) {
					return pAux;
				}else {
					p.setMsg("Nenhuma publicacao desse usuario no sistema.");
				}
				
			}catch (HibernateException e1) {
				p.setMsg("Erro do Hibernate: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro geral a criar lista de 10 publicacoes: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		}else p.setMsg(HibernateUtil.erro);
		return null;
	}
	
	public List<Publicacao> listaPublicacoesInfoUsuario(Publicacao p, Usuario u){
		if (this.session != null) {
			try {
				Criteria criteria = session.createCriteria(Publicacao.class);
				criteria.add(Restrictions.eq("usuario", u));
				criteria.add(Restrictions.isNull("restaurante"));
				criteria.add(Restrictions.isNull("hotel"));
				criteria.addOrder(Order.desc("id"));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				
				List<Publicacao> pAux = criteria.list();
				
				if (pAux.size() != 0) {
					return pAux;
				}else {
					p.setMsg("Nenhuma publicacao desse usuario no sistema.");
				}
				
			}catch (HibernateException e1) {
				p.setMsg("Erro do Hibernate: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro geral a criar lista de 10 publicacoes: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		}else p.setMsg(HibernateUtil.erro);
		return null;
	}
	
	public List<Publicacao> listaPublicacoesInfoRestaurante(Publicacao p, Restaurante r){
		if (this.session != null) {
			try {
				Criteria criteria = session.createCriteria(Publicacao.class);
				criteria.add(Restrictions.eq("restaurante", r));
				criteria.addOrder(Order.desc("id"));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				
				List<Publicacao> pAux = criteria.list();
				
				if (pAux.size() != 0) {
					return pAux;
				}else {
					p.setMsg("Nenhuma publicacao desse usuario no sistema.");
				}
				
			}catch (HibernateException e1) {
				p.setMsg("Erro do Hibernate: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro geral a criar lista de 10 publicacoes: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		}else p.setMsg(HibernateUtil.erro);
		return null;
	}
	
	public List<Publicacao> listaPublicacoesInfoHotel(Publicacao p, Hotel h){
		if (this.session != null) {
			try {
				Criteria criteria = session.createCriteria(Publicacao.class);
				criteria.add(Restrictions.eq("hotel", h));
				criteria.addOrder(Order.desc("id"));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				
				List<Publicacao> pAux = criteria.list();
				
				if (pAux.size() != 0) {
					return pAux;
				}else {
					p.setMsg("Nenhuma publicacao desse usuario no sistema.");
				}
				
			}catch (HibernateException e1) {
				p.setMsg("Erro do Hibernate: " + e1.toString());
			} catch (Exception e2) {
				p.setMsg("Erro geral a criar lista de 10 publicacoes: " + e2.toString());
			} finally {
				HibernateUtil.fecharSession();
			}
			return null;
		}else p.setMsg(HibernateUtil.erro);
		return null;
	}

}
