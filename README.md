# MarvelAvengers WIKI


<img src = "https://github.com/complextheory/MarvelAvengers/blob/master/app/src/main/res/drawable/Screenshot_1629234687.png" width="250"
height="500">
<img src = "https://github.com/complextheory/MarvelAvengers/blob/master/app/src/main/res/drawable/Screenshot_1629234510.png" width="250"
height="500">
<img src = "https://github.com/complextheory/MarvelAvengers/blob/master/app/src/main/res/drawable/Screenshot_1629234525.png" width="250"
height="500">                                                                                                                               
                                                                                                                                    
                                                                                                                                                                  

A Kotlin App that consumes the [Marvel Characters Api](https://developer.marvel.com/documentation/getting_started). 

* Uses clean code methodology and principles 

* Modern Android application tech-stacks and MVVM architecture. 

* Fetches data from the network and persists it offline. 

* Uses dependency injection to provide components to the various modules using the repository pattern.



# Tech stack & Open-source libraries

* Minimum SDK level 21

* [Kotlin](https://developer.android.com/kotlin) based + Coroutines for asynchronous.

* JetPack

* Lifecycle - dispose of observing data when lifecycle state changes.

* ViewModel - UI related data holder, lifecycle aware.

* Architecture

    * MVVM Architecture (View - DataBinding - ViewModel - Model)
    
    * Repository pattern
    
    * [Koin](https://medium.com/mobile-app-development-publication/setting-up-android-modules-with-koin-962534395a3e) - dependency injection
    
    * [Retrofit2 & Gson](https://stackoverflow.com/questions/46428477/how-to-make-two-retrofit-calls-and-combine-results?noredirect=1&lq=1)
    - construct the REST APIs.
    
    * [Glide](https://androidwave.com/loading-images-using-data-binding/) - loading images.
    
    * Material - Components - Material design components


# Architecture

MarvelAvengers utilizes clean code methodology, the MVVM architecture, and the repository Pattern

![alt text](https://miro.medium.com/max/606/1*BpxMFh7DdX0_hqX6ABkDgw.png)

# License Info

```Designed and developed by 2020 complextheory (Jarvis Charles)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.```
