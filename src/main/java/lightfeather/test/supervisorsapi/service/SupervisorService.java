package lightfeather.test.supervisorsapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lightfeather.test.supervisorsapi.config.SupervisorConfig;
import lightfeather.test.supervisorsapi.controller.SupervisorController;
import lightfeather.test.supervisorsapi.model.Person;
import lightfeather.test.supervisorsapi.model.Supervisor;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorService
{
    @Autowired
    SupervisorConfig supervisorConfig;

    private static Logger logger =  LogManager.getLogger(SupervisorController.class);


    public List<String> getAndSortSupervisors()
    {
        // Let create the client for the http connection
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // Let create the GET method for the supervisors
        HttpGet httpGet = new HttpGet(supervisorConfig.getGetSupervisorsURL());

        // This response handler will manage the execution of the get
        ResponseHandler<String> responseHandler = httpResponse -> {
            int resultStatus = httpResponse.getStatusLine().getStatusCode();
            if (resultStatus >= 200 && resultStatus < 300) {
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("An unexpected ERROR with status: " + resultStatus);
            }
        };

        ObjectMapper mapper = new ObjectMapper();

        String stringResponse;
        List<Supervisor> supervisors;
        List<Supervisor> validJurisdictions;
        List<Supervisor> sortedSupervisors;
        List<String> formattedSupervisors;
        try {
            stringResponse = httpClient.execute(httpGet,responseHandler);
            supervisors = mapper.readValue(stringResponse, new TypeReference<List<Supervisor>>() {});
            // Exclude Numeric Jurisdictions
            validJurisdictions = supervisors.stream().filter(c -> c.getJurisdiction() == null ? false : c.getJurisdiction().matches("[a-zA-Z]+")).collect(Collectors.toList());
            // Sort result in this order: jurisdiction->lastName->firtsName
            sortedSupervisors = sortSupervisors(validJurisdictions);
            // Format the result: Jurisdiction - LastName, FirstName
            formattedSupervisors = sortedSupervisors.stream().map(e -> e.getJurisdiction() + " - " + e.getLastName() + ", " + e.getFirstName()).collect(Collectors.toList());
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            return  null;
        }
        return formattedSupervisors;
    }

    public String postSupervisor(Person person)
    {
        logger.info("Last Name: " + person.getLastName());
        logger.info("First Name: " + person.getFirstName());
        logger.info("Supervisor: " + person.getSupervisor());
        logger.info("Email: " + person.getEmail());
        logger.info("Phone Number: " + person.getPhoneNumber());

        if ( person.getFirstName().isEmpty() || person.getFirstName() == null )
            return supervisorConfig.getMissingFirstName();
        if ( person.getLastName().isEmpty() || person.getLastName() == null )
            return supervisorConfig.getMissingLastName();
        if ( person.getSupervisor().isEmpty() || person.getSupervisor() == null )
            return supervisorConfig.getMissingSupervisor();

        return supervisorConfig.getSuccessfully();

    }


    public List<Supervisor> sortSupervisors(List<Supervisor> supervisors)
    {
        //comparator: jurisdiction->lastName->firtsName
        Comparator<Supervisor> sortByJurisdictionLastNameFirstName = Comparator.comparing(Supervisor::getJurisdiction).thenComparing(Supervisor::getLastName).thenComparing(Supervisor::getFirstName);
        List<Supervisor> sortedSupervisors  = supervisors.stream().sorted(sortByJurisdictionLastNameFirstName).collect(Collectors.toList());

        return sortedSupervisors;
    }

}
