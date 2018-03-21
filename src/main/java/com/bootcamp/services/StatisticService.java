package com.bootcamp.services;

import com.bootcamp.client.CommentaireClient;
import com.bootcamp.client.DebatClient;
import com.bootcamp.client.LikeClient;
import com.bootcamp.entities.Commentaire;
import com.bootcamp.entities.Debat;
import com.bootcamp.entities.LikeTable;
import com.bootcamp.helpers.*;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author bignon
 * The service class of the controller
 */
@Component
public class StatisticService {
    /**
     * the link for to client debat
     */


    public List<OutPut> getAllByEntityType(String entityType, String bigD, String endD, int pas, String unite) throws Exception {

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");

        Date startDateAsDate = simpleDate.parse(bigD);
        Date endDateAsDate = simpleDate.parse(endD);
        System.out.println("date in date/// start: "+startDateAsDate.getTime()+"end: "+endDateAsDate.getTime());

        GregorianCalendar startDate = new GregorianCalendar();
        GregorianCalendar endDate = new GregorianCalendar();

        startDate.setTime(startDateAsDate);
        endDate.setTime(endDateAsDate);
        System.out.println("date in gregor/// start: "+startDate.getTime()+"end: "+endDate.getTime());


        DebatClient debatClient = new DebatClient();
        CommentaireClient commentaireClient = new CommentaireClient();
        LikeClient likeClient = new LikeClient();


        List<OutPut> returnList = new ArrayList<>();

        Gson gson = new Gson();


            GregorianCalendar newEndDate = new GregorianCalendar(startDate.get(Calendar.YEAR),
                    startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));

            GregorianCalendar newStartDate = new GregorianCalendar(startDate.get(Calendar.YEAR),
                startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));

            newEndDate = addPas(newEndDate,unite,pas);
        System.out.println("new end date after addPas: "+newEndDate.getTime());


            do {
                OutPut outPut = new OutPut();
                Periode periode = new Periode();
                Data data = new Data();

                Date dd = new Date();
                Date ed = new Date();

                dd = newStartDate.getTime();
                ed = newEndDate.getTime();

                periode.setDebut(simpleDate.format(dd));
                periode.setFin(simpleDate.format(ed));

                List<Debat> debats = debatClient.getByEntityType(entityType, simpleDate.format(dd), simpleDate.format(ed));
                List<Commentaire> commentaires = commentaireClient.getAllCommentByAllEntity(entityType, simpleDate.format(dd), simpleDate.format(ed));
                List<LikeTable> allLikes = likeClient.getAllLikeOrUnlikeByEntity(entityType, simpleDate.format(dd), simpleDate.format(ed));

                int nblikes = 0;
                int nbunlikes = 0;

                for (int i = 0; i < allLikes.size(); i++) {
                    if (allLikes.get(i).isLikeType())
                        nblikes++;
                    else
                        nbunlikes++;
                }

                data.setNbTotalComment(commentaires.size());
                data.setNbTotalDebat(debats.size());
                data.setNbTotalLike(nblikes);
                data.setNbTotalUnlike(nbunlikes);

                outPut.setData(data);
                outPut.setDate(simpleDate.format(dd));
                outPut.setPeriode(periode);
                outPut.setTime(newStartDate.getTimeInMillis());


                returnList.add(outPut);

                newStartDate.set(Calendar.DAY_OF_MONTH,newEndDate.get(Calendar.DAY_OF_MONTH));
                newStartDate.set(Calendar.MONTH,newEndDate.get(Calendar.MONTH));
                newStartDate.set(Calendar.YEAR,newEndDate.get(Calendar.YEAR));

                newEndDate = addPas(newEndDate,unite,pas);

                } while (newEndDate.compareTo(endDate) <= 0 || (endDate.compareTo(newStartDate) >= 0 && endDate.compareTo(newEndDate) <= 0));

                 System.out.println(gson.toJson(returnList));

                return returnList;
            }



    public GregorianCalendar addPas(GregorianCalendar newEndDate,String unite, int pas){

            if (unite.equalsIgnoreCase("JOUR")){
                newEndDate.add(Calendar.DAY_OF_MONTH,pas-1);
            }else
                if (unite.equalsIgnoreCase("SEMAINE")){
                    newEndDate.add(Calendar.DAY_OF_MONTH,(7*pas)-1);
                }else
                    if (unite.equalsIgnoreCase("MOIS")){
                        newEndDate.add(Calendar.MONTH,pas);
                        newEndDate.add(Calendar.DAY_OF_MONTH,-1);
                    }else
                        if (unite.equalsIgnoreCase("ANNEE")){
                            newEndDate.add(Calendar.YEAR,pas);
                            newEndDate.add(Calendar.DAY_OF_MONTH,-1);
                        }

                        return newEndDate;
    }


}
