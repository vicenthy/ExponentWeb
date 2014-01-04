package persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;






public class ClassDao<T> implements IDao<T>{
	

	private Class<T> entity;
	private Session session;
	
	
	
	
	public ClassDao(Class<T> entity) {
	this.entity = entity;
	session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	
	
	
	@Override
	public void create(T t) {
		try {
	    	session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.save(t);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public T save(T t) {
		try {
	    	session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.save(t);			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return t;
	
	}
	
	@Override
	public void update(T t) {
		try {
	    	session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.update(t);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete(T t) {
		
		try {
	    	session = HibernateUtil.getSessionFactory().getCurrentSession();

			session.delete(t);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public List<T> findAll() {
    	session = HibernateUtil.getSessionFactory().getCurrentSession();

		@SuppressWarnings("unchecked")
		List<T> lista = (List<T>)session.createCriteria(entity).list();
		return lista;
	}

	@Override
	public T findByCod(Integer cod) {
			@SuppressWarnings("unchecked")
			T result = (T) session.createCriteria(entity).add(Restrictions.idEq(cod)).uniqueResult();
		return result;
	}
	
	
	@Override
	public List<T> findByName(String nome) {
	@SuppressWarnings("unchecked")
		List<T> lista = (List<T>)session.createCriteria(entity).add(Restrictions.ilike("nome", nome, MatchMode.START)).list();
		return lista;
	
		
	}
	
	
	
	 @SuppressWarnings("unchecked")
	public List<T> consultaByTipo(int startIndex, Integer sizeBlock, int tipoConsulta, String campo, Object valor) {

	    	session = HibernateUtil.getSessionFactory().getCurrentSession();

         Criteria crit = session.createCriteria(entity);
         if(tipoConsulta==0){
              crit.add(Restrictions.ilike(campo, "%"+valor+"%"));
         }if(tipoConsulta==1){
              crit.add(Restrictions.eq(campo, valor));
         }if(tipoConsulta==2){
              crit.add(Restrictions.ilike(campo, "%"+valor));
         }if(tipoConsulta==3){
              crit.add(Restrictions.ilike(campo, valor+"%"));
         }if(tipoConsulta==4){
              crit.add(Restrictions.lt(campo, valor));
         }if(tipoConsulta==5){
              crit.add(Restrictions.le(campo, valor));
         }if(tipoConsulta==6){
              crit.add(Restrictions.ne(campo, valor));
         }
         if(sizeBlock!=null){
             crit.setMaxResults(sizeBlock);
         }if(startIndex!=0){
             crit.setFirstResult(startIndex);

         }         return (List<T>)crit.list();

	 
	 }

	 

    public Criteria consultaByTipoCriteria(int startIndex, Integer sizeBlock, int tipoConsulta, String campo, Object valor) {

    	
    	session = HibernateUtil.getSessionFactory().getCurrentSession();

    	Criteria crit = session.createCriteria(entity);
        if(tipoConsulta==0){
             crit.add(Restrictions.ilike(campo, "%"+valor+"%"));
        }if(tipoConsulta==1){
             crit.add(Restrictions.eq(campo, valor));
        }if(tipoConsulta==2){
             crit.add(Restrictions.ilike(campo, "%"+valor));
        }if(tipoConsulta==3){
             crit.add(Restrictions.ilike(campo, valor+"%"));
        }if(tipoConsulta==4){
             crit.add(Restrictions.lt(campo, valor));
        }if(tipoConsulta==5){
             crit.add(Restrictions.le(campo, valor));
        }if(tipoConsulta==6){
             crit.add(Restrictions.ne(campo, valor));
        }
        if(sizeBlock!=null){
            crit.setMaxResults(sizeBlock);
        }if(startIndex!=0){
            crit.setFirstResult(startIndex);

        }         return crit;
   }



    
    @SuppressWarnings("unchecked")
	public List<T> consultaHQL(String consulta) {
        return (List<T>) session.createQuery(consulta).list();
    }

    public Criteria consultaByCriteria() {
        return session.createCriteria(entity);
    }
	
	
	public void clear(){
		session.flush();
		session.clear();		
	}
	
	
	
	
}
