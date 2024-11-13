# DietTrack
## *A New Comprehensive Fitness Analytics and Nutrition Tracker*
 <br />
This file will provide users with a sense of DietTrack 

The following outlines **3 vital sections** of this program:
- Application Usage // User End
- Target Demographics
- Driving Factors in Development
- User Stories

 <br />

## *Application Usage*

Using various information categories obtained from the user, 
this application serves as a consistent analysis too for health and fitness. Such aforementioned data will
be collected as a user 'joins' the service. The program will provide logistical analytics 
centered around macronutrient and diet intake as an effective alternative to contemporary food tracking software.

Users will possibly be known as 'clients.' Clients will enter as much information as thy can to be 
processed by the program. The program, once run, will release benchmarks in gross amounts
Users can choose from an array of options to direct analysis of 
the specific health aspect selected. Macronutrient information to calculate intake data and information is one key
feature of this system. It will return recommendations based upon those values;
for instance, the system could return "you are ___ from satisfying your desired protein intake"
or "the selected food item is high in saturated fat and sugars, 
we advise limiting intake to ensure you meet you weekly goal."
Data like this will be collected and returned to clients upon request.
Future integration could include certified personal trainer support/input/oversight.

## *Target Demographic*

A user would likely be defined as an individual who seeks to improve health or 
obtain a method of tracking progress in various aspects of fitness. The key premise
of this program is to not be limit users to preselected options, for each user will have the option
to add food of whatever name or macronutrient composition, or simply view historical nutrient logs.


## *Driving Factors in Development*

Not only do I seek to develop my programming knowledge with a challenging and 
atypical project choice, but I hold a deep relationship with fitness- particularly with an involvement
to bodybuilding and certified personal training.

I compete in the **World's Natural Bodybuilding Federation (WNBF)** and **Canadian Physique Alliance (CPA)**
organizations in Men's bodybuilding, classic bodybuilding, and physique. 
Additionally, I am a Certified Personal Trainer and Nutritionist through the 
**International Sports Sciences Association (ISSA)**. At the University of British Columbia,
I am the current president and founder of the largest fitness organization and supplement
retailer on campus, **UBC Powerbuilding**.

Fitness and health has burrowed itself deep in my persona, and I hold a certainty that the 
development of a program, such as thus, will sustain my own interest and could pose
benefit to the personal training of others, those in my organization, and myself.


## *User Stories*

In the context of this program (considering ongoing development):
- As a user, I want to be able to enter a username to receive individualized system responses.
- As a user, I want to view all log submissions with the day they were submitted.
- As a user, I want to be able to submit a log of a food and its macronutrients for each day.
- As a user, I want to be able to calculate individual tallies of a given macronutrient for a chosen day.
- As a user, when I submit my Nutrition Logs, I want the option to save logged User Profile information to a file.
- As a user, I want to be able to load my file of past Nutrition Logs for particular User Profile.

## *Functionality Notes*

- You can generate the first required event related to adding Nutrition Logs to a User Profile by either selecting
'Load Profile Data' or 'Create New Profile' in the main menu screen. Once selected, users can add a Nutrition log by 
entering the date of consumption, food name, protein content, carbohydrate content, fat content, and sugar content 
and clicking the 'Submit New Log' option.
- You can generate the second required event related to adding Nutrition Logs to a User Profile by 
proceeding to the add nutrition log screen and clicking the 'Remove All Nutrition Logs' option, which clears all 
Nutrition Logs from a given profile.
- You can locate my visual component by creating a new profile where a motivational image appears to incentivize user
interaction and interest in program.
- You can save the state of my application by selecting the 'Save Profile Data' option within the add nutrition log
screen or by exiting the program, where a window pop up will appear and ask users whether they would like to save before
exiting the program.
- You can reload the state of my application by selecting the 'Load Profile Data' upon startup of the program, where 
user data is loaded and all other functionality remains.

## *Phase 4: Task 2*

Wed Nov 30 22:24:30 MST 2022
Added the Cow Meat food item to the User's Nutrition Logs on 2022-10-11

Wed Nov 30 22:25:46 MST 2022
Added the Protein Powder food item to the User's Nutrition Logs on 2021-10-12

Wed Nov 30 22:25:52 MST 2022
Cleared existing Nutrition Logs from User history


## *Phase 4: Task 3*
- Reflecting on the UML Design, I recognize a very simple design structure. The notion of a single class, Gui, leads me 
to believe that refactoring is possible, particularly in relation with the single responsibility principle. This
leads me to my first point on the topic of refactoring to improve upon this design below:
- Albeit the simplified design structure, the classes could be better divided to improve overall program structure as
the majority of user interface functionality comes from a single class, Gui, and responsibilities could be partitioned
into respective methods, each with its own function/role. This would serve to improve refactoring and the Cohesion 
design concept of the single responsibility principle.
- Further to the aforementioned point, parts of the graphics initialization in Gui holds lengthy methods that could 
prove difficult to understand upon first viewing to newcomers. I would extract primary behaviour lying in parts of
larger methods into smaller, more specialized ones.
- I also understand the Singleton Pattern to be a possible improvement to the structure of this program.
DieTrack was initially perceived as an application overseeing a large user base. The current implementation, however, 
revolves around a single instance of the UserProfile class. Supplementing global access to a single instance of a class,
I would use the Singleton Pattern to refactor the code to ensure global access through an accessor method of sorts 
where all functionality works on that one and only one instance of the User. This would make sense intuitively as well,
for the application only handles a single user at a time.
  
