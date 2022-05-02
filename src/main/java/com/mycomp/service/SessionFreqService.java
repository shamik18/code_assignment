package com.mycomp.service;

import com.mycomp.cmd.CommandLine;
import com.mycomp.cmd.SessionArgs;
import com.mycomp.context.DataContext;
import com.mycomp.context.DataSet;
import com.mycomp.context.IDataContext;
import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.model.SessionInfo;
import com.mycomp.util.DateTimeUtils;
import com.mycomp.util.ReflectionUtils;
import com.mycomp.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageReadParam;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class is the service class that used to read the csv file and give the most frequent
 * session in the file for a give date.
 */
public class SessionFreqService {
    private static final Logger log = LogManager.getLogger(SessionFreqService.class);

    /**
     * This method provide the most common session in the file on the give date.
     * @param sessionArgs hold session arguments.
     * @return list of most common session.
     * @throws DataSetOperationFailureException stream exception.
     */
    public List<String> getHighterFreqSession(SessionArgs sessionArgs) throws DataSetOperationFailureException {
        log.info("Parameter received by application {}",sessionArgs);
        String fileName = sessionArgs.fileName();
        String filterDt = sessionArgs.searchDate();
        String[] mapper = ReflectionUtils.getFields(SessionInfo.class);
        log.debug("mapper ={}",Arrays.toString(mapper));
        //Read the data from CSV
        IDataContext<SessionInfo> dataContext = new DataContext<>();
        log.info("Reading the file : {}",sessionArgs.fileName());
        try(DataSet<SessionInfo> dataSet = dataContext.readFromCSV(fileName,SessionInfo.class,mapper)) {
            //find the count as stream process.
            log.info("Processing the file for frequency, filter ={}",sessionArgs.searchDate());
            return dataSet.filter(sf -> StringUtils.notBlankOrEmpty(sf.sessionID()))
                    .filter(sf -> StringUtils.notBlankOrEmpty(sf.sessionDT()))
                    .filter(sf -> DateTimeUtils.validDateTime(sf.sessionDT()))
                    .filter(sf -> DateTimeUtils.isEquals(filterDt, sf.sessionDT()))
                    .maxFrequencyKeys(mapper[0]);
        } catch (IOException e) {
            throw new DataSetOperationFailureException(e);
        }
    }
}
