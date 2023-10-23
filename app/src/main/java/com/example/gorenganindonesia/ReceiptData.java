package com.example.gorenganindonesia;

import java.util.ArrayList;

public class ReceiptData {
    public static ArrayList<Receipt> generate(){
        ArrayList<Receipt> receipts = new ArrayList<>();

        receipts.add(
                new Receipt(
                        "Cireng Pedas Manis",
                        "Aci",
                        30,
                        R.drawable.cireng,
                        "Mudah",
                        3,
                        new String[]{
                                "Campur tepung kanji, tepung terigu, bawang putih, garam, gula, dan kaldu ayam bubuk (jika digunakan) dalam mangkuk besar.",
                                "Tambahkan air perlahan-lahan sambil terus diaduk hingga membentuk adonan yang kental dan elastis. Pastikan tidak ada gumpalan.",
                                "Bentuk adonan menjadi silinder panjang dan potong-potong menjadi bagian berukuran sekitar 5 cm.",
                                "Panaskan minyak dalam wajan hingga cukup panas. Goreng cireng hingga kecokelatan dan mengembang, sekitar 4-5 menit.",
                                "Angkat cireng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
                                "Untuk bumbu pedas manis (opsional), campur semua bahan bumbu dalam mangkuk kecil dan aduk rata.",
                                "Celupkan cireng dalam bumbu pedas manis atau sajikan bumbu tersebut sebagai saus."
                        },
                        new Ingredients[]{
                                new Ingredients("250", "gram", "tepung kanji"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("½", "sdt", "garam"),
                                new Ingredients("¼", "sdt", "gula"),
                                new Ingredients("¼", "sdt", "kaldu bubuk (opsional)"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("2", "sdm", "minyak goreng"),
                                new Ingredients("3", "sdm", "saus sambal manis (untuk bumbu pedas manis)"),
                                new Ingredients("1", "sdm", "saus tomat (untuk bumbu pedas manis)"),
                                new Ingredients("1", "sdm", "kecap manis (untuk bumbu pedas manis)"),
                                new Ingredients("1", "sdt", "gula (untuk bumbu pedas manis)"),
                                new Ingredients("½", "sdt", "garam (untuk bumbu pedas manis)"),
                                new Ingredients("1", "sdm", "air (untuk bumbu pedas manis)")

                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Tempe Mendoan Crispy",
                        "Kedelai",
                        30,
                        R.drawable.mendoan,
                        "Mudah",
                        6,
                        new String[]{
                                "Campur tepung terigu, tepung beras, merica bubuk, dan garam dalam mangkuk.",
                                "Tambahkan air es ke dalam campuran tepung sambil terus diaduk hingga membentuk adonan yang kental.",
                                "Celupkan potongan tempe ke dalam adonan tepung.",
                                "Panaskan minyak dalam wajan dan goreng tempe yang telah dicelupkan ke dalam adonan tepung hingga kecokelatan dan renyah, sekitar 3-5 menit."
                        },
                        new Ingredients[]{
                                new Ingredients("200", "gram", "tempe, potong tipis"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("2", "sdm", "tepung beras"),
                                new Ingredients("½", "sdt", "merica bubuk"),
                                new Ingredients("¼", "sdt", "garam"),
                                new Ingredients("200", "ml", "air es")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Pisang Goreng Abang-Abang",
                        "Buah",
                        30,
                        R.drawable.pisang_goreng,
                        "Mudah",
                        10,
                        new String[]{
                                "Kupas pisang dan potong sesuai selera.",
                                "Campur tepung terigu, tepung beras, garam, gula, dan air hingga membentuk adonan yang kental dan melekat pada pisang.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Celupkan potongan pisang ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat pisang goreng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("4", "buah", "pisang, potong sesuai selera"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("50", "gram", "tepung beras"),
                                new Ingredients("½", "sdt", "garam"),
                                new Ingredients("2", "sdm", "gula"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak goreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Kentang Goreng Hot",
                        "Kentang",
                        45,
                        R.drawable.kentang_goreng_pedas,
                        "Sedang",
                        40,
                        new String[]{
                                "Kupas kentang dan potong sesuai selera.",
                                "Campurkan tepung terigu, merica bubuk, garam, dan bawang putih bubuk dalam mangkuk.",
                                "Celupkan potongan kentang ke dalam adonan tepung hingga rata.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng kentang hingga kecokelatan dan renyah, sekitar 5-7 menit.",
                                "Tiriskan kentang goreng di atas tisu dapur untuk menghilangkan kelebihan minyak.",
                                "Sajikan kentang goreng dengan saus pedas sebagai pelengkap."
                        },
                        new Ingredients[]{
                                new Ingredients("4", "buah", "kentang, potong sesuai selera"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("½", "sdt", "merica bubuk"),
                                new Ingredients("½", "sdt", "garam"),
                                new Ingredients("½", "sdt", "bawang putih bubuk"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                                new Ingredients("", "secukupnya","saus pedas sebagai pelengkap")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Peyek Udang",
                        "Ikan",
                        45,
                        R.drawable.peyek_udang,
                        "Sedang",
                        20,
                        new String[]{
                                "Campur tepung beras, tepung terigu, udang, air, bawang putih, garam, gula, dan kaldu bubuk dalam mangkuk hingga membentuk adonan yang kental dan melekat pada udang.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Celupkan udang ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat peyek udang dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("200", "gram", "tepung beras"),
                                new Ingredients("50", "gram", "tepung terigu"),
                                new Ingredients("100", "gram", "udang, kupas, cuci bersih, dan tiriskan"),
                                new Ingredients("150", "ml", "air es"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/2", "sdt", "gula"),
                                new Ingredients("1/4", "sdt", "kaldu bubuk"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Bakwan Kolesterol",
                        "Isian Sayur",
                        40,
                        R.drawable.bakwan,
                        "Mudah",
                        10,
                        new String[]{
                                "Campur tepung terigu, air, bawang putih, garam, gula, kaldu bubuk, dan garam dalam mangkuk hingga membentuk adonan yang kental.",
                                "Tambahkan tauge, wortel, kol, dan daun bawang ke dalam adonan. Aduk rata.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Ambil satu sendok sayur adonan dan goreng hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat bakwan dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/2", "sdt", "gula"),
                                new Ingredients("1/4", "sdt", "kaldu bubuk"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("", "secukupnya", "tauge"),
                                new Ingredients("", "secukupnya", "wortel, potong korek api"),
                                new Ingredients("", "secukupnya", "kol, iris halus"),
                                new Ingredients("", "secukupnya", "daun bawang, iris halus"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Gorengan Ubi Jalar",
                        "Manis",
                        40, // estimated cooking duration in minutes
                        R.drawable.ubi_jalar,
                        "Mudah",
                        5,
                        new String[]{
                                "Kupas ubi jalar dan iris tipis-tipis.",
                                "Campurkan tepung terigu, bawang putih bubuk, garam, merica, dan air hingga membentuk adonan yang kental.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Celupkan irisan ubi jalar ke dalam adonan tepung dan goreng hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat gorengan ubi jalar dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("500", "gram", "ubi jalar, kupas dan iris tipis"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sdt", "bawang putih bubuk"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("150", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Perkedel",
                        "Kentang",
                        40, // estimated cooking duration in minutes
                        R.drawable.perkedel,
                        "Sedang",
                        7,
                        new String[]{
                                "Kupas kentang dan rebus hingga empuk. Haluskan kentang yang sudah direbus.",
                                "Campurkan kentang yang sudah dihaluskan, daging ayam cincang, bawang putih, bawang merah, telur, garam, merica, dan penyedap rasa dalam mangkuk hingga merata.",
                                "Bentuk adonan menjadi bulatan dan pipihkan.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng perkedel hingga kecokelatan, sekitar 3-5 menit di setiap sisi.",
                                "Angkat perkedel dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("500", "gram", "kentang, kupas dan rebus hingga empuk"),
                                new Ingredients("200", "gram", "daging ayam cincang"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("4", "butir", "bawang merah, cincang halus"),
                                new Ingredients("1", "butir", "telur"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("", "secukupnya", "penyedap rasa secukupnya"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Tahu Isi",
                        "Kedelai",
                        30, // estimated cooking duration in minutes
                        R.drawable.tahu_isi,
                        "Mudah",
                        8,
                        new String[]{
                                "Potong tahu menjadi kotak-kotak kecil.",
                                "Campurkan tepung terigu, bawang putih bubuk, garam, merica, dan air hingga membentuk adonan yang kental.",
                                "Buka lubang di setiap potongan tahu dan isi dengan adonan.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng tahu hingga kecokelatan dan renyah, sekitar 3-5 menit.",
                                "Angkat tahu isi dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("4", "buah", "tahu, potong menjadi kotak-kotak kecil"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sdt", "bawang putih bubuk"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Molen",
                        "Manis",
                        40, // estimated cooking duration in minutes
                        R.drawable.molen,
                        "Sulit",
                        10,
                        new String[]{
                                "Campurkan tepung terigu, mentega, gula, garam, dan telur dalam mangkuk hingga membentuk adonan kalis.",
                                "Pisahkan adonan menjadi beberapa bagian kecil. Giling setiap bagian adonan menjadi lembaran tipis.",
                                "Olesi lembaran adonan dengan selai nanas.",
                                "Gulung lembaran adonan seperti gulungan sushi dan potong-potong sesuai selera.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng molen hingga kecokelatan, sekitar 3-5 menit.",
                                "Angkat molen dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("250", "gram", "tepung terigu"),
                                new Ingredients("100", "gram", "mentega"),
                                new Ingredients("50", "gram", "gula"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1", "butir", "telur"),
                                new Ingredients("", "secukupnya", "selai nanas"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Cimol",
                        "Aci",
                        40, // estimated cooking duration in minutes
                        R.drawable.cimol, // Ganti R.drawable.cimol dengan gambar yang sesuai
                        "Mudah",
                        5,  // portion
                        new String[]{
                                "Campurkan tepung terigu, garam, gula, bawang putih bubuk, bawang merah, daun bawang, dan air dalam mangkuk hingga membentuk adonan kalis.",
                                "Ambil sebagian adonan dan bulatkan seperti bakso kecil. Ulangi hingga adonan habis.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng cimol hingga kecokelatan dan renyah, sekitar 3-5 menit.",
                                "Angkat cimol dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("250", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1", "sdm", "gula"),
                                new Ingredients("1/2", "sdt", "bawang putih bubuk"),
                                new Ingredients("2", "butir", "bawang merah, cincang halus"),
                                new Ingredients("1", "batang", "daun bawang, iris halus"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Cilor",
                        "Aci",
                        45, // estimated cooking duration in minutes
                        R.drawable.cilor, // Ganti R.drawable.cilor dengan gambar yang sesuai
                        "Sedang",
                        3,  // portion
                        new String[]{
                                "Campurkan tepung kanji, tepung terigu, garam, gula, bawang putih bubuk, bawang merah, daun bawang, dan air dalam mangkuk hingga membentuk adonan kalis.",
                                "Bentuk adonan tipis seperti kulit lumpia.",
                                "Letakkan sepotong telur ayam di atas kulit lumpia dan bungkus rapi.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng cilor hingga kecokelatan dan renyah, sekitar 3-5 menit.",
                                "Angkat cilor dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("150", "gram", "tepung kanji"),
                                new Ingredients("100", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1", "sdm", "gula"),
                                new Ingredients("1/2", "sdt", "bawang putih bubuk"),
                                new Ingredients("2", "butir", "bawang merah, cincang halus"),
                                new Ingredients("1", "batang", "daun bawang, iris halus"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                                new Ingredients("", "secukupnya","telur ayam")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Tahu Bulat",
                        "Kedelai",
                        30, // estimated cooking duration in minutes
                        R.drawable.tahu_bulat, // Ganti R.drawable.tahu_bulat dengan gambar yang sesuai
                        "Sedang",
                        3,  // portion
                        new String[]{
                                "Siapkan tahu putih bulat. Belah tahu menjadi dua bagian.",
                                "Campurkan tepung terigu, bawang putih bubuk, garam, merica, dan air dalam mangkuk hingga membentuk adonan yang kental.",
                                "Celupkan setengah bagian tahu ke dalam adonan tepung hingga rata.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng tahu hingga kecokelatan dan renyah, sekitar 3-5 menit.",
                                "Angkat tahu bulat dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("4", "buah", "tahu putih bulat, belah dua"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Sukun Goreng",
                        "Buah",
                        45, // estimated cooking duration in minutes
                        R.drawable.sukun, // Ganti R.drawable.sukun_goreng dengan gambar yang sesuai
                        "Mudah",
                        2,  // portion
                        new String[]{
                                "Kupas sukun dan iris tipis-tipis.",
                                "Campurkan tepung terigu, garam, merica, bawang putih bubuk, dan air dalam mangkuk hingga membentuk adonan yang kental.",
                                "Celupkan irisan sukun ke dalam adonan tepung hingga rata.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng sukun hingga kecokelatan dan renyah, sekitar 5-7 menit.",
                                "Angkat sukun goreng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("500", "gram", "sukun, kupas dan iris tipis"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Apel Goreng",
                        "Buah",
                        30, // estimated cooking duration in minutes
                        R.drawable.apel_goreng, // Ganti R.drawable.apel_goreng dengan gambar yang sesuai
                        "Mudah",
                        4,  // portion
                        new String[]{
                                "Kupas apel, buang bijinya, dan potong menjadi irisan setebal 1 cm.",
                                "Campurkan tepung terigu, gula, garam, kayu manis, telur, dan air dalam mangkuk hingga membentuk adonan yang kental.",
                                "Celupkan irisan apel ke dalam adonan tepung hingga rata.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng irisan apel hingga kecokelatan dan renyah, sekitar 2-3 menit di setiap sisi.",
                                "Angkat apel goreng dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
                                "Taburkan gula bubuk atau kayu manis bubuk sebagai hiasan jika diinginkan."
                        },
                        new Ingredients[]{
                                new Ingredients("2", "buah", "apel, kupas, buang biji, dan potong menjadi irisan"),
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("1/4", "cup", "gula"),
                                new Ingredients("1/4", "sdt", "garam"),
                                new Ingredients("1/2", "sdt", "kayu manis"),
                                new Ingredients("1", "butir", "telur"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                                new Ingredients("", "secukupnya", "gula bubuk atau kayu manis bubuk untuk hiasan (opsional)")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Roti Bantal",
                        "Manis",
                        30, // estimated cooking duration in minutes
                        R.drawable.roti_bantal, // Ganti R.drawable.roti_bantal dengan gambar yang sesuai
                        "Mudah",
                        4,  // portion
                        new String[]{
                                "Potong roti tawar menjadi bentuk bantal atau sesuai selera.",
                                "Campurkan telur, susu, gula, dan garam dalam mangkuk.",
                                "Celupkan potongan roti ke dalam campuran telur.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng roti bantal hingga kecokelatan dan renyah, sekitar 2-3 menit di setiap sisi.",
                                "Angkat roti bantal dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
                                "Taburkan gula bubuk atau topping sesuai selera sebelum disajikan."
                        },
                        new Ingredients[]{
                                new Ingredients("4", "potong", "roti tawar, potong menjadi bentuk bantal"),
                                new Ingredients("2", "butir", "telur"),
                                new Ingredients("50", "ml", "susu"),
                                new Ingredients("2", "sdm", "gula"),
                                new Ingredients("1/4", "sdt", "garam"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                                new Ingredients("", "secukupnya", "gula bubuk atau topping sesuai selera")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Donat Strawberry",
                        "Manis",
                        60, // estimated cooking duration in minutes
                        R.drawable.donat_strawberry, // Ganti R.drawable.donat_strawberry dengan gambar yang sesuai
                        "Sedang",
                        12,  // portion
                        new String[]{
                                "Campurkan ragi dengan susu hangat dan 1 sendok makan gula. Biarkan ragi aktif selama 10-15 menit.",
                                "Campurkan tepung terigu, gula, garam, telur, dan mentega dalam mangkuk besar.",
                                "Tambahkan campuran ragi yang sudah aktif ke dalam mangkuk dan aduk hingga membentuk adonan kalis.",
                                "Tutup adonan dengan kain lembab dan diamkan selama 1-2 jam hingga mengembang.",
                                "Gulungkan adonan dan potong dengan cetakan donat atau gelas untuk membuat donat bundar.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng donat hingga kecokelatan, sekitar 2-3 menit di setiap sisi.",
                                "Angkat donat dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
                                "Olesi donat dengan selai strawberry dan taburi gula bubuk sebelum disajikan."
                        },
                        new Ingredients[]{
                                new Ingredients("2 1/4", "sendok teh", "ragi instan"),
                                new Ingredients("120", "ml", "susu hangat"),
                                new Ingredients("2 1/2", "sendok makan", "gula"),
                                new Ingredients("500", "gram", "tepung terigu"),
                                new Ingredients("1/2", "sendok teh", "garam"),
                                new Ingredients("1", "butir", "telur"),
                                new Ingredients("3", "sendok makan", "mentega"),
                                new Ingredients("", "secukupnya", "minyak untuk menggoreng"),
                                new Ingredients("", "secukupnya", "selai strawberry sesuai selera"),
                                new Ingredients("", "secukupnya", "gula bubuk untuk taburan")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Peyek Teri",
                        "Ikan",
                        45, // estimated cooking duration in minutes
                        R.drawable.peyek_teri, // Ganti R.drawable.peyek_teri dengan gambar yang sesuai
                        "Mudah",
                        4,  // portion
                        new String[]{
                                "Campurkan terigu, air, bawang merah, bawang putih, gula, garam, dan teri dalam mangkuk hingga membentuk adonan kalis.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
                                "Goreng hingga kecokelatan dan renyah, sekitar 2-3 menit di setiap sisi.",
                                "Angkat peyek teri dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("100", "ml", "air"),
                                new Ingredients("2", "butir", "bawang merah, cincang halus"),
                                new Ingredients("2", "siung", "bawang putih, cincang halus"),
                                new Ingredients("1", "sendok makan", "gula"),
                                new Ingredients("1/2", "sendok teh", "garam"),
                                new Ingredients("200", "gram", "teri, sangrai"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Onde-Onde",
                        "Manis",
                        60, // estimated cooking duration in minutes
                        R.drawable.onde_onde, // Ganti R.drawable.onde_onde dengan gambar yang sesuai
                        "Mudah",
                        20,  // portion
                        new String[]{
                                "Campurkan tepung ketan, gula, dan daun pandan dalam mangkuk hingga tercampur rata.",
                                "Bentuk adonan menjadi bola-bola kecil dan letakkan sepotong gula merah di tengahnya. Bulatkan kembali hingga gula merah tertutup rapat.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng onde-onde hingga kecokelatan, sekitar 3-4 menit.",
                                "Angkat onde-onde dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("250", "gram", "tepung ketan"),
                                new Ingredients("100", "gram", "gula pasir"),
                                new Ingredients("5", "lembar", "daun pandan, dihaluskan"),
                                new Ingredients("100", "gram", "gula merah, potong kecil-kecil"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Ote-Ote",
                        "Isian Sayur",
                        45, // estimated cooking duration in minutes
                        R.drawable.ote_ote, // Ganti R.drawable.ote_ote dengan gambar yang sesuai
                        "Mudah",
                        4,  // portion
                        new String[]{
                                "Campurkan tepung terigu, bawang putih, garam, gula, merica, daun seledri, wortel, tauge, dan air dalam mangkuk hingga membentuk adonan kalis.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
                                "Goreng ote-ote hingga kecokelatan dan renyah, sekitar 3-4 menit.",
                                "Angkat ote-ote dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("1/2", "sdt", "garam"),
                                new Ingredients("1", "sdt", "gula"),
                                new Ingredients("1/4", "sdt", "merica"),
                                new Ingredients("2", "batang", "daun seledri, iris halus"),
                                new Ingredients("1", "buah", "wortel, parut halus"),
                                new Ingredients("100", "gram", "tauge (kecambah)"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Risol Sayur",
                        "Isian Sayur",
                        60, // estimated cooking duration in minutes
                        R.drawable.risol_sayur, // Ganti R.drawable.risol_sayur dengan gambar yang sesuai
                        "Sedang",
                        12,  // portion
                        new String[]{
                                "Siapkan kulit lumpia dan letakkan selembar di permukaan datar.",
                                "Letakkan isian sayuran di tengah kulit lumpia.",
                                "Lipat kedua sisi kulit lumpia ke atas, lalu gulung hingga membentuk risol.",
                                "Campurkan tepung terigu, garam, dan air untuk membuat adonan lem tepung.",
                                "Celupkan risol dalam adonan lem tepung, lalu gulingkan dalam tepung panir.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng risol hingga kecokelatan, sekitar 3-4 menit.",
                                "Angkat risol dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("12", "lembar", "kulit lumpia"),
                                new Ingredients("200", "gram", "sayuran (wortel, kentang, buncis), rebus dan potong kecil-kecil"),
                                new Ingredients("1", "butir", "telur, rebus dan potong kecil-kecil"),
                                new Ingredients("1", "sendok teh", "garam"),
                                new Ingredients("100", "gram", "tepung panir"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Dadar Jagung",
                        "Isian sayur",
                        45, // estimated cooking duration in minutes
                        R.drawable.dadar_jagung, // Ganti R.drawable.dadar_jagung dengan gambar yang sesuai
                        "Mudah",
                        4,  // portion
                        new String[]{
                                "Campurkan tepung terigu, tepung beras, jagung pipil, gula, garam, daun bawang, dan air dalam mangkuk hingga membentuk adonan kalis.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
                                "Goreng dadar jagung hingga kecokelatan, sekitar 2-3 menit di setiap sisi.",
                                "Angkat dadar jagung dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("100", "gram", "tepung terigu"),
                                new Ingredients("50", "gram", "tepung beras"),
                                new Ingredients("200", "gram", "jagung pipil (manis atau biasa)"),
                                new Ingredients("1", "sendok makan", "gula"),
                                new Ingredients("1/2", "sendok teh", "garam"),
                                new Ingredients("2", "batang", "daun bawang, iris halus"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Peyek Kacang",
                        "Buah",
                        45, // estimated cooking duration in minutes
                        R.drawable.peyek_kacang, // Ganti R.drawable.peyek_kacang dengan gambar yang sesuai
                        "Mudah",
                        4,  // portion
                        new String[]{
                                "Campurkan tepung beras, tepung terigu, bawang putih, gula, garam, ketumbar, dan air dalam mangkuk hingga membentuk adonan kalis.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
                                "Goreng peyek kacang hingga kecokelatan dan renyah, sekitar 2-3 menit di setiap sisi.",
                                "Angkat peyek kacang dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("150", "gram", "tepung beras"),
                                new Ingredients("50", "gram", "tepung terigu"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("1", "sendok makan", "gula"),
                                new Ingredients("1/2", "sendok teh", "garam"),
                                new Ingredients("1/2", "sendok teh", "ketumbar bubuk"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Gehu Pedas",
                        "Kedelai",
                        45, // estimated cooking duration in minutes
                        R.drawable.gehu_pedas, // Ganti R.drawable.gehu_pedas dengan gambar yang sesuai
                        "Sedang",
                        4,  // portion
                        new String[]{
                                "Potong tahu (gehu) menjadi potongan-potongan kecil sesuai selera.",
                                "Campurkan tepung terigu, bawang putih, garam, lada, dan air dalam mangkuk hingga membentuk adonan kalis.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Celupkan potongan tahu ke dalam adonan tepung hingga rata.",
                                "Goreng tahu hingga kecokelatan dan renyah, sekitar 3-4 menit di setiap sisi.",
                                "Angkat tahu dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak.",
                                "Sajikan tahu pedas dengan saus cabai atau saus sambal sebagai pelengkap."
                        },
                        new Ingredients[]{
                                new Ingredients("250", "gram", "tahu (gehu)"),
                                new Ingredients("100", "gram", "tepung terigu"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("1/2", "sendok teh", "garam"),
                                new Ingredients("1/4", "sendok teh", "lada"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng"),
                                new Ingredients("", "secukupnya","saus cabai atau saus sambal sebagai pelengkap")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Sempol",
                        "Aci",
                        45, // estimated cooking duration in minutes
                        R.drawable.sempol, // Ganti R.drawable.sempol dengan gambar yang sesuai
                        "Mudah",
                        4,  // portion
                        new String[]{
                                "Campurkan tepung terigu, bawang putih, bawang merah, daun seledri, garam, merica, telur, dan air dalam mangkuk hingga membentuk adonan kalis.",
                                "Tambahkan potongan daging sosis ke dalam adonan.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Ambil sebagian adonan dengan sendok dan letakkan ke dalam minyak panas.",
                                "Goreng sempol hingga kecokelatan dan matang, sekitar 3-4 menit.",
                                "Angkat sempol dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("150", "gram", "tepung terigu"),
                                new Ingredients("2", "siung", "bawang putih, haluskan"),
                                new Ingredients("2", "siung", "bawang merah, haluskan"),
                                new Ingredients("2", "batang", "daun seledri, iris halus"),
                                new Ingredients("1/2", "sendok teh", "garam"),
                                new Ingredients("1/4", "sendok teh", "merica"),
                                new Ingredients("1", "butir", "telur"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("2", "buah", "daging sosis, potong-potong"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );

        receipts.add(
                new Receipt(
                        "Combro",
                        "Isian Sayur",
                        45, // estimated cooking duration in minutes
                        R.drawable.combro, // Ganti R.drawable.combro dengan gambar yang sesuai
                        "Mudah",
                        4,  // portion
                        new String[]{
                                "Campurkan tepung ketan, kelapa parut, daun bawang, daun kemangi, garam, merica, dan air dalam mangkuk hingga membentuk adonan kalis.",
                                "Bentuk adonan menjadi bulatan pipih dan letakkan sepotong oncom di tengahnya. Bulatkan kembali hingga oncom tertutup rapat.",
                                "Panaskan minyak dalam wajan hingga cukup panas.",
                                "Goreng combro hingga kecokelatan, sekitar 3-4 menit di setiap sisi.",
                                "Angkat combro dan tiriskan di atas tisu dapur untuk menghilangkan kelebihan minyak."
                        },
                        new Ingredients[]{
                                new Ingredients("250", "gram", "tepung ketan"),
                                new Ingredients("100", "gram", "kelapa parut"),
                                new Ingredients("2", "batang", "daun bawang, iris halus"),
                                new Ingredients("2", "batang", "daun kemangi, iris halus"),
                                new Ingredients("1/2", "sendok teh", "garam"),
                                new Ingredients("1/4", "sendok teh", "merica"),
                                new Ingredients("200", "ml", "air"),
                                new Ingredients("", "secukupnya","sayur oncom"),
                                new Ingredients("", "secukupnya","minyak untuk menggoreng")
                        }
                )
        );


        return receipts;
    }
}
