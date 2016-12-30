package com.varghese.oilandgas.repositories;

import com.varghese.oilandgas.dto.DailyExtractionReport;
import com.varghese.oilandgas.dto.ReserveSumReport;
import com.varghese.oilandgas.model.Extraction;
import com.varghese.oilandgas.model.Reserve;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by newuser on 12/12/16.
 */
public class ReserveRepositoryHibernateImpl implements ReserveRepository {

    private SessionFactory sessionFactory;

    @Override
    public Reserve getReserve(int reserveId) {
        return null;
    }

    @Override
    public List<Reserve> getAllReserves() {

        Session session = sessionFactory.openSession();

        List<Reserve> reserves = session.createCriteria(Reserve.class).list();

        return reserves;
    }

    @Override
    public ReserveSumReport getTotalReserveCounts() {

        Session session = sessionFactory.openSession();

        ReserveSumReport report = new ReserveSumReport();

        List<Object> result = session.createCriteria(Reserve.class).setProjection(Projections.projectionList().add(Projections.sum("oil"))
        .add(Projections.sum("currentOil")).add(Projections.sum("naturalGas")).add(Projections.sum("currentNaturalGas"))).list();

        if(result != null && !result.isEmpty()) {

            Object[] row = (Object[]) result.get(0);

                report.setTotalOil((int)((long)(row[0])));
                report.setTotalExistingOil(report.getTotalOil() - (int)((long)row[1]));
                report.setTotalNaturalGas((int)((long)row[2]));
                report.setTotalExistingGas(report.getTotalNaturalGas() - (int)((long)row[3]));
        }

        return report;
    }

    @Override
    public DailyExtractionReport getAggregateExtractionsByDate() {

        DailyExtractionReport report = new DailyExtractionReport();

        Map<Date,Integer> oilExtractionsMap = new TreeMap<>();
        Map<String,Integer> gasExtractionsMap = new TreeMap<>();


        SimpleDateFormat utc_formatter = new SimpleDateFormat();
        utc_formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        Session session = sessionFactory.openSession();

        //List<Object> results = session.createCriteria(Extraction.class).setProjection(Projections.projectionList().add(Projections.sum("oilAmount")).add(Projections.sum("naturalGasAmount")).add(Projections.sqlGroupProjection("date(extractionDate) as extractionDate", "extractionDate", new String[] { "extractionDate" }, new Type[] { StandardBasicTypes.DATE }))).list();

        List<Object> results = session.createCriteria(Extraction.class).setProjection(Projections.projectionList().add(Projections.sqlProjection("sum(oilAmount) + sum(naturalGasAmount) as sumAB", new String[] {"sumAB"} , new Type[] {StandardBasicTypes.INTEGER})).add(Projections.sqlGroupProjection("date(extractionDate) as extractionDate", "extractionDate", new String[] { "extractionDate" }, new Type[] { StandardBasicTypes.DATE }))).list();


        if(results != null ) {

            for(Object result: results) {
                Object[] row = (Object[]) result;

              long epochTime =  ((Date)row[1]).getTime();


                oilExtractionsMap.put((Date)row[1], (int)row[0]);
            }


        }

      // report.setGasExtractionReportMap(gasExtractionsMap);
        report.setOilExtractionReportMap(oilExtractionsMap);


        return report;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
