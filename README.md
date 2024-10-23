# Card Cost API
This is an API that calculates the clearing cost of a bank card based on its number. 
The [Binlist API](https://binlist.net/) is called to determine the country that the card was issued and then returns the clearing cost of the card, if this exists in the databse. 

### Get the application up & running 
To run the application there must be a running Docker instance like Docker Desktop.  
Once Docker Desktop is up, open a terminal in the application's root folder and run: 
1) `./gradlew clean build` 
2) `docker-compose up --build`
   
If a `db Error` shows up before pulling the database, rerun the 2nd command.

## Documentation and Postman Collection
- [This](https://documenter.getpostman.com/view/7555836/2sAY4rF5Z5) is the documentation
- and [this](https://github.com/user-attachments/files/17497399/card.cost.api.postman_collection.json.zip) is the Postman Collection

