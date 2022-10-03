import entity.PersonEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory fac;
        fac = Persistence.createEntityManagerFactory("default");
        EntityManager man = fac.createEntityManager();
        EntityTransaction tr = man.getTransaction();

        try{
            int val = 0;
            while (val == 0){
              tr.begin();

                PersonEntity major = new PersonEntity();
                System.out.println("Wpisz imie");
                String name = enterValue();
                major.setpName(name);
                System.out.println("Wpisz nazwisko");
                String surname = enterValue();
                major.setpSurname(surname);
                System.out.println("Wpisz email");
                String email = enterValue();
                major.setpEmail(email);

                man.persist(major);

                tr.commit();
                System.out.println("Jesli chcesz dodac kolejna osobe wpisz 0, jesli chcesz wyjsc wpisz dowolnego inta");
                val = enterInt();
            }
        }finally {
            if(tr.isActive())
                tr.rollback();
            man.close();
            fac.close();
        }

    }

    public static String enterValue(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static int enterInt() {
        Scanner scanner = new Scanner(System.in);
        int res;
        String x;
        try {
            x = scanner.nextLine();
            res = Integer.parseInt(x);
        } catch (Exception exception) {
            System.err.println("Nie wprowadzono Int");
            res = enterInt();
        }
        return res;
    }


}
