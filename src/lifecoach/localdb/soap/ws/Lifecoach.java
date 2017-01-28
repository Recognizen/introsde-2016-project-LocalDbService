package lifecoach.localdb.soap.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import lifecoach.localdb.soap.model.Achievement;
import lifecoach.localdb.soap.model.Goal;
import lifecoach.localdb.soap.model.HealthMeasureHistory;
import lifecoach.localdb.soap.model.Measure;
import lifecoach.localdb.soap.model.Person;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL, parameterStyle= ParameterStyle.WRAPPED) //optional
public interface Lifecoach {
	 
	//-- Start of Goal Table operations
    @WebMethod(operationName="readGoalList")
    @WebResult(name="goal") 
    public List<Goal> readGoalList();
    
    //Specific goal
    @WebMethod(operationName="readGoal")
    @WebResult(name="goal") 
    public Goal readGoal(@WebParam(name="goalId") long id); 
    
    //Task 3
    @WebMethod(operationName="updateGoal")
    @WebResult(name="goal") 
    public Goal updateGoal(@WebParam(name="goal") Goal g);

    //Tast 4
    @WebMethod(operationName="createGoal")
    @WebResult(name="goal") 
    public Goal createGoal(@WebParam(name="Goal") Goal g);

    //Task 5
    @WebMethod(operationName="deleteGoal")
    @WebResult(name="goalId") 
    public long deleteGoal(@WebParam(name="goalId") long id);
    
    // --- End of Goal table operations
    
    //-- Start of AchievedGoal Table operations
	
    @WebMethod(operationName="readAchievementList")
    @WebResult(name="achievement") 
    public List<Achievement> readAchievementList();
    
    //Specific goal
    @WebMethod(operationName="readAchievement")
    @WebResult(name="achievement") 
    public Achievement readAchievement(@WebParam(name="achievementId") long id); 
    
    //Task 3
    @WebMethod(operationName="updateAchievement")
    @WebResult(name="achievement") 
    public Achievement updateAchievement(@WebParam(name="achievement") Achievement a);

    //Tast 4
    @WebMethod(operationName="createAchievement")
    @WebResult(name="achievement") 
    public Achievement createAchievement(@WebParam(name="Achievement") Achievement a);

    //Task 5
    @WebMethod(operationName="deleteAchievement")
    @WebResult(name="achievementId") 
    public long deleteAchievement(@WebParam(name="achievementId") long id);
    
    // --- End of AchievedGoals table operations
    
    //--- Start of Person table operations
    
	//Task 1
    @WebMethod(operationName="readPersonList")
    @WebResult(name="person") 
    public List<Person> readPersonList();
	
    //Task 2
    @WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") long id); 
    
    //Task 3
    @WebMethod(operationName="updatePerson")
    @WebResult(name="person") 
    public Person updatePerson(@WebParam(name="person") Person p);

    //Tast 4
    @WebMethod(operationName="createPerson")
    @WebResult(name="person") 
    public Person createPerson(@WebParam(name="person") Person p);

    //Task 5
    @WebMethod(operationName="deletePerson")
    @WebResult(name="personId") 
    public long deletePerson(@WebParam(name="personId") long id);
    
    // --- End of Person table operations
    
    //--- Start of Measures operations
    
    //Task 6
    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="healthHistory") 
    public List<HealthMeasureHistory> readPersonHistory(@WebParam(name="personId") long id, @WebParam(name="measureType") String measureType);

    //Task 7
    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="measureTypes") 
    public List<String> readMeasureTypes();

    //Task 8
    @WebMethod(operationName="readPersonMeasure")
    @WebResult(name="healthHistory") 
    public HealthMeasureHistory readPersonMeasure(@WebParam(name="personId") long id, @WebParam(name="measureType") String measureType, @WebParam(name="mid") long mid );

    //Task 9
    @WebMethod(operationName="savePersonMeasure")
    @WebResult(name="person") 
    public Person savePersonMeasure(@WebParam(name="personId") long id, @WebParam(name="measure", targetNamespace="http://ws.soap.localdb/") Measure m);

    //Task 10.1 Update current Measure
    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="measureOut") 
    public Measure updatePersonMeasure(@WebParam(name="personId") long id, @WebParam(name="measure") Measure m);

    //Task 10.2 Update old Measure in history
    @WebMethod(operationName="updatePersonHistoryMeasure")
    @WebResult(name="healthHistory") 
    public HealthMeasureHistory updatePersonHistoryMeasure(@WebParam(name="personId") long id, @WebParam(name="measureHistory") HealthMeasureHistory m);
    
    // --- End of Measure operations
    
}
