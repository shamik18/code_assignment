package com.mycomp.context;

import com.mycomp.exception.DataSetOperationFailureException;
import com.mycomp.model.SessionInfo;
import com.mycomp.testhelper.BaseTest;
import com.mycomp.util.DateTimeUtils;
import com.mycomp.util.ReflectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class DataSetTest extends BaseTest {
    DataSet<SessionInfo> dataSet ;
    @Before
    public void inti(){
        String fileName = getProperties(FILE_SUCCESS_SINGLE);
        String[] mapping = ReflectionUtils.getFields(SessionInfo.class);
        DataContext<SessionInfo> dataContext = new DataContext<>();
        try {
            dataSet =  dataContext.readFromCSV(fileName,SessionInfo.class,mapping);
        } catch (DataSetOperationFailureException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void filter() {
        String date = "2018-12-07";
        IDataSet<SessionInfo> sessionInfoDataSet = dataSet.filter(sessionInfo -> DateTimeUtils.isEquals(date,sessionInfo.sessionDT()));
        Assert.assertNotNull(sessionInfoDataSet);
        List<String> sessionIDs = sessionInfoDataSet.maxFrequencyKeys("sessionID");
        assertEquals(1, sessionIDs.size());
    }

    @Test
    public void maxFrequencyKeys() {
        String[] testVal = {"AtY0laUfhglK3lC7","SAZuXPGUrfbcn5UA",
                "5UAVanZf6UtGyKVS","AtY0laUfhglK3lC7","SAZuXPGUrfbcn5UA","4sMM2LxV07bPJzwf",
                "fbcn5UAVanZf6UtG","4sMM2LxV07bPJzwf"};
        Map<Object,Long> map = createMapFromArray(testVal);
        Set<String> set = new HashSet<>();
        for(Map.Entry<Object,Long> entry:map.entrySet()){
            if(entry.getValue()==2){
                set.add((String)entry.getKey());
            }
        }
        List<String> fields = dataSet.maxFrequencyKeys("sessionID");
        assertEquals(fields.size(),set.size());
        for(String f : fields){
            assertTrue(set.contains(f));
            set.remove(f);
        }
        assertEquals(0,set.size());
    }

    @Test
    public void getHighFrequencyFields() {
        String[] sessions1 = {"AAA","BBB","CCC","AAA"};
        Map<Object,Long> map = createMapFromArray(sessions1);
        List<String> fields = dataSet.getHighFrequencyFields(map);
        assertEquals(1, fields.size());
        assertEquals("AAA", fields.get(0));
        String[]  sessions2 = {"AAA","BBB","CCC","AAA","CCC"};
        map = createMapFromArray(sessions2);
        fields = dataSet.getHighFrequencyFields(map);
        assertEquals(2, fields.size());
        Set<String> set = new HashSet<>(Arrays.asList("AAA","CCC"));
        for(String f : fields){
            assertTrue(set.contains(f));
            set.remove(f);
        }
        assertEquals(0, set.size());

        String[]  sessions3 = {"AAA","BBB","CCC","AAA","CCC","CCC"};
        map = createMapFromArray(sessions3);
        fields = dataSet.getHighFrequencyFields(map);
        assertEquals(1, fields.size());
        assertEquals("CCC", fields.get(0));
    }

    private Map<Object, Long> createMapFromArray(String[] sessions) {
        Map<Object,Long> map = new HashMap<>();
        for(String key: sessions){
            if(map.containsKey(key)){
                map.put(key, map.get(key)+1);
            }else{
                long count = 1L;
                map.put(key,count);
            }
        }
        return map;
    }

    @Test
    public void close() {
        try {
            dataSet.close();
        } catch (IOException e) {
            System.out.println("Try to close the stream already closed.");
        }
    }
}