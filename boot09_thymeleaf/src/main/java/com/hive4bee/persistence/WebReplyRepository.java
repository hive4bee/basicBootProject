package com.hive4bee.persistence;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hive4bee.domain.WebReply;
import com.hive4bee.vo.StartVO;

import lombok.extern.java.Log;

@Repository
@Log
public class WebReplyRepository {
	
	@Autowired
	protected EntityManagerFactory emf;
	
	protected EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	
	public List<WebReply> selectReplyList(StartVO startVO, Long bno){
		
		List<WebReply> list = null;
		EntityManager em = getEntityManager();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT r FROM WebReply r WHERE 1=1 ");
			sb.append("AND board_bno = :bno ");
			sb.append("ORDER BY r.rno DESC");
			Query query = em.createQuery(sb.toString());
			query.setParameter("bno", bno);
			query.setMaxResults(10);
			query.setFirstResult((startVO.getPage()-1)*10);
			list = query.getResultList();
		}catch(Exception e) {
			System.out.println("error: "+e);
		}finally {
			em.close();
		}
		
		return list;
	}
	
	public List<Object[]> selectReplyListWithRCount(StartVO startVO){
		List<Object[]> list = null;
		EntityManager em = getEntityManager();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT b.bno, b.title, COUNT(r.rno), b.writer, b.regdate ");
			sb.append("FROM WebBoard b LEFT OUTER JOIN WebReply r ");
			sb.append("ON b.bno=r.board ");
			sb.append("WHERE b.bno>0 ");
			sb.append("GROUP BY b.bno ");
			sb.append("ORDER BY b.bno DESC");
			
			Query query = em.createQuery(sb.toString());
			query.setMaxResults(10);
			query.setFirstResult((startVO.getPage()-1)*10);
			list = query.getResultList();
		}catch(Exception e) {
			System.out.println("error: " + e);
		}finally {
			em.close();
		}
		
		return list;
	}
	
	public int addReply(Long bno, WebReply reply) {
		log.info("=====================");
		log.info("reply: " + reply + " / bno: " + bno);
		log.info("=====================");
		int result = 0;
		EntityManager em = getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			
			try {
				transaction.begin();
				//WebReply reply2 = new WebReply();
				
				em.persist(reply);
				//em.merge(reply);
				result=1;
				transaction.commit();
			}catch(RollbackException e) {
				System.out.println("error: " + e);
			}catch(IllegalStateException e2){
				System.out.println("error: " + e2);
			}catch(EntityExistsException e3) {
				System.out.println("error: " + e3);
			}catch(IllegalArgumentException e4) {
				System.out.println("error: " + e4);
			}catch(TransactionRequiredException e5) {
				System.out.println("error: " + e5);
			}finally {
				if(transaction.isActive()){
					transaction.rollback();
				}
			}
		}finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		return result;
	}

	public int getCnt(Long bno) {
		int cnt = 0;
		EntityManager em = getEntityManager();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) FROM WebReply WHERE board_bno=:bno");
			Query query = em.createQuery(sb.toString());
			query.setParameter("bno", bno);
			cnt = Integer.parseInt(query.getSingleResult().toString());
		}catch(Exception e){
			System.out.println("error: "+e);
		}finally {
			em.close();
		}
		return cnt;
	}

	public int deleteReply(Long rno) {
		int result = 0;
		EntityManager em = getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			try {
				transaction.begin();
				//Class<WebReply> clz = (Class<WebReply>) GenericTypeResolver.resolveTypeArgument(getClass(), WebReplyRepository.class);
				log.info("=========================");
				//log.info("clz: " + clz);
				
				WebReply findedObj = em.find(WebReply.class, rno);
				log.info("findedObj: " + findedObj);
				log.info("=========================");
				if(findedObj == null) {
					
				}
				em.remove(findedObj);
				transaction.commit();
				result = 1;
			}catch(Exception e) {
				System.out.println("error: " + e);
			}finally {
				if(transaction.isActive()) {
					transaction.rollback();
				}
			}
		}finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		return result;
	}
	
	public int modifyReply(WebReply webReply) {
		int result = 0;
		
		EntityManager em = getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			try {
				transaction.begin();
				WebReply findedObj = em.find(WebReply.class, webReply.getRno());
				if(findedObj == null) {
					
				}
				log.info("=========================");
				log.info("findedObj: " + findedObj);
				log.info("=========================");
				findedObj.setReplyText(webReply.getReplyText());
				em.merge(findedObj);
				transaction.commit();
				result=1;
			}catch(Exception e) {
				System.out.println("error: " + e);
			}finally {
				if(transaction.isActive()) {
					transaction.rollback();
				}
			}
		}finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		
		return result;
	}

}
