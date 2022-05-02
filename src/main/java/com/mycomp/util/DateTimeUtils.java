    package com.mycomp.util;

    import com.mycomp.model.SessionInfo;
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;

    import java.time.LocalDate;
    import java.time.ZonedDateTime;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.time.format.FormatStyle;
    import java.util.Objects;
    import java.util.function.Predicate;

    public class DateTimeUtils {
        private static final Logger log = LogManager.getLogger(DateTimeUtils.class);
        private DateTimeUtils(){}
        public static boolean isEquals(String date, String datetime){
            if(null == date){
                log.debug("date could not be matched with date time as date is null");
                return false;
            }
            if(datetime == null){
                log.debug("date could not be matched with date time as date time is null");
                return false;
            }
            try{
                LocalDate sourceDate = LocalDate.parse(date,DateTimeFormatter.ISO_LOCAL_DATE);
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(datetime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                LocalDate targetDate = zonedDateTime.toLocalDate();
                if(Objects.equals(sourceDate,targetDate)){
                    log.debug("Search date ={} and date of time stamp ={} are matched",sourceDate,zonedDateTime);
                    return true;
                }
                log.debug("Search date ={} and Time stamp ={} did not matched",sourceDate,zonedDateTime);
                return false;
            }catch (DateTimeParseException dateTimeParseException){
                log.error("Either search date or date time have date format exception");
                return  false;
            }
        }

        public static boolean validDate(String date){
              try{
                  LocalDate.parse(date,DateTimeFormatter.ISO_LOCAL_DATE);
              }catch (DateTimeParseException dateTimeParseException){
                  return false;
              }
           return true;
        }

        public static boolean validDateTime(String sessionDT) {
            try{
                ZonedDateTime.parse(sessionDT,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            }catch (DateTimeParseException dateTimeParseException){
                return false;
            }
            return true;
        }
    }
