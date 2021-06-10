### Description

The application fetches a json feed of Earthquakes and shows the items in a list in Home screen.
The UI distinguishes visually earthquakes with magnitude equal or larger than 8. 
The items are saved in local storage, so if Internet is not available the cached items will be loaded. 
Clicking on an item of the list will open Google Maps on the specific Earthquake location.
Clicking on the map menu item will open all the earthquake locations on the Map.
There is also a Settings screen where the user can enter some preferences including their username 
which is required by the application. If the username is missing or it has not been registered
an error message will be shown to the user.

### Configuration

The project uses the Earthquakes API: https://www.geonames.org/export/JSON-webservices.html#earthquakesJSON  
The API requires a username which can be obtained by creating an account in the website and registering the 
account username for using the APIs. The registration is done in the same website (geonames.org). 
The username needs to be entered in the Settings screen of the application.
Also a MAPS_API_KEY needs to be entered in local.properties.

### Technologies

Kotlin, Clean Architecture + MVVM, JetPack, Coroutines, LiveData, Room, ViewModel, Retrofit, Gson, 
Dagger, Mockito-kotlin, Mockk, Espresso.

### Tests

The project contains Unit & Instrumented Tests. Some network responses from real JSON data have been 
used for mocking the network call responses and database items. The Tests share common resources under the 
sharedTest folder.

### Screenshots

#### Home
![Alt text](screenshots/home-1.png?raw=true "app screenshot")

#### Map
![Alt text](screenshots/map-1.png?raw=true "app screenshot")

#### Map - all quakes
![Alt text](screenshots/map-all-quakes.png?raw=true "app screenshot")

#### Settings
![Alt text](screenshots/settings-1.png?raw=true "app screenshot")
