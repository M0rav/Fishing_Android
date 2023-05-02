# Fogj ki ha tudsz

A Fogj ki ha tudsz egy weboldal, amelyet azoknak a horgászoknak terveztek, akik szeretnék nyomon követni a horgászatukat és megosztani tapasztalataikat másokkal. Az oldal használata egyszerű és intuitív, és lehetővé teszi a felhasználóknak, hogy egy térképen jelöljék meg, hol horgásztak, és mellé csatoljanak egy leírást az adott helyről.

## Bemutatás

A főoldalon bejelentkezés után található a térkép, ahol a felhasználók egyszerűen kattintanak a horgász helyére, majd felvehetik a leírást és elmenthetik az adatokat. A jobb felső sarokban található a kijelentkezés gomb, és a mellette található a menü gomb, ahol megjelennek az oldalak, mint például a főoldal, naptár, fogási napló, blog, gyakran ismételt kérdések, és elérhetőség.




A naptár oldalon a felhasználók új eseményeket vehetnek fel, amelyekhez meg kell adni az esemény címét, kezdési és befejezési időpontot, majd elmenthetik azokat. A fogási napló oldalon lehetőség van a fogások rögzítésére. A felhasználónak meg kell adnia a hal fajtáját, a súlyát és méretét, valamint azt, hogy hol fogta azt.

A blog oldalon számos hasznos tipp és tanács található a horgászathoz, a halászathoz és a halak feldolgozásához. A GYIK oldalon a felhasználók számára bemutatják az oldal használatát, a lehetőségeket és az esetleges kérdésekre adnak választ.

Az elérhetőség oldalon a fejlesztők elérhetők, hogy a felhasználók jelezni tudják, ha bármilyen problémába ütköznek az oldal használata során. Az oldal használata ingyenes és egyszerű, és segítséget nyújt a horgászoknak, hogy megosszák élményeiket és tanuljanak egymástól.

## Az android applikácio futtatása

### Első lépés
Indítsuk el a XAMPP szoftvert, majd futtassuk az Apache és Mysql szolgáltatásokat. Ez azért fontos, mert a weboldalunk futtatásához szükség van egy webszerverre és adatbázis-szerverre is.

### Második lépés

Nyissuk meg a Visual Studio Code szerkesztőt, majd másoljuk be a következő linket: https://github.com/dogbox36/Backend_Fishing.git. Mentsük el a projektet, majd a terminálban írjuk be a "npm install" parancsot, hogy telepítsük a szükséges modulokat. Miután ez megtörtént, futtassuk a "npm run start:debug" parancsot a terminálból, hogy elindítsuk a backend szerverünket.

### Harmadik lépés

nyissunk meg egy másik Visual Studio Code szerkesztőt, majd másoljuk be a következő linket: https://github.com/dogbox36/FrontendFishing.git. Mentsük el a projektet, majd a terminálban írjuk be a "npm install" parancsot, hogy telepítsük a szükséges modulokat. Miután ez megtörtént, futtassuk a "npm start" parancsot a terminálból, hogy elindítsuk a frontend szerverünket. Fontos, hogy a 3001 porton indítsuk a frontend szerverünket, mivel a 3000 port már foglalt a backend miatt. Amikor megkérdezi, hogy futtassa-e a szerverünket a 3001-es porton, válasszuk a "Y" opciót.

### 

Ha minden lépést sikeresen végrehajtottunk, akkor elérhető lesz a weboldalunk a böngészőben, ahol tesztelhetjük és használhatjuk a funkcióit.

Youtube bemutató videó: https://youtu.be/REGm77aYhjQ
