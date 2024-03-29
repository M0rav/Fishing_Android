# Fogj ki ha tudsz

A Fogj ki ha tudsz egy olyan androidos alkalmazás, amelyet azoknak a horgászoknak terveztek, akik szeretnék nyomon követni a horgászatukat és megosztani tapasztalataikat másokkal. Az oldal használata egyszerű és intuitív, és lehetővé teszi a felhasználóknak, hogy egy térképen jelöljék meg, hol horgásztak, és mellé csatoljanak egy leírást az adott helyről.

## Bemutatás

A főoldalon bejelentkezés után található a térkép, ahol a felhasználók egyszerűen kattintanak a horgász helyére, majd felvehetik a leírást és elmenthetik az adatokat. A jobb felső sarokban található a kijelentkezés gomb, és a mellette található a menü gomb, ahol megjelennek az oldalak, mint például a főoldal, naptár, fogási napló, blog, gyakran ismételt kérdések, és elérhetőség.




A naptár oldalon a felhasználók új eseményeket vehetnek fel, amelyekhez meg kell adni az esemény címét, kezdési és befejezési időpontot, majd elmenthetik azokat. A fogási napló oldalon lehetőség van a fogások rögzítésére. A felhasználónak meg kell adnia a hal fajtáját, a súlyát és méretét, valamint azt, hogy hol fogta azt.


A blog oldalon számos hasznos tipp és tanács található a horgászathoz, a halászathoz és a halak feldolgozásához. A GYIK oldalon a felhasználók számára bemutatják az oldal használatát, a lehetőségeket és az esetleges kérdésekre adnak választ.

![image](https://user-images.githubusercontent.com/112927855/235770921-1a86f81c-cf42-410b-bf51-e381b4cca2b8.png)
![image](https://user-images.githubusercontent.com/112927855/235770992-ea06c102-7307-4c00-8557-3efd55ae5aea.png)
![image](https://user-images.githubusercontent.com/112927855/235771028-b5e58d02-f369-458c-bb31-aa3210026d09.png)
![image](https://user-images.githubusercontent.com/112927855/235771067-6221a90c-9c22-4a6e-affb-4a0f6155577c.png)


## Az android applikácio futtatása

### Első lépés
Indítsuk el a XAMPP szoftvert, majd futtassuk az Apache és Mysql szolgáltatásokat. Ez azért fontos, mert az androidos alkalmazás futtatásához szükség van egy webszerverre és adatbázis-szerverre is.

### Második lépés

Nyissuk meg a Visual Studio Code szerkesztőt, majd másoljuk be a következő linket: https://github.com/dogbox36/Backend_Fishing.git. Mentsük el a projektet, majd a terminálban írjuk be a "npm install" parancsot, hogy telepítsük a szükséges modulokat. Miután ez megtörtént, futtassuk a "npm run start:debug" parancsot a terminálból, hogy elindítsuk a backend szerverünket.

### Harmadik lépés

nyissunk meg az Android Studiot, majd másoljuk be a következő linket: https://github.com/M0rav/Fishing_Android.git. Mentsük el a projektet, szinkronizáljuk a hiányzó dependecies-eket. Miután ez megtörtént, futtassuk az alkalmazást. Fontos hogy csak 33-as SDK-val működik!

### 

Ha minden lépést sikeresen végrehajtottunk, akkor elérhető lesz a weboldalunk a böngészőben, ahol tesztelhetjük és használhatjuk a funkcióit.

Youtube bemutató videó: https://youtu.be/REGm77aYhjQ
