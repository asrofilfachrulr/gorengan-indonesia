package com.example.gorenganindonesia.Model.data.Recipe;

import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.R;

import java.util.ArrayList;

public class RecipeData {
//    public static ArrayList<Recipe> generate(){
//        ArrayList<Recipe> recipes = new ArrayList<>();
//
//        recipes.add(
//                new Recipe(
//                        "receipt-1",
//                        "Cireng Pedas Nuklir",
//                        "surya",
//                        "4.75",
//                        "Aci",
//                        30,
//                        "R.drawable.cireng",
//                        "Mudah",
//                        3,
//                        new String[]{
//                                "Campur tepung kanji, tepung terigu, bawang putih, garam, gula, dan kaldu ayam bubuk (jika digunakan) dalam mangkuk besar.",
//                                "Tambahkan air perlahan-lahan sambil terus diaduk hingga membentuk adonan yang kental dan elastis. Pastikan tidak ada gumpalan.",
//                                "Bentuk adonan menjadi silinder panjang dan potong-potong menjadi bagian berukuran sekitar 5 cm.",
//                                "Panaskan minyak dalam wajan hingga cukup panas. Goreng cireng hingga kecokelatan dan mengembang, sekitar 4-5 menit.",
//                                "Angkat cireng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
//                                "Untuk bumbu pedas manis (opsional), campur semua bahan bumbu dalam mangkuk kecil dan aduk rata.",
//                                "Celupkan cireng dalam bumbu pedas manis atau sajikan bumbu tersebut sebagai saus."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("250", "gram", "tepung kanji"),
//                                new Ingredient("2", "siung", "bawang putih, haluskan"),
//                                new Ingredient("½", "sdt", "garam"),
//                                new Ingredient("¼", "sdt", "gula"),
//                                new Ingredient("¼", "sdt", "kaldu bubuk (opsional)"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("2", "sdm", "minyak goreng"),
//                                new Ingredient("3", "sdm", "saus sambal manis (untuk bumbu pedas manis)"),
//                                new Ingredient("1", "sdm", "saus tomat (untuk bumbu pedas manis)"),
//                                new Ingredient("1", "sdm", "kecap manis (untuk bumbu pedas manis)"),
//                                new Ingredient("1", "sdt", "gula (untuk bumbu pedas manis)"),
//                                new Ingredient("½", "sdt", "garam (untuk bumbu pedas manis)"),
//                                new Ingredient("1", "sdm", "air (untuk bumbu pedas manis)")
//
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-2",
//                        "Tempe Mendoan KFC",
//                        "ghina",
//                        "4.2",
//                        "Kedelai",
//                        30,
//                        "R.drawable.mendoan",
//                        "Mudah",
//                        6,
//                        new String[]{
//                                "Campur tepung terigu, tepung beras, merica bubuk, dan garam dalam mangkuk.",
//                                "Tambahkan air es ke dalam campuran tepung sambil terus diaduk hingga membentuk adonan yang kental.",
//                                "Celupkan potongan tempe ke dalam adonan tepung.",
//                                "Panaskan minyak dalam wajan dan goreng tempe yang telah dicelupkan ke dalam adonan tepung hingga kecokelatan dan renyah, sekitar 3-5 menit."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("200", "gram", "tempe, potong tipis"),
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("2", "sdm", "tepung beras"),
//                                new Ingredient("½", "sdt", "merica bubuk"),
//                                new Ingredient("¼", "sdt", "garam"),
//                                new Ingredient("200", "ml", "air es")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-3",
//                        "Pisang Goreng Starbucks",
//                        "riga",
//                        "4.1",
//                        "Buah",
//                        30,
//                        "R.drawable.pisang_goreng",
//                        "Mudah",
//                        10,
//                        new String[]{
//                                "Kupas pisang dan potong sesuai selera.",
//                                "Campur tepung terigu, tepung beras, garam, gula, dan air hingga membentuk adonan yang kental dan melekat pada pisang.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Celupkan potongan pisang ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
//                                "Angkat pisang goreng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("4", "buah", "pisang, potong sesuai selera"),
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("50", "gram", "tepung beras"),
//                                new Ingredient("½", "sdt", "garam"),
//                                new Ingredient("2", "sdm", "gula"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~", "minyak goreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-4",
//                        "Kentang Goreng Istana Negara",
//                        "riga",
//                        "4.3",
//                        "Kentang",
//                        45,
//                        "R.drawable.kentang_goreng_pedas",
//                        "Sedang",
//                        40,
//                        new String[]{
//                                "Kupas kentang dan potong sesuai selera.",
//                                "Campurkan tepung terigu, merica bubuk, garam, dan bawang putih bubuk dalam mangkuk.",
//                                "Celupkan potongan kentang ke dalam adonan tepung hingga rata.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng kentang hingga kecokelatan dan renyah, sekitar 5-7 menit.",
//                                "Tiriskan kentang goreng di atas tisu dapur untuk menghilangkan kelebihan minyak.",
//                                "Sajikan kentang goreng dengan saus pedas sebagai pelengkap."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("4", "buah", "kentang, potong sesuai selera"),
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("½", "sdt", "merica bubuk"),
//                                new Ingredient("½", "sdt", "garam"),
//                                new Ingredient("½", "sdt", "bawang putih bubuk"),
//                                new Ingredient("", "~", "minyak untuk menggoreng"),
//                                new Ingredient("", "~","saus pedas sebagai pelengkap")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-5",
//                        "Rempeyek Udang Cak Owi",
//                        "surya",
//                        "4.2",
//                        "Ikan",
//                        45,
//                        "R.drawable.peyek_udang",
//                        "Sedang",
//                        20,
//                        new String[]{
//                                "Campur tepung beras, tepung terigu, udang, air, bawang putih, garam, gula, dan kaldu bubuk dalam mangkuk hingga membentuk adonan yang kental dan melekat pada udang.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Celupkan udang ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
//                                "Angkat peyek udang dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("200", "gram", "tepung beras"),
//                                new Ingredient("50", "gram", "tepung terigu"),
//                                new Ingredient("100", "gram", "udang, kupas, cuci bersih, dan tiriskan"),
//                                new Ingredient("150", "ml", "air es"),
//                                new Ingredient("2", "siung", "bawang putih, haluskan"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/2", "sdt", "gula"),
//                                new Ingredient("1/4", "sdt", "kaldu bubuk"),
//                                new Ingredient("", "~", "minyak untuk menggoreng"),
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-6",
//                        "Bakwan Sahabat Kolesterol",
//                        "ghina",
//                        "4.0",
//                        "Isian Sayur",
//                        40,
//                        "R.drawable.bakwan",
//                        "Mudah",
//                        10,
//                        new String[]{
//                                "Campur tepung terigu, air, bawang putih, garam, gula, kaldu bubuk, dan garam dalam mangkuk hingga membentuk adonan yang kental.",
//                                "Tambahkan tauge, wortel, kol, dan daun bawang ke dalam adonan. Aduk rata.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Ambil satu sendok sayur adonan dan goreng hingga kecokelatan, sekitar 3-5 menit.",
//                                "Angkat bakwan dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("2", "siung", "bawang putih, haluskan"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/2", "sdt", "gula"),
//                                new Ingredient("1/4", "sdt", "kaldu bubuk"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("", "~", "tauge"),
//                                new Ingredient("", "~", "wortel, potong korek api"),
//                                new Ingredient("", "~", "kol, iris halus"),
//                                new Ingredient("", "~", "daun bawang, iris halus"),
//                                new Ingredient("", "~", "minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-7",
//                        "Ubi Jalar yang diberi Tepung",
//                        "alya",
//                        "3.8",
//                        "Manis",
//                        40,
//                        "R.drawable.ubi_jalar",
//                        "Mudah",
//                        5,
//                        new String[]{
//                                "Kupas ubi jalar dan iris tipis-tipis.",
//                                "Campurkan tepung terigu, bawang putih bubuk, garam, merica, dan air hingga membentuk adonan yang kental.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Celupkan irisan ubi jalar ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
//                                "Angkat gorengan ubi jalar dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("500", "gram", "ubi jalar, kupas dan iris tipis"),
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("1/2", "sdt", "bawang putih bubuk"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/4", "sdt", "merica"),
//                                new Ingredient("150", "ml", "air"),
//                                new Ingredient("", "~", "minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-8",
//                        "Perkedel (Persatuan Kentang dan Telur)",
//                        "hasna",
//                        "2.9",
//                        "Kentang",
//                        40,
//                        "R.drawable.perkedel",
//                        "Sedang",
//                        7,
//                        new String[]{
//                                "Kupas kentang dan rebus hingga empuk. Haluskan kentang yang sudah direbus.",
//                                "Campurkan kentang yang sudah dihaluskan, daging ayam cincang, bawang putih, bawang merah, telur, garam, merica, dan penyedap rasa dalam mangkuk hingga merata.",
//                                "Bentuk adonan menjadi bulatan dan pipihkan.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng perkedel hingga kecokelatan, sekitar 3-5 menit di setiap sisi.",
//                                "Angkat perkedel dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("500", "gram", "kentang, kupas dan rebus hingga empuk"),
//                                new Ingredient("200", "gram", "daging ayam cincang"),
//                                new Ingredient("2", "siung", "bawang putih, haluskan"),
//                                new Ingredient("4", "btr", "bawang merah, cincang halus"),
//                                new Ingredient("1", "btr", "telur"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/4", "sdt", "merica"),
//                                new Ingredient("", "~", "penyedap rasa ~"),
//                                new Ingredient("", "~", "minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-9",
//                        "Tahu Isi Harapan Rakyat",
//                        "hasna",
//                        "4.9",
//                        "Kedelai",
//                        30,
//                        "R.drawable.tahu_isi",
//                        "Mudah",
//                        8,
//                        new String[]{
//                                "Potong tahu menjadi kotak-kotak kecil.",
//                                "Campurkan tepung terigu, bawang putih bubuk, garam, merica, dan air hingga membentuk adonan yang kental.",
//                                "Buka lubang di setiap potongan tahu dan isi dengan adonan.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng tahu hingga kecokelatan dan renyah, sekitar 3-5 menit.",
//                                "Angkat tahu isi dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("4", "buah", "tahu, potong menjadi kotak-kotak kecil"),
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("1/2", "sdt", "bawang putih bubuk"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/4", "sdt", "merica"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~", "minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-10",
//                        "Molen",
//                        "asrofil",
//                        "4",
//                        "Manis",
//                        40,
//                        "R.drawable.molen",
//                        "Sulit",
//                        10,
//                        new String[]{
//                                "Campurkan tepung terigu, mentega, gula, garam, dan telur dalam mangkuk hingga membentuk adonan kalis.",
//                                "Pisahkan adonan menjadi beberapa bagian kecil. Giling setiap bagian adonan menjadi lembaran tipis.",
//                                "Olesi lembaran adonan dengan selai nanas.",
//                                "Gulung lembaran adonan seperti gulungan sushi dan potong-potong sesuai selera.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng molen hingga kecokelatan, sekitar 3-5 menit.",
//                                "Angkat molen dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("250", "gram", "tepung terigu"),
//                                new Ingredient("100", "gram", "mentega"),
//                                new Ingredient("50", "gram", "gula"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1", "btr", "telur"),
//                                new Ingredient("", "~", "selai nanas"),
//                                new Ingredient("", "~", "minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-11",
//                        "Cimol",
//                        "hasna",
//                        "4.0",
//                        "Aci",
//                        40,
//                        "R.drawable.cimol",
//                        "Mudah",
//                        5,  // portion
//                        new String[]{
//                                "Campurkan tepung terigu, garam, gula, bawang putih bubuk, bawang merah, daun bawang, dan air dalam mangkuk hingga membentuk adonan kalis.",
//                                "Ambil sebagian adonan dan bulatkan seperti bakso kecil. Ulangi hingga adonan habis.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng cimol hingga kecokelatan dan renyah, sekitar 3-5 menit.",
//                                "Angkat cimol dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("250", "gram", "tepung terigu"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1", "sdm", "gula"),
//                                new Ingredient("1/2", "sdt", "bawang putih bubuk"),
//                                new Ingredient("2", "btr", "bawang merah, cincang halus"),
//                                new Ingredient("1", "btg", "daun bawang, iris halus"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~", "minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-12",
//                        "Cilor",
//                        "surya",
//                        "4.2",
//                        "Aci",
//                        45,
//                        "R.drawable.cilor",
//                        "Sedang",
//                        3,  // portion
//                        new String[]{
//                                "Campurkan tepung kanji, tepung terigu, garam, gula, bawang putih bubuk, bawang merah, daun bawang, dan air dalam mangkuk hingga membentuk adonan kalis.",
//                                "Bentuk adonan tipis seperti kulit lumpia.",
//                                "Letakkan sepotong telur ayam di atas kulit lumpia dan bungkus rapi.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng cilor hingga kecokelatan dan renyah, sekitar 3-5 menit.",
//                                "Angkat cilor dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("150", "gram", "tepung kanji"),
//                                new Ingredient("100", "gram", "tepung terigu"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1", "sdm", "gula"),
//                                new Ingredient("1/2", "sdt", "bawang putih bubuk"),
//                                new Ingredient("2", "btr", "bawang merah, cincang halus"),
//                                new Ingredient("1", "btg", "daun bawang, iris halus"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~", "minyak untuk menggoreng"),
//                                new Ingredient("", "~","telur ayam")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-13",
//                        "Tahu Bulat Digoreng Dadakan",
//                        "hasna",
//                        "4.6",
//                        "Kedelai",
//                        30,
//                        "R.drawable.tahu_bulat",
//                        "Sedang",
//                        3,  // portion
//                        new String[]{
//                                "Siapkan tahu putih bulat. Belah tahu menjadi dua bagian.",
//                                "Campurkan tepung terigu, bawang putih bubuk, garam, merica, dan air dalam mangkuk hingga membentuk adonan yang kental.",
//                                "Celupkan setengah bagian tahu ke dalam adonan tepung hingga rata.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng tahu hingga kecokelatan dan renyah, sekitar 3-5 menit.",
//                                "Angkat tahu bulat dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("4", "buah", "tahu putih bulat, belah dua"),
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/4", "sdt", "merica"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~", "minyak untuk menggoreng"),
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-14",
//                        "Sukun",
//                        "alya",
//                        "4.1",
//                        "Buah",
//                        45,
//                        "R.drawable.sukun",
//                        "Mudah",
//                        2,  // portion
//                        new String[]{
//                                "Kupas sukun dan iris tipis-tipis.",
//                                "Campurkan tepung terigu, garam, merica, bawang putih bubuk, dan air dalam mangkuk hingga membentuk adonan yang kental.",
//                                "Celupkan irisan sukun ke dalam adonan tepung hingga rata.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng sukun hingga kecokelatan dan renyah, sekitar 5-7 menit.",
//                                "Angkat sukun goreng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("500", "gram", "sukun, kupas dan iris tipis"),
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/4", "sdt", "merica"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-15",
//                        "Apel Goreng Pak Newton",
//                        "admin",
//                        "4.3",
//                        "Buah",
//                        30,
//                        "R.drawable.apel_goreng",
//                        "Mudah",
//                        4,  // portion
//                        new String[]{
//                                "Kupas apel, buang bijinya, dan potong menjadi irisan setebal 1 cm.",
//                                "Campurkan tepung terigu, gula, garam, kayu manis, telur, dan air dalam mangkuk hingga membentuk adonan yang kental.",
//                                "Celupkan irisan apel ke dalam adonan tepung hingga rata.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng irisan apel hingga kecokelatan dan renyah, sekitar 2-3 menit di setiap sisi.",
//                                "Angkat apel goreng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
//                                "Taburkan gula bubuk atau kayu manis bubuk sebagai hiasan jika diinginkan."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("2", "buah", "apel, kupas, buang biji, dan potong menjadi irisan"),
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("1/4", "cup", "gula"),
//                                new Ingredient("1/4", "sdt", "garam"),
//                                new Ingredient("1/2", "sdt", "kayu manis"),
//                                new Ingredient("1", "btr", "telur"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~", "minyak untuk menggoreng"),
//                                new Ingredient("", "~", "gula bubuk atau kayu manis bubuk untuk hiasan (opsional)")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-16",
//                        "Roti Bantal Tidur",
//                        "asrofil",
//                        "4.5",
//                        "Manis",
//                        30,
//                        "R.drawable.roti_bantal",
//                        "Mudah",
//                        4,  // portion
//                        new String[]{
//                                "Potong roti tawar menjadi bentuk bantal atau sesuai selera.",
//                                "Campurkan telur, susu, gula, dan garam dalam mangkuk.",
//                                "Celupkan potongan roti ke dalam campuran telur.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng roti bantal hingga kecokelatan dan renyah, sekitar 2-3 menit di setiap sisi.",
//                                "Angkat roti bantal dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
//                                "Taburkan gula bubuk atau topping sesuai selera sebelum disajikan."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("4", "potong", "roti tawar, potong menjadi bentuk bantal"),
//                                new Ingredient("2", "btr", "telur"),
//                                new Ingredient("50", "ml", "susu"),
//                                new Ingredient("2", "sdm", "gula"),
//                                new Ingredient("1/4", "sdt", "garam"),
//                                new Ingredient("", "~", "minyak untuk menggoreng"),
//                                new Ingredient("", "~", "gula bubuk atau topping sesuai selera")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-17",
//                        "Donat Strawberry",
//                        "alya",
//                        "4.5",
//                        "Manis",
//                        60,
//                        "R.drawable.donat_strawberry",
//                        "Sedang",
//                        12,  // portion
//                        new String[]{
//                                "Campurkan ragi dengan susu hangat dan 1 sendok makan gula. Biarkan ragi aktif selama 10-15 menit.",
//                                "Campurkan tepung terigu, gula, garam, telur, dan mentega dalam mangkuk besar.",
//                                "Tambahkan campuran ragi yang sudah aktif ke dalam mangkuk dan aduk hingga membentuk adonan kalis.",
//                                "Tutup adonan dengan kain lembab dan diamkan selama 1-2 jam hingga mengembang.",
//                                "Gulungkan adonan dan potong dengan cetakan donat atau gelas untuk membuat donat bundar.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng donat hingga kecokelatan, sekitar 2-3 menit di setiap sisi.",
//                                "Angkat donat dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
//                                "Olesi donat dengan selai strawberry dan taburi gula bubuk sebelum disajikan."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("2 1/4", "sdt", "ragi instan"),
//                                new Ingredient("120", "ml", "susu hangat"),
//                                new Ingredient("2 1/2", "sdm", "gula"),
//                                new Ingredient("500", "gram", "tepung terigu"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1", "btr", "telur"),
//                                new Ingredient("3", "sdm", "mentega"),
//                                new Ingredient("", "~", "minyak untuk menggoreng"),
//                                new Ingredient("", "~", "selai strawberry sesuai selera"),
//                                new Ingredient("", "~", "gula bubuk untuk taburan")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-18",
//                        "Peyek Teri Ma Kasih",
//                        "admin",
//                        "4.5",
//                        "Ikan",
//                        45,
//                        "R.drawable.peyek_teri",
//                        "Mudah",
//                        4,  // portion
//                        new String[]{
//                                "Campurkan terigu, air, bawang merah, bawang putih, gula, garam, dan teri dalam mangkuk hingga membentuk adonan kalis.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
//                                "Goreng hingga kecokelatan dan renyah, sekitar 2-3 menit di setiap sisi.",
//                                "Angkat peyek teri dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("100", "ml", "air"),
//                                new Ingredient("2", "btr", "bawang merah, cincang halus"),
//                                new Ingredient("2", "siung", "bawang putih, cincang halus"),
//                                new Ingredient("1", "sdm", "gula"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("200", "gram", "teri, sangrai"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-19",
//                        "Onde-Onde Jakarta",
//                        "asrofil",
//                        "4.5",
//                        "Manis",
//                        60,
//                        "R.drawable.onde_onde",
//                        "Mudah",
//                        20,  // portion
//                        new String[]{
//                                "Campurkan tepung ketan, gula, dan daun pandan dalam mangkuk hingga tercampur rata.",
//                                "Bentuk adonan menjadi bola-bola kecil dan letakkan sepotong gula merah di tengahnya. Bulatkan kembali hingga gula merah tertutup rapat.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng onde-onde hingga kecokelatan, sekitar 3-4 menit.",
//                                "Angkat onde-onde dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("250", "gram", "tepung ketan"),
//                                new Ingredient("100", "gram", "gula pasir"),
//                                new Ingredient("5", "lbr", "daun pandan, dihaluskan"),
//                                new Ingredient("100", "gram", "gula merah, potong kecil-kecil"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-20",
//                        "Ote-Ote",
//                        "surya",
//                        "4.5",
//                        "Isian Sayur",
//                        45,
//                        "R.drawable.ote_ote",
//                        "Mudah",
//                        4,  // portion
//                        new String[]{
//                                "Campurkan tepung terigu, bawang putih, garam, gula, merica, daun seledri, wortel, tauge, dan air dalam mangkuk hingga membentuk adonan kalis.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
//                                "Goreng ote-ote hingga kecokelatan dan renyah, sekitar 3-4 menit.",
//                                "Angkat ote-ote dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("2", "siung", "bawang putih, haluskan"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1", "sdt", "gula"),
//                                new Ingredient("1/4", "sdt", "merica"),
//                                new Ingredient("2", "btg", "daun seledri, iris halus"),
//                                new Ingredient("1", "buah", "wortel, parut halus"),
//                                new Ingredient("100", "gram", "tauge (kecambah)"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-21",
//                        "Risol Sayur",
//                        "asrofil",
//                        "4.5",
//                        "Isian Sayur",
//                        60,
//                        "R.drawable.risol_sayur",
//                        "Sedang",
//                        12,  // portion
//                        new String[]{
//                                "Siapkan kulit lumpia dan letakkan selembar di permukaan datar.",
//                                "Letakkan isian sayuran di tengah kulit lumpia.",
//                                "Lipat kedua sisi kulit lumpia ke atas, lalu gulung hingga membentuk risol.",
//                                "Campurkan tepung terigu, garam, dan air untuk membuat adonan lem tepung.",
//                                "Celupkan risol dalam adonan lem tepung, lalu gulingkan dalam tepung panir.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng risol hingga kecokelatan, sekitar 3-4 menit.",
//                                "Angkat risol dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("12", "lbr", "kulit lumpia"),
//                                new Ingredient("200", "gram", "sayuran (wortel, kentang, buncis), rebus dan potong kecil-kecil"),
//                                new Ingredient("1", "btr", "telur, rebus dan potong kecil-kecil"),
//                                new Ingredient("1", "sdt", "garam"),
//                                new Ingredient("100", "gram", "tepung panir"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-22",
//                        "Dadar Jagung",
//                        "admin",
//                        "4.5",
//                        "Isian sayur",
//                        45,
//                        "R.drawable.dadar_jagung",
//                        "Mudah",
//                        4,  // portion
//                        new String[]{
//                                "Campurkan tepung terigu, tepung beras, jagung pipil, gula, garam, daun bawang, dan air dalam mangkuk hingga membentuk adonan kalis.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
//                                "Goreng dadar jagung hingga kecokelatan, sekitar 2-3 menit di setiap sisi.",
//                                "Angkat dadar jagung dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("100", "gram", "tepung terigu"),
//                                new Ingredient("50", "gram", "tepung beras"),
//                                new Ingredient("200", "gram", "jagung pipil (manis atau biasa)"),
//                                new Ingredient("1", "sdm", "gula"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("2", "btg", "daun bawang, iris halus"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-23",
//                        "Peyek Kacang",
//                        "riga",
//                        "4.5",
//                        "Buah",
//                        45,
//                        "R.drawable.peyek_kacang",
//                        "Mudah",
//                        4,  // portion
//                        new String[]{
//                                "Campurkan tepung beras, tepung terigu, bawang putih, gula, garam, ketumbar, dan air dalam mangkuk hingga membentuk adonan kalis.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
//                                "Goreng peyek kacang hingga kecokelatan dan renyah, sekitar 2-3 menit di setiap sisi.",
//                                "Angkat peyek kacang dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("150", "gram", "tepung beras"),
//                                new Ingredient("50", "gram", "tepung terigu"),
//                                new Ingredient("2", "siung", "bawang putih, haluskan"),
//                                new Ingredient("1", "sdm", "gula"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/2", "sdt", "ketumbar bubuk"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-24",
//                        "Gehu Pedas UGD",
//                        "ghina",
//                        "4.5",
//                        "Kedelai",
//                        45,
//                        "R.drawable.gehu_pedas",
//                        "Sedang",
//                        4,  // portion
//                        new String[]{
//                                "Potong tahu (gehu) menjadi potongan-potongan kecil sesuai selera.",
//                                "Campurkan tepung terigu, bawang putih, garam, lada, dan air dalam mangkuk hingga membentuk adonan kalis.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Celupkan potongan tahu ke dalam adonan tepung hingga rata.",
//                                "Goreng tahu hingga kecokelatan dan renyah, sekitar 3-4 menit di setiap sisi.",
//                                "Angkat tahu dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
//                                "Sajikan tahu pedas dengan saus cabai atau saus sambal sebagai pelengkap."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("250", "gram", "tahu (gehu)"),
//                                new Ingredient("100", "gram", "tepung terigu"),
//                                new Ingredient("2", "siung", "bawang putih, haluskan"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/4", "sdt", "lada"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~","minyak untuk menggoreng"),
//                                new Ingredient("", "~","saus cabai atau saus sambal sebagai pelengkap")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-25",
//                        "Sempol Chicken Kentucky",
//                        "hasna",
//                        "4.5",
//                        "Aci",
//                        45,
//                        "R.drawable.sempol",
//                        "Mudah",
//                        4,  // portion
//                        new String[]{
//                                "Campurkan tepung terigu, bawang putih, bawang merah, daun seledri, garam, merica, telur, dan air dalam mangkuk hingga membentuk adonan kalis.",
//                                "Tambahkan potongan daging sosis ke dalam adonan.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
//                                "Goreng sempol hingga kecokelatan dan matang, sekitar 3-4 menit.",
//                                "Angkat sempol dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("150", "gram", "tepung terigu"),
//                                new Ingredient("2", "siung", "bawang putih, haluskan"),
//                                new Ingredient("2", "siung", "bawang merah, haluskan"),
//                                new Ingredient("2", "btg", "daun seledri, iris halus"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/4", "sdt", "merica"),
//                                new Ingredient("1", "btr", "telur"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("2", "buah", "daging sosis, potong-potong"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//        recipes.add(
//                new Recipe(
//                        "receipt-26",
//                        "Combro",
//                        "alya",
//                        "4.5",
//                        "Isian Sayur",
//                        45,
//                        "R.drawable.combro",
//                        "Mudah",
//                        4,  // portion
//                        new String[]{
//                                "Campurkan tepung ketan, kelapa parut, daun bawang, daun kemangi, garam, merica, dan air dalam mangkuk hingga membentuk adonan kalis.",
//                                "Bentuk adonan menjadi bulatan pipih dan letakkan sepotong oncom di tengahnya. Bulatkan kembali hingga oncom tertutup rapat.",
//                                "Panaskan minyak dalam wajan hingga cukup panas.",
//                                "Goreng combro hingga kecokelatan, sekitar 3-4 menit di setiap sisi.",
//                                "Angkat combro dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
//                        },
//                        new Ingredient[]{
//                                new Ingredient("250", "gram", "tepung ketan"),
//                                new Ingredient("100", "gram", "kelapa parut"),
//                                new Ingredient("2", "btg", "daun bawang, iris halus"),
//                                new Ingredient("2", "btg", "daun kemangi, iris halus"),
//                                new Ingredient("1/2", "sdt", "garam"),
//                                new Ingredient("1/4", "sdt", "merica"),
//                                new Ingredient("200", "ml", "air"),
//                                new Ingredient("", "~","sayur oncom"),
//                                new Ingredient("", "~","minyak untuk menggoreng")
//                        }
//                )
//        );
//
//
//        return recipes;
//    }
}
