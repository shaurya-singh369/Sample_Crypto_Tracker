# Sample_Crypto_Tracker
This is an Android app that displays a list of cryptocurrency prices using RecyclerView. It utilizes RoomDB to store the latest fetched information.

The app has two options:

1) When the internet is available, it fetches the latest data from the CoinCap API and displays it. A refresh button is provided that, when clicked, will refetch the data and update the LiveData.

2) When the internet is not available, it will fetch the data from RoomDB and display it.

This provides offline functionality for users who may not have a stable internet connection.

Please note that the app will only fetch data from RoomDB if there is no internet connection available. If the internet becomes available, the app will switch back to option 1 and fetch the latest data from the CoinCap API.

The CoinCap API documentation can be found at https://docs.coincap.io/.

To see the app in action, please watch the following video: https://drive.google.com/file/d/13IS4z3FBBfxJkEurPGDd3cCXW-GjnOOS/view?usp=sharing
