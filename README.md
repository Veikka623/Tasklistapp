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