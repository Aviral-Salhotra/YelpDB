

Abstract Class: Business
//Abstraction Function: Represents a generic business (will use a HashMap<String,String>). Map is a set of 						string pairs representing the attribute (keys e.g "Location") and the associated string 						(values e.g. "Vancouver")
//Rep Invariant: Only one value associated with each attribute




Abstract Class: User
//Abstraction Function: Represents a generic user (using a HashMap<String, String>). Map is a set of string 						pairs representing the attribute (keys e.g. "Name") and the associated string (values 						e.g. "Frank E.") 
//Rep Invariant: Only one value associated with each attribute



Abstract Class: Review
//Abstraction Function: Represents an online review with text, ratings, etc. (using a HashMap<String, String>). 						Map is a set of string pairs representing the attribute (keys e.g. "date")and the 						associated string (values e.g. "2009-08-14")
//Rep Invariant: Only one value associated with each attribute



Abstract Class: Database 
//Abstraction Function: Represents a database that allows retrieval, modification or addition of new entries.
//Rep-Invariant: All objects in Map must have a unique ID

Concrete Representation: 
Map<String, Business>
//Rep-Invariant: String must correspond to a valid BusinessID
Map<String, User>
//Rep-Invariant: String must correspond to a valid UserID
Map<String, Review>
//Rep-Invariant: String must correspond to a valid ReviewID

Abstract Class Database Methods:
void addNewBusiness(String businessJSON)
void addNewUser(String userJSON)
void addNewReview(String reviewJSON)
void updateBusiness(String businessID, String attribute, String newVal)
void updateUser(String userID, String attribute, String newVal)
void updateReview(String reviewID, String attribute, String newVal)
boolean removeBusiness(String businessID)
boolean removeUser(String userID)
boolean removeReview(String reviewID)
String getBusiness(String businessID)
String getUser(String userID)
String getReview(String reviewID)





Class: YelpDataBase extends Database implements MPBDb
//Abstraction Function: Represents the yelp database that allows retrieval, modification, addition of new 						entries, k-clustering, querying and getting the predictor function
//Rep-Invariant: All objects in Map must have a unique ID


Map<String, Restaurant>
//Rep-Invariant: String must correspond to a valid BusinessID
Map<String, YelpUser>
//Rep-Invariant: String must correspond to a valid UserID
Map<String, YelpReview>
//Rep-Invariant: String must correspond to a valid ReviewID


Class YelpDataBase Methods:
void addNewBusiness(String businessJSON)
void addNewUser(String userJSON)
void addNewReview(String reviewJSON)
void updateBusiness(String businessID, String attribute, String newVal)
void updateUser(String userID, String attribute, String newVal)
void updateReview(String reviewID, String attribute, String newVal)
boolean removeBusiness(String businessID)
boolean removeUser(String userID)
boolean removeReview(String reviewID)
String getBusiness(String businessID)
String getUser(String userID)
String getReview(String reviewID)
Set<T> getMatches(String queryString)
String kMeansClusters_json(int k)
ToDoubleBiFunction<MP5Db<T>, String> getPredictorFunction(String user)



Class Restaurant extends Business
//Abstraction Function: Represents a Restaurant ((will use a HashMap<String,String>)
//Rep Invariant: Map is a set of string pairs representing the attribute (keys e.g "Location") and the 				 				 associated string (values e.g. "Vancouver")
//Rep Invariant: Only one value associated with each attribute

Class YelpUser extends User
//Abstraction Function: Represents a Yelp User (using a HashMap<String, String>)
//Rep Invariant: Map is a set of string pairs representing the attribute (keys e.g. "Name") and the associated 				 				 string (values e.g. "Frank E.") 
//Rep Invariant: Only one value associated with each attribute

Class YelpReview extends Review
//Abstraction Function: Represents an online Yelp review with text, ratings, etc.
						(using a HashMap<String, String>). Map is a set of string pairs representing the 						 						attribute (keys e.g. "date")and the associated string (values e.g. "2009-08-14")
//Rep Invariant: Only one value associated with each attribute





