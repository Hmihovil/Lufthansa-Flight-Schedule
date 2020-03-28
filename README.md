# Flight Schedule Application
- This app retrieves flight schedules from [Luftansa API](https://developer.lufthansa.com) and displays them on a map

![Screenshot_1585337292](https://user-images.githubusercontent.com/26750279/77830626-08771680-70e7-11ea-94ab-7ecb08778d30.png)
![Screenshot_1585337337](https://user-images.githubusercontent.com/26750279/77830630-0f058e00-70e7-11ea-833a-4d2b2b592a38.png)
![Screenshot_1585337488](https://user-images.githubusercontent.com/26750279/77830632-1167e800-70e7-11ea-922f-315dd0e1a2e2.png)

## Apk link 
It can be downloaded via this [Link](http://www.droidbin.com/p1e4h6imbiclv3bd4h51b8b17ia3)
### Installing
- Android Studio 3.6.0 to build the application
- Clone the repository
- Get your keys from [LUFTHANSA](https://developer.lufthansa.com)
- Replace your keys in the Utils file, replacing the values of client_id, client_secret and grant_type

### Technologies
- RxJava
- koin for dependency Injection
- Retrofit and OKhttp for making network calls
- MVVM


### Functionalities
- Fetch flight schedules after setting an origin and destination
- display origin and destination on the map

