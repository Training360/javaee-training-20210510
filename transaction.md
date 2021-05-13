# Tranzakciókezelés

---

## Tranzakciókezelés

* ACID tulajdonságok
  * Atomicity
  * Consistency
  * Isolation
  * Durability

---

## Két fajta megközelítés

* Programozott tranzakciókezelés
* Deklaratív tranzakciókezelés

---

## Programozott tranzakciókezelés

```java
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class EmployeesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction ut;

    public void createEmployee(Employee employee) {
      try {
        ut.begin();
        entityManager.persist(employee);
        ut.commit();
      }
      catch (Exception e) {
        ut.rollback();
      }
    }

}
```

---

## Deklaratív tranzakciókezelés

```java
@Stateless
public class EmployeesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createEmployee(Employee employee) {
        entityManager.persist(employee);
    }

}
```

---

## Alap működés

* Az első session bean indít tranzakciót, a többi ehhez csatlakozik
* A `@Transactional` akkor kell, ha módosítani akarunk a default működésen
    * CDI beanekre és EJB-kre is
    * EJB 3 szabványban szereplő `@TransactionAttribute` helyett
    * Osztály és metódus szinten is (metódus szintű felülír)
    * CDI interceptorokkal működik
    * Proxy végzi

---

## Propagáció

* `@Transactional(Transactional.TxType.REQUIRED)`

![Propagáció](images/propagacio.png)

---

## Propagációs tulajdonságok

* `REQUIRED` (default): ha nincs tranzakció, indít egyet, ha van csatlakozik hozzá
* `REQUIRES_NEW`: mindenképp új tranzakciót indít
* `SUPPORTS`: ha van tranzakció, abban fut, ha nincs, nem indít újat
* `MANDATORY`: ha van tranzakció, abban fut, ha nincs, kivételt dob
* `NOT_SUPPORTED`: ha van tranzakció, a tranzakciót felfüggeszti, ha nincs, nem indít újat
* `NEVER`: ha van tranzakció, kivételt dob, ha nincs, <br /> nem indít újat

---

## Lokális metódushívás

* Nem állítható a propagációs szint, mert nem megy át a proxy-n
---

## Visszagörgetési szabályok

* Konténer dönt a commitról vagy rollbackről
* Kivételek esetén:
  * Checked kivétel: commit  
  * Unchecked kivétel: rollback
  * Felülbírálható: `@ApplicationException(rollback = true)`
  * Felülbírálható:

```java
@Transactional(rollbackOn = SQLException.class,
    dontRollbackOn = {ArrayIndexOutOfBoundsException.class,
        IllegalArgumentException.class})
```

---

## Rollbackre explicit módon megjelölni

* `SessionContext` injektálás
* `setRollbackOnly()` és `getRollbackOnly()` metódusok

---

## Visszagörgetésre megjelölés

```java
@Stateless
public class EmployeesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private SessionContext ctx;

    @Transactional
    public void createEmployee(Employee employee) {
        entityManager.persist(employee);

        if (employee.getName().equals("")) {
            ctx.setRollbackOnly();
        }
    }

}
```

---

## Transaction scoped

* Tranzakció hosszáig létező CDI bean
* `@TransactionScoped` annotáció

---

## Izoláció

* Java EE nem támogatja a tranzakciónkénti izolácós szint állítását
* Izolációs problémák:
    * dirty read
    * non-repetable read
    * phantom read
* Izolációs szintek:
    * read uncommitted
    * read commited
    * repeatable read
    * serializable
