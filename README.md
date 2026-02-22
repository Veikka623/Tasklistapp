Viikko 1 - Tehtävälista-sovellus

Sovellus käyttää luokkaa Task, joka näyttää sovelluksessa tehtävän otsikon, kuvauksen ja tilan (valmis tai kesken).

Sovelluksessa logiikkafunktiot:

addTask: Lisää uuden tehtävän listan loppuun.

toggleDone: Vaihtaa tehtävän tilan valmiin ja keskeneräisen välillä.

filterByDone: Näyttää vain valmiit tehtävät.

sortByDueDate: Järjestää tehtävät päivämäärän perusteella (mutta sovelluksen tehtävissä ei implementoitu päivämäärää).


Viikko 2 - ViewModel

Sovellusta laajennettu siirtämällä tilanhallinta ja logiikka uuteen TaskViewModel-luokkaan. 
Sovellus nyt jaettu Domain, ViewModel ja UI- osiin.

Uusi logiikkafunktio:

removeTask: Poistaa tehtävän listasta painamalla roskakoria.

Miten Compose-tilan hallinta toimii: 
Compose piirtää näkymän sille annetun Staten perusteella. 
Kun ViewModel muuttuu kuten uuden tehtävän lisäys, Compose huomaa muutoksen ja päivittää käyttöliittymän.

Miksi ViewModel on parempi kuin pelkkä remember:

Remember-muuttujat nollautuvat välillä kuten esim laitteen kääntämisen yhteydessä. 
ViewModel ei nollaudu samoista muutoksista kuin remember, joten sovelluksen tila pysyy.
ViewModel myös erottaa sovelluksen logiikan ja datan käsittelyn sovelluksen käyttöliittymästä, 
ja se tekee koodista siistimpää ja helpompaa lukea, ja tekee testaamisen helpommaksi.


Aja sovellus tietokoneella lataamalla se GitHubista, avaamalla sen Android Studiossa
ja ajamalla sen siellä.


Viikko 3 - MVVM ja StateFlow 

Sovelluksen rakenne muutettu siirtämällä tiedostot omiin paketteihin (model, viewmodel, view) ja
se käyttää nyt StateFlowia.

Uusi ominaisuus:

updateTask: Tehtävää voi muokata klikkaamalla sitä joka aukaisee muokkausnäytön.

Miksi MVVM on hyödyllinen Compose-sovelluksissa:
Koodin kategorisointi tekee koodista helpommin ylläpidettävää ja selkeämpää.


Miten StateFlow toimii: 
StateFlow säilyttää sovelluksen uusimman tilan. 
Kun sovellusta päivitetään, StateFlow ilmoittaa siitä käyttöliittymälle, joka muotoilee itsensä uudelleen.


Viikko 4 - Navigointi ja jaettu tila

Sovellukseen lisätty navigointi JetPack Composella.

NavHost ja NavController mahdollistavat siirtymisen eri sivujen/näkymien välillä.

TaskViewModel jaetaan  Home- että Calendar-näkymille, 
joten data pysyy samana ja ajantasaisena molemmissa.

Uudet ominaisuudet:

CalendarScreen: Näyttää tehtävät listana päivämäärän mukaan.

AlertDialog: Tehtävien lisäys ja muokkaus on nyt pop-up.

Viikko 5 - Sääsovellus 

Sovellukseen lisätty uusi sääsivu joka hakee API:lla säätietoja OpenWeatherista.

Uudet ominaisuudet:

Retrofit: Tekee HTTP-pyyntöjen tekemisen sovelluksen ja OpenWeatherin välillä.

Gson: Muuttaa API:n JSON-datan Kotlinin dataluokiksi.

Coroutines: API-kutsu tehdään coroutinella viewModelScope.launch -avulla,
jotta sovelluksen käyttöliittymä ei jäädy datan latauksen ajaksi. 
Ja kun data saapuu, käyttöliittymä päivittyy.

UI-tilan hallinta: WeatherViewModel hallitsee erillistä WeatherUiState-oliota (Idle, Loading, Success, Error). 
Compose-käyttöliittymä kuuntelee tätä tilaa ja piirtää itsensä automaattisesti uudelleen tilan muuttuessa (esim. näyttää latausympyrän tai virheviestin).

API-avain: API-avain  tallennettu local.properties-tiedostoon, joka ei mene GitHubiin. 
Sieltä sen saa BuildConfig-muuttujaksi, jota Retrofit käyttää.

Viikko 6 - Room-tietokanta

Sovellukseen lisätty paikallinen tietokanta (Room), jolla tehtävät tallentuvat 
laitteen muistiin eivätkä nollaannu.

Ominausuudet:

Task: Dataluokka, josta Room tekee tietokantataulun.

TaskDao: Rajapinta tietokantakyselyille. 
AppDatabase: Kokoaa tietokannan ja yhdistää Daon sovellukseen.

TaskRepository: Toimii yhteytenä tietokannan ja ViewModelin väliin ja 
hakee datan ja tarjoilee sen eteenpäin.

Miten datavirta kulkee:
Room palauttaa tietokannan sisällön Flow-muodossa.
Kun tietokantaan tulee muutos kuten tehtävän lisäys, Room ilmoittaa siitä repositoryn kautta ViewModelille.
Compose kuuntelee ViewModelin tilaa ja päivittää näkymän.