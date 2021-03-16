### Technologies:

The project is written in Kotlin and uses Clean Architecture + MVVM \
Some frameworks & libraries have been used i.e. JetPack (Coroutines, LiveData, Room, ViewModel), 
Retrofit, Gson, Dagger, Mockito-kotlin, Mockk, Espresso.

### Configuration:
The project uses the API  
The API requires a username which can be obtained after registering with the website.
Also the username needs to be registered in the website for using the APIs 
The username needs to be entered in buildsystem/configurations.gradle

### Description:

The application fetches a json feed of Earthquakes and shows them in a list in Home Screen.
The list shows some information for each Earthquake. The UI distinguishes visually earthquakes
with magnitude equal or larger than 8. The items are saved in local storage (Room) so if internet 
is not available the cached items will be loaded. Also Home supports swipe to refresh.
Clicking on an item will open Google Maps on the selected Earthquake location.

### Tests:

The project contains Unit Tests (using Mockito) and Instrumented Tests (using mockk and Espresso). 
Some network responses from real JSON data have been used for mocking the network call responses 
and database items. The Tests share common resources under the sharedTest folder.

### Screenshots:

#### Home
![Alt text](screenshots/home-1.png?raw=true "app screenshot")

#### Map
![Alt text](screenshots/map-1.png?raw=true "app screenshot")

