Student Match Readme

1. collect data using survey monkey, put results into spreadsheet

2. copy response data into a text file, save it in same folder as the .java folders. Note it is best to paste it in from least recent
timestamp to most recent timestamp, that way if someone submitted the form twice the second entry would be the one used to find a match.

(see example in surveydata)

3. update the filepath in MatchMaker.java getData function

4. update text file - first name must match the name in dash example if student name is Gianna and 
the option to select is Gia - Group 2 the mutual matching function won't work. Same thing for girls
with initials - a girl with first name Isabella will match for isabella p. or isabella w. unless the first
name in the text file is changed.

5. Run the main method and uncomment line 52. make sure that the student selected that the group they are actually in.

6. Comment out line 52 again and just run the program. Something like the following should print out:
It is good to manually check results...

Example output from 2018:
Num students in the match generator: 39
|        Group 1|        Group 2|
=================================
|          jenna|          siena|
_________________________________
|          catey|          mulan|
_________________________________
|          mary |          jenny|
_________________________________
|            lea|    isabella w.|
_________________________________
|         alissa|         alice |
_________________________________
|        melinda|          sarah|
_________________________________
|           nina|         jensen|
_________________________________
|          meraf|       chrystal|
_________________________________
|         jessie|      katherine|
_________________________________
|            gia|            bre|
_________________________________
|      lizzie s.|         juliet|
_________________________________
|    isabella p.|         camila|
_________________________________
|         pamela|        lizi m.|
_________________________________
|       angelina|            sam|
_________________________________
|            kat|          annie|
_________________________________
abigail  has no match. Desired matches: alissa - group 1,angelina - group 1,catey - group 1,gia - group 1,isabella p. - group 1,jenna - group 1,jessie - group 1,kat - group 1,kendra - group 1,kristina - group 1,lea - group 1,lizzie s. - group 1,melinda - group 1,meraf - group 1
 Notes: Catey, Kat, Angelina 
Willing to work with anyone? No
_________________________________
|       kristina|        liliana|
_________________________________
|        sabrina|         hannah|
_________________________________
|         kendra|           mira|
_________________________________
|        aichen |      christine|
_________________________________