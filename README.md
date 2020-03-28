# Flight Schedule Application
- This app retrieves flight schedules from [Luftansa API](https://developer.lufthansa.com) and displays them on a map

<img src="https://github.com/pawnjester/Flight-Schedule/blob/develop/art/Screenshot_1585337292.png" height="450">
|<img src="https://github.com/pawnjester/Flight-Schedule/blob/develop/art/Screenshot_1585337488.png" height="450">
|<img src="https://github.com/pawnjester/Flight-Schedule/blob/develop/art/Screenshot_1585337337.png" height="450">


### Installing
- Android Studio 3.6.0 to build the application
- Clone the repository
- Get your keys from [LUFTHANSA](https://developer.lufthansa.com)
- Replace your keys in the Utils file, replacing the values of client_id, client_secret and grant_type

### Technologies
- RxJava
- koin for dependency Injection
- Retrofit and OKhttp for making network calls


### Functionalities
- Fetch flight schedules after setting an origin and destination
- display origin and destination on the map

