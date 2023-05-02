# Fogj ki ha tudsz

A Fogj ki ha tudsz egy weboldal, amelyet azoknak a horgászoknak terveztek, akik szeretnék nyomon követni a horgászatukat és megosztani tapasztalataikat másokkal. Az oldal használata egyszerű és intuitív, és lehetővé teszi a felhasználóknak, hogy egy térképen jelöljék meg, hol horgásztak, és mellé csatoljanak egy leírást az adott helyről.

## Bemutatás

A főoldalon bejelentkezés után található a térkép, ahol a felhasználók egyszerűen kattintanak a horgász helyére, majd felvehetik a leírást és elmenthetik az adatokat. A jobb felső sarokban található a kijelentkezés gomb, és a mellette található a menü gomb, ahol megjelennek az oldalak, mint például a főoldal, naptár, fogási napló, blog, gyakran ismételt kérdések, és elérhetőség.
![login](https://user-images.githubusercontent.com/62289619/235124703-08408463-7b71-4393-8ddc-70c6c49375dc.PNG)
![register](https://user-images.githubusercontent.com/62289619/235125084-4183a1ed-1236-444d-968a-d8d9fdd8b81f.PNG)

![Mapsave](https://user-images.githubusercontent.com/62289619/235124828-c5a194f5-66c8-4f70-a783-51186a382e2c.PNG)


A naptár oldalon a felhasználók új eseményeket vehetnek fel, amelyekhez meg kell adni az esemény címét, kezdési és befejezési időpontot, majd elmenthetik azokat. A fogási napló oldalon lehetőség van a fogások rögzítésére. A felhasználónak meg kell adnia a hal fajtáját, a súlyát és méretét, valamint azt, hogy hol fogta azt.
![Calendarhowtosave](https://user-images.githubusercontent.com/62289619/235124867-5053a294-144d-4183-8491-db2da7364537.PNG)
![CalendarSavedInfo](https://user-images.githubusercontent.com/62289619/235124882-2d11e2cb-5b54-4585-8d19-e880ee03ac62.PNG)
![CatchDiarySavedInfo](https://user-images.githubusercontent.com/62289619/235124984-20fe019c-011a-4037-95fd-3cd5c283e975.PNG)

A blog oldalon számos hasznos tipp és tanács található a horgászathoz, a halászathoz és a halak feldolgozásához. A GYIK oldalon a felhasználók számára bemutatják az oldal használatát, a lehetőségeket és az esetleges kérdésekre adnak választ.
![Blog](https://user-images.githubusercontent.com/62289619/235124917-0bda5457-b6e4-4b6b-9c04-7bfa0fd1aa55.PNG)
![GYIK](https://user-images.githubusercontent.com/62289619/235125035-49e08dad-2daf-4ad7-8c17-46f790235485.PNG)

Az elérhetőség oldalon a fejlesztők elérhetők, hogy a felhasználók jelezni tudják, ha bármilyen problémába ütköznek az oldal használata során. Az oldal használata ingyenes és egyszerű, és segítséget nyújt a horgászoknak, hogy megosszák élményeiket és tanuljanak egymástól.
![Contact](https://user-images.githubusercontent.com/62289619/235125017-14a9e3d6-5afa-47f6-a655-c225a13dcedd.PNG)


## A weboldal futtatása

### Első lépés
Indítsuk el a XAMPP szoftvert, majd futtassuk az Apache és Mysql szolgáltatásokat. Ez azért fontos, mert a weboldalunk futtatásához szükség van egy webszerverre és adatbázis-szerverre is.

### Második lépés

Nyissuk meg a Visual Studio Code szerkesztőt, majd másoljuk be a következő linket: https://github.com/dogbox36/Backend_Fishing.git. Mentsük el a projektet, majd a terminálban írjuk be a "npm install" parancsot, hogy telepítsük a szükséges modulokat. Miután ez megtörtént, futtassuk a "npm run start:debug" parancsot a terminálból, hogy elindítsuk a backend szerverünket.

### Harmadik lépés

nyissunk meg egy másik Visual Studio Code szerkesztőt, majd másoljuk be a következő linket: https://github.com/dogbox36/FrontendFishing.git. Mentsük el a projektet, majd a terminálban írjuk be a "npm install" parancsot, hogy telepítsük a szükséges modulokat. Miután ez megtörtént, futtassuk a "npm start" parancsot a terminálból, hogy elindítsuk a frontend szerverünket. Fontos, hogy a 3001 porton indítsuk a frontend szerverünket, mivel a 3000 port már foglalt a backend miatt. Amikor megkérdezi, hogy futtassa-e a szerverünket a 3001-es porton, válasszuk a "Y" opciót.

### 

Ha minden lépést sikeresen végrehajtottunk, akkor elérhető lesz a weboldalunk a böngészőben, ahol tesztelhetjük és használhatjuk a funkcióit.

Youtube bemutató videó: https://youtu.be/NIioBBsaLtg
