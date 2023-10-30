# Gorengan Indonesia
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Express.js](https://img.shields.io/badge/express.js-%23404d59.svg?style=for-the-badge&logo=express&logoColor=%2361DAFB) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Vercel](https://img.shields.io/badge/vercel-%23000000.svg?style=for-the-badge&logo=vercel&logoColor=white) ![Supabase](https://img.shields.io/badge/Supabase-3ECF8E?style=for-the-badge&logo=supabase&logoColor=white)

Simple Android App that provides numerous recipes of Indonesian fritters. This project is done to fulfill College Subject (Mobile Programming) Final Assignment. [Backend](https://github.com/asrofilfachrulr/API-Gorengan-Indonesia) of the app is hosted as Express.js serverless app on PaaS Vercel and use Database Hosting (configured) also Storage Hosting (planned) at Supabase.

## Snapshot
![snapshot](./snapshot.png)

## Features
- [x] User Account *(partially)*
- [ ] CRUD Receipt(s) by Logged Users *(only read for now)*
- [ ] CRUD Rating Receipt by Logged Users
- [x] Share Receipt (currently limited to plain text)
- [ ] Save Receipt(s) Offline
- [x] Mark Favourite Receipt
- [x] Filter List Data with Search Keyword

## TODO
1. change layout on detail activity: move kebab menu to the right of reciept title then replace the its place before with toggle add favourite button
2. add author name on detail, main also put small profile thumb
3. convert necessary toast to has short period
4. add logged user data to global model data which use user model
5. implement my receipt fragment
6. implement profile fragment (at least the layout)
7. implement rating activity (at least the layout + dummy data (optional))
8. ensure to reset scroll every changing category or tab
9. redesign category tabs so only active tab has solid color
