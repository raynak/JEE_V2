Site de gestion de panier et d'items en Java J2EE
Tanguy Marechal et Yassine Badache
Jeudi 24 Avril 2015


**** 	Introduction 	****

	Cette application permet d'acceder via une passerelle web, a un systeme
	de gestion de livres, avec ajout, systeme de paniers et de session utilisateur,
	de listing des livres disponibles. Les donnees sont stockees dans une base
	de donnees adjacente et le tout est gere via la technologie Java J2EE.
	
	
**** 	Execution 		****

	Pour l'execution, il faut disposer d'un serveur GlassFish, qui est fourni
	avec l'archive. Une fois que le serveur est lance, il suffit de lancer
	l'application via NetBeans, qui va ouvrir une page navigateur et vous
	amener directement a l'index.

	
**** 	Architecture 	****

	Package:
		bookshelf.exception
		
	Contient les exceptions personnalisees pouvant etre levees lors de l'execution.
	
	
	Package:
		dataAccess
		
	Contient toutes les classes permettant l'accessibilite aux donnees
	stockees dans la base depuis l'interface web developee.
	
	
	Package:
		ejb
		
	Contient les classes definissant les objets presents dans notre
	systeme, a savoir un utilisateur, un livre et un panier, tout en y
	incluant le design pattern correspondant a la technologie J2EE.
	
	
	Package:
		jpa
		
	Contient les classes de base d'un livre, un utilisateur et un panier,
	et decrit le lien etant fait entre cette classe et la base de donnees.
		

		
**** 	Design		 	****

	Il n'y a une classe abstraite par objet (User, Book, ShoppingCart) ainsi qu'une pour le pattern Facade
	Une interface est presente pour l'acces aux donnees (ItfDataAccess) et acces local (ItfDataAccessLocal)
	Nous avons utilise l'heritage pour definir nos objets en fonction de la norme EJB.
	Nous avons utilise le design pattern Facade pour chacun des objets

	
	
**** 	Classes test 	****

	
**** 	Erreurs		 	****

		Les erreurs sont gerees uniquement lors de la creation et l'utilisation de requetes SQL
		pour acceder aux donnees. Elles sont contenues dans la  java.dataAccess.dataAccessJPA
		elle presentent sous la forme suivante:
		
		try {
            return em.createQuery("select b from BOOKS b").getResultList();
        }
        catch (Throwable th){
            throw new FirstException(th);
        }
	
		FirstException est notre exception personnelle, qui ne fait qu'un super();
		
		
**** 	Code Samples 	****

Schema classique d'un objet en Java J2EE
Classe java.ejb.BooksFacade
-----
// Annotation Stateless pour le serveur
@Stateless
public class BooksFacade extends AbstractFacade<Books> implements BooksFacadeLocal, BooksFacadeRemote {
    @PersistenceContext(unitName = "BookShelfV2PU")
    private EntityManager em;

	// On retourne l'EntityManager pour traitement
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

	// On renvoie a la classe Book
    public BooksFacade() {
        super(Books.class);
    }
    
}
	
	
-----


Envoi d'une requete SQL via la classe dediee
Classe java.dataAccess.DataAccesJPA
----- 
	@Override
	// Le nom de la methode eclaire sur les intentions de celle-ci
    public List<Books> getBooksByAuthor(String author) {
        try {
			// L'entityManager va se charger de creer la requete et retourner sa reponse
            return em.createQuery("SELECT b FROM BOOKS b WHERE author=" + author).getResultList();
        } catch (Throwable th) {
            throw new FirstException(th);
        }   
    }
-----


Classe: java.ejb.AbstractFacade
-----
public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
	
	
Classe: java.ejb.AbstractFacade
-----
public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
	
	
	
Classe: java.ejb.AbstractFacade
-----
public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }