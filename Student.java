/**
 * @hoped. 7/19/19
 * WTP Motor building partner match generator
 * Takes student survey responses of preferred partners and matches
 * each student with a mutually selected partner (partner also selected them as preferred)
 * from the other group.
*/
import java.util.ArrayList;
import java.util.List;

public class Student {
    
    private final String name;
    private final String lastName;
    private final String group;
    private final List<String> desired_matches;
    private boolean hasMatch;
    private String matchName;
    private final List<String> mutual_matches;
    private final String fineWithAnyone;
    private final String notes;
    
    public Student(String name, String lastName, String group, List<String> desired_matches, boolean fine, String note) {
        this.name = name.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.group = group.toLowerCase();
        this.desired_matches = desired_matches;
        this.hasMatch = false;
        this.matchName = "";
        this.mutual_matches = new ArrayList<String>();
        this.fineWithAnyone = fine ? "Yes": "No";
        this.notes = note;
    }
    
    /**
     * Checks if this student has the n representing another student name as part of their desired matches.
     * Note this part is highly dependent on the name of the Student that they wrote in the survey and the
     * representation of the student name in the selected partner checkboxes (see README).
     * @param n Name of student that has this student listed as a preferred partner- checking to see if this student also 
     * prefers student n 
     * @return true if both students prefer each other as partners, false otherwise
     */
    public boolean isMutual(String n) {
        for (int i =0; i< desired_matches.size(); i++) {
            if (this.desired_matches.get(i).contains(n))
               return true;
            }
        return false;
    }
    
    /**
     * Adds a name to list of Students this student mutually prefers to work with.
     * Note that the same student name will not be added twice to this list.
     * Also Note that like isMutual, this is also highly dependent on form data (see isMutual documentation).
     * @param n The other student's name. 
     */
    public void addMatch(String n) {
        for (int i =0; i< desired_matches.size(); i++) {
            if (this.desired_matches.get(i).contains(n) && !this.mutual_matches.contains(n))
                this.mutual_matches.add(n);
            }
    }
    
    /**
     * Set true if this student has an assigned partner, false otherwise.
     * @param m a boolean indicating whether this person has an assigned partner.
     */
    public void setHasMatch(boolean m) {
        this.hasMatch = m;
    }
    
    /**
     * Overrides to string method to represent student as their first name, group and mutual match list 
     */
    @Override
    public String toString() {
        return this.name + "   " + this.group + "  " + " Mutual matches: " + String.join(",", this.mutual_matches);
    }
    
    /**
     * Represent the student and their partner (or lack thereof if they are missing a partner...)
     * @return a string representing the partners in format |group 1 member | group 2 member| if there is a partner 
     * or if there is no match for this partner display information about student so manual matching can commence.
     */
    public String matchString() {
        String result = this.group.contains("1") ? String.format("|%15s|%15s|", this.name, this.matchName) : 
                                                   String.format("|%15s|%15s|", this.matchName, this.name);
        if (!hasMatch)
            result = this.name + " has no match. Desired matches: " + String.join(",", this.desired_matches) +"\n Notes: "+ this.notes 
                                                    + "\nWilling to work with anyone? " + this.fineWithAnyone;
        return result;
    }
    
    /**
     * @return 'yes' if the student stated they would be fine working with any parnter; 'no' otherwise
     */
    public String isFine() {
        return this.fineWithAnyone;
    }
    
    /**
     * @return first name of this student
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return group of this student as represented in .txt file. Note this is a category the student selects
     * and is therefore prone to error. See README.
     */
    public String getGroup() {
        return this.group;
    }
    
    /**
     * @return a copy of the list of names of students that this student mutually would like to be partners with
     */
    public List<String> getMutuals(){
        List<String> mcopy = new ArrayList<String>();
        for (String name: this.mutual_matches) {
            mcopy.add(name);
        }
        return mcopy;
    }
    
    /**
     * @param n  Set the name (n) of this students partner to n
     */
    public void setMatch(String n) {
        this.matchName = n;
    }
    
    /**
     * are these two objects the same student?
     * @param that the other object we are comparing to
     * @return true if the two objects are equal, false otherwise
     */
    public boolean isEqual(Object that) {
        return that instanceof Student && this.sameValue((Student)that);
    }
    
    /**
     * Verify if two student objects are the same.
     * @param that the other student object
     * @return true if these two students have the same first name, last name and group; false otherwise
     */
    private boolean sameValue(Student that) {
        return this.name.equals(that.name) && this.lastName.equals(that.lastName) && this.group.equals(that.group);
    }

    /**
     * @return full range of data student entered in survey
     */
    public String getData() {
        return this.name + " " + this.lastName + ", group: " + this.group+ " Matches: " 
                + String.join(", ", this.desired_matches) + "\nMutual Matches: " +   
                String.join(", ", this.mutual_matches + "\nAdditional Notes: " + this.notes + "\n");
    }

    /**
     * @return first name of this student's partner
     */
    public String getMatch() {
        return this.matchName;
    }

    /**
     * @return true if this student has a matched partner; false otherwise
     */
    public boolean getHasMatch() {
        return this.hasMatch;
    }
    
}
