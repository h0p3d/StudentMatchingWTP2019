import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
/**
 * @hoped. 7/19/19
 * WTP Motor building partner match generator
 * Takes student survey responses of preferred partners and matches
 * each student with a mutually selected partner (partner also selected them as preferred)
 * from the other group.
*/

public class MatchMaker {
    
    
public static void main(String[] args) {
    List<Student> students = new ArrayList<Student>();
    try {
        students = getData();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    Map<Integer,List<Student>> numToStudent = new HashMap<Integer, List<Student>>();
    Map<String,Student> nameToStudent = new HashMap<String, Student>();
    
    //make mutual match listings
    for (Student s: students) {
        for (Student x: students) {
            if (!(s.isEqual(x)) && s.isMutual(x.getName()) && !s.getGroup().equals(x.getGroup())){
                s.addMatch(x.getName());
                x.addMatch(s.getName());
            }
        }
        nameToStudent.put(s.getName(), s);
        if (numToStudent.containsKey(s.getMutuals().size())){
            List<Student> list = numToStudent.get(s.getMutuals().size());
            list.add(s);
            numToStudent.put(s.getMutuals().size(), list);
        }
        else {
            List<Student> list = new ArrayList<Student>();
            list.add(s);
            numToStudent.put(s.getMutuals().size(), list);
        }
        //System.out.println(nameToStudent.get(s.getName()).getData());
    }
    //System.out.println(numToStudent);
    
    
    //HEADS UP: If you want to force a particular match, this is the place in the code to do it! Remember
    // when setting a match to use both setMatch and setHasMatch methods! for both students...
    
    Set<String> unmatched = new HashSet<String>();
    for (String s : nameToStudent.keySet())
        if (!nameToStudent.get(s).getHasMatch())
            unmatched.add(s);
    findMatches(unmatched,numToStudent,nameToStudent);
    
}


/**
 * Taking in data generated in main method, generate and display matches for each student.
 *  Ideas for further improvement: try starting with different students to see if better / more matches can
 *  be generated. When I ran it on the 2018 data it found matches no problems. Also allow to assign certain people partners.
 * @param unmatched
 * @param numToStudent
 * @param nameToStudent
 */
private static void findMatches(Set<String> unmatched, Map<Integer, List<Student>> numToStudent,
        Map<String, Student> nameToStudent) {
    int numTimesSame = 0;
    int prevUnmatchedSize = unmatched.size();
    //System.out.println(unmatched);
    while (unmatched.size() != 0 && numTimesSame<3) {
        for (Integer numMutual: numToStudent.keySet()) {
            for (Student s: numToStudent.get(numMutual)) {
                if (unmatched.contains(s.getName())) {
                    // For Testing: System.out.println("1" + s.getName());
                    // start at the students with least mutual matches
                    for (String mutual : s.getMutuals()) {
                        // For Testing: System.out.println("2: " + mutual);
                        if (unmatched.contains(mutual)) {
                           // match them with their first mutual unmatched person
                           s.setMatch(mutual);
                           s.setHasMatch(true);
                           // assign them their match
                           nameToStudent.get(mutual).setMatch(s.getName());
                           nameToStudent.get(mutual).setHasMatch(true);
                           // remove both names from unmatched set
                           unmatched.remove(s.getName());
                           unmatched.remove(mutual);
                           
                           //For Testing: System.out.println("3: match found, " + s.getName()+","+mutual+" num unmatched " + Integer.toString(unmatched.size()) );
                           break;
                        }
                    }
                }
            }
            
            if(prevUnmatchedSize == unmatched.size())
                numTimesSame += 1;
            else {
                numTimesSame = 0;
                prevUnmatchedSize = unmatched.size();
            }
        
        }
        // continue until unmatched is empty or the size of the unmatched peeps has not changed for like ten times?
    }
    
    //Display Results
    Set<String> printed = new HashSet<String>();
    System.out.println("Num students in the match generator: " +  Integer.toString(nameToStudent.keySet().size()));
    System.out.println(String.format("|%15s|%15s|", "Group 1", "Group 2"));
    System.out.println("=================================");
    for (String s: nameToStudent.keySet())
        if (!printed.contains(s)) {
            System.out.println(nameToStudent.get(s).matchString());
            System.out.println("_________________________________");
            printed.add(s);
            printed.add(nameToStudent.get(s).getMatch());
        }
        
    
}

/**
 * Parse in the .txt file survey data and return it in a form of List type students
 * @return list of Students representing a WTP student and their preferred partners for motor building
 * @throws FileNotFoundException
 */
private static List<Student> getData() throws FileNotFoundException {
    List<Student> students = new ArrayList<Student>();
    // pass the path to the file as a parameter 
    File file = new File("C:\\Users\\hoped\\Desktop\\6.031\\StudentMatch\\src\\surveydata.txt"); 
    // UPDATE THE ABOVE FILE PATH!!!
    Scanner sc = new Scanner(file); 
  
    while (sc.hasNextLine()) {
      String[] arrOfStr = sc.nextLine().split("\t");
      String name = arrOfStr[0].toLowerCase();
      String lastName = arrOfStr[1].toLowerCase();
      String group = arrOfStr[2].toLowerCase();
      String notes = arrOfStr[arrOfStr.length-1];
      boolean fineWithAnyone = arrOfStr[3].length() > 0;
      List<String> desiredMatches = new ArrayList<String>();
      for (int i=4; i<arrOfStr.length-1; i++) {
          if (arrOfStr[i].length() > 0)
              desiredMatches.add(arrOfStr[i].toLowerCase());
      }
      students.add(new Student(name, lastName, group, desiredMatches, fineWithAnyone, notes));
      //System.out.println(Integer.toString(arrOfStr.length) + " " + String.join(",", arrOfStr)); 
    }
    return students;
}

}
