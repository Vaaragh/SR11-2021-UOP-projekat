# SR11-2021-UOP-projekat

Projekat u okviru predmeta uvod u objektno orijentisano programiranje

Tema: sistem za rad biblioteke

## Opis rada

Zamišljena biblioteka posluje u skladu sa sledećim pravilima:
•na sistem se mogu prijaviti samo zaposleni: administratori i bibliotekari
•za svakog zaposlenog, potrebno je voditi evidenciju o sledećim podacima: ime, prezime, JMBG, adresa, pol, plata, korisničko ime i lozinka
•administratori mogu da registruju nove zaposlene, kao i da dodaju nove članove, dok bibliotekari mogu samo da dodaju nove članove biblioteke
•za svakog člana bilblioteke neohpodno je evidentirati: ime, prezime, JMBG, adresu, pol, broj članske karte, tip članarine, datum poslednje uplate članarine, broj meseci za koje je uplaćena članarina i podatak da li je član aktivan ili ne
•visina članarine na mesečnom nivou se razlikuje po tipu: 100 din. za penzionere, 150 din. za decu do 14 godina i 250 din. za ostale članove
•administratori imaju pristup svim entitetima i svim akcijama nad njima; bibliotekari nemaju pristup samo administratorima i drugim bibliotekarima
•za knjige se vodi evidencija o sledećim podacima: naslov knjige, originalni naslov knjige, ime i prezime pisca, godina objavljivanja knjige, jezik originala, opis knjige i žanr; svaki žanr ima svoju oznaku i opis
•svaki pojedinačni primerak knjige evidentiran je na sledeći način: knjiga kojoj primerak pripada, broj strana, tip poveza (mek iili tvrd), godina štampanja, jezik štampanja i podatak da li je knjiga trenutno iznajmljena iline
•pojedinačno iznajmljivanje knjige se vrši unosom sledećih podataka: zaposleni koji je obradio iznajmljivanje, član koji je iznajmio knjigu, datum iznajmljivanja, datum vraćanja i primerak knjige koji je iznajmljen
•za samu biblioteku postoje podaci o nazivu, adresi, telefonu i radnom vremenu

## Tehnička specifikacija
Potrebno je kreirati objektni model na osnovu koje će biti razvijeno programsko rešenje. Količina i priroda entiteta i veza nije bitna dokle god modelovani sistem podržava sve specificirane funkcionalnosti i dokle god je model u skladu sa pravilima i preporukama objektnog modelovanja. U skladu sa ovim, opisani sistem je moguće modelovati i implementirati na mnogo različitih načina pri čemu je na studentima da svako za sebe razvije svoje rešenje.

U drugoj fazi je potrebno razviti grafički korisnički interfejs upotrebom Java Swing biblioteke koji će omogućiti prijavu na sistem, kao i pristup formama za pregled, izmenu i brisanje podataka o sledećim entitetima:

1.Administratori
2.Bibliotekari
3.Članovi
4.Tipovi članarine
5.Knjige
6.Žanrovi knjiga
7.Primerci knjiga
8.Iznajmljivanja

U zavisnosti od konkretne implementacije, studenti mogu proizvoljno organizovati date entitete ili dodati druge. Pored ovoga, potrebno je omogućiti dijalog za izmenu podataka o samoj biblioteci.Podaci o svim entitetima se čuvaju u tekstualnim datotekama koje mogu biti u proizvoljnom formatu. Za zadate datoteke, potrebno je ručno implementirati metode za čitanje i pisanje podataka.Za podatke koji imaju predefinisan skup mogućih vrednosti (jezik, tip poveza) potrebno je implementirati odgovarajuće enumeracije.Za svaki od entiteta koji se evidentira potrebno je obezbediti jedinstvenu oznaku (ID) pri čemu je potrebo onemogućiti dodavanje više entiteta sa istom oznakom.Prilikom brisanja, svi entiteti se brišu logički, što znači da podaci o njima treba da ostanu u datotekama, ali obrisani entiteti ne treba da se vide u sistemu.

## Zadatak za ocenu 9
Potrebno je omogućiti da se prilikom jednog iznajmljivanja može iznajmiti više primeraka knjiga. Forme za unos i izmenu iznajmljivanja treba da poseduju spisak svih iznajmljenih primeraka, kao i kontrole za dodavanje i uklanjanje primeraka sa liste.

## Zadatak za ocenu 10
Omogućiti obračunavanje i prikaz ostvarenog popusta na članarinu ukoliko član uplaćuje  za više meseci i to 10% popusta za 6 meseci i 20% za godinu dana. Takođe, implementirati mogućnost automatskog proglašenja člana neaktivnim nakon isteka  članarine. 
