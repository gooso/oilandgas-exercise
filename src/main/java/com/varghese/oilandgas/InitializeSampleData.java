package com.varghese.oilandgas;

import com.varghese.oilandgas.model.Extraction;
import com.varghese.oilandgas.model.Reserve;
import com.varghese.oilandgas.repositories.ReserveRepository;
import com.varghese.oilandgas.repositories.ReserveRepositoryHibernateImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by newuser on 12/12/16.
 */
public class InitializeSampleData {


    // Set hbm2ddl.auto to create before initializing new tables
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Reserve reserve1 = new Reserve();
        reserve1.setSite("Route 66");
        reserve1.setWell("Alpha");
        reserve1.setOil(15000);
        reserve1.setNaturalGas(0);
        reserve1.setCurrentNaturalGas(0);
        reserve1.setCurrentOil(13285);


        Reserve reserve2 = new Reserve();
        reserve2.setSite("Route 66");
        reserve2.setWell("Bravo");
        reserve2.setOil(25000);
        reserve2.setNaturalGas(5000);

        Reserve reserve3 = new Reserve();
        reserve3.setSite("Route 66");
        reserve3.setWell("Charlie");
        reserve3.setOil(8000);
        reserve3.setNaturalGas(15000);

        Reserve reserve4 = new Reserve();
        reserve4.setSite("Hanamura");
        reserve4.setWell("Alpha");
        reserve4.setOil(5000);
        reserve4.setNaturalGas(5000);

        Reserve reserve5 = new Reserve();
        reserve5.setSite("Hanamura");
        reserve5.setWell("Bravo");
        reserve5.setOil(30000);
        reserve5.setNaturalGas(20000);

        Reserve reserve6 = new Reserve();
        reserve6.setSite("Numbani");
        reserve6.setWell("Alpha");
        reserve6.setOil(10000);
        reserve6.setNaturalGas(2500);

        Reserve reserve7 = new Reserve();
        reserve7.setSite("Numbani");
        reserve7.setWell("Bravo");
        reserve7.setOil(15000);
        reserve7.setNaturalGas(10000);

        Reserve reserve8 = new Reserve();
        reserve8.setSite("Numbani");
        reserve8.setWell("Charlie");
        reserve8.setOil(7500);
        reserve8.setNaturalGas(12500);

        Reserve reserve9 = new Reserve();
        reserve9.setSite("Numbani");
        reserve9.setWell("Delta");
        reserve9.setOil(16500);
        reserve9.setNaturalGas(13500);
        reserve9.setCurrentOil(15500);
        reserve9.setCurrentNaturalGas(12045);

        session.save(reserve1);
        session.save(reserve2);
        session.save(reserve3);
        session.save(reserve4);
        session.save(reserve5);
        session.save(reserve6);
        session.save(reserve7);
        session.save(reserve8);
        session.save(reserve9);


        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);


        cal.set(2016,6,1);
        Extraction extraction1 = generateExtraction(cal.getTime(),0,250,reserve1);

        cal.set(2016,6,4);
        Extraction extraction2 = generateExtraction(cal.getTime(),0,300,reserve1);

        cal.set(2016,6,5);
        Extraction extraction3 = generateExtraction(cal.getTime(),0,275,reserve1);

        cal.set(2016,6,6);
        Extraction extraction4 = generateExtraction(cal.getTime(),0,250,reserve1);

        cal.set(2016,6,7);
        Extraction extraction5 = generateExtraction(cal.getTime(),0,325,reserve1);

        cal.set(2016,6,8);
        Extraction extraction6 = generateExtraction(cal.getTime(),0,315,reserve1);

        cal.set(2016,6,9);
        Extraction extraction7 = generateExtraction(cal.getTime(),350,175,reserve9);

        cal.set(2016,6,10);
        Extraction extraction8 = generateExtraction(cal.getTime(),325,190,reserve9);

        cal.set(2016,6,11);
        Extraction extraction9 = generateExtraction(cal.getTime(),400,210,reserve9);

        cal.set(2016,6,12);
        Extraction extraction10 = generateExtraction(cal.getTime(),200,225,reserve9);

        cal.set(2016,6,14);
        Extraction extraction11 = generateExtraction(cal.getTime(),180,200,reserve9);


        session.save(extraction1);
        session.save(extraction2);
        session.save(extraction3);
        session.save(extraction4);
        session.save(extraction5);
        session.save(extraction6);
        session.save(extraction7);
        session.save(extraction8);
        session.save(extraction9);
        session.save(extraction10);
        session.save(extraction11);


        session.getTransaction().commit();
        session.close();

//
        ReserveRepository repos = (ReserveRepository) context.getBean("reserveRepositoryHibernateImpl");
//
//        repos.getTotalReserveCounts();

        repos.getAggregateExtractionsByDate();

    }

    static Extraction generateExtraction(Date date, int oil, int gas, Reserve reserve) {
        Extraction extraction= new Extraction();
        extraction.setNaturalGasAmount(gas);
        extraction.setOilAmount(oil);
        extraction.setExtractionDate(date);
        extraction.setReserve(reserve);
        return extraction;
    }
}
