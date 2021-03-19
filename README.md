### Description

The application fetches a json feed of Earthquakes and shows the items in a list in Home screen.
The list shows some information for each Earthquake. The UI distinguishes visually earthquakes
with magnitude equal or larger than 8. The items are saved in local storage, so if Internet 
is not available the cached items will be loaded. Home screen supports swipe to refresh.
Clicking on an item of the list will open Google Maps on the specific Earthquake location.
There is also a Settings screen where the user can enter some preferences including their username 
which is necessary for the application to work. If the username is missing or it has not been registered
an error message will be shown to the user.

### Configuration

The project uses the Earthquakes API: https://www.geonames.org/export/JSON-webservices.html#earthquakesJSON  
The API requires a username which can be obtained by creating an account in the website and registering the 
account username for being able to use the APIs. The registration is done in the same website (geonames.org). 
The username needs to be entered in the Settings screen of the application.

### Technologies

The project is written in Kotlin and uses Clean Architecture + MVVM \
Some frameworks & libraries have been used i.e. JetPack (Coroutines, LiveData, Room, ViewModel), 
Retrofit, Gson, Dagger, Mockito-kotlin, Mockk, Espresso.

### Tests

The project contains Unit Tests and Instrumented Tests. Some network responses from real JSON data have been 
used for mocking the network call responses and database items. The Tests share common resources under the 
sharedTest folder.

### Screenshots

#### Home
![Alt text](screenshots/home-1.png?raw=true "app screenshot")

#### Map
![Alt text](screenshots/map-1.png?raw=true "app screenshot")

#### Settings
![Alt text](screenshots/settings-1.png?raw=true "app screenshot")
