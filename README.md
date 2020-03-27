# Flight Schedule Application
- This app retrieves flight schedules from [Luftansa API] (https://developer.lufthansa.com) and displays them on a map

![Screenshot_1585337292](https://user-images.githubusercontent.com/26750279/77793223-efa72c00-7026-11ea-8511-5c38715b988c.png)
![Screenshot_1585337337](https://user-images.githubusercontent.com/26750279/77793239-f6ce3a00-7026-11ea-8615-c3d8b50f472e.png)
![Screenshot_1585337488](https://user-images.githubusercontent.com/26750279/77793260-0057a200-7027-11ea-8cec-94aa8e4f2800.png)

### Installing
- Android Studio 3.6.0 to build the application
- Clone the repository
- Get your keys from [LUFTHANSA] (https://developer.lufthansa.com)
- Replace your keys in the Utils file, replacing the values of client_id, client_secret and grant_type

### Technologies
- RxJava
- koin for dependency Injection
- Retrofit and OKhttp for making network calls


### Functionalities
- Fetch flight schedules after setting an origin and destination
- display Origin and destination on the map

