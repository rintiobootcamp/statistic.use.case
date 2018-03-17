import com.bootcamp.enums.UniteDeTemp;
import com.bootcamp.services.StatisticService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AppMain {
        public static void main(String[] args) {

            StatisticService service = new StatisticService();

        String dd = "01-02-2018", ed = "26-02-2018";
        try {
          service.getAllByEntityType("AXE",dd,ed,2,"JOUR")  ;
        }catch (Exception e){
            System.out.println("Je ne suis pas pret::: \n Erreur: "+e.getMessage());
        }

       }



    }
