# Gorengan Indonesia
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Express.js](https://img.shields.io/badge/express.js-%23404d59.svg?style=for-the-badge&logo=express&logoColor=%2361DAFB) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Vercel](https://img.shields.io/badge/vercel-%23000000.svg?style=for-the-badge&logo=vercel&logoColor=white) ![Supabase](https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white)

**Very** Simple Android App that provides numerous recipes of Indonesian fritters. This project is done to fulfill College Subject (Mobile Programming) Final Assignment. [Backend](https://github.com/asrofilfachrulr/API-Gorengan-Indonesia) of the app is hosted as Express.js serverless app on PaaS Vercel and use Database Hosting also Storage Hosting at [Supabase](https://supabase.com/).

## APK Download  
Go to [release page](https://github.com/asrofilfachrulr/gorengan-indonesia/tags) of this repo to see what available options. 

#### Quick Link For Beta 
Currently, updates on app will be released to this link. Not every commits are uploaded.  

[Download Gorengan Indonesia Beta APK](https://github.com/asrofilfachrulr/gorengan-indonesia/releases/download/beta/app-debug.apk)

## Snapshots

<img src="./snapshot-home.png" alt="snapshot-home" width="300"/> <img src="./snapshot-your-recipe.png" alt="snapshot" width="300"/>

<img src="./snapshot-5.png" alt="snapshot" width="300"/> <img src="./snapshot-detail-recipe.png" alt="snapshot" width="300"/>


## Main Features
- [x] User Account
- [x] CRUD Recipe(s) by Logged Users
- [x] CRUD Recipe's Rating by Logged Users
- [x] Share Recipe (currently limited to plain text)
- [x] CRUD Recipe's Favourite
- [x] Filter List Data with Search Keyword
- [ ] Save Recipe(s) Offline

## TODO
~~1. change layout on detail activity: move kebab menu to the right of reciept title then replace the its place before with toggle add favourite button~~  

~~2. add author name on detail, main also put small profile thumb~~  

~~3. convert necessary toast to has short period~~  

~~4. add logged user data to global model data which use account model~~  

~~5. implement myrecipe fragment~~  

~~6. implement profile fragment (at least the layout + logout)~~  

~~7. implement rating activity (at least the layout + dummy data (optional))~~  

~~8. ensure to reset scroll every changing category or tab~~  

~~9. redesign category tabs so only active tab has solid color~~  

~~10. implement new recipe layout~~  

~~11. implement api request on get recipes~~  

~~12. implement api request on detail recipes (ingredients and steps)~~  

~~13. use image from remote storage file~~   

~~14. generate rating dummy data on db~~

~~15. implement api request on get ratings~~

~~16. implement sort and filter on rating page~~

~~17. get favourites on db through api request~~   

~~18. persist and delete favourite recipes on db through api request~~  

~~19. share via whatsapp~~

~~20. implement logic on creating recipe~~

~~21. persist created recipe on db through api request~~

~~23. delete owned recipe through api request~~

~~24. update owned recipe through api request~~
    
~~24. implement logic on adding rating~~

~~25. persist added rating through api request~~  

~~26. delete owned rating through api request~~ 

~~27. implement logic on add like on a rating~~ [canceled feature]  

~~28. persist added like on a rating on db through api request~~ [canceled feature]  

~~29. implement logic on displaying bars rating each star~~  

~~30. implement layout account setting~~  

~~31. implement what user can do on account setting~~  

32. save recieps locally => handling offline connection
33. share via whatsapp but with image
